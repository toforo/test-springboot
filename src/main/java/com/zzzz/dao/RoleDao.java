package com.zzzz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zzzz.model.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long>{

}
