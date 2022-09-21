package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.DTO.OrderDto;
import com.example.ordermanagementsystem.model.Order;
import com.example.ordermanagementsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        System.out.println(orderDto);
        return orderService.addOrder(orderDto);
    }

    @GetMapping
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    public Order updateOrder(@RequestBody Order order){
        return null;
    }
}
