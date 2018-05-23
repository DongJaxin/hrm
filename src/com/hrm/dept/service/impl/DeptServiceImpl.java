package com.hrm.dept.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.commons.beans.Dept;
import com.hrm.dept.dao.IDeptDao;
import com.hrm.dept.service.IDeptService;
import com.hrm.utils.PageModel;

@Service
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private IDeptDao deptDao;
	

	@Override
	public List<Dept> selectDept(String name, PageModel pageModel) {
		Map<Object, Object> map = new HashMap<>();
		map.put("name", name);
		//System.out.println("name = "+map.get("name"));
		map.put("start", pageModel.getFirstLimitParam());
		map.put("pageSize", pageModel.getPageSize());
		return deptDao.selectDept(map);
	}


	@Override
	public int selectDeptCount(String name) {
		return deptDao.selectDeptCount(name);
	}


	@Override
	public int addDept(Dept dept) {
		return deptDao.insertDept(dept);
	}


	@Override
	public Dept findDeptById(Integer id) {
		return deptDao.selectDeptById(id);
	}


	@Override
	public int modifyDept(Dept dept) {
		return deptDao.updateDept(dept);
	}


	@Override
	public int removeDept(Integer id) {
		// TODO Auto-generated method stub
		return deptDao.deleteDept(id);
	}


	@Override
	public int RemoveDeptByChoose(Integer[] ids) {
		
		return deptDao.deleteDeptByChoose(ids);
	}

}
