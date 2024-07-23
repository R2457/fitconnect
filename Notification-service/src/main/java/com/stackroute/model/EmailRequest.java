package com.stackroute.model;


import lombok.Data;

@Data
public class EmailRequest {
	private String to; 
	private String subject; 
	private String message;
	private String mobnum;
}
