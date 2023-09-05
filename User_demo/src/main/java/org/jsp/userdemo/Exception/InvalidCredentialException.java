package org.jsp.userdemo.Exception;

public class InvalidCredentialException extends RuntimeException {
 @Override
public String getMessage() {
	
	return "Invalid phone or email or password";
}
}
