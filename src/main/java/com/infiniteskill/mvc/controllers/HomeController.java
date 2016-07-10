package com.infiniteskill.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infiniteskill.mvc.data.entities.Project;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	//@ResponseBody		// it tells that return value should be whole body of response, not to search jsp file		
	public String goHome(Model model){
		Project project = new Project();
		project.setName("First project");
		project.setSponsor("NASA");
		project.setDescription("This is a simple project sponsored by NASA");
		
		model.addAttribute("currentProject", project);
		return "home";
	}

}