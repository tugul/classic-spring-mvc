package com.infiniteskill.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infiniteskill.mvc.data.entities.Resource;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("resourceAttr", new Resource());
		return "resource_add";
	}
	
	@RequestMapping("/save")
	public String save(){
		System.out.println("Invoking the save() method.");
		return "resource_add";
	}
}
