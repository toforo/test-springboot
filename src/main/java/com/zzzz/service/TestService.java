package com.zzzz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zzzz.dao.TestDao;
import com.zzzz.model.Test;

@Service
public class TestService {
	
	@Autowired
	private TestDao testDao;
	
	
	@Cacheable(value="test_findAll")	//开启缓存, value:缓存名,对应redis的key
	public List<Test> findAll() {
		
		return testDao.findAll();
	}
	
	public Test findByCode(int code) {
		
		return testDao.findByCode(code);
	}
	
	@Transactional
	public int deleteById(long id) {
		
		return testDao.deleteById(id);
	}
	
	@Transactional
	public int updateByCode(int code, String value) {
		
		return testDao.updateByCode(code, value);
	}
	
	@Transactional
	public int updateByCode2(int code, String value) {
		
		return testDao.updateByCode2(code, value);
	}
	
	@Transactional
	public Test save(int code, String value) {
		Test test = new Test();
		test.setCode(code);
		test.setValue(value);
		
		return testDao.save(test);
	}
	
	@Transactional
	public Test save(Test test) {
		
		return testDao.save(test);
	}
	
}
