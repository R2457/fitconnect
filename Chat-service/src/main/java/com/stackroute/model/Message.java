package com.stackroute.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class Message {

	public Message() {
		this.timeStamp = LocalTime.now();
		this.DateStamp = LocalDate.now();
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSenderMessage() {
		return senderMessage;
	}

	public void setSenderMessage(String senderMessage) {
		this.senderMessage = senderMessage;
	}

	public List<String> getSenderMediaLinks() {
		return SenderMediaLinks;
	}

	public void setSenderMediaLinks(List<String> senderMediaLinks) {
		SenderMediaLinks = senderMediaLinks;
	}

	public LocalTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public LocalDate getDateStamp() {
		return DateStamp;
	}

	public void setDateStamp(LocalDate dateStamp) {
		DateStamp = dateStamp;
	}
	public Message(String senderEmail, String senderMessage, List<String> senderMediaLinks, LocalTime timeStamp , LocalDate dateStamp) {
		super();
		this.senderEmail = senderEmail;
		this.senderMessage = senderMessage;
		this.SenderMediaLinks = senderMediaLinks;
		this.timeStamp = timeStamp;
		this.DateStamp =  dateStamp;
	}


	private String senderEmail;
	private String senderMessage;
	private List<String> SenderMediaLinks;
	private LocalTime timeStamp;
	private LocalDate DateStamp ;
}
