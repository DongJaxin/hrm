package com.hrm.notice.service;

import java.util.List;

import com.hrm.commons.beans.Notice;
import com.hrm.utils.PageModel;

public interface INoticeService {

	int findNoticeCount(Notice notice);

	List<Notice> findNotice(Notice notice, PageModel pageModel);

	int addNotice(Notice notice);

	Notice findNoticeById(Integer id);

	int modifyNotice(Notice notice);

	int removeNotice(Integer[] ids);


}
