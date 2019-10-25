package edu.mass.doe.cap.dataservice.batch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.EvidenceFile;
import edu.mass.doe.cap.dataservice.entity.UploadedData;

/**
 * The Interface UploadedDataRepository.
 */
public interface UploadedDataRepository  extends JpaRepository<UploadedData, Long>{
	
	 /**
 	 * Find all records by year.
 	 *
 	 * @param fromYear the from year
 	 * @param toYear the to year
 	 * @return the list
 	 */
 	List<UploadedData> findAllRecordsByYear(@Param("fromYear")String fromYear,@Param("toYear")String toYear);

}
