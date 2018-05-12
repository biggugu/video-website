package com.thirdtest.thirddemo.jpa;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thirdtest.thirddemo.entity.ReplyEntity;

public interface ReplyJPA extends JpaRepository<ReplyEntity, Long>,JpaSpecificationExecutor<ReplyEntity>,Serializable{

	Page<ReplyEntity> findByCommentidIs(Long cid,Pageable pageable);
}
