package com.thirdtest.thirddemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RolePermission")
public class Role_PermissionEntity implements Serializable{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="roleid")
	private int roleid;

	@Column(name="permissionid")
	private Long permissionid;
	
	@Column(name="permissiontitle")
	private String permissiontitle;
	
	public Long getId() {
		return id;
	}
	
	public int getRole_id() {
		return roleid;
	}
	
	public Long getPermission_id() {
		return permissionid;
	}
	
	public String getPermission_title()
	{
		return permissiontitle;
	}

}
