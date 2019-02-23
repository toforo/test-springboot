package com.zzzz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zzzz.model.Permission;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Long>{

}
