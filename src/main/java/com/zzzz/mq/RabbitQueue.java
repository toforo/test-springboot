package com.zzzz.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ队列
 * @author zhuangyilian
 * @date 2019年2月26日
 */
@Configuration
public class RabbitQueue {

	@Bean
    public Queue directQueue() {
		
		//创建队列,并指定队列名称
        return new Queue("queue.direct");
    }
	
	@Bean
	public Queue topicQueueMsg() {
		
	    return new Queue("queue.topic.msg");
	}
	
	@Bean
	public Queue topicQueueMsgs() {
		
	    return new Queue("queue.topic.msgs");
	}
	
	@Bean
	public Queue fanoutQueue1() {
		
		return new Queue("queue.fanout.1");
	}
	
	@Bean
	public Queue fanoutQueue2() {
		
		return new Queue("queue.fanout.2");
	}
	
	@Bean
	public Queue fanoutQueue3() {
		
		return new Queue("queue.fanout.3");
	}
}
