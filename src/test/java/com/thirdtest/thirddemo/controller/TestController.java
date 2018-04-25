package com.thirdtest.thirddemo.controller;

import java.util.List;

import org.apache.shiro.authc.DisabledAccountException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thirdtest.thirddemo.ThirddemoApplication;
import com.thirdtest.thirddemo.entity.VideoEntity;
import com.thirdtest.thirddemo.entity.PermissionEntity;
import com.thirdtest.thirddemo.entity.Role_PermissionEntity;
import com.thirdtest.thirddemo.entity.UEntity;
import com.thirdtest.thirddemo.jpa.PermissionJPA;
import com.thirdtest.thirddemo.jpa.UserInfoJPA;
import com.thirdtest.thirddemo.jpa.VideoJPA;
import com.thirdtest.thirddemo.service.PermissionService;
import com.thirdtest.thirddemo.service.Role_permissionService;
//import com.thirdtest.thirddemo.jpa.UserJPA;
import com.thirdtest.thirddemo.service.UserInfoService;
import com.thirdtest.thirddemo.service.VideoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ThirddemoApplication.class)
@WebAppConfiguration
public class TestController {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserInfoJPA userInfoJPA;
	@Autowired
	private PermissionJPA permissionJPA;
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private VideoJPA videoJPA;
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private Role_permissionService role_permissionService;
	
	//private UserJPA userJPA;
	
	@Test
	public void testService() {
		//UEntity userInfoEntity=userInfoService.getByUsername("zydagu");
		//List<UEntity> userInfoEntity=userInfoJPA.findAll();
		//List<PermissionEntity> permissionEntity=permissionJPA.findAll();
		//PermissionEntity permissionEntity=permissionService.getPermissionByRole((long) 1);
		//String permissionEntity=role_permissionService.getByRole(1);
		//List<VideoEntity> permissionEntity=videoService.getVideoByAuthor("dagu");
		if(null==null) {
			System.out.println("----->>PermissionEntity=null");
	    	 return;
		}
	    else {
	    //	System.out.println("----->>PermissionEntity="+permissionEntity);
		}
	}
	
	
}
