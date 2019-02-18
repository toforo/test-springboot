package com.zzzz.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件工具类
 * @author zhuangyilian
 * @date 2019年2月18日
 */
public class FileUtil {

	/**
	 * 文件上传
	 * @author zhuangyilian
	 * @date 2019年2月18日
	 * @param is
	 * @param storePath
	 * @return
	 */
	public static boolean uploadFile(InputStream is, String storePath){
		try {
			Path path = Paths.get(storePath).toAbsolutePath();
			Files.copy(is, path);
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 文件下载
	 * @author zhuangyilian
	 * @date 2019年2月18日
	 * @param file
	 * @param os
	 * @return
	 */
	public static boolean downloadFile(File file, OutputStream os){
		if(file == null){
			throw new RuntimeException("File can not be null");
		}
		
		if (!file.exists()) {
			throw new RuntimeException("File is not exists");
		}
		
		if (os == null) {
			throw new RuntimeException("OutputStream can not be null");
		}
		
		byte[] buffer = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}  
		
		return false;
	}
}
