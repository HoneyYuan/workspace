package com.dolton.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolton.dao.ExcelImpDao;
import com.dolton.model.MessageInfo;
import com.dolton.model.PhoneVo;
import com.dolton.util.MultipleDataSourceSwitch;

import net.sf.json.JSONArray;

@Service
public class ExcelImpService {
	private static final Logger log = LoggerFactory.getLogger(ExcelImpService.class);
	@Autowired
	ExcelImpDao excelImpDao;

	
	public void deleteAll(){
		excelImpDao.deleteAll();
	}
	
	public List<PhoneVo> getListFromWB(Workbook wb) {

		// 取第一个工作表
		List<PhoneVo> list = new ArrayList<PhoneVo>(0);
		Sheet sht = wb.getSheetAt(0);

		for (Row r : sht) {
			// 标题行忽略
			if (r.getRowNum() < 1) {
				continue;
			}
			PhoneVo vo = new PhoneVo();
			
//			System.out.println(); r.getCell(0).getCellType();
			
			
			vo.setPhone(null == r.getCell(0) ? "" : getCellValue(r.getCell(0)));
			vo.setText(null == r.getCell(1) ? "" : getCellValue(r.getCell(1)));
			list.add(vo);
		}

		return list;
	}
	
	
	private String getCellValue(Cell cell) {  
        String cellValue = "";  
        DecimalFormat df = new DecimalFormat("#");  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_STRING:  
            cellValue = cell.getRichStringCellValue().getString().trim();  
            break;  
        case HSSFCell.CELL_TYPE_NUMERIC:  
            cellValue = df.format(cell.getNumericCellValue()).toString();  
            break;  
        case HSSFCell.CELL_TYPE_BOOLEAN:  
            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
            break;  
        case HSSFCell.CELL_TYPE_FORMULA:  
            cellValue = cell.getCellFormula();  
            break;  
        default:  
            cellValue = "";  
        }  
        return cellValue;  
    }  
	
	

	public int addPhone(List<PhoneVo> list) {
		int result = 0;
		for (PhoneVo vo : list) {
			result += excelImpDao.addPhone(vo);
		}

		return result;
	}

	public String sendMsg(String contents) {

		MessageInfo msg = new MessageInfo();
		// String strContent = "信息部测试短信 ";
		// String strMsg = "";
		// p.setContent("年后减负，轻松甩肉？有机绿色—五常稻花香米，2.5kg装现48包邮！好货不错过http://www.dwz.cn/2MHPBq，退订回TD");
		// //
		// p.setContent("321世界睡眠日，爱“尚”自然深睡，精梳棉四件套仅售228元，全场包邮，换季大促，等你来抢http://dwz.cn/2WAlZA
		// 退订回复TD"); //
		msg.setContent(contents);
		msg.setDapt_name("80"); //
		msg.setSource_name("80"); //
		msg.setType("2"); // 营销短信

		MultipleDataSourceSwitch.setDataSourceKey("phoneDataSource");

		List<PhoneVo> list = excelImpDao.selectPhoneList();

		List<PhoneVo> failList = new ArrayList<PhoneVo>(0);
		
		
		
		
		int idx = contents.indexOf("{code}");
		
		for (PhoneVo vo : list) {
			String code = vo.getText();

			if(idx!=-1){
				if (null != code && !"".equals(code)) {
					msg.setContent( contents.replace("{code}", code));
				}
			}
			
			
			

			if (vo.getPhone().length() > 4) {

				try {
					msg.setTarget_address(vo.getPhone());

					MultipleDataSourceSwitch.setDataSourceKey("oracleDataSource");

					int result = excelImpDao.insertDX(msg);

					MultipleDataSourceSwitch.setDataSourceKey("phoneDataSource");
					if (result > 0) {

						log.debug("发送成功");

						// 修改状态
						result = excelImpDao.updateStatic(vo.getPhone());

					} else {
						failList.add(vo);
					}

				} catch (Exception e) {
					failList.add(vo);
				}

			} else {
				failList.add(vo);
			}

		}

		JSONArray jsa = JSONArray.fromObject(failList);
		System.out.println("得到的JSON:"+jsa.toString());

		
		return jsa.toString();

	}

}
