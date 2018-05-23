package com.hrm.employee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.commons.beans.Dept;
import com.hrm.commons.beans.Employee;
import com.hrm.commons.beans.Job;
import com.hrm.employee.dao.IEmployeeDao;
import com.hrm.employee.service.IEmployeeService;
import com.hrm.utils.PageModel;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;
	
	
	@Override
	public List<Job> findAllJob() {
		return employeeDao.selectAllJob();
	}

	@Override
	public List<Dept> findAllDept() {
		return employeeDao.selectAllDept();
	}


	@Override
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		Map<Object, Object> map = new HashMap<>();
		map.put("employee", employee);
		map.put("pageModel", pageModel);
		
		List<Employee> employees = employeeDao.selectEmployee(map);
		return employees;
	}

	@Override
	public int findEmployeeCount(Employee employee) {
		return employeeDao.selectEmployeeCount(employee);
	}

	@Override
	public int addEmployee(Employee employee) {
		return employeeDao.insertEmployee(employee);
	}

	@Override
	public Employee findEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return employeeDao.selectEmployeeById(id);
	}

	@Override
	public int modifyEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public int removeEmployee(Integer[] ids) {
		System.out.println(ids[0]+"=service");
		return employeeDao.deleteEmployee(ids);
	}

}
