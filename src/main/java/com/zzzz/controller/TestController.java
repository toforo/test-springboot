package com.zzzz.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zzzz.config.Config;
import com.zzzz.config.JunitConfigTest;
import com.zzzz.model.Test;
import com.zzzz.service.TestService;
import com.zzzz.util.FileUtil;

/**
 * 随便测试
 * @author zhuangyilian
 * @date 2019年2月11日
 */
@RestController	//返回json串
@RequestMapping("/")
public class TestController {
	
	@Autowired
	private TestService testService;
	@Autowired
	private JunitConfigTest testConfig;
	@Autowired
	private Config config;
	

	/**
	 * just test
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @return
	 */
	@RequestMapping("/test")
	public String test(){
		
		return "test";
	}
	
	/**
	 * 请求参数绑定测试
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param id
	 * @return
	 */
	@RequestMapping("/test1")
	public String test1(@RequestParam(value="id", defaultValue="0") long id){
		
		return "test1: " + id;
	}
	
	/**
	 * URL变量测试
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param id
	 * @return
	 */
	@RequestMapping("/test2/{id}")
	public String test2(@PathVariable long id){
		
		return "test2: " + id;
	}
	
	/**
	 * 查询所有
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @return
	 */
	@RequestMapping("/findAll")
	public String findAll(){
		List<Test> testList = testService.findAll();
		
		return "findAll: " + testList.toString();
	}
	
	/**
	 * 条件查询
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param code
	 * @return
	 */
	@RequestMapping("/findByCode")
	public String findByCode(@RequestParam(value="code") int code){
		Test test = testService.findByCode(code);
		
		return "findByCode: " + test;
	}
	
	/**
	 * 条件删除
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteById")
	public String deleteById(@RequestParam(value="id") long id){
		int rows = testService.deleteById(id);
		
		return "deleteById: " + rows;
	}
	
	/**
	 * 条件更新(使用参数占位符)
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param code
	 * @param value
	 * @return
	 */
	@RequestMapping("/updateByCode")
	public String updateByCode(@RequestParam(value="code") int code, @RequestParam(value="value") String value){
		int rows = testService.updateByCode(code, value);
		
		return "updateByCode: " + rows;
	}
	
	/**
	 * 条件更新(使用具名参数)
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param code
	 * @param value
	 * @return
	 */
	@RequestMapping("/updateByCode2")
	public String updateTestByCode2(@RequestParam(value="code") int code, @RequestParam(value="value") String value){
		int rows = testService.updateByCode2(code, value);
		
		return "updateByCode2: " + rows;
	}
	
	/**
	 * 保存实体(参数绑定)
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param code
	 * @param value
	 * @return
	 */
	@RequestMapping("/save")
	public String saveTest(@RequestParam(value="code") int code, @RequestParam(value="value") String value){
		Test test = testService.save(code, value);
		
		return "save: " + test.toString();
	}
	
	/**
	 * 保存实体(对象绑定)
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param test
	 * @return
	 */
	@RequestMapping("/save2")
	public String save2(Test test){
		test = testService.save(test);
		
		return "save2: " + test.toString();
	}
	
	/**
	 * 默认首页
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param mv
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv){
		
	    return new ModelAndView("index");
	}
	
	/**
	 * 自定义配置测试
	 * @author zhuangyilian
	 * @date 2019年2月18日
	 * @return
	 */
	@RequestMapping("/config")
	public String config(){
		
		return "config: " + testConfig.toString();
	}
	
	/**
	 * 上传文件测试页面
	 * @author zhuangyilian
	 * @date 2019年2月18日
	 * @return
	 */
	@RequestMapping("/file")
	public ModelAndView file(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("testFile");
		
		return mv;
	}
	
	/**
	 * 上传文件
	 * @author zhuangyilian
	 * @date 2019年2月18日
	 * @return
	 */
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file){
		try {
			InputStream is = file.getInputStream();
			String path = config.getUploadPath() + file.getOriginalFilename();
			
			FileUtil.uploadFile(is, path);
		} catch (IOException e) {
			e.printStackTrace();
			
			return "fail";
		}
		
		return "succ";
	}
	
	/**
	 * 下载文件
	 * @author zhuangyilian
	 * @date 2019年2月18日
	 * @return
	 */
	@RequestMapping("/download")
	public void download(HttpServletResponse response){
		String fileName = "1F524114F4-2.jpg";// 文件名
		response.setContentType("application/force-download");// 设置强制下载不打开
		response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
		
		try {
			String filePath = Paths.get(config.getUploadPath() + fileName).toAbsolutePath().toString();
			File file = new File(filePath);
			
			FileUtil.downloadFile(file, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * test error 500
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/test500")
	public String test500(){
		int a = 1 / 0;
		
		return "test500";
	}
	
}
