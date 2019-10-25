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
 * The Class GoalElementService.
 */
@Repository
public class GoalElementService {
	
	
	
	private static Logger logger = LoggerFactory.getLogger(GoalElementService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	GoalPlanElementRepository goalPlanElementRepository;
	
	/**
	 * Find goal plan element.
	 *
	 * @param goalPlanId the goal plan id
	 * @param elementCode the element code
	 * @return the goal plan element
	 */
	public GoalPlanElement findGoalPlanElement(Long goalPlanId,String elementCode){
		return goalPlanElementRepository.findGoalPlanElement(goalPlanId, elementCode);
	}
	
	
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the goal plan element
	 */
	public GoalPlanElement findByPrimaryKey(Long primaryKey) {
		return entityManager.find(GoalPlanElement.class, primaryKey);
		
	}
	
	
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the goal plan element
	 */
	@Transactional(value=TxType.REQUIRED)
	public GoalPlanElement create(GoalPlanElement obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the goal plan element
	 */
	@Transactional(value=TxType.REQUIRED)
	public GoalPlanElement update(GoalPlanElement obj) {
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
		GoalPlanElement goalPlanElement = entityManager.find(GoalPlanElement.class, primaryKey);
		entityManager.remove(goalPlanElement);

	}







}
