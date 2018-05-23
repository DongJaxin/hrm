package com.hrm.document.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hrm.commons.beans.Document;

public interface IDocumentDao {

	int insertDocument(Document document);

	List<Document> selectDocument(Map<Object, Object> map);

	int selectDocumentCount(@Param("title")String title);

	Document selectDocumentById(Integer id);

	int updateDocument(Document document);

	int deleteDocument(Integer id);

}
