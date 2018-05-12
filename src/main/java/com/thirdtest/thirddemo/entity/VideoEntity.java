package com.thirdtest.thirddemo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserVideo")
public class VideoEntity implements Serializable{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="videoname")
	private String videoname;
	
	@Column(name="videosize")
	private Long videosize;
	
	@Column(name="author")
	private String author;
	
	@Column(name="intro")
	private String intro;
	
	@Column(name="institution")
	private String institution;
	
	
	@Column(name="uploadtime")
	private String uploadtime;
	
	@Column(name="count")
	private int count;
	
	@Column(name="goals")
	private int goals;
	
	@Column(name="videopicture")
	private String videopicture;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long nullid) {
		id=nullid;
	}
	
	public String getVideoname() {
		return videoname;
	}
	
	public void setVideoname(String str) {
		videoname=str;
	}
	
	public Long getVideosize() {
		return videosize;
	}
	
	public void setVideosize(Long size) {
		videosize=size;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String str) {
		author=str;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public void setIntro(String str) {
		intro=str;
	}
	
	public void setInstitution(String str) {
		institution=str;
	}
	
	public String getInstitution() {
		return institution;
	}
	
	public String getUploadtime() {
		return uploadtime;
	}
	
	public void setUploadtime(String str) {
		uploadtime=str;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int num) {
		count=num;
	}
	
	public int getGoals() {
		return goals;
	}
	
	public void setGoals(int num) {
		goals=num;
	}
	
	public String getVideopicture() {
		return videopicture;
	}
	
	public void setVideopicture(String str) {
		videopicture=str;
	}
}
