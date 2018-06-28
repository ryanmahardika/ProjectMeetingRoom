package com.example.mr.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
	
	@Autowired
	public JavaMailSender emailsender;
	@Autowired
	public SimpleMailMessage message;
	
	public void sendMessage(String to,String subject,String text) {
		message.setTo(to);
		message.setText(text); //Use StringBuilder
		message.setSubject(subject);
		emailsender.send(message);
	}
	
}
