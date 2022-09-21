package com.example.ordermanagementsystem;

import com.example.ordermanagementsystem.model.Order;
import com.example.ordermanagementsystem.model.OrderItem;
import com.example.ordermanagementsystem.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderManagementSystemApplicationTests {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void saveOrder(){

		OrderItem orderItem1 = new OrderItem();
		orderItem1.setItemId(1);
		orderItem1.setQuantity(10);
		orderItem1.setPrice(100);

		OrderItem orderItem2 = new OrderItem();
		orderItem2.setItemId(5);
		orderItem2.setQuantity(50);
		orderItem2.setPrice(1000);

		Order order = new Order();

		order.addOrderItem(orderItem1);
		order.addOrderItem(orderItem2);

		orderRepository.save(order);
	}


}
