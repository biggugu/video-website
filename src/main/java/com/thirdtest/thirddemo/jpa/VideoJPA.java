package com.thirdtest.thirddemo.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thirdtest.thirddemo.entity.VideoEntity;


public interface VideoJPA extends JpaRepository<VideoEntity, Long>,JpaSpecificationExecutor<VideoEntity>,Serializable{
	
	Page<VideoEntity> findByAuthorIs(String author,Pageable pageable);
	
	List<VideoEntity> findByVideonameContaining(String name);
	
	VideoEntity findByIdIs(Long id);

}
