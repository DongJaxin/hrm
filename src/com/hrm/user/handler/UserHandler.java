package com.hrm.user.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrm.commons.beans.User;
import com.hrm.user.service.IUserService;
import com.hrm.utils.PageModel;

@Controller
@RequestMapping("/user")
public class UserHandler {

	@Autowired
	private IUserService userService;
	@RequestMapping("/login.do")
	public String login(User user,HttpSession session,Model model){
		User login_user = userService.findUserByLoginnameAndPassword(user);
		//System.out.println(login_user);
		if(login_user != null){
			session.setAttribute("login_user", login_user);
			return "/jsp/main.jsp";
		}else{
			model.addAttribute("message", "账号或密码错误，请重新输入");
			return "/index.jsp";
		}
	}
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/findUser.do")
	public String findUser(@RequestParam(defaultValue="1")Integer pageIndex,
			User user,Model model){
		
		int count = userService.FindUserCount(user);
		
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setRecordCount(count);
		//System.out.println(pageModel.getPageIndex());
		//System.out.println(pageModel.getPageSize());
		
		List<User> users = userService.findUser(user,pageModel);
		
		model.addAttribute("user", user);
		model.addAttribute("users", users);
		model.addAttribute("pageModel", pageModel);
		return "/jsp/user/user.jsp";
		
	}
	
	@RequestMapping("/addUser.do")
	public String addUser(User user,Model model){
		Date date = new Date();
		user.setCreatedate(date);
		
		int rows = userService.addUser(user);
		if(rows > 0){
			return "redirect:/user/findUser.do";
		}else{
			model.addAttribute("fail", "添加用户失败！");
			return "/jsp/fail.jsp";
		}
	}
	
	@RequestMapping("/findUserById.do")
	public String findUserById(Integer id,Model model){
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		
		return "/jsp/user/showUpdateUser.jsp";
		
	}
	
	@RequestMapping("/modifyUser.do")
	public String modifyUser(User user,Model model){
		
		int rows = userService.modifyUser(user);
		if(rows > 0){
			return "redirect:/user/findUser.do";
		}else{
			model.addAttribute("fail", "修改用户失败！");
			return "/jsp/fail.jsp";
		}
		
	}
	
	@RequestMapping("/removeUser.do")
	public String removeUser(String ids,Model model){
		String[] idArray = ids.split(",");
		List<Integer> IDS = new ArrayList<>();
		for(String s:idArray){
			IDS.add(Integer.parseInt(s));
		}
		int rows = userService.removeUserById(IDS);
		if(rows > 0){
			return "redirect:/user/findUser.do";
		}else{
			model.addAttribute("fail", "删除用户失败！");
			return "/jsp/fail.jsp";
		}
		/*String[] idArray = ids.split(",");
		int i = 0;
		for(String s:idArray){
			int rows = userService.removeUser(Integer.parseInt(s));
			if(rows > 0)	{ i++; }
		}
		if(i == idArray.length){
			return "redirect:/user/findUser.do";
		}else{
			model.addAttribute("fail", "删除用户失败！");
			return "/jsp/fail.jsp";
		}*/
		
	}
	
	
	
	
	
	
	
	
	
	
}
