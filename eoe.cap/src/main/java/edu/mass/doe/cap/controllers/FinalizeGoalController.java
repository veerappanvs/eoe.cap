package edu.mass.doe.cap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mass.doe.cap.dataservice.pojo.AssessmentInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.DimensionInfo;
import edu.mass.doe.cap.dataservice.pojo.GoalPlanInfo;
import edu.mass.doe.cap.dataservice.pojo.ImplementationPlanInfo;
import edu.mass.doe.cap.dataservice.pojo.SelfAssessmentAttributeInfo;
import edu.mass.doe.cap.util.CAPValidationUtil;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class FinalizeGoalController.
 */
@Controller
@RequestMapping("goal")
@SessionAttributes("goalPlanInfo")
public class FinalizeGoalController extends BaseCycleInformation {
	
	@Autowired
	private Environment env;
	
	/**
	 * Gets the goal.
	 *
	 * @param cycleId the cycle id
	 * @param model the model
	 * @param authentication the authentication
	 * @return the goal
	 * @throws Exception the exception
	 */
	@RequestMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_SUPERVISOR','ROLE_PRACTITIONER','ROLE_CANDIDATE')")
	public String getGoal(@RequestParam("cycleId") Long cycleId, 
			Model model, Authentication authentication) throws Exception {
		
		GoalPlanInfo goalPlanInfo=goalManager.getFinalizedGoalInfo(cycleId);
		loadAssessment(goalPlanInfo, cycleId, model, authentication);
		
		
		if(cycleManager.getCycleInfo(cycleId).getCycleStatusCode()!=null)
			return ".finalizeGoalSettingView";
			
		
		if(goalPlanInfo.getComplete()!=null && goalPlanInfo.getComplete().equals('Y'))
			return ".finalizeGoalSettingView";
			
		
		return ".finalizeGoalSetting";
		
	}
	
