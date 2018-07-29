package com.qianfeng.dao;

import com.qianfeng.entity.User;

public interface IUserDao {
	//登录
	public User findInfo(User user);//判断用户名密码是否匹配
	
	//public User findInfo(String name,String password);
	
	public User findUser(String name);//根据名字找用户
	public User findUserById(Integer id);//根据id找用户
	
	public void failLog(String username);//失败次数加一
	public User GetFailNum(User user);//得到失败次数
	public void LockUser(String username);//锁定用户
	public User IsLock(User user);//用户是否锁定
	
	
	//注册
	public void addUser(User user);//增加用户
	
	//删除用户
	public void delUser(String name);
	//解锁
	public void RelockUser(String name);
}
