package com.dolton.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	/**
	 * Excel 2003
	 */
	private final static String XLS = "xls";
	/**
	 * Excel 2007
	 */
	private final static String XLSX = "xlsx";
	
	
	/**
	 * 获取EXCEL WorkBook对象
	 * @param is
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWB(InputStream is,String fileName) throws IOException{
		
		Workbook workbook = null;  
		//xls表示2003版本  xlsx表示2007版本  
        if (fileName.toLowerCase().endsWith(XLS)) {  
            workbook = new HSSFWorkbook(is);  
        } else if (fileName.toLowerCase().endsWith(XLSX)) {  
            workbook = new XSSFWorkbook(is);  
        }  
        
        
        
        return workbook;
	}
	
	
}
