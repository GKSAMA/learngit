package com.qianfeng.dao;

import com.qianfeng.entity.User;

public interface IUserDao {
	//��¼
	public User findInfo(User user);//�ж��û��������Ƿ�ƥ��
	
	//public User findInfo(String name,String password);
	
	public User findUser(String name);//�����������û�
	public User findUserById(Integer id);//����id���û�
	
	public void failLog(String username);//ʧ�ܴ�����һ
	public User GetFailNum(User user);//�õ�ʧ�ܴ���
	public void LockUser(String username);//�����û�
	public User IsLock(User user);//�û��Ƿ�����
	
	
	//ע��
	public void addUser(User user);//�����û�
	
	//ɾ���û�
	public void delUser(String name);
	//����
	public void RelockUser(String name);
}
