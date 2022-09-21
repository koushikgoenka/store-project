package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.DTO.OrderDto;
import com.example.ordermanagementsystem.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDto addOrder(OrderDto orderDto);

    Optional<Order> getOrderById(long orderId);

    List<OrderDto> getAllOrders();
}
