package com.tejait.batch15.dto;

import java.util.List;

import com.tejait.batch15.model.Orders;

import lombok.Data;

@Data
public class OrdersRequestDto {
	private int customerId;
	private List<Orders>orders;
	

}
