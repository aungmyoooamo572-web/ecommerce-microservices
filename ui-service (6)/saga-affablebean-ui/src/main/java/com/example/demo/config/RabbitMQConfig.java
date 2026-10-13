package com.example.demo.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	public MessageConverter messgaeConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
