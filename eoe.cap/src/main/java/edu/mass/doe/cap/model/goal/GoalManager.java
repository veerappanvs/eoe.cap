
package edu.mass.doe.cap.model.goal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.dataservice.assessment.ElementTypeService;
import edu.mass.doe.cap.dataservice.candidate.CycleService;
import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.GoalLearningMeasure;
import edu.mass.doe.cap.dataservice.entity.GoalPlan;
import edu.mass.doe.cap.dataservice.entity.GoalPlanElement;
import edu.mass.doe.cap.dataservice.entity.GoalPlanImplementation;
import edu.mass.doe.cap.dataservice.entity.SelfAssessmentAttribute;
import edu.mass.doe.cap.dataservice.entity.SelfassessmentElement;
import edu.mass.doe.cap.dataservice.goal.GoalElementService;
import edu.mass.doe.cap.dataservice.goal.GoalLearningMeasureService;
import edu.mass.doe.cap.dataservice.goal.GoalPlanImplementationService;
import edu.mass.doe.cap.dataservice.goal.GoalPlanService;
import edu.mass.doe.cap.dataservice.pojo.GoalPlanInfo;
import edu.mass.doe.cap.dataservice.pojo.ImplementationPlanInfo;
import edu.mass.doe.cap.dataservice.pojo.SelfAssessmentAttributeInfo;
import edu.mass.doe.cap.dataservice.pojo.SelfAssessmentElementInfo;
import edu.mass.doe.cap.util.CapUtil;
import edu.mass.doe.cap.dataservice.pojo.AssessmentInfo;
import edu.mass.doe.cap.dataservice.pojo.GoalElementInfo;
import edu.mass.doe.cap.dataservice.pojo.GoalLearningMeasureInfo;

/**
 * The Class GoalManager.
 */
@Component
public class GoalManager {

	@Autowired
	private ElementTypeService elementTypeService;

	@Autowired
	private GoalPlanService goalPlanService;

	@Autowired
	private GoalElementService goalElementService;

	@Autowired
	private GoalPlanImplementationService goalPlanImplementationService;

	@Autowired
	private GoalLearningMeasureService goalLearningMeasureService;

	@Autowired
	private CycleService cycleService;

