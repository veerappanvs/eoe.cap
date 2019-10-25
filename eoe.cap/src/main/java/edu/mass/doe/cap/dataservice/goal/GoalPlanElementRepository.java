package edu.mass.doe.cap.dataservice.goal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.GoalPlanElement;


/**
 * The Interface GoalPlanElementRepository.
 */
public interface GoalPlanElementRepository extends JpaRepository<GoalPlanElement, Long>  {
	
	/**
	 * Find goal plan element.
	 *
	 * @param goalPlanId the goal plan id
	 * @param elementCode the element code
	 * @return the goal plan element
	 */
	GoalPlanElement findGoalPlanElement(@Param("goalPlanId")Long goalPlanId,@Param("elementCode")String elementCode);
}
