package edu.mass.doe.cap.dataservice.assessment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.Assessment;
import edu.mass.doe.cap.dataservice.entity.ElementRating;
import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.pojo.EvidenceSource;

/**
 * The Class AssessmentService.
 */
@Repository
public class AssessmentService {
	

	
	
	private static Logger logger = LoggerFactory.getLogger(AssessmentService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	@Autowired
	private ElementTypeService elementTypeService;
	
	
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the assessment
	 */
	public Assessment findByPrimaryKey(Long primaryKey) {
		return entityManager.find(Assessment.class, primaryKey);
	}
	
	/**
	 * Find by cycle id.
	 *
	 * @param cycleId the cycle id
	 * @param typeCode the type code
	 * @return the assessment
	 */
	public Assessment findByCycleId(Long cycleId,String typeCode) {
		return assessmentRepository.findByCycle(cycleId, typeCode);
	}

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the assessment
	 */
	@Transactional(value=TxType.REQUIRED)
	public Assessment create(Assessment obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the assessment
	 */
	@Transactional(value=TxType.REQUIRED)
	public Assessment update(Assessment obj) {
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
		ElementRating observation = entityManager.find(ElementRating.class, primaryKey);
		entityManager.remove(observation);

	}
	
	public boolean assessmentIsComplete(Long cycleId,String typeCode) {
		
		Query q = entityManager.createNativeQuery(env.getProperty("assessment.isComplete"));
		q.setParameter("cycleId", cycleId);
		q.setParameter("typeCode", typeCode);

		int count = ((BigDecimal)q.getSingleResult()).intValue();	
		
		if(count>0)
			return true;
			else return false;
		}
	
	/**
	 * Gets the evidence source.
	 *
	 * @param cycleId the cycle id
	 * @return the evidence source
	 */
	public Map<String,EvidenceSource> getEvidenceSource(Long cycleId) {
		
		Query q = entityManager.createNativeQuery(env.getProperty("evidence.source.finalgoal"));
		q.setParameter("cycleId", cycleId);

		List<Object[]> results = q.getResultList();	
		boolean ppg =false;
		boolean msl=false;
		
		for (Object[] result : results) {
			if (result != null) {
				ppg = ((BigDecimal) result[0]).intValue() > 0 ? true : false;
				msl = ((BigDecimal) result[1]).intValue() > 0 ? true : false;
			}
		}
		
		Map<String,EvidenceSource> evidenceSources= new HashMap<String,EvidenceSource>();
		
		
		for(ElementType elementType:elementTypeService.findAll()){
		EvidenceSource evidenceSource = new EvidenceSource();
		evidenceSources.put(elementType.getElementCode(),evidenceSource);
		if(msl)
			evidenceSource.setMsl(true);
		
		if(ppg)
			evidenceSource.setPpg(true);
		
		}
		
		q = entityManager.createNativeQuery(env.getProperty("evidence.source.observation"));
		q.setParameter("cycleId", cycleId);

		results = q.getResultList();			
	

		for (Object[] result : results) {
			String elementCode =((String) result[0]);
			String typeCode=(((String) result[1]));
			int obsNumber=((BigDecimal) result[2]).intValue();
			int count =((BigDecimal) result[3]).intValue();
			
			EvidenceSource evidenceSource=evidenceSources.get(elementCode);
			
			if(typeCode.equals("01")&&obsNumber==01&&count>0)
				evidenceSource.setAnnounced1(true);
			else if(typeCode.equals("01")&&obsNumber==02&&count>0)
				evidenceSource.setAnnounced2(true);
			else if(typeCode.equals("02")&&obsNumber==01&&count>0)
				evidenceSource.setUnAnnounced1(true);
			else if(typeCode.equals("02")&&obsNumber==02&&count>0)
				evidenceSource.setUnAnnounced2(true);
			
			
		}
		
		return evidenceSources;

	}



}
