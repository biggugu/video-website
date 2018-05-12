package com.thirdtest.thirddemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thirdtest.thirddemo.entity.CommentEntity;
import com.thirdtest.thirddemo.jpa.CommentJPA;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentJPA commentJPA;

	@Override
	public Page<CommentEntity> getCommentByVideoid(Long vid, Pageable pageable) {
		// TODO Auto-generated method stub
		return commentJPA.findByVideoidIs(vid, pageable);
	}

	@Override
	public void saveComment(CommentEntity commentEntity) {
		// TODO Auto-generated method stub
		commentJPA.save(commentEntity);
	}

}
