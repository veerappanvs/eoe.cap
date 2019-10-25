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

import edu.mass.doe.cap.dataservice.entity.AssessmentElementLink;

/**
 * The Class AssessmentElementLinkService.
 */
@Repository
public class AssessmentElementLinkService {
	

	
	private static Logger logger = LoggerFactory.getLogger(AssessmentElementLinkService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private AssessmentElementLinkRepository assessmentElementLinkRepository;

	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the assessment element link
	 */
	public AssessmentElementLink findByPrimaryKey(Long primaryKey) {
		return entityManager.find(AssessmentElementLink.class, primaryKey);
	}
	
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the assessment element link
	 */
	@Transactional(value=TxType.REQUIRED)
	public AssessmentElementLink create(AssessmentElementLink obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the assessment element link
	 */
	@Transactional(value=TxType.REQUIRED)
	public AssessmentElementLink update(AssessmentElementLink obj) {
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
		AssessmentElementLink assessmentElementLink = entityManager.find(AssessmentElementLink.class, primaryKey);
		entityManager.remove(assessmentElementLink);

	}



	/**
	 * Find assessment element link by assessment.
	 *
	 * @param assessmentId the assessment id
	 * @param elementCode the element code
	 * @param attributeType the attribute type
	 * @return the assessment element link
	 */
	public 	AssessmentElementLink findAssessmentElementLinkByAssessment(Long assessmentId,String elementCode,String attributeType){
		return assessmentElementLinkRepository.findAssessmentElementLinkByAssessment(assessmentId, elementCode, attributeType);
	}






}
