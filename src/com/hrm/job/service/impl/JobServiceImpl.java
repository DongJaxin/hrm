package com.hrm.job.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.commons.beans.Job;
import com.hrm.job.dao.IJobDao;
import com.hrm.job.service.IJobService;
import com.hrm.utils.PageModel;

@Service
public class JobServiceImpl implements IJobService {

	@Autowired
	private IJobDao jobDao;
	@Override
	public List<Job> findJob(String name, PageModel pageModel) {
		HashMap<Object, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("start", pageModel.getFirstLimitParam());
		map.put("pageSize", pageModel.getPageSize());
		return jobDao.selectJob(map);
	}
	@Override
	public int findJobCount(String name) {
		return jobDao.selectJobCount(name);
	}
	@Override
	public int addJob(Job job) {
		return jobDao.insertJob(job);
	}
	@Override
	public Job findJobById(int id) {
		return jobDao.selectJobById(id);
	}
	@Override
	public int modifyJob(Job job) {
		return jobDao.updateJob(job);
	}
	@Override
	public int removeJob(Integer id) {
		return jobDao.deleteJob(id);
	}
	@Override
	public int removeJobByChoose(Integer[] ids) {
		return jobDao.deleteJobByChoose(ids);
	}

}
