package com.maycon.coursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maycon.coursomc.domain.BilletPayment;
import com.maycon.coursomc.domain.Order;
import com.maycon.coursomc.domain.OrderItem;
import com.maycon.coursomc.domain.enums.StatusPayment;
import com.maycon.coursomc.repositories.OrderItensRepository;
import com.maycon.coursomc.repositories.OrderRepository;
import com.maycon.coursomc.repositories.PaymentRepository;
import com.maycon.coursomc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private BilletService billetService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItensRepository orderItensRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;

	public Order find(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));

	}
	
	@Transactional
	public Order insert(Order obj) {
		
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setStatus(StatusPayment.PENDENTE);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof BilletPayment) {
			BilletPayment pgto = (BilletPayment) obj.getPayment();
			billetService.fillBilletPayment(pgto, obj.getInstant());
		}
		
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem ip : obj.getOrderItens()) {
			ip.setDiscount(0.0);
			ip.setProduct(productService.find(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setOrder(obj);
		}
		
		orderItensRepository.saveAll(obj.getOrderItens());
		
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
		
	}
}
