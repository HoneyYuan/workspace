package com.dolton.model;

public class Cods {

	private String id;
	private String cods;
	private String phone;
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCods() {
		return cods;
	}

	public void setCods(String cods) {
		this.cods = cods;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Cods [cods=" + cods + ", id=" + id + ", phone=" + phone + ", text=" + text + "]";
	}
}
