package com.stackroute.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.config.RabbitMQConfig;
import com.stackroute.model.EmailRequest;
import com.stackroute.service.MailService;
import com.stackroute.service.NumService;

@Service
public class RegistrationService {
	@Autowired
	private MailService mailservice;
	@Autowired
	private NumService numservice;
	 @RabbitListener(queues = RabbitMQConfig.Queue)
	 public void registerUser(EmailRequest emailrequest) {
	        // Send a confirmation email
	    
	      if(emailrequest.getTo()!= null) {
	    	  mailservice.sendEmail(emailrequest);
	      }
	      if(emailrequest.getMobnum()!=null) {
	    	  numservice.send(emailrequest);
	      }
	    }
}
