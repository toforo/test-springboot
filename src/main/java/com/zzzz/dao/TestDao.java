package com.zzzz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zzzz.model.Test;

/**
 * Spring Data JPA实现
 * @author zhuangyilian
 * @date 2019年2月11日
 */
@Repository
public interface TestDao extends JpaRepository<Test, Long>{

	@Query(value="SELECT * FROM test WHERE code = ?1 LIMIT 1", nativeQuery=true)
	public Test findByCode(int code);

	@Modifying	//service层必须加@Transactional
	@Query(value="DELETE FROM test WHERE id = ?1", nativeQuery=true)
	public int deleteById(long id);

	@Modifying
	@Query(value="UPDATE test SET value = ?2 WHERE code = ?1", nativeQuery=true)	//参数占位符
	public int updateByCode(int code, String value);
	
	@Modifying
	@Query(value="UPDATE test SET value = :value WHERE code = :code", nativeQuery=true)	//具名参数
	public int updateByCode2(@Param("code") int code, @Param("value") String value);
	
}
