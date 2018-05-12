package com.thirdtest.thirddemo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thirdtest.thirddemo.entity.ReplyEntity;

public interface ReplyService {

	public void saveReply(ReplyEntity replyEntity);
	 
	public Page<ReplyEntity> getReplyByCommentid(Long cid,Pageable pageable);
}
