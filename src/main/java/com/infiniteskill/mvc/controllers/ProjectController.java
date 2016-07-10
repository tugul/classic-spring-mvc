package com.infiniteskill.mvc.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.infiniteskill.mvc.data.services.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="/{projectId}")
	public String findProject(Model model, @PathVariable("projectId") Long projectId){
		// Here path variable projectId is assigned to Long parameter projectId
		model.addAttribute("project", projectService.find(projectId));
		return "project";
	}
	
	@RequestMapping(value="/find")
	public String find(Model model){
		model.addAttribute("projects", projectService.findAll());
		return "projects";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addProject(HttpSession session){
		session.setAttribute("token", "12345");		// set example data into session
		System.out.println("invoke addProject");
		return "project_add";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveProject(@RequestParam("projName") String name, HttpSession session){
		System.out.println(session.getAttribute("token"));	// get the data back from session
		System.out.println(name);							// print value in control named projName
		System.out.println("invoke saveProject");
		return "project_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, params={"type=multi"})
	public String saveMultiYearProject(){
		System.out.println("invoke saveMultiYearProject");
		return "project_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, params={"type=multi", "special"})
	public String saveSpecialProject(){
		System.out.println("invoke saveSpecialProject");
		return "project_add";
	}	
}
