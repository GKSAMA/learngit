package com.qianfeng.service;

import java.util.List;

import com.qianfeng.entity.Book;

public interface IBookService {
	//≤È—Ø È
	public List<Book> findBook(Integer page);
	public Book findBookByName(String name);
	
	public void addBook(String name,float price,int store);
	public void removeBook(String name);
	public void modifyBook(String mname,String name,float price,int store);
}
