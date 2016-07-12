package com.infiniteskill.mvc.controllers;

import java.util.Arrays;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.infiniteskill.mvc.data.entities.Project;
import com.infiniteskill.mvc.data.services.ProjectService;
import com.infiniteskill.mvc.data.validators.ProjectValidator;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="find/{projectId}")
	public @ResponseBody Project findProjectObject(Model model, @PathVariable("projectId") Long projectId){
		return this.projectService.find(projectId);
	}

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
	public String addProject(Model model, HttpSession session){
		session.setAttribute("token", "12345");		// set example data into session
		System.out.println("invoke addProject");
		
		model.addAttribute("typeOptions", Arrays.asList("", "Single Year", "Multi Year"));
		model.addAttribute("projectAttr", new Project());
		
		return "project_add";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveProject(@Valid @ModelAttribute("projectAttr") Project project, Errors errors, RedirectAttributes attributes){
		// @ModelAttribute specifies DataBinding is to be used meaning that object project is mapped from 
		// view file by matching each control's value in name tag with fields' name in Project class 
		System.out.println("invoke saveProject");
		
		if (errors.hasErrors())
			System.out.println("The project did not validate");
		else 
			System.out.println("The project validated!");
			
		System.out.println(project);
		
		project.setProjectId(55L);
		projectService.save(project);
		attributes.addAttribute("projectId", project.getProjectId().toString());
		return "redirect:/";
	}
	
	@InitBinder
	public void init(WebDataBinder binder){
		binder.addValidators(new ProjectValidator());
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, params={"type=multi"})
	public String saveMultiYearProject(){
		System.out.println("invoke saveMultiYearProject");
		return "project_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, params={"type=multi", "special"})
	public String saveSpecialProject(@RequestParam("projName") String name, HttpSession session){
		System.out.println(session.getAttribute("token"));	// get the data back from session
		System.out.println(name);							// print value in control named 'name'		
		System.out.println("invoke saveSpecialProject");
		return "project_add";
	}	
}
