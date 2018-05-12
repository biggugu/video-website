package com.thirdtest.thirddemo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thirdtest.thirddemo.entity.CommentEntity;

public interface CommentService {
	
	public Page<CommentEntity> getCommentByVideoid(Long vid,Pageable pageable);

	public void saveComment(CommentEntity commentEntity);
}
