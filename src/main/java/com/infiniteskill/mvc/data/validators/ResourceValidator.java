package com.infiniteskill.mvc.data.validators;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.infiniteskill.mvc.data.entities.Resource;

public class ResourceValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Resource.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		System.out.println("Validating a new resource... ");
		Resource resource = (Resource)object;
		
		if (resource.getName().length() < 3)
			errors.rejectValue("name", "resource.name", "Resource name is too short");

		// If cost comes empty, it will throw NullPointerException which is not handled here intentionally
		// because that exception will be caught in ResourceController
		if (resource.getCost().compareTo(new BigDecimal("1000")) == 1)
			errors.rejectValue("cost", "resource.cost", "Cost should not be greater than 1000");
	}

}
