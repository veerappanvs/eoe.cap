package edu.mass.doe.cap.dataservice.candidate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mass.doe.cap.dataservice.entity.StatusReasonType;

/**
 * The Interface StatusReasonTypeRepository.
 */
public interface StatusReasonTypeRepository  extends JpaRepository<StatusReasonType, String>{
	

    /* (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    List<StatusReasonType> findAll();

}
