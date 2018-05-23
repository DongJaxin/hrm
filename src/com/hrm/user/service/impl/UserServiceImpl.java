package com.hrm.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.commons.beans.User;
import com.hrm.user.dao.IUserDao;
import com.hrm.user.service.IUserService;
import com.hrm.utils.PageModel;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Override
	public User findUserByLoginnameAndPassword(User user) {
		return userDao.selectUserByLoginnameAndPassword(user);
	}
	@Override
	public List<User> findUser(User user, PageModel pageModel) {
		HashMap<Object, Object> map = new HashMap<>();
		map.put("username", user.getUsername());
		map.put("status", user.getStatus());
		
		map.put("start", pageModel.getFirstLimitParam());
		map.put("pageSize", pageModel.getPageSize());
		
		List<User> users = userDao.selectUser(map);
		//System.out.println(users);
		
		return users;
	}
	@Override
	public int FindUserCount(User user) {
		return userDao.selectUserCount(user);
	}
	@Override
	public int addUser(User user) {
		return userDao.insertUser(user);
	}
	@Override
	public User findUserById(Integer id) {
		return userDao.selectUserById(id);
	}
	@Override
	public int modifyUser(User user) {
		return userDao.updateUser(user);
	}
	@Override
	public int removeUser(int parseInt) {
		return userDao.deleteUser(parseInt);
	}
	@Override
	public int removeUserById(List<Integer> iDS) {
		return userDao.deleteUserById(iDS);
	}

}
