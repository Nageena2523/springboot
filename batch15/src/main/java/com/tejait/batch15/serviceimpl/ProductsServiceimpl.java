package com.tejait.batch15.serviceimpl;

import org.springframework.stereotype.Service;

import com.tejait.batch15.model.Products;
import com.tejait.batch15.repository.ProductsRepository;
import com.tejait.batch15.service.ProductsService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProductsServiceimpl implements ProductsService {
	
	
 ProductsRepository repository;
	@Override
	public Products saveproducts(Products products) {
	
		return repository.save(products);
	}

}
