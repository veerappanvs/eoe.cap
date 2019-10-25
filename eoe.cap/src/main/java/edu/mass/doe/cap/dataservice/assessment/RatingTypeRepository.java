package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mass.doe.cap.dataservice.entity.RatingType;

/**
 * The Interface RatingTypeRepository.
 */
public interface RatingTypeRepository extends JpaRepository<RatingType, String>{
	 
 	/* (non-Javadoc)
 	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
 	 */
 	List<RatingType> findAll();

}
 