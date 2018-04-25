package com.thirdtest.thirddemo.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thirdtest.thirddemo.entity.PermissionEntity;

public interface PermissionJPA extends JpaRepository<PermissionEntity, Long>,JpaSpecificationExecutor<PermissionEntity>,Serializable{

	PermissionEntity findByIdIs(Long id);
}
