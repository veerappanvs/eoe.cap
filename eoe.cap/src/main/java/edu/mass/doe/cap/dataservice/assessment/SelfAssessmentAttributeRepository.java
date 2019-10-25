package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.RatingType;
import edu.mass.doe.cap.dataservice.entity.RubricMap;
import edu.mass.doe.cap.dataservice.entity.SelfAssessmentAttribute;

/**
 * The Interface SelfAssessmentAttributeRepository.
 */
public interface SelfAssessmentAttributeRepository extends JpaRepository<SelfAssessmentAttribute, Long>{
	 
 	/**
 	 * Find attributes.
 	 *
 	 * @param cycleId the cycle id
 	 * @param typeCode the type code
 	 * @return the list
 	 */
 	List<SelfAssessmentAttribute> findAttributes(@Param("cycleId")Long cycleId,@Param("typeCode")String typeCode);
	 
 	/**
 	 * Find assessment.
 	 *
 	 * @param assessmentId the assessment id
 	 * @param typeCode the type code
 	 * @param attributeNumber the attribute number
 	 * @return the self assessment attribute
 	 */
 	SelfAssessmentAttribute find_Assessment(@Param("assessmentId")Long assessmentId,@Param("typeCode")String typeCode,@Param("attributeNumber")Long attributeNumber);
	 
}
 