	/**
	 * Save goal.
	 *
	 * @param command the command
	 * @param goalPlanInfo the goal plan info
	 * @param cycleId the cycle id
	 * @param model the model
	 * @param result the result
	 * @param authentication the authentication
	 * @return the string
	 * @throws Exception the exception
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_SUPERVISOR','ROLE_PRACTITIONER','ROLE_CANDIDATE')")
	public String saveGoal(@RequestParam("command") String command,@ModelAttribute("goalPlanInfo") GoalPlanInfo goalPlanInfo,@RequestParam("cycleId") Long cycleId, 
			Model model, BindingResult result, Authentication authentication) throws Exception {
		
		if(cycleManager.getCycleInfo(cycleId).getCycleStatusCode()!=null)
			return ".finalizeGoalSettingView";
			
		
		
		if (command.equals("Edit")&&(CapUtil.isSupervisor()||CapUtil.isManager())) {
			goalManager.unlockGoal(goalPlanInfo.getId());
			return "redirect:/goal?cycleId="+cycleId;

		}
		
		
		if (validate(goalPlanInfo,result)&&command.equals("Save")) {

			if (goalPlanInfo.isCompleteGoal()	&& enableCompletion(goalPlanInfo)&&(CapUtil.isSupervisor()||CapUtil.isManager())){
			goalPlanInfo.setComplete('Y');
		
			}
			
			try{
			goalManager.save(goalPlanInfo);
			}catch(Exception e){
			goalManager.save(goalPlanInfo);
			}

			return "redirect:/goal?cycleId="+cycleId;

		}
		
		
		
		loadAssessment(goalPlanInfo, cycleId, model, authentication);
		
	
		
		return ".finalizeGoalSetting";
		
	}
	
	/**
	 * Validate.
	 *
	 * @param goalPlanInfo the goal plan info
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validate(GoalPlanInfo goalPlanInfo, BindingResult bindingResult){
		boolean result=true;
		
		/*result=CAPValidationUtil.validateText(goalPlanInfo.getGoalDesc(), "goalDesc", bindingResult, new Object[]{"CAP Professional Practice Goal"});
		result=CAPValidationUtil.validateText(goalPlanInfo.getLearningMeasure(), "learningMeasure", bindingResult, new Object[]{"Measure of Student Learning"})&&result;
		result=CAPValidationUtil.validateText(goalPlanInfo.getGoalLearningMeasureInfos().get(0).getParameter(), "goalLearningMeasureInfos[0].parameter", bindingResult, new Object[]{" High Parameter"})&&result;
		result=CAPValidationUtil.validateText(goalPlanInfo.getGoalLearningMeasureInfos().get(1).getParameter(), "goalLearningMeasureInfos[1].parameter", bindingResult, new Object[]{"Moderate Parameter"})&&result;
		result=CAPValidationUtil.validateText(goalPlanInfo.getGoalLearningMeasureInfos().get(2).getParameter(), "goalLearningMeasureInfos[2].parameter", bindingResult, new Object[]{"Low Parameter"})&&result;
		int idx=0;
		for (ImplementationPlanInfo implementationPlanInfo : goalPlanInfo.getImplementationPlanInfos()) {
			result=CAPValidationUtil.validateText(implementationPlanInfo.getActionEvidence()  , "implementationPlanInfos["+idx+"].actionEvidence", bindingResult, new Object[]{"Action Evidence"})&&result;
			result=CAPValidationUtil.validateText(implementationPlanInfo.getActionSupport() , "implementationPlanInfos["+idx+"].actionSupport", bindingResult, new Object[]{"Action Support"})&&result;
			result=CAPValidationUtil.validateText(implementationPlanInfo.getActionTimeline()  , "implementationPlanInfos["+idx+"].actionTimeline", bindingResult, new Object[]{"Action Timeline"})&&result;
			result=CAPValidationUtil.validateText(implementationPlanInfo.getActionDesc()  , "implementationPlanInfos["+idx+"].actionDesc", bindingResult, new Object[]{"Action Desc"})&&result;

		}
*/
		
		
		return result;
	}
	
	
	
	/**
	 * Enable completion.
	 *
	 * @param goalPlanInfo the goal plan info
	 * @return true, if successful
	 */
	public boolean enableCompletion(GoalPlanInfo goalPlanInfo) {
		boolean result = true;

		result = !goalPlanInfo.getGoalLearningMeasureInfos().stream().anyMatch(temp->(temp.getParameter()==null||temp.getParameter().isEmpty()));
		result= !(goalPlanInfo.getLearningMeasure()==null||(goalPlanInfo.getLearningMeasure().isEmpty()))&&result;
				

		return result;
	}

	

	/**
	 * Load assessment.
	 *
	 * @param goalPlanInfo the goal plan info
	 * @param cycleId the cycle id
	 * @param model the model
	 * @param authentication the authentication
	 * @throws Exception the exception
	 */
	public void loadAssessment(GoalPlanInfo goalPlanInfo, Long cycleId, Model model, Authentication authentication)
			throws Exception {

		CapCycleInfo cycleInfo = cycleManager.getCycleInfo(cycleId);
		
		if (CapUtil.isSupervisor()||CapUtil.isManager())
			goalPlanInfo.setEnableCompletion(enableCompletion(goalPlanInfo));
		
		ImplementationPlanInfo implementationPlanInfo=goalPlanInfo.getImplementationPlanInfos().get(goalPlanInfo.getImplementationPlanInfos().size()-1);
		
		if(implementationPlanInfo!=null){
			goalPlanInfo.setCanAddAction(
					implementationPlanInfo.getActionEvidence()!=null&&implementationPlanInfo.getActionEvidence().trim().length()>0&&
					implementationPlanInfo.getActionSupport()!=null&&implementationPlanInfo.getActionSupport().trim().length()>0&&
					implementationPlanInfo.getActionTimeline()!=null&&implementationPlanInfo.getActionTimeline().trim().length()>0&&
					implementationPlanInfo.getActionDesc()!=null&&implementationPlanInfo.getActionDesc().trim().length()>0);	
				}
		

		model.addAttribute("goalPlanInfo", goalPlanInfo);
		model.addAttribute("cycleInfo", cycleInfo);
		model.addAttribute("showEditButton", cycleInfo.getCycleStatusCode()==null?true:false);

	}
	
	
	/**
	 * Adds the attribute.
	 *
	 * @param goalPlanInfo the goal plan info
	 * @param mode the mode
	 * @return the string
	 */
	@PostMapping("addrow")
	public String addAttribute(@ModelAttribute("goalPlanInfo") GoalPlanInfo goalPlanInfo, Model mode) {

		
		ImplementationPlanInfo implementationPlanInfo = new ImplementationPlanInfo();
		goalPlanInfo.getImplementationPlanInfos().add(implementationPlanInfo);
		goalPlanInfo.setCanAddAction(false);
		return "implementationPlanTable";

	}
	
	/**
	 * Delete attribute.
	 *
	 * @param index the index
	 * @param goalPlanInfo the goal plan info
	 * @param mode the mode
	 * @return the string
	 */
	@PostMapping("deleterow")
	public String deleteAttribute(@RequestParam("idx") int  index, @ModelAttribute("goalPlanInfo") GoalPlanInfo goalPlanInfo, Model mode) {
		
		goalPlanInfo.getDeletedImplementationPlanInfos().add(goalPlanInfo.getImplementationPlanInfos().remove(index));
		ImplementationPlanInfo implementationPlanInfo=goalPlanInfo.getImplementationPlanInfos().get(goalPlanInfo.getImplementationPlanInfos().size()-1);
		
		if(implementationPlanInfo!=null){
			goalPlanInfo.setCanAddAction(implementationPlanInfo.getActionEvidence()!=null&&implementationPlanInfo.getActionEvidence().trim().length()>1&&
					implementationPlanInfo.getActionSupport()!=null&&implementationPlanInfo.getActionSupport().trim().length()>1&&
					implementationPlanInfo.getActionTimeline()!=null&&implementationPlanInfo.getActionTimeline().trim().length()>1&&
					implementationPlanInfo.getActionDesc()!=null&&implementationPlanInfo.getActionDesc().trim().length()>1);	
				}
		
		return "implementationPlanTable";

	}


	
	
	

}
