package com.zzzz.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author zhuangyilian
 * @date 2019年2月26日
 */
@Component
public class SchedulerTask {
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Scheduled(cron="*/5 * * * * ?")
	public void printTime(){
		
		System.out.println("now1: " + DATE_FORMAT.format(new Date()));
	}
	
	/**
	 * fixedRate=5000: 上一次开始执行时间点之后5秒再执行
	 * fixedDelay=5000: 上一次执行完毕时间点之后5秒再执行
	 * initialDelay=1000, fixedRate=5000: 第一次延迟1秒后执行,之后按fixedRate的规则每5秒执行一次
	 * @author zhuangyilian
	 * @date 2019年2月26日
	 */
//	@Scheduled(fixedRate=5000)
//	@Scheduled(fixedDelay=5000)
	@Scheduled(initialDelay=1000, fixedRate=5000)
	public void printTime2(){
		
		System.out.println("now2: " + DATE_FORMAT.format(new Date()));
	}
}
