package edu.mass.doe.cap.dataservice.meeting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.ThreewayMeeting;

/**
 * The Interface ThreewayMeetingRepository.
 */
public interface ThreewayMeetingRepository extends JpaRepository<ThreewayMeeting, Long> {
	
	 /**
 	 * Find meetings by cycle id.
 	 *
 	 * @param cycleId the cycle id
 	 * @return the list
 	 */
 	List<ThreewayMeeting> findMeetingsByCycleId(@Param("cycleId")Long cycleId);
	 
	 /**
 	 * Find meeting by cycle id.
 	 *
 	 * @param cycleId the cycle id
 	 * @param meetingNumber the meeting number
 	 * @return the threeway meeting
 	 */
 	ThreewayMeeting findMeetingByCycleId(@Param("cycleId")Long cycleId,@Param("meetingNumber")Long meetingNumber);

	
}