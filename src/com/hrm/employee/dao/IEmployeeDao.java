package com.hrm.employee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hrm.commons.beans.Dept;
import com.hrm.commons.beans.Employee;
import com.hrm.commons.beans.Job;

public interface IEmployeeDao {

	List<Employee> selectEmployee(Map<Object, Object> map);

	int selectEmployeeCount(Employee employee);

	List<Job> selectAllJob();

	List<Dept> selectAllDept();

	int insertEmployee(Employee employee);

	Employee selectEmployeeById(Integer id);

	int updateEmployee(Employee employee);

	int deleteEmployee(@Param("ids")Integer[] ids);

}
