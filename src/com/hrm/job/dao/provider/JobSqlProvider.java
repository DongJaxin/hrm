package com.hrm.job.dao.provider;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.hrm.commons.beans.Job;

public class JobSqlProvider {

	public String selectJob(HashMap<Object, Object> map){
		String sql = new SQL(){
			{
				this.SELECT("*");
				this.FROM("job_inf");
				if(map.get("name") != null && !map.get("name").equals("")){
					this.WHERE(" name like '%' #{name} '%'");
				}
			}
		}.toString();
		sql += " limit #{start},#{pageSize}";
		//System.out.println(sql);
		return sql;
	}
	
	public String selectJobCount(String name){
		return new SQL(){
			{
				this.SELECT("count(*)");
				
				this.FROM("job_inf");
				if(name != null && !name.equals("")){
					this.WHERE(" name like '%' #{name} '%'");
				}
			}
			
		}.toString();
	}
	
	public String insertJob(Job job){
		return new SQL(){
			{
				this.INSERT_INTO("job_inf");
				if(job.getName()!=null && !job.getName().equals("")){
					this.VALUES("name", "#{name}");
				}
				if(job.getRemark()!=null && !job.getRemark().equals("")){
					this.VALUES("remark", "#{remark}");
				}
			}
		}.toString();
	}
	
	public String deleteJobByChoose(Map map){
		Integer[] ids = (Integer[]) map.get("ids");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("delete from job_inf");
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
