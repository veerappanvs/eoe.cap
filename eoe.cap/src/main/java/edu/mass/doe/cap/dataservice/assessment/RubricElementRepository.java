package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.RubricElement;

/**
 * The Interface RubricElementRepository.
 */
public interface RubricElementRepository extends JpaRepository<RubricElement, Long>{

	/**
	 * Find rubric element by assessment.
	 *
	 * @param assessmentId the assessment id
	 * @param elementCode the element code
	 * @return the rubric element
	 */
	public RubricElement findRubricElementByAssessment(@Param("assessmentId")Long assessmentId,@Param("elementCode")String elementCode );

}
