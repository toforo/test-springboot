package com.zzzz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 主配置
 * @author zhuangyilian
 * @date 2019年2月18日
 */
@Component
@ConfigurationProperties(prefix="web")
public class Config {
	
	/**文件上传路径*/
	@Value("${web.upload-path}")
    private String uploadPath;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Override
	public String toString() {
		return "Config [uploadPath=" + uploadPath + "]";
	}
	
}
