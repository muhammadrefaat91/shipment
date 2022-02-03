package com.springcard.exception;

public class ResourceDoesnotExist extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String code;
	
	public ResourceDoesnotExist(String code, String message) {
		super(message);
		this.setCode(code);
    }
	
	public ResourceDoesnotExist(String code) {
		this.setCode(code);
    }
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
