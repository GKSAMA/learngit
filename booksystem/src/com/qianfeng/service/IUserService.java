package com.qianfeng.service;

import com.qianfeng.entity.User;

public interface IUserService {
	
	
	//���е�½�ж�
	public void login(String name ,String password);
	public void lockuser(String name);
	public void failnumadd(String name);
	public int getUserFailNum(String name);
	
	//����ע���ж�
	public void register(String name,String password,String passwordr,String email);
	
	//��ɾ
	public User findUser(String name);
	public void delUser(Integer id);
	//����
	public void relockUser(Integer id);
}
