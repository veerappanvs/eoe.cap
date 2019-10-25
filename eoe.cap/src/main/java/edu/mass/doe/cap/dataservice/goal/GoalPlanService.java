package edu.mass.doe.cap.dataservice.goal;

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

import edu.mass.doe.cap.dataservice.entity.GoalPlan;
import edu.mass.doe.cap.dataservice.entity.GoalPlanElement;

/**
 * The Class GoalPlanService.
 */
@Repository
public class GoalPlanService {
	
	
	
	private static Logger logger = LoggerFactory.getLogger(GoalPlanService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;

	@Autowired
	private GoalPlanRepository goalPlanRepository;
	
	
	/**
	 * Find goal by cycle id.
	 *
	 * @param cycleId the cycle id
	 * @param typeCode the type code
	 * @return the goal plan
	 */
	public GoalPlan findGoalByCycleId(Long cycleId,String typeCode){
		return goalPlanRepository.find_Goal(cycleId,typeCode);
	}

	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the goal plan
	 */
	public GoalPlan findByPrimaryKey(Long primaryKey) {
		return entityManager.find(GoalPlan.class, primaryKey);
		
	}
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the goal plan
	 */
	@Transactional(value=TxType.REQUIRED)
	public GoalPlan create(GoalPlan obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the goal plan
	 */
	@Transactional(value=TxType.REQUIRED)
	public GoalPlan update(GoalPlan obj) {
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
		GoalPlan goalPlan = entityManager.find(GoalPlan.class, primaryKey);
		entityManager.remove(goalPlan);

	}







}
