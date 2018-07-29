package com.qianfeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.dao.IUserDao;
import com.qianfeng.entity.User;
import com.qianfeng.service.IUserService;
import com.qianfeng.vo.JsonBean;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value="/finduser",method=RequestMethod.POST)
	//@ResponseBody将返回的对象自动转换为json格式的字符串
	public @ResponseBody JsonBean FindUser(String findu){
		
		
		JsonBean bean=new JsonBean();
		User u=new User();
		
		try {
			u = userService.findUser(findu);
			
			bean.setCode(1);
			bean.setMsg(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
		
		
	}
	
	
	@RequestMapping("/deluser")
	//@ResponseBody将返回的对象自动转换为json格式的字符串
	public @ResponseBody JsonBean DelUser(Integer id){
		
		
		JsonBean bean=new JsonBean();
		User u=new User();
		
		try {
			userService.delUser(id);
			
			bean.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
		
		
	}
	
	@RequestMapping("/relockuser")
	//@ResponseBody将返回的对象自动转换为json格式的字符串
	public @ResponseBody JsonBean RelockUser(Integer id){
		
		
		JsonBean bean=new JsonBean();
		User u=new User();
		
		try {
			userService.relockUser(id);
			
			bean.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
		
		
	}
}
