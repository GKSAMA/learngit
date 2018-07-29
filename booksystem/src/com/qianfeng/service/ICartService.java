package com.qianfeng.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import com.qianfeng.entity.Book;

@Service
public interface ICartService {
	public String addCart(String[] bookIds, String cartId);
	
	// 查询购物车中显示的数据
	public List<Book> findCartInfo(String ids);
}
