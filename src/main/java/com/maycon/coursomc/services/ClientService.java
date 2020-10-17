package com.maycon.coursomc.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.maycon.coursomc.domain.Adress;
import com.maycon.coursomc.domain.City;
import com.maycon.coursomc.domain.Client;
import com.maycon.coursomc.domain.enums.Perfil;
import com.maycon.coursomc.domain.enums.TypeClient;
import com.maycon.coursomc.dto.ClientDTO;
import com.maycon.coursomc.dto.ClientNewDTO;
import com.maycon.coursomc.repositories.AdressRepository;
import com.maycon.coursomc.repositories.ClientRepository;
import com.maycon.coursomc.security.UserSS;
import com.maycon.coursomc.services.exceptions.AuthorizationException;
import com.maycon.coursomc.services.exceptions.DataIntegrityException;
import com.maycon.coursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	@Autowired
	private AdressRepository adressRepository;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;

	public Client find(Integer id) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		adressRepository.saveAll(obj.getAdress());
		return obj;
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionadas");
		}
	}

	public List<Client> findAll() {
		return repo.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}

	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(),
				TypeClient.toEnum(objDto.getType()), pe.encode(objDto.getPassword()));
		City city = new City(objDto.getCidadeId(), null, null);
		Adress adr = new Adress(null, objDto.getStreet(), objDto.getNumber(), objDto.getComplement(),
				objDto.getNeighborhood(), objDto.getCep(), cli, city);
		cli.getAdress().add(adr);
		cli.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2() != null) {
			cli.getPhones().add(objDto.getPhone2());
		}
		if (objDto.getPhone3() != null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	
	public URI uploadProfilePicture (MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile);
	}
	
}
