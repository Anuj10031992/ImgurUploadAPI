package com.example.synchrony.assignment.demo.conroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.synchrony.assignment.demo.model.ErrorResponse;

@ControllerAdvice
public class NotaValidUserExceptionController  {
	

//	@Override
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
			) {
		List<String> details = new ArrayList<>();
	System.out.println("hi");
	System.out.println("Hello" +ex.getBindingResult().getAllErrors());
	    for(ObjectError error : ex.getBindingResult().getAllErrors()) {
	      details.add(error.getDefaultMessage());
	    }
	    System.out.println("hi-aj");
	    ErrorResponse error = new ErrorResponse("Validation Failed", details);
	    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}
