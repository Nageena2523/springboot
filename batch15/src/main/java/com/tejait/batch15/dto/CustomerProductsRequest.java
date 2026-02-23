package com.tejait.batch15.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerProductsRequest {
	private int customerId;
	private List<Integer> prodIds;

}
