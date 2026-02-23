package com.tejait.batch15.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorDtls>idNotFoundException(HttpServletRequest request){
		Date date=new Date();
		ErrorDtls error =new ErrorDtls(date, 407, "Id Not Found", "given Id Not Available", request.getRequestURI());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AccountAlreadyExists.class)
	public ResponseEntity<ErrorDtls>AccountAlreadyexistsException(HttpServletRequest request){
		Date  date = new Date();
ErrorDtls error= new ErrorDtls(date, 409, "AccountNotFound", "given Id  Not Availabe",request.getRequestURI() );
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(MailAlreadyExists.class)
	public ResponseEntity<ErrorDtls>MailAlreadyExists(HttpServletRequest request){
		ErrorDtls error= new ErrorDtls(new Date(), 410, "Mail Already Exists", "gaiven Mail Already Exists", request.getRequestURI());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(MobileAlreadyexists.class)
	public ResponseEntity<ErrorDtls>MobileAlreadyExists(HttpServletRequest request){
		ErrorDtls error = new ErrorDtls(new Date(), 409, "mobile Already Exists", "given mobile Already exists",request.getRequestURI());
		return new ResponseEntity<>(error,HttpStatus.BAD_GATEWAY);
		
	}
	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<ErrorDtls>InvalidAmountException(HttpServletRequest request){
		ErrorDtls error= new ErrorDtls(new Date(), 410, "inavalid ampunt", "Amount not found", request.getRequestURI());
	return new ResponseEntity<ErrorDtls>(error, HttpStatus.BAD_GATEWAY);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDtls>  globalExceptionMethod(HttpServletRequest request){
		ErrorDtls error= new ErrorDtls(new Date(), 501, "something went wrong", "unknown error occured", request.getRequestURI());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
