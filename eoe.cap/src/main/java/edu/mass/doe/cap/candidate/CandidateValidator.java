package edu.mass.doe.cap.candidate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;




@Component
public class CandidateValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg) {
		return CandidateEnrollment.class.isAssignableFrom(arg);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CandidateEnrollment CandidateEnrollment=   (CandidateEnrollment) target;
		
	}
	
	
	
	
	
	

}
