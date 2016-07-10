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
		List<String> options = new LinkedList<>(Arrays.asList("Material", "Other", "Staff", "Technics"));
		model.addAttribute("typeOptions", options);
		model.addAttribute("resourceAttr", new Resource());
		return "resource_add";
	}
	
	@RequestMapping("/save")
	public String save(@ModelAttribute("resourceAttr") Resource resource){
		System.out.println("Invoking the save() method.");
		System.out.println(resource);
		return "resource_add";
	}
}
