package com.thirdtest.thirddemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VideoComment")
public class CommentEntity implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="videoid")
	private Long videoid;
	
	@Column(name="customer")
	private String customer;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="time")
	private String time;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long cid) {
		id=cid;
	}
	
	public Long getVideoid() {
		return videoid;
	}
	
	public void setVideoid(Long cid) {
		videoid=cid;
	}
	
	
	public String getCustomer() {
		return customer;
	}
	
	public void setCustomer(String str) {
		customer=str;
	}
	
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String str) {
		comment=str;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String str) {
		time=str;
	}
}
