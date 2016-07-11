package com.infiniteskill.mvc.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infiniteskill.mvc.data.entities.Resource;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@RequestMapping("/add")
	public String add(Model model){
		return "resource_add";
	}
	
	@ModelAttribute("resourceAttr")
	public Resource getResource(){
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

	@RequestMapping("/save")
	public String save(@ModelAttribute("resourceAttr") Resource resource){
		System.out.println("Invoking the save() method.");
		System.out.println(resource);
		return "redirect:/resource/add";
	}
}
