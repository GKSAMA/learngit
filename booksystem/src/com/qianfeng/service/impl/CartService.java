package com.qianfeng.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.qianfeng.dao.IBookDao;
import com.qianfeng.entity.Book;
import com.qianfeng.service.ICartService;
@Service
public class CartService implements ICartService {

	@Autowired
	private IBookDao bookDao;
	
	@Override
	public String addCart(String[] bookIds, String cartId) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		if(!StringUtils.isNullOrEmpty(cartId)){
			String[] split = cartId.split(",");
			for(String v : split){
				list.add(v);
			}
			
		}
		if(bookIds == null || bookIds.length == 0){
			throw new RuntimeException("请选择图书");
		}
		// 1,2,3
		String info = "";
		for(int i = 0; i < bookIds.length; i++){
			if(!list.contains(bookIds[i])){
				list.add(bookIds[i]);
			}
		}
		
		for(String v : list){
			info += v + ",";
		}
		info = info.substring(0, info.length() - 1);
		
		return info;
	}

	@Override
	public List<Book> findCartInfo(String ids) {
		// TODO Auto-generated method stub
		
		if(StringUtils.isNullOrEmpty(ids)){
			throw new RuntimeException("购物车为空");
		}
		try {
			
			List<String> list = new ArrayList<>();
			String[] split = ids.split(",");
			for (String info : split) {
				list.add(info);
			}
			List<Book> books = bookDao.findByIds(split);
			return books;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
}
