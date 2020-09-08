package com.java.migros.utils;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

	private HttpStatus status;
	private T data;
	private String errMsg;
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public ApiResponse(HttpStatus status, String errMsg) {
		super();
		this.status = status;
		this.errMsg = errMsg;
	}
	
	public ApiResponse(HttpStatus status, T data) {
		super();
		this.status = status;
		this.data = data;
	}
	
}
