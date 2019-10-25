package edu.mass.doe.cap.dataservice.goal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.GoalLearningMeasure;
import edu.mass.doe.cap.dataservice.entity.GoalPlanElement;
import edu.mass.doe.cap.dataservice.entity.GoalPlanImplementation;

/**
 * The Class GoalLearningMeasureService.
 */
@Repository
public class GoalLearningMeasureService {
	
	private static Logger logger = LoggerFactory.getLogger(GoalLearningMeasureService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;

	
	@Autowired
	private GoalLearningMeasureRepository goalLearningMeasureRepository;
	
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the goal learning measure
	 */
	public GoalLearningMeasure findByPrimaryKey(Long primaryKey) {
		return entityManager.find(GoalLearningMeasure.class, primaryKey);
		
	}
	
	/**
	 * Find goal learning measure.
	 *
	 * @param goalPlanId the goal plan id
	 * @param impactCode the impact code
	 * @return the goal learning measure
	 */
	public GoalLearningMeasure findGoalLearningMeasure(Long goalPlanId,String impactCode) {
		return goalLearningMeasureRepository.find_LearningMeasure(goalPlanId, impactCode);
		
	}
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the goal learning measure
	 */
	@Transactional(value=TxType.REQUIRED)
	public GoalLearningMeasure create(GoalLearningMeasure obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the goal learning measure
	 */
	@Transactional(value=TxType.REQUIRED)
	public GoalLearningMeasure update(GoalLearningMeasure obj) {
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
		GoalLearningMeasure goalLearningMeasure = entityManager.find(GoalLearningMeasure.class, primaryKey);
		entityManager.remove(goalLearningMeasure);

	}



}
