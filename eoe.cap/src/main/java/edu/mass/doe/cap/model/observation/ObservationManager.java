package edu.mass.doe.cap.model.observation;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.cap.dataservice.candidate.ObservationGroupLinkService;
import edu.mass.doe.cap.dataservice.entity.CycleGradeLevel;
import edu.mass.doe.cap.dataservice.entity.Evidence;
import edu.mass.doe.cap.dataservice.entity.Observation;
import edu.mass.doe.cap.dataservice.entity.ObservationGroupLink;
import edu.mass.doe.cap.dataservice.entity.ObservationGroupType;
import edu.mass.doe.cap.dataservice.entity.PracticumType;
import edu.mass.doe.cap.dataservice.observation.EvidenceService;
import edu.mass.doe.cap.dataservice.observation.ObservationGroupTypeService;
import edu.mass.doe.cap.dataservice.observation.ObservationService;
import edu.mass.doe.cap.dataservice.observation.ObservationTypeService;
import edu.mass.doe.cap.dataservice.pojo.EvidenceInfo;
import edu.mass.doe.cap.dataservice.pojo.ObservationInfo;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class ObservationManager.
 */
@Component
public class ObservationManager {

	private static Logger logger = LoggerFactory.getLogger(ObservationManager.class);

	@Autowired
	private Environment env;

	@Autowired
	private ObservationService observationService;

	@Autowired
	private EvidenceService evidenceService;

	@Autowired
	private ObservationGroupTypeService observationGroupTypeService;

	@Autowired
	private ObservationTypeService observationTypeService;
	
	@Autowired
	private ObservationGroupLinkService observationGroupLinkService;
	
	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;


