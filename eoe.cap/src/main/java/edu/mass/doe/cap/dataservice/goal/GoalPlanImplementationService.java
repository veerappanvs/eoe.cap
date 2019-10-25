package edu.mass.doe.cap.dataservice.goal;

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

import edu.mass.doe.cap.dataservice.entity.GoalPlanElement;
import edu.mass.doe.cap.dataservice.entity.GoalPlanImplementation;

/**
 * The Class GoalPlanImplementationService.
 */
@Repository
public class GoalPlanImplementationService {
	
	private static Logger logger = LoggerFactory.getLogger(GoalPlanImplementationService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private GoalPlanImplementationRepository  goalPlanImplementationRepository;
	
	
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the goal plan implementation
	 */
	public GoalPlanImplementation findByPrimaryKey(Long primaryKey) {
		return entityManager.find(GoalPlanImplementation.class, primaryKey);
		
	}

	/**
	 * Find goal implementation.
	 *
	 * @param goalPlanId the goal plan id
	 * @param actionNumber the action number
	 * @return the goal plan implementation
	 */
	public GoalPlanImplementation findGoalImplementation(Long goalPlanId,Long actionNumber) {
		return goalPlanImplementationRepository.find_GoalImplementation(goalPlanId, actionNumber);
		
	}
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the goal plan implementation
	 */
	@Transactional(value=TxType.REQUIRED)
	public GoalPlanImplementation create(GoalPlanImplementation obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the goal plan implementation
	 */
	@Transactional(value=TxType.REQUIRED)
	public GoalPlanImplementation update(GoalPlanImplementation obj) {
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
		GoalPlanImplementation goalPlanImplementation = entityManager.find(GoalPlanImplementation.class, primaryKey);
		entityManager.remove(goalPlanImplementation);

	}



}
