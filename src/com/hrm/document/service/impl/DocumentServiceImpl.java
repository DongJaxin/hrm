package com.hrm.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.commons.beans.Document;
import com.hrm.document.dao.IDocumentDao;
import com.hrm.document.service.IDocumentService;
import com.hrm.utils.PageModel;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private IDocumentDao documentDao;
	@Override
	public int addDocument(Document document) {
		
		return documentDao.insertDocument(document);
	}
	@Override
	public List<Document> findDocument(String title, PageModel pageModel) {
		Map<Object, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("start", pageModel.getFirstLimitParam());
		map.put("pageSize", pageModel.getPageSize());
		return documentDao.selectDocument(map);
	}
	@Override
	public int findDocumentCount(String title) {
		return documentDao.selectDocumentCount(title);
	}
	@Override
	public Document findDocumentById(Integer id) {
		return documentDao.selectDocumentById(id);
	}
	@Override
	public int modifyDocument(Document document) {
		// TODO Auto-generated method stub
		return documentDao.updateDocument(document);
	}
	@Override
	public int removeDocument(Integer id) {
		// TODO Auto-generated method stub
		return documentDao.deleteDocument(id);
	}

}
