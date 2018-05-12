package com.thirdtest.thirddemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CommentReply")
public class ReplyEntity implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="commentid")
	private Long commentid;
	
	@Column(name="fromusername")
	private String fromusername;
	
	@Column(name="tousername")
	private String tousername;
	
	@Column(name="reply")
	private String reply;
	
	@Column(name="time")
	private String time;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long rid) {
		id=rid;
	}
	
	public Long getCommentid() {
		return commentid;
	}
	
	public void setCommentid(Long cid) {
		commentid=cid;
	}
	
	
	public String getFromusername() {
		return fromusername;
	}
	
	public void setFromusername(String str) {
		fromusername=str;
	}
	
	
	public String getTousername() {
		return tousername;
	}
	
	public void setTousername(String str) {
		tousername=str;
	}
	
	public String getReply() {
		return reply;
	}
	
	public void setReply(String str) {
		reply=str;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String str) {
		time=str;
	}
}
