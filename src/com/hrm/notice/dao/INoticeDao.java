package com.hrm.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hrm.commons.beans.Notice;

public interface INoticeDao {

	int selectNoticeCount(Notice notice);

	List<Notice> selectNotice(Map<Object, Object> map);

	int insertNotice(Notice notice);

	Notice selectNoticeById(Integer id);

	int updateNotcie(Notice notice);

	int deleteNotice(@Param("ids")Integer[] ids);

}
