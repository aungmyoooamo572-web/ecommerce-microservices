package com.example.demo.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDao;
import com.example.demo.ds.OrderCreateDto;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final RabbitTemplate rabbitTemplate;
	private final OrderDao orderDao;
	
	
	@RabbitListener(queues = "order_create_queue")
	public void crateOrder(OrderCreateDto orderCreateDto) {
		orderDao.findByOrderCode(orderCreateDto.orderCode())
			.ifPresentOrElse(e ->{
				System.out.println("Alerady Existed.");
			}, ()->{
				List<CartItem> cartItems=new ArrayList<>();
				cartItems.addAll(orderCreateDto.cartITems());
				Order order=new Order(
						orderCreateDto.orderCode(),
						orderCreateDto.username(), 
						orderCreateDto.accountNumber(),
						orderCreateDto.email(),
						orderCreateDto.address(),
						cartItems);
				orderDao.save(order);
			});
	}
}
