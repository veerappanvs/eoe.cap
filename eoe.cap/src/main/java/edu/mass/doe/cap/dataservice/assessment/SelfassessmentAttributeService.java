package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

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
import edu.mass.doe.cap.dataservice.entity.SelfAssessmentAttribute;
import edu.mass.doe.cap.dataservice.entity.SelfassessmentElement;

/**
 * The Class SelfassessmentAttributeService.
 */
@Repository
public class SelfassessmentAttributeService {
	

	
	
	private static Logger logger = LoggerFactory.getLogger(SelfassessmentAttributeService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	
	@Autowired
	private SelfAssessmentAttributeRepository selfAssessmentAttributeRepository;

	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the self assessment attribute
	 */
	public  SelfAssessmentAttribute findByPrimaryKey(Long primaryKey) {
		return entityManager.find(SelfAssessmentAttribute.class, primaryKey);
	}
	
	/**
	 * Find attributes.
	 *
	 * @param cycleId the cycle id
	 * @param typeCode the type code
	 * @return the list
	 */
	public List<SelfAssessmentAttribute> findAttributes(Long cycleId ,String typeCode){
		 return selfAssessmentAttributeRepository.findAttributes(cycleId,typeCode);
	 }
	
	/**
	 * Find by assessment.
	 *
	 * @param assessmentId the assessment id
	 * @param typeCode the type code
	 * @param attributeNumber the attribute number
	 * @return the self assessment attribute
	 */
	public SelfAssessmentAttribute findByAssessment(Long assessmentId ,String typeCode,Long attributeNumber){
		 return selfAssessmentAttributeRepository.find_Assessment(assessmentId, typeCode,attributeNumber);
	 }
	


	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the self assessment attribute
	 */
	@Transactional(value=TxType.REQUIRED)
	public SelfAssessmentAttribute create(SelfAssessmentAttribute obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the self assessment attribute
	 */
	@Transactional(value=TxType.REQUIRED)
	public SelfAssessmentAttribute update(SelfAssessmentAttribute obj) {
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
		SelfAssessmentAttribute selfAssessmentAttribute = entityManager.find(SelfAssessmentAttribute.class, primaryKey);
		entityManager.remove(selfAssessmentAttribute);

	}





}
