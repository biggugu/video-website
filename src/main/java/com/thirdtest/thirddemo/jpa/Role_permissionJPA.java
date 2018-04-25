package com.thirdtest.thirddemo.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thirdtest.thirddemo.entity.Role_PermissionEntity;

public interface Role_permissionJPA extends JpaRepository<Role_PermissionEntity, Long>,JpaSpecificationExecutor<Role_PermissionEntity>,Serializable{

	List<Role_PermissionEntity> findByRoleidIs(int id);
}
