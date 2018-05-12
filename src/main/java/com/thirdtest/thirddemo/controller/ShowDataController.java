package com.thirdtest.thirddemo.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirdtest.thirddemo.entity.CommentEntity;
import com.thirdtest.thirddemo.entity.ReplyEntity;
import com.thirdtest.thirddemo.entity.UEntity;
import com.thirdtest.thirddemo.entity.VideoEntity;
import com.thirdtest.thirddemo.jpa.VideoJPA;
import com.thirdtest.thirddemo.service.CommentService;
import com.thirdtest.thirddemo.service.ReplyService;
import com.thirdtest.thirddemo.service.VideoService;
@Controller
public class ShowDataController {

	@Autowired
	private VideoService videoService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/videoList.do")
	@ResponseBody
	public Map<String, Object> showVideo(@RequestParam(value="page", required=false) String page, 
            @RequestParam(value="rows", required=false) String rows) {
		try {
			UEntity uEntity = (UEntity) SecurityUtils.getSubject().getPrincipal();
			Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(rows));
			Page<VideoEntity> videoEntities= videoService.getVideoByAuthor(uEntity.getTruename(),pageable);
			Long total=videoEntities.getTotalElements();
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("rows", videoEntities.getContent());     //存放每页记录数
			reMap.put("total", total);   //存放总记录数 
			return reMap;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/delete.do")
	@ResponseBody
	public String deleteVideo(@RequestParam(value="ID", required=false) Long id){
		String  filePath="//home//zydagu//Videos//";
		String picturePath="//home//zydagu//Pictures//";
		VideoEntity vEntity=videoService.getById(id);
		File file1 = new File(filePath + vEntity.getVideoname());
		File file2=new File(picturePath+vEntity.getVideopicture()+".jpg");
		UEntity uEntity = (UEntity) SecurityUtils.getSubject().getPrincipal();
		if(file1.exists() && file1.isFile() && vEntity.getAuthor().equals(uEntity.getTruename())) {
			videoService.deleteById(id);
		    file1.delete();
		    file2.delete();
		    return "success";
		}
		else {
			return "false";
		}
	}
	
	@RequestMapping(value="/search.do")
	@ResponseBody
	public List<VideoEntity> searchVideo(@RequestParam(value="findname", required=false) String name){
		
		List<VideoEntity> videoEntities=videoService.getByVideonameContaining(name);
		
		return videoEntities;
	}
	
	
	@RequestMapping(value="/showComment.do")
	@ResponseBody
	public Map<String, Object> showComment(@RequestParam(value="page", required=false) String page, 
            @RequestParam(value="rows", required=false) String rows,
            @RequestParam(value="videoid", required=false) Long videoid){
		
		try {
			Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(rows));
			Page<CommentEntity> commentEntities= commentService.getCommentByVideoid(videoid, pageable);
			Long total=commentEntities.getTotalElements();
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("rows", commentEntities.getContent());     //存放每页记录数
			reMap.put("total", total);   //存放总记录数 
			return reMap;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/showReply.do")
	@ResponseBody
	public Map<String, Object> showReply(@RequestParam(value="page", required=false) String page, 
            @RequestParam(value="rows", required=false) String rows,
            @RequestParam(value="replyid", required=false) Long replyid){
		
		try {
			Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(rows));
			Page<ReplyEntity> replyEntities= replyService.getReplyByCommentid(replyid, pageable);
			Long total=replyEntities.getTotalElements();
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("rows", replyEntities.getContent());     //存放每页记录数
			reMap.put("total", total);   //存放总记录数 
			return reMap;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(value="/show.do")
	@ResponseBody
	public Map<String, Object> showAllVideo(@RequestParam(value="page", required=false) String page, 
            @RequestParam(value="rows", required=false) String rows) {
		try {
			Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(rows));
			Page<VideoEntity> videoEntities= videoService.getAllVideo(pageable);
			Long total=videoEntities.getTotalElements();
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("rows", videoEntities.getContent());     //存放每页记录数
			reMap.put("total", total);   //存放总记录数 
			return reMap;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/play.do")
	public void playVideo(@RequestParam(value="videoName", required=false) String videoName,HttpServletResponse response) {
		response.setHeader("Content-Type", "video/mp4");
		response.setHeader("X-Accel-Redirect", "/"+videoName);
		System.out.println("play success");
		
	}
	
	@RequestMapping(value="/download.do")
	public void downloadVideo(@RequestParam(value="fileName", required=false) String fileName,HttpServletResponse response) {
		response.setHeader("Content-Type", "video/mp4");
   	 	response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		response.setHeader("X-Accel-Redirect", "/"+fileName);
		System.out.println("download success");
	}
	
	@RequestMapping(value="/saveComment.do")
	@ResponseBody
	public void saveComment(@RequestParam(value="videoid", required=false) Long vid,
			@RequestParam(value="comment", required=false) String comment){
		
		UEntity uEntity = (UEntity) SecurityUtils.getSubject().getPrincipal();
		String commentor=uEntity.getTruename();
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String commenttime=df.format(date);
		
		CommentEntity commentEntity=new CommentEntity();
		commentEntity.setId(null);
		commentEntity.setVideoid(vid);
		commentEntity.setCustomer(commentor);
		commentEntity.setComment(comment);
		commentEntity.setTime(commenttime);
		commentService.saveComment(commentEntity);
	}
	
	@RequestMapping(value="/saveReply.do")
	@ResponseBody
	public void saveReply(@RequestParam(value="commentid", required=false) Long cid,
			@RequestParam(value="targetusername", required=false) String name,
			@RequestParam(value="reply", required=false) String content){
		
		UEntity uEntity = (UEntity) SecurityUtils.getSubject().getPrincipal();//获取当前登录者
		String user=uEntity.getTruename();
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String replytime=df.format(date);
		
		ReplyEntity replyEntity=new ReplyEntity();
		replyEntity.setId(null);
		replyEntity.setCommentid(cid);
		replyEntity.setFromusername(user);
		replyEntity.setTousername(name);
		replyEntity.setReply(content);
		replyEntity.setTime(replytime);
		replyService.saveReply(replyEntity);
	}
	
	@RequestMapping(value="/updateGoals.do")
	@ResponseBody
	public String updateGoals(@RequestParam(value="goals", required=false) String goals,
			@RequestParam(value="videoid", required=false) Long videoid) {
		try {
			VideoEntity vEntity=videoService.getById(videoid);
			int newCount=vEntity.getCount()+1;
			vEntity.setGoals(((vEntity.getGoals()*vEntity.getCount())+Integer.valueOf(goals))/(newCount));
			vEntity.setCount(newCount);
			videoService.saveVideo(vEntity);
			return "succeed";
		}catch (Exception e) {
			// TODO: handle exception
			return "fail";
		}
	}
}
