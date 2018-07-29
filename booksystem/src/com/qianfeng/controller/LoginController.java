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
//@SessionAttributes({"User","Y"})			//���ڴ���session����
public class LoginController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value="/login",method=RequestMethod.POST)
	//@ResponseBody�����صĶ����Զ�ת��Ϊjson��ʽ���ַ���
	public @ResponseBody JsonBean login(@RequestParam("userName") String username,  @RequestParam("passWord") String password, HttpSession session, HttpServletResponse response){
		
		
		JsonBean bean=new JsonBean();
		//HttpContext.Response.AddHeader("Access-Control-Allow-Credentials", "true");
        //HttpContext.Response.AppendHeader("Access-Control-Allow-Origin", "����Action������");
		try {
			userService.login(username, password);
			bean.setCode(1);
			if(username.equals("adminer")||password.equals("adminer")){
				bean.setCode(2);
			}
			//��session
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
			if(e.getMessage()=="�������"){
				userService.failnumadd(username);
				bean.setMsg(e.getMessage());
				
				bean.setCode(0);
			}
			if(userService.getUserFailNum(username)==3){
				userService.lockuser(username);
				bean.setMsg("����������룬�˻�������");
				
				bean.setCode(0);
			}
		}
		return bean;
		
		
	}
	
	//ע�����session
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "login";
	}
	
	
}
