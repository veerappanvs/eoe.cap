package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.EvidenceSourceFocus;
import edu.mass.doe.cap.dataservice.entity.ObservationFocus;
import edu.mass.doe.cap.dataservice.pojo.EvidenceSource;

/**
 * The Interface EvidenceSourceFocusRepository.
 */
public interface EvidenceSourceFocusRepository extends JpaRepository<EvidenceSourceFocus, Long>{
	 
 	/* (non-Javadoc)
 	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
 	 */
 	List<EvidenceSourceFocus> findAll();

}
 