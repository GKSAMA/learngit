package com.qianfeng.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.qianfeng.dao.IUserDao;
import com.qianfeng.entity.User;
import com.qianfeng.service.IUserService;
import com.qianfeng.vo.JsonBean;
import com.sun.net.httpserver.HttpContext;

@Controller
//@SessionAttributes({"User","Y"})			//用于创建session对象
public class LoginController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value="/login",method=RequestMethod.POST)
	//@ResponseBody将返回的对象自动转换为json格式的字符串
	public @ResponseBody JsonBean login(@RequestParam("userName") String username,  @RequestParam("passWord") String password, HttpSession session, HttpServletResponse response){
		
		
		JsonBean bean=new JsonBean();
		//HttpContext.Response.AddHeader("Access-Control-Allow-Credentials", "true");
        //HttpContext.Response.AppendHeader("Access-Control-Allow-Origin", "请求本Action的域名");
		try {
			userService.login(username, password);
			bean.setCode(1);
			if(username.equals("adminer")||password.equals("adminer")){
				bean.setCode(2);
			}
			//存session
			session.setAttribute("loginname", username);
			String sessionId = session.getId();
			Cookie cookie = new Cookie("JSESSIONID", sessionId);
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//IUserDao userDao;
			User u = new User();
			u.setUserName(username);
			u.setPassword(password);
			if(e.getMessage()=="密码错误"){
				userService.failnumadd(username);
				bean.setMsg(e.getMessage());
				
				bean.setCode(0);
			}
			if(userService.getUserFailNum(username)==3){
				userService.lockuser(username);
				bean.setMsg("输错三次密码，账户已锁定");
				
				bean.setCode(0);
			}
		}
		return bean;
		
		
	}
	
	//注销清空session
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "login";
	}
	
	
}
