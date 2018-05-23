package com.hrm.dept.dao.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.hrm.commons.beans.Dept;


public class DeptSqlProvider {
	public String selectWithParam(Map<Object, Object> map){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dept_inf");
		if(map.get("name") != null && !map.get("name").equals("")){
			sql.append(" where name like '%' #{name} '%'");
		}
		if(map.get("start")!=null&&map.get("pageSize")!=null){
			sql.append(" limit #{start},#{pageSize}");
		}
		//System.out.println(sql.toString());
		return sql.toString();
		
	}
	
	public String selectDeptCount(String name){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from dept_inf");
		if(name != null && !name.equals("")){
			sql.append(" where name like '%' #{name} '%'");
		}
		
		return sql.toString();
		
	}
	public String insertDept(Dept dept){
		String sql = new SQL(){
			{
				this.INSERT_INTO("dept_inf");
				if(dept.getName()!=null && !dept.getName().equals("")){
					this.VALUES("name", "#{name}");
				}
				if(dept.getRemark()!=null && !dept.getRemark().equals("")){
					this.VALUES("remark", "#{remark}");
				}
			}
		}.toString();
		//System.out.println("插入："+sql);
		return sql;
	}
	
	public String deleteDeptByChoose(Map map){
		
		Integer[] ids = (Integer[]) map.get("ids");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("delete from dept_inf");
		sql.append(" where id in (");
		for(Integer id:ids){
			sql.append(id+",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		
		
		//System.out.println(sql);
		return sql.toString();
	}
	
}
