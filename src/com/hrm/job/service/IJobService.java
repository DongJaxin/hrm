package com.hrm.job.service;

import java.util.List;

import com.hrm.commons.beans.Job;
import com.hrm.utils.PageModel;

public interface IJobService {

	List<Job> findJob(String name, PageModel pageModel);

	int findJobCount(String name);

	int addJob(Job job);

	Job findJobById(int id);

	int modifyJob(Job job);

	int removeJob(Integer id);

	int removeJobByChoose(Integer[] ids);

}
