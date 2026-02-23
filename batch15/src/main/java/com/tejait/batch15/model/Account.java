package com.tejait.batch15.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Table(name="account_b15")
@Entity

public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
	private String accountType;
	private Long accountNum;
	private String holderName;
	private Long balance;
	private String Ifsc;
	private String KycStatus;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	
	
	
	
	
 

}
