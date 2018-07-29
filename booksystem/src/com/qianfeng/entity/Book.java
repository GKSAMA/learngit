package com.qianfeng.entity;

public class Book {
	private Integer b_id;
	private String b_name;
	private float b_price;
	private Integer b_store;
	private Integer del;
	
	public Integer getDel() {
		return del;
	}
	public void setDel(Integer del) {
		this.del = del;
	}
	public void setB_store(Integer b_store) {
		this.b_store = b_store;
	}
	public Integer getB_id() {
		return b_id;
	}
	public void setB_id(Integer b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public float getB_price() {
		return b_price;
	}
	public void setB_price(float b_price) {
		this.b_price = b_price;
	}
	public Integer getB_store() {
		return b_store;
	}
	public void setB_store(int b_store) {
		this.b_store = b_store;
	}
	
	
}
