package com.thirdtest.thirddemo.config;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.thirdtest.thirddemo.entity.UEntity;
import com.thirdtest.thirddemo.service.Role_permissionService;
import com.thirdtest.thirddemo.service.UserInfoService;


public class MyShiroRealm extends AuthorizingRealm{

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private Role_permissionService role_permissionService;
	
	private Logger logger = Logger.getLogger(MyShiroRealm.class);
	/*权限认证*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		System.out.println("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UEntity userInfo=(UEntity)principals.getPrimaryPrincipal();
		
		Set<String> permissionSet = new HashSet<String>();
		List<String> str=new ArrayList<>();
		
		str=role_permissionService.getByRole(userInfo.getId_role());
		for(int i=0;i<str.size();i++) {
			permissionSet.add(str.get(i));
		}
		
		authorizationInfo.setStringPermissions(permissionSet);
		
		return authorizationInfo;
	}

	/*登录认证*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为：" + token.toString());
		
	    UEntity userInfoEntity=userInfoService.getByUsername(token.getUsername());
	    System.out.println(authenticationToken.getCredentials());
	     if(null==userInfoEntity) {
	    	 return null;
	     }else if(0==userInfoEntity.getStatus()){
	    	 throw new DisabledAccountException("帐号已经禁止登录！");
		}
	    else {
	    	System.out.println("----->>userInfo="+userInfoEntity);
		}
 
		return new SimpleAuthenticationInfo(userInfoEntity,userInfoEntity.getPassword(),ByteSource.Util.bytes(userInfoEntity.getSalt()),getName());
	}

}
