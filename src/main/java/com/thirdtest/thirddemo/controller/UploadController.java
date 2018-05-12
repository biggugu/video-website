package com.thirdtest.thirddemo.controller;

import java.io.File;

import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thirdtest.thirddemo.entity.UEntity;
import com.thirdtest.thirddemo.entity.VideoEntity;
import com.thirdtest.thirddemo.jpa.VideoJPA;
import com.thirdtest.thirddemo.service.VideoService;

@Controller
public class UploadController {
	
	@Autowired
	private VideoService videoService;

	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(HttpServletRequest request,MultipartFile file) {
		try {
			Date date = new Date();
			String uuid = UUID.randomUUID().toString().replaceAll("-", ""); //获取uuid
			String intro=request.getParameter("intro");
			String institution=request.getParameter("institution");
			UEntity uEntity = (UEntity) SecurityUtils.getSubject().getPrincipal();
			String author=uEntity.getTruename();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String uploadtime=df.format(date);
			String filename=file.getOriginalFilename();
			String uploadDir="//home//zydagu//Videos//";
			File serverFile=new File(uploadDir+filename);
			long filesize=file.getSize();
			
			//写入数据库
			VideoEntity videoEntity=new VideoEntity();
			videoEntity.setId(null);
			videoEntity.setAuthor(author);
			videoEntity.setVideosize(filesize);
			videoEntity.setIntro(intro);
			videoEntity.setInstitution(institution);
			videoEntity.setVideoname(filename);
			videoEntity.setUploadtime(uploadtime);
			videoEntity.setCount(0);
			videoEntity.setGoals(0);
			videoEntity.setVideopicture(uuid);
			videoService.saveVideo(videoEntity);
			file.transferTo(serverFile);
			try {
				
				Runtime runtime = Runtime.getRuntime(); 
				Process proce = null; 
				//截图命令
				String cmd="ffmpeg -y -i "+serverFile+" -ss 5 -s 600x400 -f image2 -vframes 1  /home/zydagu/Pictures/"+uuid+".jpg";
				proce = runtime.exec(cmd);
				proce.waitFor();
				System.out.println(uuid);
			}catch(Exception e){
				e.printStackTrace();
				return "截图失败";
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "上传失败";
		}
		
		return "redirect:teacher";
	}
}