	/**
	 * Gets the finalized goal info.
	 *
	 * @param cycleId the cycle id
	 * @return the finalized goal info
	 */
	public GoalPlanInfo getFinalizedGoalInfo(Long cycleId) {
		GoalPlanInfo goalPlanInfo = new GoalPlanInfo();
		goalPlanInfo.setCycleId(cycleId);
		goalPlanInfo.setTypeCode("2");
		goalPlanInfo.setElements(getGoalElementInfo());
		goalPlanInfo.setImplementationPlanInfos(getImplementationInfos(goalPlanInfo));

		List<GoalLearningMeasureInfo> goalLearningMeasureInfos = new ArrayList<GoalLearningMeasureInfo>();

		GoalPlan goalPlan = goalPlanService.findGoalByCycleId(cycleId,"2");

		GoalLearningMeasureInfo highInfo = new GoalLearningMeasureInfo();
		highInfo.setTypeCode("1");
		GoalLearningMeasureInfo moderateInfo = new GoalLearningMeasureInfo();
		moderateInfo.setTypeCode("2");
		GoalLearningMeasureInfo lowInfo = new GoalLearningMeasureInfo();
		lowInfo.setTypeCode("3");

		goalLearningMeasureInfos.add(highInfo);
		goalLearningMeasureInfos.add(moderateInfo);
		goalLearningMeasureInfos.add(lowInfo);
		goalPlanInfo.setGoalLearningMeasureInfos(goalLearningMeasureInfos);


		if (goalPlan != null) {

			goalPlanInfo.setId(goalPlan.getPlanId());
			goalPlanInfo.setGoalDesc(goalPlan.getGoalDesc());
			goalPlanInfo.setImportance(goalPlan.getImportance());
			goalPlanInfo.setAction(goalPlan.getAction());
			goalPlanInfo.setSupport(goalPlan.getSupport());
			goalPlanInfo.setLearningMeasure(goalPlan.getLearningMeasure());
			goalPlanInfo.setSkillsAcquired(goalPlan.getAcquired());
			goalPlanInfo.setSuccessEval(goalPlan.getSuccessEval());
			goalPlanInfo.setProgressMeasure(goalPlan.getProgressMeasure());
			goalPlanInfo.setRealisticMeasure(goalPlan.getRealisticMeasure());
			goalPlanInfo.setAchievementTime(goalPlan.getAchievementTime());
			goalPlanInfo.setFeedback(goalPlan.getFeedback());
			goalPlanInfo.setComplete(goalPlan.getComplete());

			if (goalPlan.getComplete() != null && goalPlan.getComplete().equals('Y'))
				goalPlanInfo.setCompleteGoal(true);

			goalPlanInfo.getElements().stream().forEach(elementInfo -> {
				List<GoalPlanElement> elements = goalPlan.getGoalPlanElements();
				if (elements != null) {
					GoalPlanElement goalPlanElement = elements.stream()
							.filter(temp -> temp.getElementCode().equals(elementInfo.getCode())).findFirst()
							.orElse(null);
					if (goalPlanElement != null){
						elementInfo.setId(goalPlanElement.getId());
						elementInfo.setSelected(true);
					}
				}

			});

			List<GoalPlanImplementation> goalPlanImplementations = goalPlan.getGoalPlanImplementations();

			goalPlanImplementations = goalPlanImplementations != null ? goalPlanImplementations
					: new ArrayList<GoalPlanImplementation>();
			List<ImplementationPlanInfo> implementationPlanInfos = new ArrayList<ImplementationPlanInfo>();

			int size = goalPlanImplementations.size();

			int idx = 0;

			do {

				ImplementationPlanInfo implementationPlanInfo = new ImplementationPlanInfo();

				GoalPlanImplementation goalPlanImplementation = size > 0 ? goalPlanImplementations.get(idx)
						: new GoalPlanImplementation();

				implementationPlanInfo.setId(goalPlanImplementation.getId());
				implementationPlanInfo.setActionEvidence(goalPlanImplementation.getActionEvidence());
				implementationPlanInfo.setActionSupport(goalPlanImplementation.getActionSupport());
				implementationPlanInfo.setActionTimeline(goalPlanImplementation.getActionTimeline());
				implementationPlanInfo.setActionDesc(goalPlanImplementation.getActionDesc());
				implementationPlanInfo.setActionNumber(goalPlanImplementation.getActionNumber());
				implementationPlanInfos.add(implementationPlanInfo);
				idx++;
			} while (size > idx);

			goalPlanInfo.setImplementationPlanInfos(implementationPlanInfos);

			goalLearningMeasureInfos.stream().forEach(temp -> {

				GoalLearningMeasure goalLearningMeasure = goalPlan.getGoalLearningMeasure().stream()
						.filter(temp1 -> temp1.getImpactCode().equals(temp.getTypeCode())).findFirst().orElse(null);

				if (goalLearningMeasure != null) {
					temp.setId(goalLearningMeasure.getId());
					temp.setParameter(goalLearningMeasure.getParameter());
				}

			});

		}

		goalPlanInfo.setPrelimGoalPlanInfo(getPrelimanaryGoalInfo(cycleId));

		return goalPlanInfo;

	}

	/**
	 * Gets the implementation infos.
	 *
	 * @param goalPlanInfo the goal plan info
	 * @return the implementation infos
	 */
	private List<ImplementationPlanInfo> getImplementationInfos(GoalPlanInfo goalPlanInfo) {

		List<ImplementationPlanInfo> implementationPlanInfos = new ArrayList<ImplementationPlanInfo>();

		ImplementationPlanInfo implementationPlanInfo = new ImplementationPlanInfo();

		implementationPlanInfos.add(implementationPlanInfo);

		return implementationPlanInfos;

	}

