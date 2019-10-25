package edu.mass.doe.cap.dataservice.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.SelfassessmentElement;


/**
 * The Interface SelfassessmentElementRepository.
 */
public interface SelfassessmentElementRepository extends JpaRepository<SelfassessmentElement, Long>{
	
	/**
	 * Find self assessment attribute.
	 *
	 * @param attributeId the attribute id
	 * @param elementCode the element code
	 * @return the selfassessment element
	 */
	SelfassessmentElement find_SelfAssessmentAttribute(@Param("attributeId")Long attributeId,@Param("elementCode")String elementCode);


}
