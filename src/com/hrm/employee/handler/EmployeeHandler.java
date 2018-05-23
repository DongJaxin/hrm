package com.hrm.employee.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrm.commons.beans.Dept;
import com.hrm.commons.beans.Employee;
import com.hrm.commons.beans.Job;
import com.hrm.employee.service.IEmployeeService;
import com.hrm.utils.PageModel;

@Controller
@RequestMapping("/employee")
public class EmployeeHandler {
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping("/findEmployee.do")
	public String findEmployee(@RequestParam(defaultValue="1")Integer pageIndex,Integer job_id,Integer dept_id,Employee employee,Model model){
		this.assocation(job_id, dept_id, employee);
		
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		
		List<Job> jobs = employeeService.findAllJob();
		List<Dept> depts = employeeService.findAllDept();
		
		int count = employeeService.findEmployeeCount(employee);
		pageModel.setRecordCount(count);
		List<Employee> employees = employeeService.findEmployee(employee,pageModel);
		
		model.addAttribute("employee", employee);
		model.addAttribute("job_id", job_id);
		model.addAttribute("dept_id", dept_id);
		model.addAttribute("employees", employees);
		model.addAttribute("depts", depts);
		model.addAttribute("jobs", jobs);
		model.addAttribute("pageModel", pageModel);
		
		return "/jsp/employee/employee.jsp";
	}
	
	@RequestMapping("/addEmployee.do")
	public String addEmployee(String flag,Integer job_id,Integer dept_id,Employee employee,Model model){
		//System.out.println(employee);
		//return "/jsp/employee/showAddEmployee.jsp";
		if(flag.equals("1")){
			List<Job> jobs = employeeService.findAllJob();
			List<Dept> depts = employeeService.findAllDept();
			model.addAttribute("depts", depts);
			model.addAttribute("jobs", jobs);
			return "/jsp/employee/showAddEmployee.jsp";
		}else{
			this.assocation(job_id, dept_id, employee);
			int rows = employeeService.addEmployee(employee);
			if(rows > 0){
				return "redirect:/employee/findEmployee.do";
			}else{
				model.addAttribute("employee", "employee");
				return "/jsp/employee/showAddEmployee.jsp";
			}
		}
		
	}
	
	@RequestMapping("/modifyEmployee.do")
	public String modifyEmployee(String flag,Integer job_id,Integer dept_id,Employee employee,Model model){
		if(flag.equals("1")){
			List<Job> jobs = employeeService.findAllJob();
			List<Dept> depts = employeeService.findAllDept();
			Employee target = employeeService.findEmployeeById(employee.getId());
			//System.out.println(employee+"1");
			//System.out.println(target+"2");
			model.addAttribute("depts", depts);
			model.addAttribute("jobs", jobs);
			model.addAttribute("employee", target);
			return "/jsp/employee/showUpdateEmployee.jsp";
		}else{
			this.assocation(job_id, dept_id, employee);
			int rows = employeeService.modifyEmployee(employee);
			if(rows > 0){
				return "redirect:/employee/findEmployee.do";
			}else{
				model.addAttribute("employee", "employee");
				return "/jsp/employee/showAddEmployee.jsp";
			}
			
		}
		
	}
	
	@RequestMapping("removeEmployee.do")
	public String removeEmployee(Integer[] ids,Model model){
		
		System.out.println(ids[0]+"=handler");
		int rows = employeeService.removeEmployee(ids);
		if(rows > 0){
			return "redirect:/employee/findEmployee.do";
		}else{
			model.addAttribute("fail", "员工删除失败！");
			return "/jsp/fail.jsp";
		}
	}
	
	public void assocation(Integer job_id,Integer dept_id,Employee employee){
		if(job_id != null){
			Job job = new Job();
			job.setId(job_id);
			employee.setJob(job);
		}
		if(dept_id != null){
			Dept dept = new Dept();
			dept.setId(dept_id);
			employee.setDept(dept);
		}
	}
	
	
	
	
}
