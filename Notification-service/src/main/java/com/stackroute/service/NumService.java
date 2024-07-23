package com.stackroute.service;

import org.springframework.stereotype.Service;

import com.stackroute.model.EmailRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class NumService {
	  	  @Value("${TWILIO_ACCOUNT_SID}")
    	  private String accountSid;

    	@Value("${TWILIO_AUTH_TOKEN}")
    	private String authToken;

    	@Value("${TWILIO_FROM_NUMBER}")
    	private String fromNumber;
	  
	      public void send(EmailRequest sms) {
	      	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	  
	          Message message = Message.creator(new PhoneNumber(sms.getMobnum()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
	                  .create();
	          System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction
	  
	      }
	  
}