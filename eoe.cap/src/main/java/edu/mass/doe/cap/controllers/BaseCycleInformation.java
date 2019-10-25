package edu.mass.doe.cap.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mass.doe.cap.dataservice.entity.Observation;
import edu.mass.doe.cap.dataservice.pojo.AssessmentInfo;
import edu.mass.doe.cap.dataservice.pojo.ObservationMenuInfo;
import edu.mass.doe.cap.mail.EmailService;
import edu.mass.doe.cap.model.Meeting.ThreewayMeetingManager;
import edu.mass.doe.cap.model.assessment.AssessmentManager;
import edu.mass.doe.cap.model.assessment.SelfassessmentManager;
import edu.mass.doe.cap.model.candidate.CandidateManager;
import edu.mass.doe.cap.model.cycle.CycleManager;
import edu.mass.doe.cap.model.email.EmailManager;
import edu.mass.doe.cap.model.file.EvidenceFileManager;
import edu.mass.doe.cap.model.goal.GoalManager;
import edu.mass.doe.cap.model.observation.ObservationManager;
import edu.mass.doe.cap.util.CapUtil;

public class BaseCycleInformation extends BaseController {
	@Autowired(required = true)
	protected CycleManager cycleManager;
	
	@Autowired(required = true)
	protected CandidateManager candidateManager;
	
	@Autowired(required = true)
	protected ObservationManager observationManager;
	
	@Autowired(required = true)
	public ThreewayMeetingManager threewayMeetingManager;
	

	@Autowired(required = true)
	public AssessmentManager assessmentManager;
	

	@Autowired(required = true)
	public SelfassessmentManager selfassessmentRubricManager;
	

	@Autowired(required = true)
	public GoalManager goalManager;
	
	@Autowired(required = true)
	public EvidenceFileManager evidenceFileManager;
		
	@Autowired
	public Environment env;
	
	@Autowired(required = true)
	public EmailManager emailManager;
	
	/**
	 * Gets the candidate completed observations.
	 *
	 * @param cycleId the cycle id
	 * @param model the model
	 * @return the candidate completed observations
	 * @throws Exception the exception
	 */
	@ModelAttribute
	protected void getCandidateCompletedObservations(@RequestParam("cycleId") Long cycleId,Model model) throws Exception{
		
		model.addAttribute("cycleId",cycleId);
		
		boolean displaySummative=true;
		boolean displayFormative=true;
		boolean displayMessage=true;
		
		List<ObservationMenuInfo> linkIds = new ArrayList<ObservationMenuInfo>();
		
		if(CapUtil.isCandidate()){
			
			for(Observation observation: observationManager.getCandidateCompletedObservation(cycleId)){
				
				ObservationMenuInfo observationMenuInfo = new ObservationMenuInfo();				
				observationMenuInfo.setObservationTypeCode(observation.getObservationType().getTypeCode());
				observationMenuInfo.setTypeDesc(observation.getObservationType().getTypeDesc());
				observationMenuInfo.setCycleId(cycleId);
				observationMenuInfo.setObservationNumber(observation.getObservationNumber());
				
				linkIds.add(observationMenuInfo);
			}
			
			if(!assessmentManager.assessmentIsComplete(cycleId, "3"))
			displaySummative=false;
			
			if(!assessmentManager.assessmentIsComplete(cycleId, "2"))
			displayFormative=false;
			
				
			
		}else if(CapUtil.isManager() || CapUtil.ispractitioner() || CapUtil.isSupervisor()){
			
			
			ObservationMenuInfo observationMenuInfo = new ObservationMenuInfo();				
			observationMenuInfo.setObservationTypeCode("01");
			observationMenuInfo.setTypeDesc("Announced");
			observationMenuInfo.setCycleId(cycleId);
			observationMenuInfo.setObservationNumber(1L);
			linkIds.add(observationMenuInfo);
			
			observationMenuInfo = new ObservationMenuInfo();				
			observationMenuInfo.setObservationTypeCode("01");
			observationMenuInfo.setTypeDesc("Announced");
			observationMenuInfo.setCycleId(cycleId);
			observationMenuInfo.setObservationNumber(2L);
			linkIds.add(observationMenuInfo);
			
			
			observationMenuInfo = new ObservationMenuInfo();				
			observationMenuInfo.setObservationTypeCode("02");
			observationMenuInfo.setTypeDesc("Unannounced");
			observationMenuInfo.setCycleId(cycleId);
			observationMenuInfo.setObservationNumber(1L);
			linkIds.add(observationMenuInfo);
			
			observationMenuInfo = new ObservationMenuInfo();				
			observationMenuInfo.setObservationTypeCode("02");
			observationMenuInfo.setTypeDesc("Unannounced");
			observationMenuInfo.setCycleId(cycleId);
			observationMenuInfo.setObservationNumber(2L);
			linkIds.add(observationMenuInfo);			
		}
		
		model.addAttribute("observationsLinks",linkIds);
		model.addAttribute("displaySummative",displaySummative);
		model.addAttribute("displayFormative",displayFormative);
		
		if(cycleManager.getCycleInfo(cycleId).getCycleStatusCode()!=null)
			displayMessage=false;
		model.addAttribute("displayMessage",displayMessage);
		
	}
	
	
}
