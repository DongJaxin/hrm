package com.hrm.dept.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrm.commons.beans.Dept;
import com.hrm.dept.service.IDeptService;
import com.hrm.utils.PageModel;

@Controller
@RequestMapping("/dept")
public class DeptHandler {
	
	@Autowired
	private IDeptService deptService;
	
	@RequestMapping("/selectDept.do")
	public String selectDept(@RequestParam(defaultValue="1")Integer pageIndex,String name,Model model){
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		
		int count = deptService.selectDeptCount(name);
		pageModel.setRecordCount(count);
		//System.out.println(pageModel.getRecordCount());
		List<Dept> depts = deptService.selectDept(name,pageModel);
		
		model.addAttribute("name", name);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel",pageModel);
		return "/jsp/dept/dept.jsp";
		
	}
	
	@RequestMapping("/addDept.do")
	@ResponseBody
	public String addDept(Dept dept){
		
		int rows = deptService.addDept(dept);
		//System.out.println(rows);
		if(rows > 0){
			return "OK";
		}else{
			return "FAIL";
		}
		
	}
	
	@RequestMapping("/findDeptById.do")
	public String findDeptById(Integer pageIndex,Integer id,Model model){
		Dept target = deptService.findDeptById(id);
		model.addAttribute("dept", target);
		model.addAttribute("pageIndex", pageIndex);
		return "/jsp/dept/showUpdateDept.jsp";
		
	}
	
	@RequestMapping("/modifyDept.do")
	@ResponseBody
	public String modifyDept(Dept dept){
		
		int rows = deptService.modifyDept(dept);
		//System.out.println(rows);
		if(rows > 0){
			return "OK";
		}else{
			return "FAIL";
		}
		
	}
	@RequestMapping("/removeDept.do")
	@ResponseBody
	public String removeDept(Integer[] ids){
		try {
			int rows = deptService.RemoveDeptByChoose(ids);
			if(rows > 0){
				return "OK";
			}else{
				return "FAIL";
			}
			
		} catch (DataIntegrityViolationException e) {
			//System.out.println(e);
			return "ERROR";
		}
		/*int rows = 0;
		for(Integer id:ids){
			try {
				int i = deptService.removeDept(id);
				if(i > 0){
					rows++;
				}
			} catch (Exception e) {
				System.out.println(e);
				return "ERROR";
			}
		}
		System.out.println(rows);
		if(rows == ids.length){
			return "OK";
		}else{
			return "FAIL";
		}*/
		
	}
}
