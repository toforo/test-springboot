package com.zzzz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzzz.model.User;
import com.zzzz.mq.RabbitExchange;
import com.zzzz.mq.RabbitSender;

/**
 * RabbitMQ测试
 * @author zhuangyilian
 * @date 2019年2月26日
 */
public class RabbitTest extends BaseTest {
	
	@Autowired
	private RabbitSender rabbitSender;
	@Autowired
	private RabbitExchange rabbitExchange;
	
	@Test
	public void testSendDirect(){
		rabbitSender.sendDirect("queue.direct", "test direct...");
	}
	
	@Test
	public void testSendDirectObj(){
		User user = new User();
		user.setName("tom");
		
		//发送对象
		rabbitSender.sendDirect("queue.direct", user);
	}
	
	@Test
	public void testSendTopic(){
		rabbitSender.sendTopic("amq.topic", "topic.msg", "test topic msg...");
		rabbitSender.sendTopic("amq.topic", "topic.msg2", "test topic msgs...");
	}
	
	@Test
	public void testSendFanout(){
		rabbitSender.sendFanout("amq.fanout", "test fanout...");
	}
}
