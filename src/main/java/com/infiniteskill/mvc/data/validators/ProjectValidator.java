package com.infiniteskill.mvc.data.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.infiniteskill.mvc.data.entities.Project;

public class ProjectValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Project.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Project project = (Project)object;
		
		if (project.getName().length() < 5)
			errors.rejectValue("name", "project.name", "The project name is too short");
	}

}
