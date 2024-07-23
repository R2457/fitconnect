package com.stackroute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.model.PaymentModel;
import com.stackroute.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/created/{amount}")
	public ResponseEntity<?> createTransaction(@PathVariable double amount) {
		ResponseEntity<?> entity = null;
		
		PaymentModel createTransaction = paymentService.createTransaction(amount);
		
//		if(createTransaction != null) {
			entity = new ResponseEntity<PaymentModel>(createTransaction, HttpStatus.OK);
//		}else {
//			entity = new ResponseEntity<String>("Transaction not created", HttpStatus.BAD_REQUEST);
//		}
		return entity;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> allTransactionDetails(){
		
		List<PaymentModel> paymentList = paymentService.allTransactionDetails();
		return ResponseEntity.ok(paymentList);
	}
	
	@GetMapping("/transactions/{userEmail}")
	public ResponseEntity<?> findTransaction(@PathVariable String userEmail){
		ResponseEntity<?> entity = null;
		
		List<PaymentModel> paymentList = paymentService.findTransaction(userEmail);
		return new ResponseEntity<List<PaymentModel>>(paymentList, HttpStatus.OK);
	}
}
