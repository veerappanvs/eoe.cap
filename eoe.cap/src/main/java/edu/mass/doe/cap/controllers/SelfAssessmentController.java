package edu.mass.doe.cap.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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
import edu.mass.doe.cap.dataservice.pojo.ObservationInfo;
import edu.mass.doe.cap.dataservice.pojo.SelfAssessmentAttributeInfo;
import edu.mass.doe.cap.util.CAPValidationUtil;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class SelfAssessmentController.
 */
@Controller
@RequestMapping("selfassessment")
@SessionAttributes("assessmentInfo")
public class SelfAssessmentController extends BaseCycleInformation {

	private final Map<Integer, String> pageForms = new HashMap<Integer, String>(3);

	/**
	 * Initialize.
	 */
	public void initialize() {
		if(CapUtil.isCandidate()){
		pageForms.put(0, ".selfassessment");
		pageForms.put(1, ".prelimGoalSetting");
		pageForms.put(2, ".assessmentGoalSummary");
		}else{
		pageForms.put(0, ".selfassessmentManagerView");
		pageForms.put(1, ".prelimGoalSettingManagerView");
		pageForms.put(2, ".assessmentGoalSummaryManagerView");}
	}

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
	public String getRubricInfo(@RequestParam("cycleId") Long cycleId, Model model, Authentication authentication)
			throws Exception {
		initialize();
		AssessmentInfo assessmentInfo = selfassessmentRubricManager.getRubricInfo(cycleId);
		assessmentInfo.setGoalPlanInfo(goalManager.getPrelimanaryGoalInfo(cycleId));
		loadAssessment(assessmentInfo, cycleId, model, authentication);

		String page = ".assessmentGoalSummary";
		
		if(cycleManager.getCycleInfo(cycleId).getCycleStatusCode()!=null)
			return page;

		int size =  assessmentInfo.getStrengths().size();
		
		SelfAssessmentAttributeInfo selfAssessmentAttributeInfo = assessmentInfo.getStrengths().get(size-1);
		
		if(selfAssessmentAttributeInfo.getArea()!=null&&selfAssessmentAttributeInfo.getArea().trim().length()>0&&selfAssessmentAttributeInfo.getRationale()!=null&&selfAssessmentAttributeInfo.getRationale().trim().length()>0 && selfAssessmentAttributeInfo.getElements().stream().anyMatch(temp1 -> temp1.isSelected())){
				assessmentInfo.setCanAddStrengths(true);
		}else{
				assessmentInfo.setCanAddStrengths(false);
		}
		
		
		size =  assessmentInfo.getGrowths().size();
		
		selfAssessmentAttributeInfo = assessmentInfo.getGrowths().get(size-1);
		
		if(selfAssessmentAttributeInfo.getArea()!=null&&selfAssessmentAttributeInfo.getArea().trim().length()>0&&selfAssessmentAttributeInfo.getRationale()!=null&&selfAssessmentAttributeInfo.getRationale().trim().length()>0 && selfAssessmentAttributeInfo.getElements().stream().anyMatch(temp1 -> temp1.isSelected())){
				assessmentInfo.setCanAddGrowths(true);
		}else{
				assessmentInfo.setCanAddGrowths(false);
		}
	

		
		if ((CapUtil.isManager()||CapUtil.isSupervisor())
				&& (assessmentInfo.getLocked() != null && assessmentInfo.getLocked().equals('Y'))
				&& (assessmentInfo.getCompleted() == null || !assessmentInfo.getCompleted().equals('Y'))) {
			return ".selfassessmentManagerView";
		}

		if (CapUtil.isCandidate()
				&& (assessmentInfo.getLocked() != null && assessmentInfo.getLocked().equals('Y'))
				&& (assessmentInfo.getCompleted() == null || !assessmentInfo.getCompleted().equals('Y'))) {
			return ".selfassessment";
		}

		return page;

	}

