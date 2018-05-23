package com.hrm.employee.service;

import java.util.List;

import com.hrm.commons.beans.Dept;
import com.hrm.commons.beans.Employee;
import com.hrm.commons.beans.Job;
import com.hrm.utils.PageModel;

public interface IEmployeeService {

	List<Job> findAllJob();

	List<Dept> findAllDept();

	List<Employee> findEmployee(Employee employee, PageModel pageModel);

	int findEmployeeCount(Employee employee);

	int addEmployee(Employee employee);

	Employee findEmployeeById(Integer id);

	int modifyEmployee(Employee employee);

	int removeEmployee(Integer[] ids);

}
