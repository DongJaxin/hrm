package com.hrm.notice.handler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrm.commons.beans.Notice;
import com.hrm.commons.beans.User;
import com.hrm.notice.service.INoticeService;
import com.hrm.utils.PageModel;

@Controller
@RequestMapping("/notice")
public class NoticeHandler {
	
	@Autowired
	private INoticeService noticeService;
	
	@RequestMapping("/findNotice.do")
	public String findNotice(@RequestParam(defaultValue="1")Integer pageIndex,Notice notice,Model model){
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		
		int count = noticeService.findNoticeCount(notice);
		pageModel.setRecordCount(count);
		List<Notice> notices = noticeService.findNotice(notice,pageModel);
		System.out.println(notices);
		
		model.addAttribute("notice", notice);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("notices", notices);
		return "/jsp/notice/notice.jsp";
		
	}
	
	@RequestMapping("/addNotice.do")
	public String addNotice(Notice notice,HttpSession session,Model model){
		User user = (User) session.getAttribute("login_user");
		notice.setUser(user);
		int rows = noticeService.addNotice(notice);
		if(rows > 0){
			return "redirect:/notice/findNotice.do";
		}else{
			model.addAttribute("fail", "添加公告失败！");
			return "/jsp/fail.jsp";
		}
	}
	
	@RequestMapping("/modifyNotice.do")
	public String modifyNotice(String flag,Notice notice,Model model,HttpSession session){
		if(flag.equals("1")){
			Notice target = noticeService.findNoticeById(notice.getId());
			model.addAttribute("notice", target);
			return "/jsp/notice/showUpdateNotice.jsp";
		}else{
			User user = (User) session.getAttribute("login_user");
			notice.setUser(user);
			int rows = noticeService.modifyNotice(notice);
			if(rows > 0){
				return "redirect:/notice/findNotice.do";
			}else{
				model.addAttribute("fail", "修改公告失败！");
				return "/jsp/fail.jsp";
			}
		}
	}
	
	@RequestMapping("/previewNotice.do")
	public String previewNotice(Integer id,Model model){
		Notice notice = noticeService.findNoticeById(id);
		model.addAttribute("notice", notice);
		return "/jsp/notice/previewNotice.jsp";
	}
	
	@RequestMapping("/removeNotice.do")
	public String removeNotice(Integer[] ids,Model model){
		int rows = noticeService.removeNotice(ids);
		if(rows > 0){
			return "redirect:/notice/findNotice.do";
		}else{
			model.addAttribute("fail", "删除公告失败！");
			return "/jsp/fail.jsp";
		}
	}
	
	
	
	
	
	
	

}