	/**
	 * Enable completion.
	 *
	 * @param assessmentInfo the assessment info
	 * @return true, if successful
	 */
	public boolean enableCompletion(AssessmentInfo assessmentInfo) {
		boolean result = true;

		if (assessmentInfo.getCompleted() != null && assessmentInfo.getCompleted().equals('Y'))
			return false;

		result = !assessmentInfo.getStrengths().stream()
				.anyMatch(temp -> (temp.getArea() == null || temp.getArea().isEmpty())
						|| (temp.getRationale() == null || temp.getRationale().isEmpty())
						|| !(temp.getElements().stream().anyMatch(temp1 -> temp1.isSelected())));

		result = result && !assessmentInfo.getGrowths().stream()
				.anyMatch(temp -> (temp.getArea() == null || temp.getArea().isEmpty())
						|| (temp.getRationale() == null || temp.getRationale().isEmpty())
						|| !(temp.getElements().stream().anyMatch(temp1 -> temp1.isSelected())));

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
	public void loadAssessment(AssessmentInfo assessmentInfo, Long cycleId, Model model, Authentication authentication)
			throws Exception {

		CapCycleInfo cycleInfo = cycleManager.getCycleInfo(cycleId);
		List<DimensionInfo> dimensions = assessmentManager.getAlldimensions();

		if (CapUtil.isCandidate())
			assessmentInfo.setEnableCompletion(enableCompletion(assessmentInfo));

		model.addAttribute("assessmentInfo", assessmentInfo);
		model.addAttribute("cycleInfo", cycleInfo);
		model.addAttribute("dimensions", dimensions);

		boolean displayEdit = false;

		displayEdit = cycleInfo.getCycleStatusCode() == null;

		if (displayEdit) {
			if (CapUtil.isCandidate()) {
				displayEdit = (assessmentInfo.getLocked() != null && assessmentInfo.getLocked().equals('Y')
						&& (assessmentInfo.getCompleted() == null || assessmentInfo.getCompleted().equals('N')));
			} else {
				displayEdit = assessmentInfo.getCompleted() != null && assessmentInfo.getCompleted().equals('Y');
			}
		}

		model.addAttribute("displayEdit", displayEdit);

	}

	/**
	 * Update assessment.
	 *
	 * @param command the command
	 * @param prev the prev
	 * @param curr the curr
	 * @param nxt the nxt
	 * @param assessmentInfo the assessment info
	 * @param model the model
	 * @param result the result
	 * @param authentication the authentication
	 * @return the string
	 * @throws Exception the exception
	 */
	@PostMapping

	public String updateAssessment(@RequestParam("command") String command, @RequestParam("prev") int prev,
			@RequestParam("curr") int curr, @RequestParam("nxt") int nxt,
			@ModelAttribute("assessmentInfo") AssessmentInfo assessmentInfo, Model model, BindingResult result,
			Authentication authentication) throws Exception {

		String page = "";
		
		
		
		
		if (command.contains("Cancel")) {
			return "redirect:/home";
		}

		if (cycleManager.getCycleInfo(assessmentInfo.getCycleId()).getCycleStatusCode() != null)
			return "redirect:selfassessment?cycleId=" + assessmentInfo.getCycleId();

		if (command.contains("Next")) {
			page = pageForms.get(nxt);

			if (curr == 0) {
				if (!validateAssessment(assessmentInfo, result))
					page = pageForms.get(curr);

			} else if (curr == 1) {
				if (!validateGoal(assessmentInfo, result))
					page = pageForms.get(curr);
			}

		} else if (command.contains("Back")) {
			page = pageForms.get(prev);
		}

		if (command.equals("Save")) {

			if (assessmentInfo.isCompleteSelfAssessment() && assessmentInfo.isEnableCompletion())
				assessmentInfo.setCompleted('Y');

			GoalPlanInfo finalizedGoalInfo = goalManager.getFinalizedGoalInfo(assessmentInfo.getCycleId());
			finalizedGoalInfo.setGoalDesc(assessmentInfo.getGoalPlanInfo().getGoalDesc());
			
			try{
			goalManager.save(finalizedGoalInfo);
			}catch(Exception e){
				goalManager.save(finalizedGoalInfo);
			}
			
			try{
				selfassessmentRubricManager.saveAssessment(assessmentInfo);
				}catch(Exception e){
					selfassessmentRubricManager.saveAssessment(assessmentInfo);
				}
			
			
			return "redirect:selfassessment?cycleId=" + assessmentInfo.getCycleId();
		}

		if (command.equals("Return for edits")) {
			assessmentManager.unlockAsssessment(assessmentInfo.getAssessmentId());
			return "redirect:selfassessment?cycleId=" + assessmentInfo.getCycleId();
		}

		loadAssessment(assessmentInfo, assessmentInfo.getCycleId(), model, authentication);

		return page;

	}

	/**
	 * Adds the attribute.
	 *
	 * @param attType the att type
	 * @param assessmentInfo the assessment info
	 * @param mode the mode
	 * @return the string
	 */
	@PostMapping("addrow")
	public String addAttribute(@RequestParam("attType") String attType,
			@ModelAttribute("assessmentInfo") AssessmentInfo assessmentInfo, Model mode) {

		List<SelfAssessmentAttributeInfo> attributes = attType.equals("1") ? assessmentInfo.getStrengths()
				: assessmentInfo.getGrowths();
		SelfAssessmentAttributeInfo selfAssessmentAttributeInfo = new SelfAssessmentAttributeInfo();
		selfAssessmentAttributeInfo.setTypeCode(attType);
		selfAssessmentAttributeInfo.setElements(selfassessmentRubricManager.getSelfAssessmentElementInfo());
		attributes.add(selfAssessmentAttributeInfo);
		
		
		if (attType.equals("1"))
			assessmentInfo.setCanAddStrengths(false);
			else
			assessmentInfo.setCanAddGrowths(false);
		
	
		if (attType.equals("1"))
			return "selfassessmentStrengthTable";
		else
			return "selfassessmentGrowthTable";

	}

	/**
	 * Delete attribute.
	 *
	 * @param attType the att type
	 * @param attIndex the att index
	 * @param assessmentInfo the assessment info
	 * @param mode the mode
	 * @return the string
	 */
	@PostMapping("deleterow")
	public String deleteAttribute(@RequestParam("attType") String attType, @RequestParam("attIndex") int attIndex,
			@ModelAttribute("assessmentInfo") AssessmentInfo assessmentInfo, Model mode) {
		
		

		List<SelfAssessmentAttributeInfo> deleteAttributes = attType.equals("1") ? assessmentInfo.getDeletedStrengths()
				: assessmentInfo.getDeletedGrowths();
		List<SelfAssessmentAttributeInfo> attributes = attType.equals("1") ? assessmentInfo.getStrengths()
				: assessmentInfo.getGrowths();

		SelfAssessmentAttributeInfo selfAssessmentAttributeInfo = attributes.remove(attIndex);

		if (selfAssessmentAttributeInfo != null && selfAssessmentAttributeInfo.getId() != null)
			deleteAttributes.add(selfAssessmentAttributeInfo);

		if (attributes.size() < 1)
			addAttribute(attType, assessmentInfo, mode);
		
		int size=attType.equals("1") ? assessmentInfo.getStrengths().size()
				: assessmentInfo.getGrowths().size();
		
		selfAssessmentAttributeInfo=attType.equals("1") ? assessmentInfo.getStrengths().get(size-1)
				: assessmentInfo.getGrowths().get(size-1);
		
		if(selfAssessmentAttributeInfo.getArea()!=null&&selfAssessmentAttributeInfo.getArea().trim().length()>0 &&  selfAssessmentAttributeInfo.getRationale()!=null&&selfAssessmentAttributeInfo.getRationale().trim().length()>0 && selfAssessmentAttributeInfo.getElements().stream().anyMatch(temp1 -> temp1.isSelected())){
			if (attType.equals("1"))
				assessmentInfo.setCanAddStrengths(true);
				else
				assessmentInfo.setCanAddGrowths(true);
		}else{
			if (attType.equals("1"))
				assessmentInfo.setCanAddStrengths(false);
				else
				assessmentInfo.setCanAddGrowths(false);
		}
		
		


		if (attType.equals("1"))
			return "selfassessmentStrengthTable";
		else
			return "selfassessmentGrowthTable";

	}

	/**
	 * Validate assessment.
	 *
	 * @param assessmentInfo the assessment info
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validateAssessment(AssessmentInfo assessmentInfo, BindingResult bindingResult) {
		boolean result = true;
		int idx = 0;
		/*for (SelfAssessmentAttributeInfo selfAssessmentAttributeInfo : assessmentInfo.getGrowths()) {
			result = CAPValidationUtil.validateText(selfAssessmentAttributeInfo.getArea(), "growths[" + idx + "].area",
					bindingResult, new Object[] { "Area(s) of Growths" }) && result;
			result = CAPValidationUtil.validateText(selfAssessmentAttributeInfo.getRationale(),
					"growths[" + idx + "].rationale", bindingResult, new Object[] { "Evidence/Rationale" }) && result;

		}

		idx = 0;
		for (SelfAssessmentAttributeInfo selfAssessmentAttributeInfo : assessmentInfo.getStrengths()) {
			result = CAPValidationUtil.validateText(selfAssessmentAttributeInfo.getArea(),
					"strengths[" + idx + "].area", bindingResult, new Object[] { "Area(s) of Strengths" }) && result;
			result = CAPValidationUtil.validateText(selfAssessmentAttributeInfo.getRationale(),
					"strengths[" + idx + "].rationale", bindingResult, new Object[] { "Evidence/Rationale" }) && result;

		}*/

		return result;
	}

