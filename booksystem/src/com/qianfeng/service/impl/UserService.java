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
	
	
	//登录
	@Override
	public void login(String name, String password) {
		// TODO Auto-generated method stub
		if (name == null || name.isEmpty()) {
			throw new RuntimeException("用户名为空");
		}
		if (password == null || password.isEmpty()) {
			throw new RuntimeException("密码为空");
		}
		User u = new User();
		u.setUserName(name);
		u.setPassword(password);
		
		User user=null;
		// 输错多次锁定修改部分
		try {
			user = userDao.findUser(name);//先找用户
			if (user == null) {
				throw new RuntimeException("用户不存在");
			} else {
				// user存在
				if (userDao.IsLock(user).getIsLock() == 1) {//判断是否锁定
					throw new RuntimeException("用户已被锁定");
				} else {
					User userp = userDao.findInfo(u);
					if (userp == null) {//更改数据端不能放在此处，程序拿到异常会回滚
										//导致事务无法提交，因而数据库不能更新
										//因此把判断放入控制层进行
						// userp未找到
						
						throw new RuntimeException("密码错误");
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			userDao.failLog(user.getUserName());
//			if (userDao.GetFailNum(user).getFailure_num() == 3) {
//				userDao.LockUser(user.getUserName());
//				//throw new RuntimeException("输错三次密码用户已锁定");
//			}
			throw e;
		}

	}
	
	//封装一系列方法用于在控制层判断
	public void lockuser(String name){//锁定用户方法
		userDao.LockUser(name);
	}
	
	public void failnumadd(String name){//增加失败次数方法
		userDao.failLog(name);
	}
	
	public int getUserFailNum(String name){//得到失败次数方法，用于判断是否锁定用户
		User user=userDao.findUser(name);
		return userDao.GetFailNum(user).getFailure_num();
	}
	

	
	
	//注册
	@Override
	public void register(String name, String password, String passwordr, String email) {
		// TODO Auto-generated method stub
		if (name == null || name.isEmpty()) {
			throw new RuntimeException("用户名为空");
		}
		if (password == null || password.isEmpty()) {
			throw new RuntimeException("密码为空");
		}
		if (email == null || email.isEmpty()) {
			throw new RuntimeException("邮箱为空");
		}
		if (passwordr == null || passwordr.isEmpty()) {
			throw new RuntimeException("请再次输入密码");
		}
		
		User u = new User();
		u.setUserName(name);
		u.setPassword(password);
		u.setEmail(email);
		User user=null;
		
		try {
			user=userDao.findUser(name);
			
			if(user!=null){
				throw new RuntimeException("用户名已存在");
			}else{
				userDao.addUser(u);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	
	
	//查找
	//@SuppressWarnings("finally")
	@Override
	public User findUser(String name) {
		// TODO Auto-generated method stub
		User user=null;
		
		try {
			user=userDao.findUser(name);
			
			if(user==null){
				throw new RuntimeException("用户不存在");
			}
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		}
		
		
	}
	//删除
	@Override
	public void delUser(Integer id) {
		// TODO Auto-generated method stub

		User user = null;
		user=userDao.findUserById(id);
		String name=user.getUserName();
		try {
			user=userDao.findUser(name);
			
			if(user==null){
				throw new RuntimeException("用户不存在");
			}else{
				userDao.delUser(name);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	
	//解锁
	@Override
	public void relockUser(Integer id) {
		// TODO Auto-generated method stub
		User user =null;
		user=userDao.findUserById(id);
		String name=user.getUserName();
		try {
			user=userDao.findUser(name);
			
			if(user==null){
				throw new RuntimeException("用户不存在");
			}else if(userDao.findUser(name).getIsLock()==0){
				throw new RuntimeException("用户未锁定");
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
