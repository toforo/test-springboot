package com.zzzz.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ交换机
 * @author zhuangyilian
 * @date 2019年2月26日
 */
@Configuration
public class RabbitExchange {
	
	@Bean
	public TopicExchange topicExchange() {
		
		//创建交换机,并指定交换机名称
	    return new TopicExchange("amq.topic");
	}
	
	@Bean
	public FanoutExchange fanoutExchange() {
		
		return new FanoutExchange("amq.fanout");
	}
}
