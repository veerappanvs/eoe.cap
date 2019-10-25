package edu.mass.doe.cap.dataservice.observation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mass.doe.cap.dataservice.entity.ObservationGroupType;

/**
 * The Interface ObservationGroupTypeRepository.
 */
public interface ObservationGroupTypeRepository extends JpaRepository<ObservationGroupType, String> {
	
	 /* (non-Javadoc)
 	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
 	 */
 	List<ObservationGroupType> findAll();

}
