package com.thirdtest.thirddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thirdtest.thirddemo.entity.VideoEntity;
import com.thirdtest.thirddemo.jpa.VideoJPA;

@Service
public class VideoServiceImpl implements VideoService{

	@Autowired
	private VideoJPA videoJPA;
	
	@Override
	public Page<VideoEntity> getVideoByAuthor(String author,Pageable pageable) {
		// TODO Auto-generated method stub
		return videoJPA.findByAuthorIs(author,pageable);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		videoJPA.delete(id);
	}
	

	@Override
	public List<VideoEntity> getByVideonameContaining(String name) {
		// TODO Auto-generated method stub
		return videoJPA.findByVideonameContaining(name);
	}

	@Override
	public void saveVideo(VideoEntity videoEntity) {
		// TODO Auto-generated method stub
		videoJPA.save(videoEntity);
	}

	@Override
	public VideoEntity getById(Long id) {
		// TODO Auto-generated method stub
		return videoJPA.findByIdIs(id);
	}

	@Override
	public Page<VideoEntity> getAllVideo(Pageable pageable) {
		// TODO Auto-generated method stub
		return videoJPA.findAll(pageable);
	}

}
