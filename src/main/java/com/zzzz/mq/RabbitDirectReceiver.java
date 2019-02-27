package com.zzzz.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.zzzz.model.User;

@Component
@RabbitListener(queues="queue.direct")	//监听指定队列
public class RabbitDirectReceiver {
	
	@RabbitHandler
	public void process(String context) {
		 System.out.println("DirectReceiver: " + context);
	}
	
	/**
	 * 接收对象(类必须实现Serializable)
	 * @author zhuangyilian
	 * @date 2019年2月26日
	 * @param user
	 */
	@RabbitHandler
	public void process(User user) {
		System.out.println("DirectObjectReceiver: " + user.toString());
	}
}
