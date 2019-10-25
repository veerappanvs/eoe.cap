package edu.mass.doe.cap.dataservice.file;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.EvidenceFile;
import edu.mass.doe.cap.dataservice.entity.EvidenceType;
import edu.mass.doe.cap.dataservice.entity.ThreewayMeeting;

public interface EvidenceFileTypeRepository extends JpaRepository<EvidenceType, String>  {
	
	 List<EvidenceType> findAllEvidencesType();


}


