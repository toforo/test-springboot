package com.zzzz.service;


import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class TestMailService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	@Value("${web.from-mail}")
	private String from;
	
	/**
	 * 发送简单邮件
	 * @author zhuangyilian
	 * @date 2019年2月26日
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		
		try {
			mailSender.send(message);
			logger.info("简单邮件发送成功...");
		} catch (Exception e) {
			logger.error("简单邮件发送异常...", e);
		}
	}
	
	/**
	 * 发送html邮件
	 * @author zhuangyilian
	 * @date 2019年2月26日
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);	//true: 需要创建一个multipart message
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);	//true: 发送内容为html格式
			
			mailSender.send(message);
			logger.info("html邮件发送成功...");
		} catch (MessagingException e) {
			logger.error("html邮件发送异常...", e);
		}
	}
	
	/**
	 * 发送带附件的邮件
	 * @author zhuangyilian
	 * @date 2019年2月26日
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String filePath){
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			FileSystemResource res = new FileSystemResource(new File(filePath));
			helper.addAttachment(fileName, res);	//添加附件(添加多个时,多次调用即可)
		
			mailSender.send(message);
			logger.info("带附件的邮件发送成功...");
		} catch (MessagingException e) {
			logger.error("带附件的邮件发送异常...", e);
		}
	}
	
	/**
	 * 发送嵌入静态资源的邮件
	 * 添加多个图片使用多条 <img src='cid:" + cid + "' > 和  helper.addInline(cid, res) 来实现
	 * @author zhuangyilian
	 * @date 2019年2月26日
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 * @param cid
	 */
	public void sendInlineResourceMail(String to, String subject, String content, String filePath, String cid){
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			FileSystemResource res = new FileSystemResource(new File(filePath));
			helper.addInline(cid, res);
			
			mailSender.send(message);
			logger.info("嵌入静态资源的邮件发送成功...");
		} catch (MessagingException e) {
			logger.error("嵌入静态资源的邮件发送异常...", e);
		}
	}
}
