package com.example.ordermanagementsystem.service.impl;

import com.example.ordermanagementsystem.DTO.OrderDto;
import com.example.ordermanagementsystem.model.Item;
import com.example.ordermanagementsystem.model.Order;
import com.example.ordermanagementsystem.model.OrderItem;
import com.example.ordermanagementsystem.repository.OrderItemRepository;
import com.example.ordermanagementsystem.repository.OrderRepository;
import com.example.ordermanagementsystem.service.ItemService;
import com.example.ordermanagementsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemRepository orderItemRepository;


    public OrderDto addOrder(OrderDto orderDto) {
        Order order = mapToEntity(orderDto);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalQuantity = 0;
        double totalPrice = 0;

        for (OrderItem orderItem : orderItems) {
            Long itemId = orderItem.getItemId();
            Optional<Item> item = itemService.getItemById(itemId);
            if (item.isEmpty()) {
                throw new RuntimeException("Invalid itemId passed");
            }
            Item item1 = item.get();
            if (item1.getQuantity() < orderItem.getQuantity()) {
                throw new RuntimeException("Item out of stock");
            }
            double price = orderItem.getQuantity() * item1.getPrice();
            orderItem.setPrice(price);

            //order.addOrderItem(orderItem);
            orderItem.setOrder(order);

            totalPrice = totalPrice + price;
            totalQuantity = totalQuantity + orderItem.getQuantity();

        }

        order.setTotalPrice(totalPrice);
        order.setTotalQuantity(totalQuantity);

        //orderItems.forEach(orderItem->order.addOrderItem(orderItem));
        Order order2 = orderRepository.save(order);

        OrderDto orderDto1 = mapToDTO(order2);
        return orderDto1;

    }



    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
        return orderDtos;
    }


    public Optional<Order> getOrderById(long orderId) {
        return orderRepository.findById(orderId);
    }

    // convert Entity to DTO
    private OrderDto mapToDTO(Order order) {

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderItems(order.getOrderItems());
        orderDto.setTotalQuantity(order.getTotalQuantity());
        orderDto.setTotalPrice(order.getTotalPrice());

        return orderDto;
    }

    // convert DTO to entity
    private Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();

        order.setOrderId(orderDto.getOrderId());
        order.setOrderItems(orderDto.getOrderItems());
        order.setTotalQuantity(orderDto.getTotalQuantity());
        order.setTotalPrice(orderDto.getTotalPrice());

        return order;
    }

}
