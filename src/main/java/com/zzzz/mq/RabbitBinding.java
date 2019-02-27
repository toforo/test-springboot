package com.zzzz.mq;

import javax.annotation.Resource;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ绑定(队列绑定到交换机)
 * @author zhuangyilian
 * @date 2019年2月27日
 */
@Configuration
public class RabbitBinding {
	
	@Resource
	private Queue directQueue;
	@Resource
	private Queue topicQueueMsg;
	@Resource
	private Queue topicQueueMsgs;
	@Resource
	private Queue fanoutQueue1;
	@Resource
	private Queue fanoutQueue2;
	@Resource
	private Queue fanoutQueue3;
	
	@Resource
	private TopicExchange topicExchange;
	@Resource
	private FanoutExchange fanoutExchange;
	
	@Bean
	public Binding bindTopicExchangeMsg() {
		
		//以指 路由键 绑定队列 到交换机上
		return BindingBuilder.bind(topicQueueMsg).to(topicExchange).with("topic.msg");
	}
	
	@Bean
	public Binding bindTopicExchangeMsgs() {
		
		return BindingBuilder.bind(topicQueueMsgs).to(topicExchange).with("topic.#");
	}
	
	@Bean
	public Binding bindFanoutExchange1() {
		
		return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
	}
	
	@Bean
	public Binding bindFanoutExchange2() {
		
		return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
	}
	
	@Bean
	public Binding bindFanoutExchange3() {
		
		return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
	}
}
