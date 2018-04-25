package com.thirdtest.thirddemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdtest.thirddemo.entity.Role_PermissionEntity;
import com.thirdtest.thirddemo.jpa.Role_permissionJPA;

@Service
public class Role_permissionServiceImpl implements Role_permissionService{
	
	@Autowired
	private Role_permissionJPA role_permissionJPA;

	@Override
	public List<String> getByRole(int id) {
		
		List<String> str=new ArrayList<>();
		List<Role_PermissionEntity> role_PermissionEntities=role_permissionJPA.findByRoleidIs(id);
		for(int i=0;i<role_PermissionEntities.size();i++) {
			str.add(role_PermissionEntities.get(i).getPermission_title());
		}
		return str;
	}

}
