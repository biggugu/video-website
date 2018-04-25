/*
package com.thirdtest.thirddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thirdtest.thirddemo.entity.UserEntity;
import com.thirdtest.thirddemo.jpa.UserJPA;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserJPA userJPA;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public List<UserEntity> list(){
		return userJPA.findAll();
	}
	
	@RequestMapping(value="/save",method=RequestMethod.GET)
	public UserEntity save(UserEntity entity){
		return userJPA.save(entity);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public List <UserEntity> delete(Long id){
		userJPA.delete(id);
		return userJPA.findAll();
	}
}
*/