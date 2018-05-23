package com.hrm.job.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.hrm.commons.beans.Job;
import com.hrm.job.dao.provider.JobSqlProvider;

public interface IJobDao {

	@SelectProvider(method = "selectJob", type = JobSqlProvider.class)
	List<Job> selectJob(HashMap<Object, Object> map);

	@SelectProvider(method = "selectJobCount", type = JobSqlProvider.class)
	int selectJobCount(String name);

	@InsertProvider(method = "insertJob", type = JobSqlProvider.class)
	int insertJob(Job job);
	
	@Select("select * from job_inf where id = #{id}")
	Job selectJobById(int id);

	@Update("update job_inf set name=#{name}, remark=#{remark} where id=#{id}")
	int updateJob(Job job);

	@Delete("delete from job_inf where id = #{id}")
	int deleteJob(Integer id);

	@DeleteProvider(method = "deleteJobByChoose", type = JobSqlProvider.class)
	int deleteJobByChoose(@Param("ids")Integer[] ids);

}
