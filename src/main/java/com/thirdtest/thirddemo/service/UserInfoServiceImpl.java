package com.thirdtest.thirddemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdtest.thirddemo.entity.UEntity;
import com.thirdtest.thirddemo.jpa.UserInfoJPA;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoJPA userInfoJPA;
	public UEntity getByUsername(String username) {
		return userInfoJPA.findByNameIs(username);
		
	}
}
