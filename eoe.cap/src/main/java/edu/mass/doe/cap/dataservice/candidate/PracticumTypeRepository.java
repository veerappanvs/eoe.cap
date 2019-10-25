package edu.mass.doe.cap.dataservice.candidate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mass.doe.cap.dataservice.entity.PracticumType;

/**
 * The Interface PracticumTypeRepository.
 */
public interface PracticumTypeRepository  extends JpaRepository<PracticumType, String>{
	

    /* (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    List<PracticumType> findAll();

}