	/**
	 * Gets the prelimanary goal info.
	 *
	 * @param cycleId the cycle id
	 * @return the prelimanary goal info
	 */
	public GoalPlanInfo getPrelimanaryGoalInfo(Long cycleId) {
		GoalPlanInfo goalPlanInfo = new GoalPlanInfo();
		goalPlanInfo.setTypeCode("1");
		goalPlanInfo.setElements(getGoalElementInfo());

		GoalPlan goalPlan = goalPlanService.findGoalByCycleId(cycleId,"1");
		;

		if (goalPlan != null) {
			goalPlanInfo.setId(goalPlan.getPlanId());
			goalPlanInfo.setGoalDesc(goalPlan.getGoalDesc());
			goalPlanInfo.setImportance(goalPlan.getImportance());
			goalPlanInfo.setAction(goalPlan.getAction());
			goalPlanInfo.setSupport(goalPlan.getSupport());
			goalPlanInfo.setLearningMeasure(goalPlan.getLearningMeasure());
			goalPlanInfo.setSkillsAcquired(goalPlan.getAcquired());
			goalPlanInfo.setSuccessEval(goalPlan.getSuccessEval());
			goalPlanInfo.setProgressMeasure(goalPlan.getProgressMeasure());
			goalPlanInfo.setRealisticMeasure(goalPlan.getRealisticMeasure());
			goalPlanInfo.setAchievementTime(goalPlan.getAchievementTime());
			goalPlanInfo.setFeedback(goalPlan.getFeedback());
			goalPlanInfo.setComplete(goalPlan.getComplete());

			goalPlanInfo.getElements().stream().forEach(elementInfo -> {

				List<GoalPlanElement> elements = goalPlan.getGoalPlanElements();
				if (elements != null) {
					GoalPlanElement goalPlanElement = elements.stream()
							.filter(temp -> temp.getElementCode().equals(elementInfo.getCode())).findFirst()
							.orElse(null);
					if (goalPlanElement != null){
						elementInfo.setId(goalPlanElement.getId());
						elementInfo.setSelected(true);
					}
				}

			});

		}

		return goalPlanInfo;
	}

	/**
	 * Gets the goal element info.
	 *
	 * @return the goal element info
	 */
	public List<GoalElementInfo> getGoalElementInfo() {

		List<ElementType> elementsTypes = elementTypeService.findAll();

		List<GoalElementInfo> GoalElementInfos = new ArrayList<GoalElementInfo>();

		elementsTypes.stream().forEach(elementType -> {
			GoalElementInfo GoalElementInfo = new GoalElementInfo();
			GoalElementInfo.setCode(elementType.getElementCode());
			GoalElementInfo.setLabel(elementType.getLabel());
			GoalElementInfo.setDesc(elementType.getAltDesc());
			GoalElementInfos.add(GoalElementInfo);
		});

		return GoalElementInfos;
	}

