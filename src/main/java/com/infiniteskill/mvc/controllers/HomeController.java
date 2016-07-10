package com.infiniteskill.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	//@ResponseBody		// it tells that return value should be whole body of response, not to search jsp file		
	public String goHome(){
		//return "Welcome Home";
		return "home";
	}

}
