package edu.mass.doe.cap.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import edu.mass.doe.cap.util.CapUtil;

public class BaseController {
	
	/**
	 * Inits the.
	 *
	 * @param model the model
	 */
	@ModelAttribute
	protected void init(Model model){
		
		model.addAttribute("candidate",CapUtil.isCandidate());
		model.addAttribute("supervisor",CapUtil.isSupervisor());
		model.addAttribute("practitioner",CapUtil.ispractitioner());
		model.addAttribute("manager",CapUtil.isManager());
		model.addAttribute("admin",CapUtil.isAdmin());

		
	}
		


}
