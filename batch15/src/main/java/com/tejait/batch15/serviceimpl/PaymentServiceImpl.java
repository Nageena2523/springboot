package com.tejait.batch15.serviceimpl;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tejait.batch15.exceptions.InvalidAmountException;
import com.tejait.batch15.model.Payment;
import com.tejait.batch15.repository.PaymentRepository;
import com.tejait.batch15.service.PaymentService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PaymentServiceImpl  implements PaymentService{
	PaymentRepository repository;
	private static final Logger logger= LogManager.getLogger(PaymentServiceImpl.class);

	@Override
	public Payment savePayment(Payment payment) {
		logger.debug("Entered into Payment Service");
		logger.info("Payment Data :{}", payment);
		//failure scenario
		if(payment.getAmount()<1) {
			logger.info("Transaction Amount :{}", payment.getAmount());
			
			payment.setTransactionId(UUID.randomUUID().toString());
			payment.setPaymentStatus("failed");
logger.warn("Payment Status:{},Transaction Id :{}", payment.getPaymentStatus(),payment.getTransactionId());
logger.error("Invalid Amount:{}", payment.getAmount());
			throw new InvalidAmountException("Invalid Amount");
		}
		//fraud alert
		if(payment.getAmount()>100000) {
	 logger.info("Fraud Alert Amount:{},Payer name{}", payment.getPayerName(),payment.getAmount());
			

		
		}
		try {
			payment.setTransactionId(UUID.randomUUID().toString());
			payment.setPaymentStatus("Success");
			return repository.save(payment);
		}catch (Exception e) {
			logger.error("Error While making payment");
			
			throw e;
		}
	}

	@Override
	public Payment getBypaymentId(Integer paymentId) {
		
return repository.findById(paymentId).orElseThrow(()-> new IllegalArgumentException("Id not found"));
	}

	@Override
	public Payment getRefundByPaymentId(Integer paymentId) {
		Payment payment= repository.findById(paymentId)
				.orElseThrow(()->{
					logger.error("payment Id Not Found:{}", paymentId);
					
					return new IllegalArgumentException("Payment Not Found");
							});
		
		if(!payment.getPaymentStatus().equalsIgnoreCase("Success")) {
		   logger.warn("Payment Status :{}", payment.getPaymentStatus());
		   logger.error("Payment Not Allowed for Refund:{}", paymentId);
			
			throw new IllegalArgumentException("payment Not Allowed for refund");
		}
		payment.setPaymentStatus("Refund");
		payment.setTransactionId(UUID.randomUUID().toString());
			
				
		return repository.save(payment);
	}

}
