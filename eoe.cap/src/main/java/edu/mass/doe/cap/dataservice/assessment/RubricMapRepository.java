package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.RatingType;
import edu.mass.doe.cap.dataservice.entity.RubricMap;

/**
 * The Interface RubricMapRepository.
 */
public interface RubricMapRepository extends JpaRepository<RubricMap, Long>{
	 
 	/**
 	 * Find all by cycle id and type.
 	 *
 	 * @param cycleId the cycle id
 	 * @param typeCode the type code
 	 * @return the list
 	 */
 	List<RubricMap> findAllByCycleIdAndType(@Param("cycleId")Long cycleId,@Param("typeCode")String typeCode );
}
 