package edu.mass.doe.cap.dataservice.goal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.GoalPlan;

/**
 * The Interface GoalPlanRepository.
 */
public interface GoalPlanRepository extends JpaRepository<GoalPlan, Long>{
	 
 	/**
 	 * Find goal.
 	 *
 	 * @param cycleId the cycle id
 	 * @param typeCode the type code
 	 * @return the goal plan
 	 */
 	GoalPlan find_Goal(@Param("cycleId")Long cycleId,@Param("typeCode")String typeCode);
}
 