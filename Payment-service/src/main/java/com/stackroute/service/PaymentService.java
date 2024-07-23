package com.stackroute.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.stackroute.model.PaymentModel;
import com.stackroute.repository.PaymentRepository;

@Service
public class PaymentService {
	
	private static final String KEY = "rzp_test_zeASdQN0Rx4urz";
	private static final String KEY_SECRET = "Z6SfxA5pYOxYs4uFDT6pjXmo";
	private static final String CURRENCY = "INR";
	

	@Autowired
	private PaymentRepository paymentRepository;
	
	public PaymentModel createTransaction(Double amount) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", amount);
		jsonObject.put("currency", CURRENCY);
//		jsonObject.put("userEmail", "abc@gmail.com");
//		jsonObject.put("paymentModel", "UPI");
//		jsonObject.put("timestamp", LocalDateTime.now());
//		jsonObject.put("status", "");
		
		try {
			RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
			
			Order order = razorpayClient.orders.create(jsonObject);
			System.out.println(order);
			
			PaymentModel payment = prepareTransactionDetails(order);
			return payment;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	private PaymentModel prepareTransactionDetails(Order order) {
		
			String paymentId = order.get("id");
			String currency = order.get("currency");
			Integer amount = order.get("amount");
			String userEmail = "abc@gmail.com";
			String paymentStatus = order.get("status");
			String paymentTitle = order.get("entity");
			String paymentMode = "UPI";
			Date Timestamp = order.get("created_at");
			
			PaymentModel payment = new PaymentModel(paymentId, userEmail, paymentTitle, amount, paymentStatus, paymentMode, Timestamp, currency, KEY);
			paymentRepository.save(payment);
			return payment;
	}


	public List<PaymentModel> allTransactionDetails() {
		// TODO Auto-generated method stub
		return paymentRepository.findAll();
	}


	public List<PaymentModel> findTransaction(String userEmail) {
		// TODO Auto-generated method stub
		List<PaymentModel> optional = paymentRepository.findByUserEmail(userEmail);
//		List<PaymentModel> paymentList = optional.isPresent() ? (List<PaymentModel>) optional.get() : null;
		
		return optional;
	}
}
