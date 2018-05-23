package com.hrm.document.handler;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpHandler;

import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hrm.commons.beans.Document;
import com.hrm.commons.beans.User;
import com.hrm.document.service.IDocumentService;
import com.hrm.utils.PageModel;

@Controller
@RequestMapping("/document")
public class DocumentHandler {
	
	@Autowired
	private IDocumentService documentService;
	
	@RequestMapping("/findDocument.do")
	public String findDocument(@RequestParam(defaultValue="1")Integer pageIndex,String title,Model model){
		
		//System.out.println(title+"=123");
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		int count = documentService.findDocumentCount(title);
		pageModel.setRecordCount(count);
		List<Document> documents = documentService.findDocument(title,pageModel);
		//System.out.println(documents);
		model.addAttribute("title", title);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("documents", documents);
		return "/jsp/document/document.jsp";
		
	}
	
	
	@RequestMapping("/addDocument.do")
	public String addDocument(Document document,HttpSession session,Model model) throws IllegalStateException, IOException{
		
		//上传文件路径
		String path = "E:/upload";//session.getServletContext().getRealPath("/upload");
		File upload = new File(path);
		if(!upload.exists()){
			upload.mkdirs();
		}
		//System.out.println(document);
		//上传文件名
		String filename = document.getFile().getOriginalFilename();
		document.getFile().transferTo(new File(path+File.separator+filename));
		document.setFilename(filename);
		User user = (User) session.getAttribute("login_user");
		document.setUser(user);
		int rows = documentService.addDocument(document);
		if(rows > 0){
			return "redirect:/document/findDocument.do";
		}else{
			model.addAttribute("fail", "文件上传失败");
			return "/jsp/fail.jsp";
		}
	}
	
	@RequestMapping("/modifyDocument.do")
	public String modifyDocument(String flag,Document document,Model model,HttpSession session) throws IllegalStateException, IOException{
		if(flag.equals("1")){
			Document target = documentService.findDocumentById(document.getId());
			model.addAttribute("document", target);
			return "/jsp/document/showUpdateDocument.jsp";
		}else{
			
			if(!document.getFile().isEmpty()){
				//上传文件路径
				String path = "E:/upload";//session.getServletContext().getRealPath("/upload");
				//System.out.println(document.getFile().getOriginalFilename());
				Document target = documentService.findDocumentById(document.getId());
				File targetFile = new File(path, target.getFilename());
				 if(targetFile.exists()){
					 targetFile.delete();
				 }
				 //另一种删除文件方法
				/*File folder = new File(path);
				File[] files = folder.listFiles();
				for(File file:files){
					if(file.getName().equals(target.getFilename())){
					file.delete();
					break;
					}
				}*/
				//上传文件名
				String filename = document.getFile().getOriginalFilename();
				document.getFile().transferTo(new File(path+File.separator+filename));
				document.setFilename(filename);
			}
			User user = (User) session.getAttribute("login_user");
			document.setUser(user);
			int rows = documentService.modifyDocument(document);
			if(rows > 0){
				return "redirect:/document/findDocument.do";
			}else{
				model.addAttribute("fail", "文件修改失败");
				return "/jsp/fail.jsp";
			}
		}
	}
	
	
	@RequestMapping("/downLoad.do")
	public ResponseEntity<byte[]> download(Integer id,HttpSession session,HttpServletRequest request) throws IOException{
		Document target = documentService.findDocumentById(id);
		String fileName = target.getFilename();
		
		String path = "E:/upload"; //session.getServletContext().getRealPath("/upload");
		
		File file = new File(path+File.separator+fileName);
		
		HttpHeaders headers = new HttpHeaders();
		String downloadFileName = this.processFileName(request, fileName);//new String(fileName.getBytes("UTF-8"),"iso-8859-1");
		headers.setContentDispositionFormData("attachment", downloadFileName);
		//headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadFileName + "\""); 
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.OK);
	}
	
	@RequestMapping("/removeDocument.do")
	public String removeDocument(Integer[] ids,Model model){
		String path = "E:/upload";//session.getServletContext().getRealPath("/upload");
		int rows = 0;
		for(Integer id:ids){
			Document target = documentService.findDocumentById(id);
			 File targetFile = new File(path, target.getFilename());
			 if(targetFile.exists()){
				 targetFile.delete();
			 }
			 int i = documentService.removeDocument(id);
			 if(i >0 ){
				 rows++;
			 }
		}
		if(rows == ids.length){
			return "redirect:/document/findDocument.do";
		}else{
			model.addAttribute("fail", "文件修改失败");
			return "/jsp/fail.jsp";
		}
	}
	
	
	//IE、chrom、Firefox文件中文乱码问题
	public String processFileName(HttpServletRequest request, String fileNames) {  
	       String codedfilename = null;  
	       try {  
	           String agent = request.getHeader("USER-AGENT");  
	           if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
	                   && -1 != agent.indexOf("Trident")) {// ie  
	  
	               String name = java.net.URLEncoder.encode(fileNames, "UTF8");  
	  
	               codedfilename = name;  
	           } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等  
	  
	  
	               codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");  
	           }  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       }  
	       return codedfilename;  
	   }  
	
	
	
	
	
	
	
	
	
	
	
	
	
}
