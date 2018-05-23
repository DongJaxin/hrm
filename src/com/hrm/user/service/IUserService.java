package com.hrm.user.service;

import java.util.List;

import com.hrm.commons.beans.User;
import com.hrm.utils.PageModel;

public interface IUserService {

	User findUserByLoginnameAndPassword(User user);

	List<User> findUser(User user, PageModel pageModel);

	int FindUserCount(User user);

	int addUser(User user);

	User findUserById(Integer id);

	int modifyUser(User user);

	int removeUser(int parseInt);

	int removeUserById(List<Integer> iDS);

}
