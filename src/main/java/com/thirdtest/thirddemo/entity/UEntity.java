package com.thirdtest.thirddemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserInfo")
public class UEntity implements Serializable{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long uid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="salt")
	private String salt;
	
	@Column(name="truename")
	private String truename;
	
	@Column(name="email")
	private String email;
	
	@Column(name="id_role")
	private int id_role;
	
	@Column(name="role")
	private String role;

	@Column(name="status")
	private int status;
	
	public Long getUid() {
		return uid;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public String getTruename() {
		return truename;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getId_role() {
		return id_role;
	}
	
	public String getRole() {
		return role;
	}
	
	public int getStatus() {
		return status;
	}
}
