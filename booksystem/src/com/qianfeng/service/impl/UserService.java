package com.qianfeng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IUserDao;
import com.qianfeng.entity.User;
import com.qianfeng.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	
	//��¼
	@Override
	public void login(String name, String password) {
		// TODO Auto-generated method stub
		if (name == null || name.isEmpty()) {
			throw new RuntimeException("�û���Ϊ��");
		}
		if (password == null || password.isEmpty()) {
			throw new RuntimeException("����Ϊ��");
		}
		User u = new User();
		u.setUserName(name);
		u.setPassword(password);
		
		User user=null;
		// ����������޸Ĳ���
		try {
			user = userDao.findUser(name);//�����û�
			if (user == null) {
				throw new RuntimeException("�û�������");
			} else {
				// user����
				if (userDao.IsLock(user).getIsLock() == 1) {//�ж��Ƿ�����
					throw new RuntimeException("�û��ѱ�����");
				} else {
					User userp = userDao.findInfo(u);
					if (userp == null) {//�������ݶ˲��ܷ��ڴ˴��������õ��쳣��ع�
										//���������޷��ύ��������ݿⲻ�ܸ���
										//��˰��жϷ�����Ʋ����
						// userpδ�ҵ�
						
						throw new RuntimeException("�������");
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			userDao.failLog(user.getUserName());
//			if (userDao.GetFailNum(user).getFailure_num() == 3) {
//				userDao.LockUser(user.getUserName());
//				//throw new RuntimeException("������������û�������");
//			}
			throw e;
		}

	}
	
	//��װһϵ�з��������ڿ��Ʋ��ж�
	public void lockuser(String name){//�����û�����
		userDao.LockUser(name);
	}
	
	public void failnumadd(String name){//����ʧ�ܴ�������
		userDao.failLog(name);
	}
	
	public int getUserFailNum(String name){//�õ�ʧ�ܴ��������������ж��Ƿ������û�
		User user=userDao.findUser(name);
		return userDao.GetFailNum(user).getFailure_num();
	}
	

	
	
	//ע��
	@Override
	public void register(String name, String password, String passwordr, String email) {
		// TODO Auto-generated method stub
		if (name == null || name.isEmpty()) {
			throw new RuntimeException("�û���Ϊ��");
		}
		if (password == null || password.isEmpty()) {
			throw new RuntimeException("����Ϊ��");
		}
		if (email == null || email.isEmpty()) {
			throw new RuntimeException("����Ϊ��");
		}
		if (passwordr == null || passwordr.isEmpty()) {
			throw new RuntimeException("���ٴ���������");
		}
		
		User u = new User();
		u.setUserName(name);
		u.setPassword(password);
		u.setEmail(email);
		User user=null;
		
		try {
			user=userDao.findUser(name);
			
			if(user!=null){
				throw new RuntimeException("�û����Ѵ���");
			}else{
				userDao.addUser(u);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	
	
	//����
	//@SuppressWarnings("finally")
	@Override
	public User findUser(String name) {
		// TODO Auto-generated method stub
		User user=null;
		
		try {
			user=userDao.findUser(name);
			
			if(user==null){
				throw new RuntimeException("�û�������");
			}
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		}
		
		
	}
	//ɾ��
	@Override
	public void delUser(Integer id) {
		// TODO Auto-generated method stub

		User user = null;
		user=userDao.findUserById(id);
		String name=user.getUserName();
		try {
			user=userDao.findUser(name);
			
			if(user==null){
				throw new RuntimeException("�û�������");
			}else{
				userDao.delUser(name);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	
	//����
	@Override
	public void relockUser(Integer id) {
		// TODO Auto-generated method stub
		User user =null;
		user=userDao.findUserById(id);
		String name=user.getUserName();
		try {
			user=userDao.findUser(name);
			
			if(user==null){
				throw new RuntimeException("�û�������");
			}else if(userDao.findUser(name).getIsLock()==0){
				throw new RuntimeException("�û�δ����");
			}else{
				userDao.RelockUser(name);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	
}
