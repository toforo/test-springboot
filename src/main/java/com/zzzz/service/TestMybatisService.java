package com.zzzz.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzzz.mapper.TestMapper;
import com.zzzz.model.Test;

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

}
