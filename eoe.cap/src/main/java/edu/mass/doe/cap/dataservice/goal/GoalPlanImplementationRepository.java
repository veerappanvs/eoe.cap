package edu.mass.doe.cap.dataservice.goal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.GoalPlan;
import edu.mass.doe.cap.dataservice.entity.GoalPlanImplementation;

/**
 * The Interface GoalPlanImplementationRepository.
 */
public interface GoalPlanImplementationRepository extends JpaRepository<GoalPlanImplementation, Long>{
	 
 	/**
 	 * Find goal implementation.
 	 *
 	 * @param goalPlanId the goal plan id
 	 * @param actionNumber the action number
 	 * @return the goal plan implementation
 	 */
 	GoalPlanImplementation find_GoalImplementation(@Param("goalPlanId")Long goalPlanId,@Param("actionNumber")Long actionNumber);
}
 