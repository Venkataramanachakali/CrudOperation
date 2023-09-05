package org.jsp.userdemo.Exception;

import org.jsp.userdemo.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserDemoExceptionHandler  extends ResponseEntityExceptionHandler{
	@ExceptionHandler(IdNotFoundException.class)
  public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException exception){
	  ResponseStructure<String> structure=new ResponseStructure();
	  structure.setData("user not found");
	  structure.setMessage(exception.getMessage());
      structure.setStatusCode(HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
  }
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(InvalidCredentialException exception) {
		 ResponseStructure<String> structure=new ResponseStructure();
		  structure.setData("user not found");
		  structure.setMessage(exception.getMessage());
	      structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	      return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
