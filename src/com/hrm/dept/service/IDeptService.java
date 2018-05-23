package com.hrm.dept.service;

import java.util.List;

import com.hrm.commons.beans.Dept;
import com.hrm.utils.PageModel;

public interface IDeptService {

	List<Dept> selectDept(String name, PageModel pageModel);

	int selectDeptCount(String name);

	int addDept(Dept dept);

	Dept findDeptById(Integer id);

	int modifyDept(Dept dept);

	int removeDept(Integer id);

	int RemoveDeptByChoose(Integer[] ids);

}
