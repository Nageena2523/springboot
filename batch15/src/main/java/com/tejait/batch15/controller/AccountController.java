package com.tejait.batch15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.dto.AccountCustomerDto;
import com.tejait.batch15.dto.AccountResponseDto;
import com.tejait.batch15.model.Account;
import com.tejait.batch15.service.AccountServic;

@RestController
@RequestMapping("/account")
public class AccountController {
	  
	  
	  
	AccountServic service;
	  @Autowired
	  public AccountController(AccountServic service) {
		super();
		this.service = service;
	}

	  @PostMapping(value="/saveaccount")
	  public ResponseEntity<Account>saveAccount(@RequestBody Account account){
		Account savedaccount= service.saveAccount(account) ;
		return new ResponseEntity<>(savedaccount, HttpStatus.CREATED);
	  }

	@GetMapping("getById/{id}")
	public ResponseEntity<Account>getByIdAcouintDtls(@PathVariable Long id){
		Account account=service.getByAccountId(id);
		return new ResponseEntity<>(account,HttpStatus.OK);
		
		
		 }
	@GetMapping("getByAccId/{id}")
	public ResponseEntity<AccountResponseDto>giveCustomerAccountInfo(@PathVariable Long id){
		AccountResponseDto dto= service.getByCustomerAccountId(id);
		
		return  new ResponseEntity<>(dto,HttpStatus.OK);
		
	}
	
	
	@GetMapping("getBycustomerId/{id}")	
	
	public ResponseEntity<AccountCustomerDto>giveCustomerAccountDtls(@PathVariable Long id){
		AccountCustomerDto dto1= service.getAccoundIdInfo(id);
		
		return new ResponseEntity<>(dto1,HttpStatus.OK);
		
	
		
		
	}
	
}
