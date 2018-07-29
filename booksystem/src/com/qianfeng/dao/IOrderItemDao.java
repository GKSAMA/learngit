package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.OrderItem;

public interface IOrderItemDao {
	public List<OrderItem> findByIndex(Map<String, Object> info);
	
	public void add(OrderItem item);
}
