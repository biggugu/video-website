package com.thirdtest.thirddemo.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thirdtest.thirddemo.entity.UEntity;


public interface UserInfoJPA extends JpaRepository<UEntity, Long>,JpaSpecificationExecutor<UEntity>,Serializable{
	
	UEntity findByNameIs(String username);
}
