package edu.mass.doe.cap.dataservice.file;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.EvidenceFile;
import edu.mass.doe.cap.dataservice.entity.ThreewayMeeting;

/**
 * The Interface EvidencefileRepository.
 */
public interface EvidencefileRepository extends JpaRepository<EvidenceFile, Long>  {
	
	 /**
 	 * Find files by cycle.
 	 *
 	 * @param cycleId the cycle id
 	 * @return the list
 	 */
 	List<EvidenceFile> findFilesByCycle(@Param("cycleId")Long cycleId);


}


