package com.maycon.coursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.maycon.coursomc.domain.Order;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);

}
