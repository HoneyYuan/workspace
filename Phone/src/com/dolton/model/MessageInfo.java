package com.dolton.model;

import java.sql.Date;

public class MessageInfo {

	private String smsid;// id

	private String target_address;// 手机号码

	private String content;// 短息内容

	private String desuserid;// 接收id

	private String desname;// 接收姓名

	private String type;// 短息类型 ‘

	private String source_name = "80";// 发送人

	private String dapt_name = "80";// 发送部门

	private Date savetime = null;// 时间

	public String getSmsid(){
		return smsid;
	}

	public void setSmsid(String smsid) {
		this.smsid = smsid;
	}

	public String getTarget_address() {
		return target_address;
	}

	public void setTarget_address(String target_address) {
		this.target_address = target_address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDesuserid() {
		return desuserid;
	}

	public void setDesuserid(String desuserid) {
		this.desuserid = desuserid;
	}

	public String getDesname() {
		return desname;
	}

	public void setDesname(String desname) {
		this.desname = desname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource_name() {
		return source_name;
	}

	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}

	public String getDapt_name() {
		return dapt_name;
	}

	public void setDapt_name(String dapt_name) {
		this.dapt_name = dapt_name;
	}

	public Date getSavetime() {
		return savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

	@Override
	public String toString() {
		return "Phone [content=" + content + ", dapt_name=" + dapt_name + ", desname=" + desname + ", desuserid="
				+ desuserid + ", savetime=" + savetime + ", smsid=" + smsid + ", source_name=" + source_name
				+ ", target_address=" + target_address + ", type=" + type + "]";
	}

}
