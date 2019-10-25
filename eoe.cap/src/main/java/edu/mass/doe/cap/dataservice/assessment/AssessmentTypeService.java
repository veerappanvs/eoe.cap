package edu.mass.doe.cap.dataservice.assessment;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.AssessmentType;

/**
 * The Class AssessmentTypeService.
 */
@Repository
public class AssessmentTypeService {
	

	
	
	private static Logger logger = LoggerFactory.getLogger(AssessmentTypeService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	
		
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the assessment type
	 */
	public AssessmentType findByPrimaryKey(String primaryKey) {
		return entityManager.find(AssessmentType.class, primaryKey);
	}
	
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the assessment type
	 */
	@Transactional(value=TxType.REQUIRED)
	public AssessmentType create(AssessmentType obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the assessment type
	 */
	@Transactional(value=TxType.REQUIRED)
	public AssessmentType update(AssessmentType obj) {
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
		AssessmentType assessmentType = entityManager.find(AssessmentType.class, primaryKey);
		entityManager.remove(assessmentType);

	}



}
