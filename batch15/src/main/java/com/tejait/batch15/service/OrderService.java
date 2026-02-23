package com.tejait.batch15.service;

import org.springframework.stereotype.Service;

import com.tejait.batch15.dto.OrdersRequestDto;

public interface OrderService {

	OrdersRequestDto saveOrders(OrdersRequestDto dto);

}
