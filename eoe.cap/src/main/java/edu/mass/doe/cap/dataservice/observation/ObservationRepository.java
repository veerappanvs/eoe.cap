package edu.mass.doe.cap.dataservice.observation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.Observation;

/**
 * The Interface ObservationRepository.
 */
public interface ObservationRepository extends JpaRepository<Observation, Long> {
	
	 /**
 	 * Find completed observation by cycle id.
 	 *
 	 * @param cycleId the cycle id
 	 * @return the list
 	 */
 	List<Observation> findCompletedObservationByCycleId(@Param("cycleId")Long cycleId);
	 
 	/**
 	 * Find observation by cycle id.
 	 *
 	 * @param cycleId the cycle id
 	 * @param observationNumber the observation number
 	 * @param typeCode the type code
 	 * @return the observation
 	 */
 	Observation find_ObservationByCycleId(@Param("cycleId")Long cycleId,@Param("observationNumber")Long observationNumber,@Param("typeCode")String typeCode);
	

}
