package edu.mass.doe.cap.dataservice.goal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.GoalLearningMeasure;


/**
 * The Interface GoalLearningMeasureRepository.
 */
public interface GoalLearningMeasureRepository  extends JpaRepository<GoalLearningMeasure, Long> {
	
	/**
	 * Find learning measure.
	 *
	 * @param goalPlanId the goal plan id
	 * @param impactCode the impact code
	 * @return the goal learning measure
	 */
	GoalLearningMeasure find_LearningMeasure(@Param("goalPlanId")Long goalPlanId,@Param("impactCode")String impactCode);
}
