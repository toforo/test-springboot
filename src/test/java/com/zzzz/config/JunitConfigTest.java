package com.zzzz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:config/test.properties", ignoreResourceNotFound=true, encoding="UTF-8")	//会从src/test/resources寻找
@ConfigurationProperties(prefix="junit")
public class JunitConfigTest {
	
	private String testValue;

	public String getTestValue() {
		return testValue;
	}
	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}
	
	@Override
	public String toString() {
		return "JunitConfigTest [testValue=" + testValue + "]";
	}
}