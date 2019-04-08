package org.sang.bean;

public class StringBean {
	private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "StringBean [string=" + string + "]";
	}

	public StringBean(String string) {
		super();
		this.string = string;
	}
	
}
