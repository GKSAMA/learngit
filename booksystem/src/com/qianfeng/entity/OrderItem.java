package com.qianfeng.entity;

public class OrderItem {
	private Integer id;
	//private Integer orderId;
	private Order order;
	private Book book;
	private Integer num;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Order getOrders() {
		return order;
	}
	public void setOrders(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
}
