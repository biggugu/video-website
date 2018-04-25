package com.thirdtest.thirddemo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thirdtest.thirddemo.entity.UEntity;

@Controller
public class LoginController {
	//跳转到登录表单页面
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	/**
	 * ajax登录请求
	 * @param username
	 * @param password
	 * @return 
	 * @return 
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	
	public String submitLogin(String username, String password,String vcode, Model model) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		
		if(vcode==null||vcode==""){
	        return "验证码不能为空！";
	    }
		
		Session session=SecurityUtils.getSubject().getSession();
		//转成小写
		vcode=vcode.toLowerCase();
		String vString=(String)session.getAttribute("_code");
		//清空验证码
		session.removeAttribute("_code");
		if(!vcode.equals(vString)) {
			return "验证码错误";
		}
		
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			SecurityUtils.getSubject().login(token);
			UEntity uEntity = (UEntity) SecurityUtils.getSubject().getPrincipal();
			if(uEntity.getId_role()==1) {
				return "redirect:admin";
			}
			else if(uEntity.getId_role()==2){
				return "redirect:teacher";
			}
			else {
				return "redirect:student";
			}
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", e.getMessage());
			return "redirect:403";
		}
		
	}
	
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)  
    public String logout(RedirectAttributes redirectAttributes ){  
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
        SecurityUtils.getSubject().logout();  
        redirectAttributes.addFlashAttribute("message", "您已安全退出");  
        return "redirect:login";  
    }  
  
    @RequestMapping("/403")  
    public String unauthorizedRole(){  
        System.out.println("------没有权限-------");  
        return "403";  
    }  
    
    @RequestMapping("/admin")  
    public String adminpage(){  
        return "admin";  
    }
    
    @RequestMapping("/file")  
    public String videopage(HttpServletResponse response){  
        return "file";
    }
    
    @RequestMapping("/test")  
    public void testpage(HttpServletResponse response){
    	
    	 response.setHeader("Content-Type", "video/mp4");
    	 //response.setHeader("Content-Disposition", "attachment; filename=1.mp4");
    	response.setHeader("X-Accel-Redirect", "/5.mp4");
        System.out.println("test easyui");  
          
    }
    
    @RequestMapping("/teacher")  
    public String teacherpage(HttpServletResponse response){  
        return "teacher";
    }
    
    @RequestMapping("/student")  
    public String studentpage(HttpServletResponse response){  
        return "student";
    }
    
    @RequestMapping("/jwplayer")  
    public String jwplayerpage(HttpServletResponse response){  
        return "jwplayertest";
    }
    
}
