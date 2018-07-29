package com.qianfeng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.Book;
import com.qianfeng.service.IBookService;
import com.qianfeng.vo.JsonBean;
@Controller
public class BookController {

	@Autowired
	private IBookService bookService;
	
	@RequestMapping(value="/book",method=RequestMethod.POST)
	public @ResponseBody JsonBean FindBook(Integer page){
		JsonBean bean = new JsonBean();
		//System.out.println(apage);
		//Integer apage=Integer.valueOf(page);
		try {
			List<Book> list = bookService.findBook(page);
			//System.out.println(page);
			bean.setCode(1);
			bean.setMsg(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	@RequestMapping(value="/selectbook",method=RequestMethod.POST)
	public @ResponseBody JsonBean selectBook(String selectbookname){
		JsonBean bean = new JsonBean();
		//System.out.println(apage);
		//Integer apage=Integer.valueOf(page);
		try {
			Book book = bookService.findBookByName(selectbookname);
			//System.out.println(page);
			bean.setCode(1);
			bean.setMsg(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	
	//增加书
	@RequestMapping(value="/addbook",method=RequestMethod.POST)
	public @ResponseBody JsonBean AddBook(String addbookname,float addbookprice,int addbookstore){
		JsonBean bean = new JsonBean();
		
		
		try {
			bookService.addBook(addbookname, addbookprice, addbookstore);
			
			bean.setCode(1);
			bean.setMsg("增加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		
		
		
		return bean;
	}
	
	
	//删除书
	@RequestMapping(value="/removebook",method=RequestMethod.POST)
	public @ResponseBody JsonBean RemoveBook(String removebookname){
		JsonBean bean = new JsonBean();
		
		System.out.println(removebookname);
		try {
			bookService.removeBook(removebookname);
			
			bean.setCode(1);
			bean.setMsg("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
	
	
	//修改书
	@RequestMapping(value="/modifybook",method=RequestMethod.POST)
	public @ResponseBody JsonBean ModifyBook(String modifiedbookname,String modifybookname,float modifybookprice,int modifybookstore){
		JsonBean bean = new JsonBean();
		
		try {
			bookService.modifyBook(modifiedbookname, modifybookname, modifybookprice, modifybookstore);
			
			bean.setCode(1);
			bean.setMsg("修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.getMessage());
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
}
