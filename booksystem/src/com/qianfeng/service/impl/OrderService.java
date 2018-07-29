package com.qianfeng.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IOrderDao;
import com.qianfeng.dao.IOrderItemDao;
import com.qianfeng.dao.IUserDao;
import com.qianfeng.entity.Book;
import com.qianfeng.entity.Order;
import com.qianfeng.entity.OrderItem;
import com.qianfeng.entity.User;
import com.qianfeng.service.IOrderService;
@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IOrderItemDao itemDao;
	@Override
	public void addOrderInfo(String[] ids, String[] nums, Double price, String username) {
		// TODO Auto-generated method stub
		//添加订单
				Order order = new Order();
				order.setTotalPrice(price);
				order.setState(0);
				order.setCreateDate(new Date());
				//System.out.println(order.getTotalPrice());
				//System.out.println(order.getCreateDate());
				// 使用uuid作为订单编号
				order.setOrderNum(UUID.randomUUID().toString());
				//System.out.println(order.getOrderNum());
				try {
					User user = userDao.findUser(username);
					order.setBuyer(user);
					orderDao.add(order);
					//ids = null;
					this.addOrderItems(ids, nums, order);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw e;
				}
	}
	
	//订单明细
	private void addOrderItems(String[] ids, String[] nums, Order orders) {
		// TODO Auto-generated method stub
		if(ids == null || ids.length == 0){
			throw new RuntimeException("图书数据不存在");
		}
		if(nums == null || nums.length == 0){
			throw new RuntimeException("购买数量不存在");
		}
		if(orders == null){
			throw new RuntimeException("订单数据不存在");
		}
		
		try {
			for(int i = 0; i < ids.length; i++){
				OrderItem item = new OrderItem();
				Book book = new Book();
				book.setB_id(Integer.parseInt(ids[i]));
				item.setBook(book);
				item.setOrders(orders);
				item.setNum(Integer.parseInt(nums[i]));
				
				itemDao.add(item);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	
	
	
}
