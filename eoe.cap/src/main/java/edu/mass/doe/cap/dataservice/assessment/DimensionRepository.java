package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.DimensionType;


/**
 * The Interface DimensionRepository.
 */
public interface DimensionRepository extends JpaRepository<DimensionType, String>{
	 
 	/* (non-Javadoc)
 	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
 	 */
 	List<DimensionType> findAll();

}
	
	
	


