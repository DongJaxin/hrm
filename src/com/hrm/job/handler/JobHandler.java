package com.hrm.job.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrm.commons.beans.Job;
import com.hrm.job.service.IJobService;
import com.hrm.utils.PageModel;

@Controller
@RequestMapping("/job")
public class JobHandler {

	@Autowired
	private IJobService jobService;
	
	@RequestMapping("/findJob.do")
	public String findJob(@RequestParam(defaultValue="1")Integer pageIndex,String name,Model model){
		
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		
		int count = jobService.findJobCount(name);
		pageModel.setRecordCount(count);
		List<Job> jobs = jobService.findJob(name,pageModel);
		
		model.addAttribute("name", name);
		model.addAttribute("jobs", jobs);
		model.addAttribute("pageModel", pageModel);
		return "/jsp/job/job.jsp";
		
	}
	
	@RequestMapping("/findJobById.do")
	public String findJobById(Integer pageIndex,int id,Model model){
		Job job = jobService.findJobById(id);
		model.addAttribute("job", job);
		model.addAttribute("pageIndex", pageIndex);
		return "/jsp/job/showUpdateJob.jsp";
		
	}
	
	@RequestMapping("/modifyJob.do")
	@ResponseBody
	public String modifyJob(Job job){
		int rows = jobService.modifyJob(job);
		if(rows > 0){
			return "OK";
		}else{
			return "FAIL";
		}
	}
	
	@RequestMapping("/addJob.do")
	@ResponseBody
	public String addJob(Job job){
		int rows = jobService.addJob(job);
		if(rows > 0){
			return "OK";
		}else{
			return "FAIL";
		}
	}
	
	@RequestMapping("/removeJob.do")
	@ResponseBody
	public String removeJob(Integer[] ids){
		try {
			int rows = jobService.removeJobByChoose(ids);
			if(rows > 0){
				return "OK";
			}else{
				return "FAIL";
			}
		} catch (DataIntegrityViolationException e) {
			//e.printStackTrace();
			//System.out.println(e);
			return "ERROR";
		}
	}
		/*int rows = 0;
		for(Integer id:ids){
			int i = jobService.removeJob(id);
			if(i > 0){
				rows++;
			}
		}
		if(rows == ids.length){
			return "OK";
		}else{
			return "FAIL";
		}
	}*/
}
