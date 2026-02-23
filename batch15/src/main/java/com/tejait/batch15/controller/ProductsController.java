package com.tejait.batch15.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.model.Products;
import com.tejait.batch15.service.ProductsService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductsController {
	
	ProductsService service;
	@PostMapping("save")
	public ResponseEntity<Products>saveProducts(@RequestBody Products products){
		Products savedProduct= service.saveproducts(products);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
		
		
	}

}
