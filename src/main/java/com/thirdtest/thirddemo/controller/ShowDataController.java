package com.thirdtest.thirddemo.controller;

import java.io.File;
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

import com.thirdtest.thirddemo.entity.UEntity;
import com.thirdtest.thirddemo.entity.VideoEntity;
import com.thirdtest.thirddemo.jpa.VideoJPA;
import com.thirdtest.thirddemo.service.VideoService;
@Controller
public class ShowDataController {

	@Autowired
	private VideoService videoService;
	
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
		VideoEntity vEntity=videoService.getById(id);
		File file = new File(filePath + vEntity.getVideoname());
		UEntity uEntity = (UEntity) SecurityUtils.getSubject().getPrincipal();
		if(file.exists() && file.isFile() && vEntity.getAuthor().equals(uEntity.getTruename())) {
			videoService.deleteById(id);
		    file.delete();
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
}