	/**
	 * Validate goal.
	 *
	 * @param assessmentInfo the assessment info
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validateGoal(AssessmentInfo assessmentInfo, BindingResult bindingResult) {
		boolean result = true;

		/*result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getGoalDesc(), "goalPlanInfo.goalDesc",
				bindingResult, new Object[] { "Draft Professional Practice Goal" });
		result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getImportance(),
				"goalPlanInfo.importance", bindingResult, new Object[] { "Topic/focus area" }) && result;
		result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getAction(), "goalPlanInfo.action",
				bindingResult, new Object[] { "Action" }) && result;
		result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getSupport(), "goalPlanInfo.support",
				bindingResult, new Object[] { "Support" }) && result;
		result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getAchievementTime(),
				"goalPlanInfo.achievementTime", bindingResult, new Object[] { "Achievement Time" }) && result;
		result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getRealisticMeasure(),
				"goalPlanInfo.realisticMeasure", bindingResult, new Object[] { "Realistic Measure" }) && result;
		result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getProgressMeasure(),
				"goalPlanInfo.progressMeasure", bindingResult, new Object[] { "Progress Measure" }) && result;
		result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getSuccessEval(),
				"goalPlanInfo.successEval", bindingResult, new Object[] { "Success Evaluate" }) && result;
		result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getSkillsAcquired(),
				"goalPlanInfo.skillsAcquired", bindingResult, new Object[] { "Skills Acquired" }) && result;
		result = CAPValidationUtil.validateText(assessmentInfo.getGoalPlanInfo().getSkillsAcquired(),
				"goalPlanInfo.feedback", bindingResult, new Object[] { "Feedback" }) && result;*/

		return result;
	}

}
