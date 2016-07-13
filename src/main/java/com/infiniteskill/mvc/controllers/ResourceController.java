package com.infiniteskill.mvc.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.infiniteskill.mvc.data.entities.Resource;
import com.infiniteskill.mvc.data.services.ResourceService;
import com.infiniteskill.mvc.data.validators.ResourceValidator;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resourceAttr")
public class ResourceController {
	
	@Autowired
	private ResourceService service;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model){
		System.out.println("Invoking add()");
		
//		if (true)
//			throw new RuntimeException("There was error triggered by ResourceController");
			
		return "resource_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveResource(@Valid @ModelAttribute("resourceAttr") Resource resource, Errors errors, RedirectAttributes attributes){
		if (errors.hasErrors())
			return "resource_add";
		
		System.out.println("The new resource validated!");
		
		resource.setResourceId(100L);
		service.save(resource);
		attributes.addFlashAttribute("project", resource);
		return "redirect:/resource/review";
	}
	
	@InitBinder("resourceAttr")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ResourceValidator());
	}
	
	@RequestMapping("/{resourceId}")
	@ResponseBody
	public Resource findResource(@PathVariable("resourceId") Long resourceId){
		return service.find(resourceId);
	}
	
	@RequestMapping("/find")
	public String find(Model model){
		model.addAttribute("resources", service.findAll());
		return "resources";
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String handleExceptions(){
		return "controller_error";
	}
	
	@RequestMapping("/request")
	@ResponseBody
	public String request(@RequestBody String resource){
		System.out.println(resource);
		return "The request has been sent for approval";
	}
	
	@ModelAttribute("resourceAttr")
	public Resource getResource(){
		System.out.println("Adding a new resource to the model");
		return new Resource();
	}
	
	@ModelAttribute("typeOptions")
	public List<String> getTypeOptions(){
		return new LinkedList<>(Arrays.asList("Material", "Other", "Staff", "Technics"));
	}
	
	@ModelAttribute("radioOptions")
	public List<String> getRadioOptions(){
		return new LinkedList<>(Arrays.asList("Hours", "Piece", "Tons"));
	}
	
	@ModelAttribute("checkOptions")
	public List<String> getCheckOptions(){
		return new LinkedList<>(Arrays.asList("Lead Time", "Special Rate", "Requires Approval"));
	}

	@RequestMapping("/review")
	public String review(@ModelAttribute("resourceAttr") Resource resource){
		System.out.println("Invoking review()");
		return "resource_review";
	}
	
	@RequestMapping("/save")
	public String save(@ModelAttribute("resourceAttr") Resource resource, SessionStatus status){
		System.out.println("Invoking the save() method.");
		System.out.println(resource);
		// After save, clear session attributes
		status.setComplete();
		return "redirect:/resource/find";
	}
}
