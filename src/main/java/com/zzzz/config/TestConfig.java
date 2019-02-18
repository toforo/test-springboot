package com.zzzz.config;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.zzzz.model.Test;

/**
 * 将配置文件属性值，映射到组件中
 * @author zhuangyilian
 * @date 2019年2月18日
 */
@Component //只有容器中的组件,才能使用@ConfigurationProperties提供的功能
@PropertySource(value="classpath:config/test.properties", ignoreResourceNotFound=true, encoding="UTF-8")	//指定从哪个配置文件读取属性值(不加该注解,则默认从主配置文件读取)
@ConfigurationProperties(prefix="test")	//属性前缀
public class TestConfig {
	
	//@Value("${test.testStr}")
	private String testStr;
	private Integer testInt;
    private boolean testBool;
    private Date testDate;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date testDateFormat;
	private Map<String, Object> testMap;
    private List<Object> testList;
    private Test testTest;
    
	public String getTestStr() {
		return testStr;
	}
	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}
	public Integer getTestInt() {
		return testInt;
	}
	public void setTestInt(Integer testInt) {
		this.testInt = testInt;
	}
	public boolean isTestBool() {
		return testBool;
	}
	public void setTestBool(boolean testBool) {
		this.testBool = testBool;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public Date getTestDateFormat() {
		return testDateFormat;
	}
	public void setTestDateFormat(Date testDateFormat) {
		this.testDateFormat = testDateFormat;
	}
	public Map<String, Object> getTestMap() {
		return testMap;
	}
	public void setTestMap(Map<String, Object> testMap) {
		this.testMap = testMap;
	}
	public List<Object> getTestList() {
		return testList;
	}
	public void setTestList(List<Object> testList) {
		this.testList = testList;
	}
	public Test getTestTest() {
		return testTest;
	}
	public void setTestTest(Test testTest) {
		this.testTest = testTest;
	}
	
	@Override
	public String toString() {
		return "TestConfig [testStr=" + testStr + ", testInt=" + testInt
				+ ", testBool=" + testBool + ", testDate=" + testDate
				+ ", testDateFormat=" + testDateFormat + ", testMap=" + testMap
				+ ", testList=" + testList + ", testTest=" + testTest + "]";
	}
	
}
