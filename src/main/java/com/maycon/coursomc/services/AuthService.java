package com.maycon.coursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maycon.coursomc.domain.Client;
import com.maycon.coursomc.repositories.ClientRepository;
import com.maycon.coursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPasswordEmail(String email) {
		Client client = clientRepository.findByEmail(email);

		if (client == null) {
			throw new ObjectNotFoundException("Email não cadastrado");
		}
		String newPass = newPassword();
		client.setPassword(pe.encode(newPass));

		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else {
			return (char) (rand.nextInt(26) + 97);
		}		
	}
}
