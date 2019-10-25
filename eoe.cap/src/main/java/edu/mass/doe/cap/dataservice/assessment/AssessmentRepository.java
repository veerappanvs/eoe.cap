package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.Assessment;
import edu.mass.doe.cap.dataservice.entity.DimensionType;


/**
 * The Interface AssessmentRepository.
 */
public interface AssessmentRepository extends JpaRepository<Assessment, Long>{
	 
 	/**
 	 * Find by cycle.
 	 *
 	 * @param cycleId the cycle id
 	 * @param typeCode the type code
 	 * @return the assessment
 	 */
 	Assessment findByCycle(@Param("cycleId")Long cycleId,@Param("typeCode")String typeCode);

}
	
	
	


