package com.infiniteskill.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infiniteskill.mvc.data.entities.Project;
import com.infiniteskill.mvc.data.entities.Sponsor;
import com.infiniteskill.mvc.data.services.ProjectService;

@Controller
public class HomeController {

	@Autowired
	private ProjectService service;

	@RequestMapping(value = "/", params = "projectId")
	public String goHomeAgain(Model model, @RequestParam("projectId") Long projectId) {
		model.addAttribute("currentProject", service.find(projectId));
		return "home";
	}

	@RequestMapping("/")
	public String goHome(Model model){
		Project project = new Project();
		project.setName("First project");
		project.setSponsor(new Sponsor("NASA", "555-555-5555", "nasa@nasa.com"));
		project.setDescription("This is a simple project sponsored by NASA");

		model.addAttribute("currentProject", project);
		return "welcome";
	}

}
