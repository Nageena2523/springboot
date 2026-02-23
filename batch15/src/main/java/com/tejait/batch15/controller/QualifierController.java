package com.tejait.batch15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.service.CustomerService;

import lombok.AllArgsConstructor;
@AllArgsConstructor

@RequestMapping("qualifier")
public class QualifierController {
	//@Autowired
	@Qualifier("business")         //when two beans are injected when we want to specify the specific bean by using @qualifier annotation
   CustomerService service;
	@GetMapping("test")
	public String getQualifiername() {
		return service.getCustomerType();
	}

}
