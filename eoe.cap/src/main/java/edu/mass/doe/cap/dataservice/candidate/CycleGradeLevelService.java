package edu.mass.doe.cap.dataservice.candidate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import edu.mass.doe.cap.dataservice.entity.CycleGradeLevel;
import edu.mass.doe.cap.model.util.CAPUtil;

@Repository
public class CycleGradeLevelService {

	
	private static Logger logger = LoggerFactory.getLogger(CycleGradeLevelService.class);

	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private CAPUtil capUtil;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CycleGradeLevelRepository cycleGradeLevelRepository;
	
	
	public CycleGradeLevel findByPrimaryKey(Long primaryKey) {
		return entityManager.find(CycleGradeLevel.class, primaryKey);
	}
	
	public List<CycleGradeLevel> findGradesByCycle(Long cycleId) {
		return cycleGradeLevelRepository.findAll(cycleId);
	}
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the cap cycle
	 */
	@Transactional(value=TxType.REQUIRED)
	public CycleGradeLevel create(CycleGradeLevel obj) {
		entityManager.persist(obj);
		return obj;
		
	}
		
	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the cap cycle
	 */
	@Transactional(value=TxType.REQUIRED)
	public CycleGradeLevel update(CycleGradeLevel obj) {
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
		
		CycleGradeLevel cycleGradeLevel = 
				entityManager
				.find(CycleGradeLevel.class, primaryKey);
		entityManager
		.remove(cycleGradeLevel);
		
	}
}
