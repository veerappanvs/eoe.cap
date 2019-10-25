package edu.mass.doe.cap.dataservice.candidate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.CycleGradeLevel;
import edu.mass.doe.cap.dataservice.entity.StatusReasonType;

/**
 * The Interface CycleGradeLevelRepository.
 */
public interface CycleGradeLevelRepository  extends JpaRepository<CycleGradeLevel, String>{
    /* (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    List<CycleGradeLevel> findAll(@Param("cycleId")Long cycleId);
}
