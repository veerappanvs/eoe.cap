package edu.mass.doe.cap.dataservice.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.AssessmentElementLink;
import edu.mass.doe.cap.dataservice.entity.RubricMap;

/**
 * The Interface AssessmentElementLinkRepository.
 */
public interface AssessmentElementLinkRepository extends JpaRepository<AssessmentElementLink, Long> {
	
	/**
	 * Find assessment element link by assessment.
	 *
	 * @param assessmentId the assessment id
	 * @param elementCode the element code
	 * @param attributeType the attribute type
	 * @return the assessment element link
	 */
	AssessmentElementLink findAssessmentElementLinkByAssessment(@Param("assessmentId")Long assessmentId,@Param("elementCode")String elementCode,@Param("attributeType")String attributeType);

	

}
