package com.tejait.batch15.serviceimpl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tejait.batch15.service.CustomerService;
@Service("retail")
//@Primary
public class RetailsCustomerServiceImpl  implements CustomerService{

	@Override
	
	public String getCustomerType() {
		
		return "Retail Customer";
	}

}
