package com.qianfeng.service;

import java.util.List;

public interface IOrderService {
	//添加订单
	public void addOrderInfo(String[] ids, String[] nums, Double price, String username);
	
	//按页查订单明细
	
}
