package com.zzzz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zzzz.mapper.TestMapper;
import com.zzzz.model.Test;
import com.zzzz.util.PageBean;

@Service
public class TestMybatisService {
	
	@Autowired
	private TestMapper testMapper;
	
	
	public Test findByCode(int code) {
		
		return testMapper.findByCode(code);
	}
	
	@Transactional
	public int deleteById(long id) {
		
		return testMapper.deleteById(id);
	}
	
	@Transactional
	public int updateByCode(int code, String value) {
		
		return testMapper.updateByCode(code, value);
	}
	
	@Transactional
	public int updateByCode(Test test) {
		
		return testMapper.updateByCode2(test);
	}
	
	@Transactional
	public int save(int code, String value) {
		
		return testMapper.save(code, value);
	}
	
	@Transactional
	public int save(Test test) {
		
		return testMapper.save2(test);
	}
	
	/**
	 * 分页查询
	 * @author zhuangyilian
	 * @date 2019年3月5日
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public List<Test> findByPage(int currPage, int pageSize) {
		//设置分页信息(当前页数和每页条数), 必须在调用mapper接口的方法之前设置
		PageHelper.startPage(currPage, pageSize);
		
		List<Test> testList = testMapper.findAll();
		int count = testMapper.countAll();
		PageBean<Test> pageBean = new PageBean<>(currPage, pageSize, count);
		pageBean.setPage(testList);
		
		return pageBean.getPage();
	}

}
