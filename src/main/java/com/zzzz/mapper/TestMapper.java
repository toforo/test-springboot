package com.zzzz.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;

import com.zzzz.model.Test;

/**
 * Mybatis实现
 * @author zhuangyilian
 * @date 2019年2月11日
 */
//@Mapper //启动类上加了@MapperScan时,可以不要@Mapper
public interface TestMapper{

	@Select("SELECT * FROM test WHERE code = #{code} LIMIT 1")
	public Test findByCode(int code);

	@Delete("DELETE FROM test WHERE id = #{id}")
	public int deleteById(long id);

	@Update("UPDATE test SET value = #{value} WHERE code = #{code}")
	public int updateByCode(@Param("code") int code, @Param("value") String value);	//多个参数,要加@Param
	
	@Update("UPDATE test SET value = #{value} WHERE code = #{code}")
	public int updateByCode2(Test test);
	
	@Insert("INSERT INTO test (code, value) VALUES(#{code}, #{value})")
	public int save(@Param("code") int code, @Param("value") String value);

	@Insert("INSERT INTO test (code, value) VALUES(#{code}, #{value})")
	public int save2(Test test);
	
}
