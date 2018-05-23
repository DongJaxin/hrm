package com.hrm.dept.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrm.commons.beans.Dept;
import com.hrm.dept.dao.provider.DeptSqlProvider;

public interface IDeptDao {

	@SelectProvider(method = "selectWithParam", type = DeptSqlProvider.class)
	List<Dept> selectDept(Map<Object, Object> map);

	@SelectProvider(method = "selectDeptCount", type = DeptSqlProvider.class)
	int selectDeptCount(String name);
	
	@InsertProvider(method = "insertDept", type = DeptSqlProvider.class)
	int insertDept(Dept dept);

	@Select("select * from dept_inf where id = #{id}")
	Dept selectDeptById(Integer id);

	@Update("update dept_inf set name=#{name},remark=#{remark} where id=#{id}")
	int updateDept(Dept dept);

	@Delete("delete from dept_inf where id=#{id}")
	int deleteDept(Integer id);

	@DeleteProvider(method = "deleteDeptByChoose", type = DeptSqlProvider.class)
	int deleteDeptByChoose(@Param("ids")Integer[] ids);

}