	/**
	 * Save.
	 *
	 * @param assessmentInfo the assessment info
	 */
	public void save(AssessmentInfo assessmentInfo) {

		GoalPlanInfo goalPlanInfo = assessmentInfo.getGoalPlanInfo();
		GoalPlan goalPlan = goalPlanService.findGoalByCycleId(assessmentInfo.getCycleId(), goalPlanInfo.getTypeCode());


		if(goalPlan==null){
			goalPlan = new GoalPlan();
			goalPlan.setCapCycle(cycleService.findByPrimaryKey(assessmentInfo.getCycleId()));
			goalPlan.setEffDate(new Date());
			goalPlan.setTypeCode(goalPlanInfo.getTypeCode());
		}

		goalPlan.setGoalDesc(goalPlanInfo.getGoalDesc());

		goalPlan.setImportance(goalPlanInfo.getImportance());
		goalPlan.setAction(goalPlanInfo.getAction());
		goalPlan.setSupport(goalPlanInfo.getSupport());
		goalPlan.setLearningMeasure(goalPlanInfo.getLearningMeasure());
		goalPlan.setAcquired(goalPlanInfo.getSkillsAcquired());
		goalPlan.setSuccessEval(goalPlanInfo.getSuccessEval());
		goalPlan.setProgressMeasure(goalPlanInfo.getProgressMeasure());
		goalPlan.setRealisticMeasure(goalPlanInfo.getRealisticMeasure());
		goalPlan.setAchievementTime(goalPlanInfo.getAchievementTime());
		goalPlan.setFeedback(goalPlanInfo.getFeedback());
		goalPlan.setComplete(goalPlanInfo.getComplete());

		if (goalPlanInfo.getId() != null) {
			goalPlanService.update(goalPlan);
		} else {
			goalPlan = goalPlanService.create(goalPlan);
		}

		for (GoalElementInfo goalElementInfo : goalPlanInfo.getElements()) {

			if (goalElementInfo.getId() != null && !goalElementInfo.isSelected()) {
				GoalPlanElement goalElement = goalElementService.findByPrimaryKey(goalElementInfo.getId());
				goalElement.setExpDate(new Date());
				goalElementService.update(goalElement);
				continue;
			}
			
				GoalPlanElement goalElement=goalElementService.findGoalPlanElement(goalPlan.getPlanId(), goalElementInfo.getCode());
				if (goalElementInfo.isSelected() && goalElement == null) {
				goalElement = new GoalPlanElement();
				goalElement.setEffDate(new Date());
				goalElement.setElementCode(goalElementInfo.getCode());
				goalElement.setGoalPlan(goalPlan);
				goalElementService.create(goalElement);
			}
				

		}

	}

