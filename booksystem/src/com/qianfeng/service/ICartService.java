package com.qianfeng.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import com.qianfeng.entity.Book;

@Service
public interface ICartService {
	public String addCart(String[] bookIds, String cartId);
	
	// ��ѯ���ﳵ����ʾ������
	public List<Book> findCartInfo(String ids);
}
