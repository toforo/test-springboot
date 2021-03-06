package com.zzzz.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="queue.fanout.3")
public class RabbitFanout3Receiver {
	
	@RabbitHandler
	public void process(String context) {
		 System.out.println("FanoutReceiver3: " + context);
	}
}
