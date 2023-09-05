package org.jsp.userdemo.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseStructure<T>{
	private T Data;
	private String message;
	private int StatusCode;
	
	
	
	
}
