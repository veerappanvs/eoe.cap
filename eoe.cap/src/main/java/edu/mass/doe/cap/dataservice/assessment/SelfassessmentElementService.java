package edu.mass.doe.cap.dataservice.assessment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.RubricMap;
import edu.mass.doe.cap.dataservice.entity.SelfassessmentElement;

/**
 * The Class SelfassessmentElementService.
 */
@Repository
public class SelfassessmentElementService {
	

	
	
	private static Logger logger = LoggerFactory.getLogger(SelfassessmentElementService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private SelfassessmentElementRepository selfassessmentElementRepository;
	
	
	

	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the selfassessment element
	 */
	public SelfassessmentElement findByPrimaryKey(Long primaryKey) {
		return entityManager.find(SelfassessmentElement.class, primaryKey);
	}
	
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the selfassessment element
	 */
	@Transactional(value=TxType.REQUIRED)
	public SelfassessmentElement create(SelfassessmentElement obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the selfassessment element
	 */
	@Transactional(value=TxType.REQUIRED)
	public SelfassessmentElement update(SelfassessmentElement obj) {
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
		SelfassessmentElement selfassessmentRubric = entityManager.find(SelfassessmentElement.class, primaryKey);
		entityManager.remove(selfassessmentRubric);

	}

	/**
	 * Find byself assessment attribute.
	 *
	 * @param attributeId the attribute id
	 * @param elementCode the element code
	 * @return the selfassessment element
	 */
	public 	SelfassessmentElement findByselfAssessmentAttribute(Long attributeId,String elementCode)
	{
		return selfassessmentElementRepository.find_SelfAssessmentAttribute(attributeId, elementCode);
	}



}
