package com.maycon.coursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.maycon.coursomc.domain.Client;
import com.maycon.coursomc.domain.Order;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Order ojb);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Client client, String newPass);
}
