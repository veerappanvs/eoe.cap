package edu.mass.doe.cap.dataservice.observation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.Evidence;

/**
 * The Interface EvidenceRepository.
 */
public interface EvidenceRepository extends JpaRepository<Evidence, Long>{
	
	/**
	 * Find evidencefor cylce.
	 *
	 * @param observationId the observation id
	 * @param elementCode the element code
	 * @return the evidence
	 */
	Evidence findEvidenceforCylce(@Param("observationId")Long observationId,@Param("elementCode")String elementCode);
}
