package com.tejait.batch15.service;

import java.util.Optional;

import com.tejait.batch15.dto.CustomerProductsRequest;
import com.tejait.batch15.model.Customer;

public interface CustomerDataService {

	Customer saveCustomer(Customer customer);

	Customer mapProductsToCustomer(CustomerProductsRequest request);

	Optional<Customer> getcustomerId(Integer id);

	

}
