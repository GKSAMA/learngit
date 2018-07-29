package com.qianfeng.service;

import com.qianfeng.entity.User;

public interface IUserService {
	
	
	//进行登陆判断
	public void login(String name ,String password);
	public void lockuser(String name);
	public void failnumadd(String name);
	public int getUserFailNum(String name);
	
	//进行注册判断
	public void register(String name,String password,String passwordr,String email);
	
	//查删
	public User findUser(String name);
	public void delUser(Integer id);
	//解锁
	public void relockUser(Integer id);
}
