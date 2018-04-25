package com.thirdtest.thirddemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdtest.thirddemo.entity.PermissionEntity;
import com.thirdtest.thirddemo.jpa.PermissionJPA;

@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionJPA permissionJPA;

	@Override
	public PermissionEntity getPermissionByRole(Long id) {
		// TODO Auto-generated method stub
		return permissionJPA.findByIdIs(id);
	}

}
