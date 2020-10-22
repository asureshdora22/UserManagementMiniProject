package com.suresh.utils;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtils {

	@Autowired
	private JavaMailSender mailSender;
	public Boolean sendMail(String to,String subject,String body) {
		boolean isSender=false;
		
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailSender.send(mimeMessage);
			isSender=true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return isSender;
		
	}
}
