package com.thirdtest.thirddemo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thirdtest.thirddemo.entity.VideoEntity;

public interface VideoService {
	public Page<VideoEntity> getVideoByAuthor(String author,Pageable pageable);
	
	public void deleteById(Long id);
	
	public List<VideoEntity> getByVideonameContaining(String name);
	
	public void saveVideo(VideoEntity videoEntity);
	
	public VideoEntity getById(Long id);
	
	public Page<VideoEntity> getAllVideo(Pageable pageable);
}
