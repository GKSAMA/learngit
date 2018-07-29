package com.qianfeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.service.IUserService;
import com.qianfeng.vo.JsonBean;

@Controller
public class RegisterController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	//controller中的参数要和表单中的数据一致
	public @ResponseBody JsonBean Register(String userName,String passWord,String rePassWord,String email){
		JsonBean bean=new JsonBean();
		
		try {
			userService.register(userName, passWord, rePassWord, email);
			
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
