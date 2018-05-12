package com.thirdtest.thirddemo.jpa;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.thirdtest.thirddemo.entity.CommentEntity;


public interface CommentJPA extends JpaRepository<CommentEntity, Long>,JpaSpecificationExecutor<CommentEntity>,Serializable{

	Page<CommentEntity> findByVideoidIs(Long vid,Pageable pageable);
}
