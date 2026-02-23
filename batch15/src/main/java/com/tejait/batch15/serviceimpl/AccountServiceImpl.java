package com.tejait.batch15.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejait.batch15.dto.AccountCustomerDto;
import com.tejait.batch15.dto.AccountResponseDto;
import com.tejait.batch15.model.Account;
import com.tejait.batch15.repository.AccountRepository;
import com.tejait.batch15.service.AccountServic;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountServic{
	//@Autowired
	AccountRepository repository;
	@Override
	public Account saveAccount(Account account) {
		account.setCreatedDate(LocalDateTime.now());
		account.setUpdatedDate(LocalDateTime.now());
	
		return repository.save(account);
	}
	@Override
	public Account getByAccountId(Long id) {
		return repository.findById(id).get();
		
	
	}
	@Override
	public AccountResponseDto getByCustomerAccountId(Long id) {
		Account account=repository.findById(id).get();
		System.out.println("account info:"+account);
		AccountResponseDto dto=new AccountResponseDto();
		                   dto.setAccountId(account.getAccountId());
		                   dto.setAccountType(account.getAccountType());
		                   dto.setHolderName(account.getHolderName());
		                   dto.setIfsc(account.getIfsc());
		                   dto.setKycStatus(account.getKycStatus());
		
		return dto;
	}
	@Override
	public AccountCustomerDto getAccoundIdInfo(Long id) {
	Account account= repository.findById(id).get();
	AccountCustomerDto dto1= new AccountCustomerDto();
	                    dto1.setAccountId(account.getAccountId());
	                    dto1.setAccountNum(account.getAccountNum());
	                    dto1.setBalance(account.getBalance());
		return dto1;
	}

}
