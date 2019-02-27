package com.zzzz.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ消息发送者
 * @author zhuangyilian
 * @date 2019年2月26日
 */
@Component
public class RabbitSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	
	/**
	 * 简单发送
	 * @author zhuangyilian
	 * @date 2019年2月26日
	 * @param route 路由键
	 * @param msg 消息内容
	 */
	public void sendDirect(String route, Object msg) {
		System.out.println("DirectSender: " + msg.toString());
		
		//默认交换机模式为direct,并且路由键为direct队列名
		rabbitTemplate.convertAndSend(route, msg);
	}
	
	/**
	 * 通配符模式发送
	 * @author zhuangyilian
	 * @date 2019年2月26日
	 * @param route
	 * @param msg
	 */
	public void sendTopic(String exchange, String route, Object msg) {
		System.out.println("TopicSender: " + msg.toString());
		
		//amq.topic: rabbitmq默认的topic交换机,也可以自己创建
		rabbitTemplate.convertAndSend(exchange, route, msg);
	}
	
	/**
	 * 广播模式发送
	 * @author zhuangyilian
	 * @date 2019年2月26日
	 * @param msg
	 */
	public void sendFanout(String exchange, Object msg) {
		System.out.println("FanoutSender: " + msg.toString());
		
		//amq.fanout: rabbitmq默认的fanout交换机
		//指定广播模式时,无论设置什么样的路由键都会被忽略
		rabbitTemplate.convertAndSend(exchange, "", msg);
	}
}
