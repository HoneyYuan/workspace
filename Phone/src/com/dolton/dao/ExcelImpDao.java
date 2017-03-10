package com.dolton.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dolton.model.MessageInfo;
import com.dolton.model.PhoneVo;

@Repository
public interface ExcelImpDao {
	
	public List<PhoneVo> selectPhoneList();
	
	public int addPhone(PhoneVo vo);
	
	public int insertDX(MessageInfo msg);
	
	public int updateStatic(String phone);
	
	public int deleteAll();

}
