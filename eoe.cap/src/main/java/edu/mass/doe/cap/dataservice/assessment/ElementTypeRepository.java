package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mass.doe.cap.dataservice.entity.ElementType;

/**
 * The Interface ElementTypeRepository.
 */
public interface ElementTypeRepository extends JpaRepository<ElementType, Long>{
	 
 	/* (non-Javadoc)
 	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
 	 */
 	List<ElementType> findAll();

}
 