package edu.mass.doe.cap.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mass.doe.cap.dataservice.pojo.AssessmentInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.DimensionInfo;
import edu.mass.doe.cap.dataservice.pojo.ObservationInfo;
import edu.mass.doe.cap.dataservice.pojo.SelfAssessmentAttributeInfo;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class SelfAssessmentRubricController.
 */
@Controller
@RequestMapping("selfassessmentrubric")
@SessionAttributes("assessmentInfo")
public class SelfAssessmentRubricController  extends BaseCycleInformation {
	
	
	
	
	/**
	 * Gets the rubric info.
	 *
	 * @param cycleId the cycle id
	 * @param model the model
	 * @param authentication the authentication
	 * @return the rubric info
	 * @throws Exception the exception
	 */
	@RequestMapping
	@PreAuthorize("hasAnyRole('ROLE_CANDIDATE,ROLE_MANAGER,ROLE_SUPERVISOR')")
	public String getRubricInfo(@RequestParam("cycleId") Long cycleId, Model model, Authentication authentication) throws Exception {
		
		AssessmentInfo assessmentInfo=selfassessmentRubricManager.getRubricInfo(cycleId);
	
		loadAssessment(assessmentInfo, cycleId, model, authentication);
		
		String page=".selfassessmentRubricView";
		
		
		if(cycleManager.getCycleInfo(cycleId).getCycleStatusCode()!=null)
			return page;
		
		if(assessmentInfo.getLocked()!=null&&assessmentInfo.getLocked().equals('Y'))
			return page;
			
		
		if(CapUtil.isManager()||CapUtil.isSupervisor())
			return ".selfassessmentRubricManagerView";
		
		if(assessmentInfo.getLocked()==null||assessmentInfo.getLocked().equals('N'))
			page= ".selfassessmentRubric";
				
		return page;
		
	}
	
	
	/**
	 * Enable completion.
	 *
	 * @param assessmentInfo the assessment info
	 * @return true, if successful
	 */
	public boolean enableCompletion(AssessmentInfo assessmentInfo){
		boolean result=true;
		
		result = !assessmentInfo.getElements().stream().anyMatch(temp -> temp.getDimensions().stream()
				.anyMatch(temp1 -> (temp1.getRatingCode() == null || temp1.getRatingCode().isEmpty())));		
			
		
		return result;
	}

	
	/**
	 * Load assessment.
	 *
	 * @param assessmentInfo the assessment info
	 * @param cycleId the cycle id
	 * @param model the model
	 * @param authentication the authentication
	 * @throws Exception the exception
	 */
	public void loadAssessment(AssessmentInfo assessmentInfo, Long cycleId, Model model,
			Authentication authentication) throws Exception {

		CapCycleInfo cycleInfo = cycleManager.getCycleInfo(cycleId);
		List<DimensionInfo> dimensions = assessmentManager.getAlldimensions();
		
		assessmentInfo.setEnableCompletion(enableCompletion(assessmentInfo));
	
		model.addAttribute("assessmentInfo", assessmentInfo);
		model.addAttribute("cycleInfo", cycleInfo);
		model.addAttribute("dimensions", dimensions);

		boolean displayEdit=false;
		
		displayEdit=cycleInfo.getCycleStatusCode()==null;

		if (displayEdit) {
				//No edit is allowed once self assessment is completed by candidate
				displayEdit= assessmentInfo.getCompleted()==null||assessmentInfo.getCompleted().equals('N');
		}
		
		model.addAttribute("displayEdit", displayEdit);
	}
	
	
	/**
	 * Update assessment.
	 *
	 * @param request the request
	 * @param assessmentInfo the assessment info
	 * @param model the model
	 * @param authentication the authentication
	 * @return the string
	 * @throws Exception the exception
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_CANDIDATE','ROLE_SUPERVISOR','ROLE_MANAGER')")
	public String updateAssessment(HttpServletRequest request,@ModelAttribute("assessmentInfo") AssessmentInfo assessmentInfo, Model model, Authentication authentication)
			throws Exception {
		
		if(request.getParameter("Cancel")!=null){
			//selfassessmentRubricManager.completeRubric(assessmentInfo);
			return "redirect:/home";
		}
		
		if(!CapUtil.isCandidate())
			return "redirect:/home";
		
		if(request.getParameter("complete")!=null){
			selfassessmentRubricManager.completeRubric(assessmentInfo);
			return "redirect:/selfassessmentrubric?cycleId="+assessmentInfo.getCycleId();
		}
		
		if(request.getParameter("edit")!=null){
			selfassessmentRubricManager.unlockSelfAssessmet(assessmentInfo.getAssessmentId());
			return "redirect:/selfassessmentrubric?cycleId="+assessmentInfo.getCycleId();
		}
		
		try{
		selfassessmentRubricManager.saveRubric(assessmentInfo);	
		}catch(Exception e){
		selfassessmentRubricManager.saveRubric(assessmentInfo);	
		}
		
		
		return "redirect:/selfassessmentrubric?cycleId="+assessmentInfo.getCycleId();

		
	}
	
	
	

}
