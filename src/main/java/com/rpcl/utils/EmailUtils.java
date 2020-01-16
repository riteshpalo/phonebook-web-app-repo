package com.rpcl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
/**
 * This class is used to send email to user
 * @author Ritesh
 *
 */
@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mailSender;
	/**
	 * This method is used to send email to user by subject body 
	 * @param to
	 * @param subject
	 * @param body
	 */
	public void sendMail(String to,String subject,String body) {
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(body);
		mailSender.send(msg);
	}

}