	/**
	 * Gets the observation.
	 *
	 * @param cycleId the cycle id
	 * @param observationNumber the observation number
	 * @param typeCode the type code
	 * @return the observation
	 */
	public ObservationInfo getObservation(Long cycleId, Long observationNumber, String typeCode) {
	ObservationInfo observationInfo =	observationService.findObservation(cycleId, observationNumber, typeCode);
		
		if (observationInfo.getObservationId() != null){
		Observation	observation = observationService.findByPrimaryKey(observationInfo.getObservationId());
		observationInfo.setCompletedBy(observation.getCompletedBy());
		observationInfo.setCompleteDate(observation.getObsCompleteDate());
		List<String>  obl  = observationGroupLinkService.getAllGroupCodeForObservationid(observationInfo.getObservationId());
		logger.debug("After  getting  ObservationGroupLink list --> "+ obl);
		observationInfo.setGroupCode(obl);
		
		}
		return observationInfo;
	}

	
	/**
	 * Save observation.
	 *
	 * @param observationInfo the observation info
	 * @throws Exception the exception
	 */
	@Transactional(value = TxType.REQUIRES_NEW)
	public void saveObservation(ObservationInfo observationInfo) throws Exception {

		try{
		
		Observation observation=observationService.findObservationByCycleId(observationInfo.getCycleId(),
				new Long(observationInfo.getObservationNumber()), observationInfo.getObservationTypecode());
		
		if (observation==null)
		{	
			observation = new Observation();
		}

		observation.setCycleId(observationInfo.getCycleId());
		observation.setBothPSSPCompleted(observationInfo.getPsspCompleted());
		observation.setObservationDate(observationInfo.getObservationDate());
		observation.setStartTime(observationInfo.getStartTime());
		observation.setEndTime(observationInfo.getEndTime());
		
		observation.setTopicObjective(observationInfo.getTopicObjective());
		observation.setCompleted(observationInfo.getCompleted());
		observation.setCompletedBy(observationInfo.getCompletedBy());
		observation.setObsCompleteDate(observationInfo.getCompleteDate());
		observation.setCalRefineFeedback(observationInfo.getCalRefineFeedback());
		observation.setCalReinFeedback(observationInfo.getCalReinFeedback());
		observation.setObservationNumber(new Long(observationInfo.getObservationNumber()));

		observation
				.setObservationType(observationTypeService.findByPrimaryKey(observationInfo.getObservationTypecode()));

		if(CapUtil.isManager()||CapUtil.isSupervisor()){
		observation.setPsRefineFeedback(observationInfo.getPsRefineFeedback());
		observation.setPsReinFeedback(observationInfo.getPsReinFeedback());
		}

		if(CapUtil.ispractitioner()){
		observation.setSpRefineFeedback(observationInfo.getSpRefineFeedback());
		observation.setSpReinFeedback(observationInfo.getSpReinFeedback());
		}
		
		if (observationInfo.isEnableCompletion() && observationInfo.isCompleteObservation()) {

			if (observation.getBothPSSPCompleted().equals('B') && observation.getCompletedBy() != null)
				observation.setCompleted('Y');
			else if (observation.getBothPSSPCompleted().equals('P')||observation.getBothPSSPCompleted().equals('S'))
				observation.setCompleted('Y');
			else
				observation.setCompleted('N');

			observation.setCompletedBy(
					((EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
							.getPersonId());
			observation.setObsCompleteDate(new Date());

		}

		List<String> groupCodes = observationInfo.getGroupCode();
		Long observationid = observation.getObservationId();
		if (observationid != null) {

			List <ObservationGroupLink> groupLinks=observationGroupLinkService.findGroupsByObservation(observationid);
		
				for(ObservationGroupLink observationGroupLink : groupLinks) {
					  if (!groupCodes.remove(observationGroupLink.getGroupCode())) {
						  	observationGroupLink.setExpDate(new Date());
						  	observationGroupLinkService.update(observationGroupLink);
					  }					  
				}
				
				for(String groupCode : groupCodes)
					observationGroupLinkService.create(new ObservationGroupLink(observationid, groupCode, new Date()));
				
		}
		else {
				observation.setEffDate(new Date());
				observation = observationService.create(observation);
				observationid  = observation.getObservationId();
				for(String groupCode : groupCodes)
					observationGroupLinkService.create(new ObservationGroupLink(observationid, groupCode, new Date()));
		}

		for (EvidenceInfo evidenceInfo : observationInfo.getEvidences()) {

			if (evidenceInfo.getEvidenceId() != null&&!(evidenceInfo.getSpEvidence() != null || evidenceInfo.getCalEvidence() != null
					|| evidenceInfo.getPsEvidence() != null))
				continue;

			Evidence evidence=evidenceService.findEvidenceforCylce(observation.getObservationId(), evidenceInfo.getElementCode());

			if (evidence== null)
			{
				evidence = new Evidence();
				evidence.setObservation(observation);
			}

			evidence.setElementCode(evidenceInfo.getElementCode());
			evidence.setCalEvidence(evidenceInfo.getCalEvidence());
			
			if(CapUtil.ispractitioner())
			evidence.setSpEvidence(evidenceInfo.getSpEvidence());
			
			if(CapUtil.isManager()||CapUtil.isSupervisor())
			evidence.setPsEvidence(evidenceInfo.getPsEvidence());

			if (evidenceInfo.getEvidenceId() != null)
				evidenceService.update(evidence);
			else {
				evidence.setEffDate(new Date());
				evidence.setObservation(observation);
				evidenceService.create(evidence);
			}

		}
		}catch(Exception e){
			entityManager.flush();
			entityManager.clear();
			throw e;
		}

		entityManager.flush();
		entityManager.clear();
		
	}

	/**
	 * Gets the group types.
	 *
	 * @return the group types
	 */
	public List<ObservationGroupType> getGroupTypes() {
		return observationGroupTypeService.findAll();
	}

	/**
	 * Gets the candidate completed observation.
	 *
	 * @param cycleId the cycle id
	 * @return the candidate completed observation
	 */
	public List<Observation> getCandidateCompletedObservation(Long cycleId) {
		return observationService.findCompletedObservationByCycleId(cycleId);
	}

	/**
	 * Unlock obserrvation.
	 *
	 * @param id the id
	 */
	public void unlockObserrvation(Long id) {

		Observation observation = observationService.findByPrimaryKey(id);
		observation.setCompleted('N');
		observation.setCompletedBy(null);
		observation.setObsCompleteDate(null);
		observationService.update(observation);

	}
	
	
	/**
	 * Gets the max observation date.
	 *
	 * @param cycleId the cycle id
	 * @return the max observation date
	 */
	public Date getMaxObservationDate(Long cycleId){
		return observationService.findMaxObservationDate(cycleId);
	}

}
