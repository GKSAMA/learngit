package com.qianfeng.dao;

import java.util.List;

import com.qianfeng.entity.Book;

public interface IBookDao {
	public List<Book> findBookByPage(Integer page);
	
	public Book findBookByName(String name);
	
	public void ADDBook(Book book);
	public void RemoveBook(Book book);
	public void ModifyBook(Book book);
	public int IsDelete(Book book);
	
	public List<Book> findByIds(String[] ids);
	
}
