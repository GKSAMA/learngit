package com.qianfeng.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.service.IOrderService;
import com.qianfeng.vo.JsonBean;
@Controller
public class OrderController {

	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping(value="/orders", method=RequestMethod.POST)
	public @ResponseBody JsonBean addOrder(String[] ids, String[] nums, Double totalPrice, HttpSession session, HttpServletResponse response){
		
		JsonBean bean = new JsonBean();
		System.out.println(ids[1]);
		System.out.println(nums);
		System.out.println(totalPrice);
		// 获取session对象中存放的用户名
		String name = (String)session.getAttribute("loginname");
		try {
			// 添加订单
			orderService.addOrderInfo(ids, nums, totalPrice, name);
			
			//清空购物车
			Cookie cookie = new Cookie("cartids", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			
			
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
