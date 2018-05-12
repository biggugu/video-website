package com.thirdtest.thirddemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thirdtest.thirddemo.entity.ReplyEntity;
import com.thirdtest.thirddemo.jpa.ReplyJPA;


@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyJPA replyJPA;

	@Override
	public void saveReply(ReplyEntity replyEntity) {
		// TODO Auto-generated method stub
		replyJPA.save(replyEntity);
	}

	@Override
	public Page<ReplyEntity> getReplyByCommentid(Long cid, Pageable pageable) {
		// TODO Auto-generated method stub
		return replyJPA.findByCommentidIs(cid, pageable);
	}
	
	
}
