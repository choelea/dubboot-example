package com.dubboot.webdemo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dubboot.exception.DubbootException;
import com.dubboot.webdemo.data.ErrorData;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(DubbootException.class)
	public ErrorData dubbootException(DubbootException e){
		return new ErrorData(e.getCode(), e.getMessage());
	}
}
