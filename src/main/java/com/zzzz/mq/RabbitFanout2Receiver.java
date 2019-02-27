package com.zzzz.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="queue.fanout.2")
public class RabbitFanout2Receiver {
	
	@RabbitHandler
	public void process(String context) {
		 System.out.println("FanoutReceiver2: " + context);
	}
}
