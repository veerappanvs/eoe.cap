
package edu.mass.doe.cap.dataservice.observation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.entity.Observation;
import edu.mass.doe.cap.dataservice.pojo.EvidenceInfo;
import edu.mass.doe.cap.dataservice.pojo.ObservationInfo;

/**
 * The Class ObservationService.
 */
@Repository
public class ObservationService {

	private static Logger logger = LoggerFactory.getLogger(ObservationService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private ObservationRepository observationRepository;
	
	/**
	 * Find max observation date.
	 *
	 * @param cycleId the cycle id
	 * @return the date
	 */
	public Date findMaxObservationDate(Long cycleId){
		Query q = entityManager.createNativeQuery(env.getProperty("max.cycle.observationDate"));
		q.setParameter("cycleId", cycleId);


		Date date= (Date) q.getSingleResult();
		
		return date;
			
	}
	
	/**
	 * Find observation by cycle id.
	 *
	 * @param cycleId the cycle id
	 * @param observationNumber the observation number
	 * @param typeCode the type code
	 * @return the observation
	 */
	public Observation findObservationByCycleId(Long cycleId,Long observationNumber,String typeCode) {
		return observationRepository.find_ObservationByCycleId(cycleId, observationNumber, typeCode);
	}

	/**
	 * Find observation.
	 *
	 * @param cycleId the cycle id
	 * @param observationNumber the observation number
	 * @param typecode the typecode
	 * @return the observation info
	 */
	public ObservationInfo findObservation(Long cycleId,Long observationNumber,String typecode) {

		Query q =null;
		
		if(observationNumber.equals(1L) && typecode.equals("01")) 
		q = entityManager.createNativeQuery(env.getProperty("observation.announced01"));
		
		if(observationNumber.equals(2L) && typecode.equals("01")) 
			q = entityManager.createNativeQuery(env.getProperty("observation.announced02"));
		
		
		if(observationNumber.equals(1L) && typecode.equals("02")) 
			q = entityManager.createNativeQuery(env.getProperty("observation.unannounced01"));
		
		if(observationNumber.equals(2L) && typecode.equals("02")) 
			q = entityManager.createNativeQuery(env.getProperty("observation.unannounced02"));
		
		 
		 q.setParameter("cycleId", cycleId);

		List<Object[]> objects = q.getResultList();
		ObservationInfo observationInfo = new ObservationInfo();

		for (Object[] obj : objects) {
			observationInfo.setObservationTypecode((String) obj[0]);
			observationInfo.setObservationNumber(((BigDecimal) obj[1]).intValue());
			observationInfo.setPsspCompleted((Character) obj[2]);
			observationInfo.setCompleted((Character) obj[3]);
			
			
			if(obj[3]!=null && ((Character) obj[3]).equals('Y') )
				observationInfo.setCompleteObservation(true);
				
			
			observationInfo.setStatusDesc(observationInfo.isCompleteObservation()?"Complete":"Incomplete");
			
			if( obj[4]!=null)
			observationInfo.setObservationDate((Date) obj[4]);
			else
			observationInfo.setObservationDate(new Date());
			
			observationInfo.setStartTime((Date) obj[5]);
			observationInfo.setEndTime((Date) obj[6]);
			
			//Commented as a part of Multi Selection Group Code Changes.
			//observationInfo.setGroupCode((String) obj[7]);
			observationInfo.setTopicObjective((String) obj[8]);
			
			if(obj[21]!=null)
			observationInfo.setObservationId(((BigDecimal) obj[21]).longValue());
			
			observationInfo.setCycleId(cycleId);

			EvidenceInfo evidence = new EvidenceInfo();
			if(obj[23]!=null)
			evidence.setEvidenceId(((BigDecimal) obj[23]).longValue());
			evidence.setElementDesc((String) obj[9]);
			evidence.setFocusElement(((BigDecimal) obj[10]).intValue());
			evidence.setElementCode((String) obj[11]);
			evidence.setPsEvidence((String) obj[12]);
			evidence.setSpEvidence((String) obj[13]);
			evidence.setCalEvidence((String) obj[14]);
			
			observationInfo.getEvidences().add(evidence);

			observationInfo.setSpRefineFeedback((String) obj[15]);
			observationInfo.setPsRefineFeedback((String) obj[16]);
			observationInfo.setCalRefineFeedback((String) obj[17]);
			observationInfo.setSpReinFeedback((String) obj[18]);
			observationInfo.setPsReinFeedback((String) obj[19]);
			observationInfo.setCalReinFeedback((String) obj[20]);
			observationInfo.setObservationTypeDesc((String) obj[24]);
		}

		return observationInfo;

	}

	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the observation
	 */
	public Observation findByPrimaryKey(Long primaryKey) {
		return entityManager.find(Observation.class, primaryKey);
	}
	
	
	/**
	 * Find completed observation by cycle id.
	 *
	 * @param cycleId the cycle id
	 * @return the list
	 */
	public  List<Observation> findCompletedObservationByCycleId(Long cycleId){
	return observationRepository.findCompletedObservationByCycleId(cycleId);
	}


	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the observation
	 */
	@Transactional(value=TxType.REQUIRED)
	public Observation create(Observation obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the observation
	 */
	@Transactional(value=TxType.REQUIRED)
	public Observation update(Observation obj) {
		entityManager.merge(obj);
		return obj;
	}

	/**
	 * Delete.
	 *
	 * @param primaryKey the primary key
	 */
	@Transactional(value=TxType.REQUIRED)
	public void delete(Long primaryKey) {

		Observation observation = entityManager.find(Observation.class, primaryKey);
		entityManager.remove(observation);

	}

}
