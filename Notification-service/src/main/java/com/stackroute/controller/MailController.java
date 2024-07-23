package com.stackroute.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.model.EmailRequest;
import com.stackroute.service.MailService;
import com.stackroute.service.NumService;


@RestController
@RequestMapping("/mail")
public class MailController {
	@Autowired
	private MailService mailservice;
	@Autowired
	private NumService numservice;
	
	@GetMapping("/mail")
	public String welcome() {
		return "hello aman";
	}
	@PostMapping("/mail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
		System.out.println(request); 
		boolean result = this.mailservice.sendEmail(request); 
		if(result) {
			return ResponseEntity.ok ("Email is sent successfully..."); 
			}
		else { 
			return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR). body ("Email not send "); 
			}
		}
	
	 @Autowired
	    private SimpMessagingTemplate webSocket;

	    private final String  TOPIC_DESTINATION = "/lesson/sms";

	    @PostMapping(value = "/sms",
                consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public void smsSubmit(@RequestBody EmailRequest sms) {
	        try{
	        	numservice.send(sms);
	        }
	        catch(Exception e){

	            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
	            throw e;
	        }
	        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+sms.getMobnum());

	    }
	    private String getTimeStamp() {
	        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	     }
	   
}
