package com.ecommerce.bikes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.http.PaymentIntentDTO;
import com.ecommerce.bikes.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/stripe")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@PostMapping("/payment_intent")
	public ResponseEntity<String> payment(@RequestBody PaymentIntentDTO paymentIntentDTO) throws StripeException {
		PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDTO);
		String paymentString = paymentIntent.toJson();
		System.err.println("@@@ Payment intent succesfully ");
		return new ResponseEntity<String>(paymentString, HttpStatus.OK);
	}

	@PostMapping("/confirm/{id}")
	public ResponseEntity<String> confirm(@PathVariable("id") String id) {
		try {
			PaymentIntent paymentIntent = paymentService.confirm(id);
			String paymentString = paymentIntent.toJson();		
			System.err.println("@@@ Payment confirmation succesfully ");
			return new ResponseEntity<String>(paymentString, HttpStatus.OK);
		} catch (StripeException e) {
			System.err.println("Payment confirm - " + e.getLocalizedMessage());
			return new ResponseEntity<String>("Mierda", HttpStatus.OK);
		}
	}

	@PostMapping("/cancel/{id}")
	public ResponseEntity<String> cancel(@PathVariable("id") String id) throws StripeException {
		PaymentIntent paymentIntent = paymentService.cancel(id);
		String paymentString = paymentIntent.toJson();
		System.err.println("@@@ Payment cancelation succesfully");
		return new ResponseEntity<String>(paymentString, HttpStatus.OK);
	}

}
