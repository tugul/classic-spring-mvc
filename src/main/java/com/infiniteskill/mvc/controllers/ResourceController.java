package com.infiniteskill.mvc.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.infiniteskill.mvc.data.entities.Resource;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resourceAttr")
public class ResourceController {

	@RequestMapping("/add")
	public String add(Model model){
		System.out.println("Invoking add()");
		
		if (true)
			throw new RuntimeException("There was error triggerred by ResourceController");
			
		return "resource_add";
	}
	
	@ExceptionHandler(Exception.class)
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
		return "redirect:/resource/add";
	}
}
