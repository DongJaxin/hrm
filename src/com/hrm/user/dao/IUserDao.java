package com.hrm.user.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrm.commons.beans.User;

public interface IUserDao {
	
	User selectUserByLoginnameAndPassword(User user);

	List<User> selectUser(HashMap<Object, Object> map);

	int selectUserCount(User user);

	int insertUser(User user);

	User selectUserById(Integer id);

	int updateUser(User user);

	int deleteUser(int parseInt);

	int deleteUserById(@Param("iDS")List<Integer> iDS);

}
