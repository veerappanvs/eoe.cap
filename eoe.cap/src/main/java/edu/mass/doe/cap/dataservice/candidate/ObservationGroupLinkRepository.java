package edu.mass.doe.cap.dataservice.candidate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.ObservationGroupLink;
import edu.mass.doe.cap.dataservice.entity.StatusReasonType;
/**
 * The Interface CycleGradeLevelRepository.
 */
public interface ObservationGroupLinkRepository  extends JpaRepository<ObservationGroupLink, String>{
    /* (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    List<ObservationGroupLink> findAll(@Param("observationId") Long observationId);
	//List<ObservationGroupLink> findByObservationIdOrderByGroupCode(@Param("observationId") Long observationId);
	//List<ObservationGroupLink> findByObservationIdOrder(@Param("observationId") Long observationId);
}
