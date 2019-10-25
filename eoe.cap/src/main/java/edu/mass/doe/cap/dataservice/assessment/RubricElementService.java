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

import edu.mass.doe.cap.dataservice.entity.RubricElement;

/**
 * The Class RubricElementService.
 */
@Repository
public class RubricElementService {
	
	private static Logger logger = LoggerFactory.getLogger(RubricElementService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private RubricElementRepository rubricElementRepository;
	

	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the rubric element
	 */
	public RubricElement findByPrimaryKey(Long primaryKey) {
		return entityManager.find(RubricElement.class, primaryKey);
	}
	
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the rubric element
	 */
	@Transactional(value=TxType.REQUIRED)
	public RubricElement create(RubricElement obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the rubric element
	 */
	@Transactional(value=TxType.REQUIRED)
	public RubricElement update(RubricElement obj) {
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
		RubricElement rubricElement = entityManager.find(RubricElement.class, primaryKey);
		entityManager.remove(rubricElement);

	}


	 /**
 	 * Find rubric element by assessment.
 	 *
 	 * @param assessmentId the assessment id
 	 * @param elementCode the element code
 	 * @return the rubric element
 	 */
 	public RubricElement findRubricElementByAssessment(Long assessmentId,String elementCode ){
	 return rubricElementRepository.findRubricElementByAssessment(assessmentId, elementCode);
	 }





}
