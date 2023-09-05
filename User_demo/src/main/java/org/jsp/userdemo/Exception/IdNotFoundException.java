package org.jsp.userdemo.Exception;

public class IdNotFoundException extends RuntimeException {
  @Override
public String getMessage() {
	return "the id is invalid";
}
}
