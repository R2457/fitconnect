package com.stackroute.service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
//import com.twilio.rest.api.v2010.account.Message;
//import javax.mail.Message.RecipientType;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.stackroute.model.EmailRequest;

@Service
public class MailService {
	public boolean sendEmail(EmailRequest emailrequest) {
		//Variable for gmail
		boolean f =false;
		String from = "fitconnect39@gmail.com";
		String host="smtp.gmail.com"; 
		//get the system properties 
		Properties properties = System.getProperties(); 
		System.out.println("PROPERTIES "+properties); 
		//setting important information to properties object
		properties.put("mail.smtp.host", host); 
	    properties.put("mail.smtp.port", "465"); 
		properties.put("mail.smtp.ssl.enable","true");  
		properties.put("mail.smtp.auth","true");
	    //Step 1: to get the session object.. 
		Session session = Session.getInstance(properties, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("fitconnect39@gmail.com", "cwgztigpbizqoyfg");
		    }
		});

		session.setDebug(true); 
		//Step 2: compose the message [text, multi media] 
		MimeMessage m = new MimeMessage (session); 
		try { 
			//from email 
			 m.setFrom (from);
			//adding recipient to message 
			 m.addRecipient (Message. RecipientType.TO, new InternetAddress (emailrequest.getTo())); 
			 //adding subject to message 
			 m.setSubject (emailrequest.getSubject()); 
			 //adding text to message 
			 m.setText (emailrequest.getMessage());
			 
			 Transport.send (m); 
			 System.out.println("Sent success.. .");
			 f = true;
		}catch (Exception e) { 
			e.printStackTrace();
		}
		return f;
	}
	

}