	/**
	 * Save.
	 *
	 * @param goalPlanInfo the goal plan info
	 */
	public void save(GoalPlanInfo goalPlanInfo) {

		GoalPlan goalPlan = goalPlanService.findGoalByCycleId(goalPlanInfo.getCycleId(), goalPlanInfo.getTypeCode());



		if (goalPlan== null) {
			goalPlan = new GoalPlan();
			goalPlan.setCapCycle(cycleService.findByPrimaryKey(goalPlanInfo.getCycleId()));
			goalPlan.setEffDate(new Date());
			goalPlan.setTypeCode(goalPlanInfo.getTypeCode());
		}

		goalPlan.setGoalDesc(goalPlanInfo.getGoalDesc());

		goalPlan.setImportance(goalPlanInfo.getImportance());
		goalPlan.setAction(goalPlanInfo.getAction());
		goalPlan.setSupport(goalPlanInfo.getSupport());
		goalPlan.setLearningMeasure(goalPlanInfo.getLearningMeasure());
		goalPlan.setAcquired(goalPlanInfo.getSkillsAcquired());
		goalPlan.setSuccessEval(goalPlanInfo.getSuccessEval());
		goalPlan.setProgressMeasure(goalPlanInfo.getProgressMeasure());
		goalPlan.setRealisticMeasure(goalPlanInfo.getRealisticMeasure());
		goalPlan.setAchievementTime(goalPlanInfo.getAchievementTime());
		goalPlan.setFeedback(goalPlanInfo.getFeedback());
		goalPlan.setComplete(goalPlanInfo.getComplete());

		if (goalPlan.getPlanId()!= null) {
			goalPlanService.update(goalPlan);
		} else {
			goalPlan = goalPlanService.create(goalPlan);
		}

			for (GoalElementInfo goalElementInfo : goalPlanInfo.getElements()) {

				if (goalElementInfo.getId() != null && !goalElementInfo.isSelected()) {
					GoalPlanElement goalElement = goalElementService.findByPrimaryKey(goalElementInfo.getId());
					goalElement.setExpDate(new Date());
					goalElementService.update(goalElement);
					continue;
				}
				GoalPlanElement goalElement=goalElementService.findGoalPlanElement(goalPlan.getPlanId(), goalElementInfo.getCode());
				if (goalElementInfo.isSelected() && goalElement == null) {
					goalElement = new GoalPlanElement();
					goalElement.setEffDate(new Date());
					goalElement.setElementCode(goalElementInfo.getCode());
					goalElement.setGoalPlan(goalPlan);
					goalElementService.create(goalElement);
				}

			}
		
		List<ImplementationPlanInfo> implementationPlanInfos=	goalPlanInfo.getImplementationPlanInfos();

		for (ImplementationPlanInfo implementationPlanInfo : goalPlanInfo.getImplementationPlanInfos()) {

			GoalPlanImplementation goalPlanImplementation = goalPlanImplementationService.findGoalImplementation(goalPlan.getPlanId(),implementationPlanInfo.getActionNumber()!=null?implementationPlanInfo.getActionNumber():new Long(implementationPlanInfos.indexOf(implementationPlanInfo)));

			if (goalPlanImplementation== null){
				goalPlanImplementation = new GoalPlanImplementation();
				goalPlanImplementation.setEffDate(new Date());
				goalPlanImplementation.setGoalPlan(goalPlan);
			}

			goalPlanImplementation.setActionEvidence(implementationPlanInfo.getActionEvidence());
			goalPlanImplementation.setActionSupport(implementationPlanInfo.getActionSupport());
			goalPlanImplementation.setActionTimeline(implementationPlanInfo.getActionTimeline());
			goalPlanImplementation.setActionDesc(implementationPlanInfo.getActionDesc());
			goalPlanImplementation.setActionNumber(new Long(implementationPlanInfos.indexOf(implementationPlanInfo)));

			if (goalPlanImplementation.getId() != null)
				goalPlanImplementationService.update(goalPlanImplementation);
			else
				goalPlanImplementationService.create(goalPlanImplementation);

		}
		
		
		
		for (ImplementationPlanInfo implementationPlanInfo : goalPlanInfo.getDeletedImplementationPlanInfos()) {

			GoalPlanImplementation goalPlanImplementation = null;

			if (implementationPlanInfo.getId() != null){
				goalPlanImplementation = goalPlanImplementationService.findByPrimaryKey(implementationPlanInfo.getId());
				goalPlanImplementation.setExpDate(new Date());
				goalPlanImplementationService.update(goalPlanImplementation);
			}
			

		}

		for (GoalLearningMeasureInfo goalLearningMeasureInfo : goalPlanInfo.getGoalLearningMeasureInfos()) {

			GoalLearningMeasure goalLearningMeasure = goalLearningMeasureService.findGoalLearningMeasure(goalPlan.getPlanId(),goalLearningMeasureInfo.getTypeCode());

			if (goalLearningMeasure== null) {
				goalLearningMeasure = new GoalLearningMeasure();
				goalLearningMeasure.setEffDate(new Date());
				goalLearningMeasure.setGoalPlan(goalPlan);
				goalLearningMeasure.setImpactCode(goalLearningMeasureInfo.getTypeCode());
			}

			goalLearningMeasure.setParameter(goalLearningMeasureInfo.getParameter());

			if (goalLearningMeasure.getId() != null)
				goalLearningMeasureService.update(goalLearningMeasure);
			else
				goalLearningMeasureService.create(goalLearningMeasure);

		}

	}

	/**
	 * Unlock goal.
	 *
	 * @param id the id
	 */
	public void unlockGoal(Long id) {
		GoalPlan goalPlan = goalPlanService.findByPrimaryKey(id);
		goalPlan.setComplete('N');
		goalPlanService.update(goalPlan);
	}
}
