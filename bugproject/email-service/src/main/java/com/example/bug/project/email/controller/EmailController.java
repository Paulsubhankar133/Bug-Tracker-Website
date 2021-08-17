package com.example.bug.project.email.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bug.project.email.entity.Mail;
import com.example.bug.project.email.service.MailServiceImpl;

@RestController
public class EmailController {
	Logger logger = LoggerFactory.getLogger(EmailController.class);
	@Autowired
	private MailServiceImpl mailServiceImpl;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping("/api/mail")
	public void sendMail(@RequestBody Mail mail) {
		mailServiceImpl.sendEmail(mail);
	}
}
