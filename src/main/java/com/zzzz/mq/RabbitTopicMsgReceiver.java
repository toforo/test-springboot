package com.zzzz.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="queue.topic.msg")
public class RabbitTopicMsgReceiver {
	
	@RabbitHandler
	public void process(String context) {
		 System.out.println("TopicMsgReceiver: " + context);
	}
}
