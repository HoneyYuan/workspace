package com.dolton.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.dolton.model.PhoneVo;
import com.dolton.service.ExcelImpService;
import com.dolton.util.ExcelUtil;

@RestController
@RequestMapping("/eximp")
public class ExcelImpController {

	@Autowired
	ExcelImpService excelImpService;
	
	
	  @RequestMapping(value = "/download", method = RequestMethod.GET)
	  public ResponseEntity<byte[]> download(HttpServletRequest req, HttpServletResponse response) throws IOException {    
		  

		  	String path = req.getServletContext().getRealPath("");
		  	String filepath=path+"/eg.xlsx";  
		  	System.out.println("路径:"+filepath);
	        File file=new File(filepath);  
	        HttpHeaders headers = new HttpHeaders();    
	        String fileName=new String("模板.xlsx".getBytes("utf-8"),"iso-8859-1");//解决中文名称乱码问题  
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
	                                          headers, HttpStatus.CREATED);   
	        
	    }    
	
	
	

	@RequestMapping(value = "/impexcel", method = RequestMethod.GET)
	public ModelAndView getExcelPage() {

		ModelAndView mv = new ModelAndView("impexcel");

		return mv;
	}

	@RequestMapping(value = "/uploadEx", method = RequestMethod.POST)
	public ModelAndView uploadExcel(HttpServletRequest req, HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) req;
		MultipartFile file = mreq.getFile("file");
		String fileName = file.getOriginalFilename();
		InputStream is = file.getInputStream();

		excelImpService.deleteAll();
		
		Workbook wb = ExcelUtil.getWB(is, fileName);

		List<PhoneVo> list = excelImpService.getListFromWB(wb);

		int result = excelImpService.addPhone(list);

		is.close();
		ModelAndView mv = new ModelAndView("impexcel");

		if (result > 0) {
			mv.addObject("rst", "ok");
		}

		return mv;
	}

	@RequestMapping(value = "/sendEx", method = RequestMethod.POST)
	public String sendExcelMsg(HttpServletRequest req, HttpServletResponse response,
			@RequestParam(value = "contents", required = false) String contents) throws Exception {

		String msg = excelImpService.sendMsg(contents);


		StringBuffer sb = new StringBuffer();
		sb.append("{");

		sb.append("code:");
		sb.append("'");
		sb.append("000");
		sb.append("'");
		sb.append(",");
		sb.append("data:");
		sb.append(msg);
		sb.append("}");

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(sb.toString());


		return null;

	}

}
