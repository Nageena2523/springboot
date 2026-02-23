package com.tejait.batch15.service;

import com.tejait.batch15.dto.AccountCustomerDto;
import com.tejait.batch15.dto.AccountResponseDto;
import com.tejait.batch15.model.Account;

public interface AccountServic {

	

	 public Account saveAccount(Account account);

	 public Account getByAccountId(Long id);

	 public AccountResponseDto getByCustomerAccountId(Long id);

	 public AccountCustomerDto getAccoundIdInfo(Long id);

}
