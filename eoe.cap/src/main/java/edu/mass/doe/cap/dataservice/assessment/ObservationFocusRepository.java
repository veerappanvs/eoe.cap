package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.ObservationFocus;

/**
 * The Interface ObservationFocusRepository.
 */
public interface ObservationFocusRepository extends JpaRepository<ObservationFocus, Long>{
	 
 	/* (non-Javadoc)
 	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
 	 */
 	List<ObservationFocus> findAll();

}
 