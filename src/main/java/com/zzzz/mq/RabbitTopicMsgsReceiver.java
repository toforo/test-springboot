package com.zzzz.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="queue.topic.msgs")
public class RabbitTopicMsgsReceiver {
	
	@RabbitHandler
	public void process(String context) {
		 System.out.println("TopicMsgsReceiver: " + context);
	}
}
