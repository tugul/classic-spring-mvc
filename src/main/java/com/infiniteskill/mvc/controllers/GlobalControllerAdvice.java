package com.infiniteskill.mvc.controllers;

import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.infiniteskill.mvc.data.validators.ProjectValidator;

/**
 * ControllerAdvice can serve as a central place for application 
 * to handle exceptions, add model attributes and bind custom validators
 */
@ControllerAdvice(annotations=Controller.class)
public class GlobalControllerAdvice{

	@ModelAttribute("currentTime")
	public LocalTime getCurrentTime(){
		return LocalTime.now();
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleExceptions(){
		return "global_error";
	}
		
	@InitBinder("projectAttr")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProjectValidator());
	}
}
