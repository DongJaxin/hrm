package com.hrm.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.commons.beans.Notice;
import com.hrm.notice.dao.INoticeDao;
import com.hrm.notice.service.INoticeService;
import com.hrm.utils.PageModel;

@Service
public class NoticeServiceImpl implements INoticeService {

	@Autowired
	private INoticeDao noticeDao;
	
	@Override
	public int findNoticeCount(Notice notice) {
		return noticeDao.selectNoticeCount(notice);
	}

	@Override
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		Map<Object, Object> map = new HashMap<>();
		map.put("title", notice.getTitle());
		map.put("content", notice.getContent());
		map.put("start", pageModel.getFirstLimitParam());
		map.put("pageSize", pageModel.getPageSize());
		return noticeDao.selectNotice(map);
	}

	@Override
	public int addNotice(Notice notice) {
		return noticeDao.insertNotice(notice);
	}

	@Override
	public Notice findNoticeById(Integer id) {
		return noticeDao.selectNoticeById(id);
	}

	@Override
	public int modifyNotice(Notice notice) {
		return noticeDao.updateNotcie(notice);
	}

	@Override
	public int removeNotice(Integer[] ids) {
		return noticeDao.deleteNotice(ids);
	}


}
