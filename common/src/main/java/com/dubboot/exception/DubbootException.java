package com.dubboot.exception;


public class DubbootException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3108308221392119888L;
	private String code;

	/**
	 * Code must be globally unique
	 * @param code
	 * @param message
	 */
	public DubbootException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
