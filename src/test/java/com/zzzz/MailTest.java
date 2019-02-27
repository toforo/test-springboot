package com.zzzz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzzz.service.TestMailService;

/**
 * 邮件测试
 * @author zhuangyilian
 * @date 2019年2月26日
 */
public class MailTest {
	
	private static final String TO_MAIL = "1010092116@qq.com";
	
	@Autowired
	private TestMailService testMailService;
	
	@Test
	public void testSimpleMail(){
		
		testMailService.sendSimpleMail(TO_MAIL, "test simple mail", "This is a simple mail");
	}
	
	@Test
	public void testHtmlMail(){
		String content = "<html>\n" +
	            "<body>\n" +
	            "    <h3>This is a html email</h3>\n" +
	            "</body>\n" +
	            "</html>";
		
		testMailService.sendHtmlMail(TO_MAIL, "test html mail", content);
	}
	
	@Test
	public void testAttachmentsMail(){
		String filePath = "D:\\test.txt";
		
		testMailService.sendAttachmentsMail(TO_MAIL, "test attachments mail", "注意查收附件...", filePath);
	}
	
	@Test
	public void testInlineResourceMail(){
		String cid = "pic_test_001";
	    String content="<html><body>这是有图片的邮件：<img src=\'cid:" + cid + "\' ></body></html>";
	    String imgPath = "D:\\pic_test.jpg";
		
		testMailService.sendInlineResourceMail(TO_MAIL, "test inline resource mail", content, imgPath, cid);
	}
}
