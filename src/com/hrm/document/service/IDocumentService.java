package com.hrm.document.service;

import java.util.List;

import com.hrm.commons.beans.Document;
import com.hrm.utils.PageModel;

public interface IDocumentService {

	int addDocument(Document document);

	List<Document> findDocument(String title, PageModel pageModel);

	int findDocumentCount(String title);

	Document findDocumentById(Integer id);

	int modifyDocument(Document document);

	int removeDocument(Integer id);

}
