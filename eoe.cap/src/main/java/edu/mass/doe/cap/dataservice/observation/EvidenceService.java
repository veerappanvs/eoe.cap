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
import edu.mass.doe.cap.dataservice.entity.Evidence;
import edu.mass.doe.cap.dataservice.entity.Observation;
import edu.mass.doe.cap.dataservice.pojo.EvidenceInfo;
import edu.mass.doe.cap.dataservice.pojo.ObservationInfo;


/**
 * The Class EvidenceService.
 */
@Repository
public class EvidenceService {
	
	
	private static Logger logger = LoggerFactory.getLogger(EvidenceService.class);

	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	EvidenceRepository evidenceRepository;
	
	@Autowired
	private Environment env;
	
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the evidence
	 */
	public Evidence findByPrimaryKey(Long primaryKey) {
		return entityManager.find(Evidence.class, primaryKey);
	}
	
	/**
	 * Find evidencefor cylce.
	 *
	 * @param observationId the observation id
	 * @param elementCode the element code
	 * @return the evidence
	 */
	public Evidence findEvidenceforCylce(Long observationId,String elementCode){
		return evidenceRepository.findEvidenceforCylce(observationId, elementCode);
	}


/**
 * Creates the.
 *
 * @param obj the obj
 * @return the evidence
 */
@Transactional(value=TxType.REQUIRED)
public Evidence create(Evidence obj) {
	entityManager.persist(obj);
	return obj;
	
}
	
/**
 * Update.
 *
 * @param obj the obj
 * @return the evidence
 */
@Transactional(value=TxType.REQUIRED)
public Evidence update(Evidence obj) {
	entityManager
	.merge(obj);		
	return obj;
}

/**
 * Delete.
 *
 * @param primaryKey the primary key
 */
@Transactional(value=TxType.REQUIRED)
public void delete(Long primaryKey) {
	
	Evidence evidence = 
			entityManager
			.find(Evidence.class, primaryKey);
	entityManager
	.remove(evidence);
	
}

}
