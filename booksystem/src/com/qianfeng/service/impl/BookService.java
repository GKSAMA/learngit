package com.qianfeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IBookDao;
import com.qianfeng.entity.Book;
import com.qianfeng.service.IBookService;
@Service
public class BookService implements IBookService{
	
	@Autowired
	private IBookDao bookDao;
	
	@SuppressWarnings("finally")
	@Override
	public List<Book> findBook(Integer page) {
		// TODO Auto-generated method stub
		
		int num=page*5;
		if(num>10){
			throw new RuntimeException("没有更多商品了");
		}
		
		List<Book> list = null;
		
		try {
			list = bookDao.findBookByPage(page);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}finally{
			return list;
		}
	}
	
	
	
	//加书
	@Override
	public void addBook(String name,float price,int store){
		// TODO Auto-generated method stub
		Book book = new Book();
		Book b=new Book();
		b.setB_name(name);
		b.setB_price(price);
		b.setB_store(store);
		try {
			book=bookDao.findBookByName(name);
			
			if(book==null){
				bookDao.ADDBook(b);
				
			}else{
				if(bookDao.IsDelete(book)==1){
					throw new RuntimeException("该书籍即将被删除，不可添加");
				}
				throw new RuntimeException("书籍已存在");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	//删书
	@Override
	public void removeBook(String name) {
		// TODO Auto-generated method stub
		Book book =new Book();
		Book b=new Book();
		b.setB_name(name);
		
		System.out.println(name);
		
		try {
			book=bookDao.findBookByName(name);
			
			if(book==null||bookDao.IsDelete(book)==1){
				throw new RuntimeException("书籍不存在或已被删除");
			}else{
				bookDao.RemoveBook(b);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	//改书
	@Override
	public void modifyBook(String mname, String name, float price, int store) {
		// TODO Auto-generated method stub
		Book book = new Book();
		Book b=new Book();
		b.setB_name(name);
		b.setB_price(price);
		b.setB_store(store);
		
		try {
			book=bookDao.findBookByName(mname);
			
			if(book==null||bookDao.IsDelete(book)==1){
				throw new RuntimeException("书籍不存在或已被删除");
			}else{
				bookDao.ModifyBook(b);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}


	//找书
	@Override
	public Book findBookByName(String name) {
		// TODO Auto-generated method stub
		Book book=new Book();
		
		try {
			book=bookDao.findBookByName(name);
			
			if(book==null||bookDao.IsDelete(book)==1){
				throw new RuntimeException("图书不存在或已被删除");
			}
			return book;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		}
	}
	
	
}
