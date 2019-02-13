package com.zzzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzzz.model.Test;
import com.zzzz.service.TestMybatisService;

/**
 * Mybatis测试
 * @author zhuangyilian
 * @date 2019年2月11日
 */
@RestController
@RequestMapping("/mybatis")
public class TestMybatisController {
	
	@Autowired
	private TestMybatisService testMybatisService;
	

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
	 * 条件查询
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param code
	 * @return
	 */
	@RequestMapping("/findByCode")
	public String findTestByCode(@RequestParam(value="code") int code){
		Test test = testMybatisService.findByCode(code);
		
		return "findByCode: " + test.toString();
	}
	
	/**
	 * 条件删除
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteById")
	public String deleteTestById(@RequestParam(value="id") long id){
		int rows = testMybatisService.deleteById(id);
		
		return "deleteById: " + rows;
	}
	
	/**
	 * 条件更新(参数绑定)
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param code
	 * @param value
	 * @return
	 */
	@RequestMapping("/updateByCode")
	public String updateByCode(@RequestParam(value="code") int code, @RequestParam(value="value") String value){
		int rows = testMybatisService.updateByCode(code, value);
		
		return "updateByCode: " + rows;
	}
	
	/**
	 * 条件更新(对象绑定)
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param test
	 * @return
	 */
	@RequestMapping("/updateByCode2")
	public String updateByCode2(Test test){
		int rows = testMybatisService.updateByCode(test);
		
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
	public String save(@RequestParam(value="code") int code, @RequestParam(value="value") String value){
		int rows = testMybatisService.save(code, value);
		
		return "save: " + rows;
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
		int rows = testMybatisService.save(test);
		
		return "save2: " + rows;
	}
	
}
