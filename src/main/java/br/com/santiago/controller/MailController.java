package br.com.santiago.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santiago.config.CustomPropertyConfig;
import br.com.santiago.domain.Mail;
import br.com.santiago.service.EmailSenderService;

@RestController
public class MailController {

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private CustomPropertyConfig customPropertyConfig;

	public MailController() {

	}

	@GetMapping(value = "/send")
	public String sendMail() throws MessagingException {
		Mail mail = getMail();
		emailSenderService.sendEmail(mail);
		return "Check your email";

	}

	private Mail getMail() {
		Mail mail = new Mail();
		mail.setFrom(customPropertyConfig.mailFrom);
		mail.setTo("<thiago.santiago@aluno.uepb.edu.br>");
		mail.setSubject("Simple mail with AWS SES and Spring Boot");
		Map<String, Object> model = new HashMap<>();
		model.put("templateVariable", "Simple mail with aws..");
		mail.setModel(model);
		return mail;
	}
}
