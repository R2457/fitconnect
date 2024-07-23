package com.stackroute.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class PaymentModel {

	@Id
	private String paymentId;
	private String userEmail;
	private String paymentTitle;
	private Integer amount;
	private String paymentStatus;
	private String paymentMode;
	private Date Timestamp;
	private String currency;
	private String key;
	
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPaymentTitle() {
		return paymentTitle;
	}
	public void setPaymentTitle(String paymentTitle) {
		this.paymentTitle = paymentTitle;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Date getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public PaymentModel() {
		
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public PaymentModel(String paymentId, String userEmail, String paymentTitle, Integer amount, String paymentStatus,
			String paymentMode, Date timestamp, String currency, String key) {
	
		this.paymentId = paymentId;
		this.userEmail = userEmail;
		this.paymentTitle = paymentTitle;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.paymentMode = paymentMode;
		Timestamp = timestamp;
		this.currency = currency;
		this.key = key;
	}
	
	
//	public PaymentModel(String paymentId, String currency, Integer amount) {
//		super();
//		this.paymentId = paymentId;
//		this.amount = amount;
//		this.currency = currency;
//	}
	
	
}
