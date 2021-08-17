package com.sapient.bugproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sapient.bugproject.entity.Mail;


@CrossOrigin
@RestController
public class MailServiceController {
	Logger logger = LoggerFactory.getLogger(MailServiceController.class);

//	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate1;


	@PostMapping("/api/mailrequest")
	public Mail sendMail(@RequestBody Mail mail) {
		HttpHeaders headers = new HttpHeaders();
		System.out.println("hello" + mail.getMailFrom());

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Mail> httpEntity = new HttpEntity<>(mail, headers);

		this.restTemplate1.exchange("http://localhost:8087/api/mail", HttpMethod.POST, httpEntity,
				Mail.class);
		return mail;

	}


}

