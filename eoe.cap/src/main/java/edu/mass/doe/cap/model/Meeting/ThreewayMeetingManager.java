package edu.mass.doe.cap.model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.dataservice.candidate.CycleService;
import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.entity.ThreewayMeeting;
import edu.mass.doe.cap.dataservice.meeting.ThreewayMeetingService;
import edu.mass.doe.cap.dataservice.pojo.ThreewayMeetingInfo;

/**
 * The Class ThreewayMeetingManager.
 */
@Component
public class ThreewayMeetingManager {
	
	
	private static Logger logger = LoggerFactory.getLogger(ThreewayMeetingManager.class);
	
	

	@Autowired
	private ThreewayMeetingService threewayMeetingService;
	
	@Autowired
	private CycleService cycleService;
	
	
	/**
	 * Gets the meetings by cycle id.
	 *
	 * @param cycleId the cycle id
	 * @return the meetings by cycle id
	 */
	public ThreewayMeetingInfo getMeetingsByCycleId(Long cycleId)
	{
		
		ThreewayMeetingInfo meetingInfo = new ThreewayMeetingInfo();
		meetingInfo.setCapCycleId(cycleId);
		 
		for(ThreewayMeeting threewayMeeting :threewayMeetingService.findMeetingsByCycleId(cycleId)){
			
			
			switch (threewayMeeting.getMeetingNumber().intValue()) {
			case 1:
				meetingInfo.setMeetingNumber1(1L);
				meetingInfo.setMeetingDate1(threewayMeeting.getMeetingDate());
				meetingInfo.setMeetingId1(threewayMeeting.getMeetingId());
				meetingInfo.setMeetingNotes1(threewayMeeting.getNotes());
				break;
			case 2:
				meetingInfo.setMeetingNumber2(2L);
				meetingInfo.setMeetingDate2(threewayMeeting.getMeetingDate());
				meetingInfo.setMeetingId2(threewayMeeting.getMeetingId());
				meetingInfo.setMeetingNotes2(threewayMeeting.getNotes());
				break;
			case 3:
				meetingInfo.setMeetingNumber3(3L);
				meetingInfo.setMeetingDate3(threewayMeeting.getMeetingDate());
				meetingInfo.setMeetingId3(threewayMeeting.getMeetingId());
				meetingInfo.setMeetingNotes3(threewayMeeting.getNotes());
				break;

			default:
				break;
			}
			
						
			
		}
		
		return meetingInfo;
	}
	
	
	
	/**
	 * Save meeting.
	 *
	 * @param meetingInfo the meeting info
	 * @param meetingNumber the meeting number
	 */
	public void saveMeeting(ThreewayMeetingInfo meetingInfo, Long meetingNumber) {

		ThreewayMeeting threewayMeeting = null;

		CapCycle capCycle = cycleService.findByPrimaryKey(meetingInfo.getCapCycleId());

		switch (meetingNumber.intValue()) {
		case 1:
			
			threewayMeeting=threewayMeetingService.findMeetingByCycleId(meetingInfo.getCapCycleId(), meetingNumber);
			if (threewayMeeting != null) {
				threewayMeeting.setMeetingDate(meetingInfo.getMeetingDate1());
				threewayMeeting.setNotes(meetingInfo.getMeetingNotes1());
				threewayMeetingService.update(threewayMeeting);
			} else {
				threewayMeeting = new ThreewayMeeting();
				threewayMeeting.setMeetingDate(meetingInfo.getMeetingDate1());
				threewayMeeting.setMeetingNumber(meetingNumber);
				threewayMeeting.setNotes(meetingInfo.getMeetingNotes1());
				threewayMeeting.setEffDate(new Date());
				threewayMeeting.setCapCycle(capCycle);
				threewayMeetingService.create(threewayMeeting);
			}

			break;

		case 2:
			threewayMeeting=threewayMeetingService.findMeetingByCycleId(meetingInfo.getCapCycleId(), meetingNumber);
			if (threewayMeeting != null) {
				threewayMeeting.setMeetingDate(meetingInfo.getMeetingDate2());
				threewayMeeting.setNotes(meetingInfo.getMeetingNotes2());
				threewayMeetingService.update(threewayMeeting);
			} else {
				threewayMeeting = new ThreewayMeeting();
				threewayMeeting.setMeetingDate(meetingInfo.getMeetingDate2());
				threewayMeeting.setNotes(meetingInfo.getMeetingNotes2());
				threewayMeeting.setEffDate(new Date());
				threewayMeeting.setMeetingNumber(meetingNumber);
				threewayMeeting.setCapCycle(capCycle);
				threewayMeetingService.create(threewayMeeting);
			}
			break;

		case 3:
			threewayMeeting=threewayMeetingService.findMeetingByCycleId(meetingInfo.getCapCycleId(), meetingNumber);
			if (threewayMeeting != null){
				threewayMeeting.setMeetingDate(meetingInfo.getMeetingDate3());
				threewayMeeting.setNotes(meetingInfo.getMeetingNotes3());
				threewayMeetingService.update(threewayMeeting);
			} else {
				threewayMeeting = new ThreewayMeeting();
				threewayMeeting.setMeetingDate(meetingInfo.getMeetingDate3());
				threewayMeeting.setNotes(meetingInfo.getMeetingNotes3());
				threewayMeeting.setEffDate(new Date());
				threewayMeeting.setMeetingNumber(meetingNumber);
				threewayMeeting.setCapCycle(capCycle);
				threewayMeetingService.create(threewayMeeting);
			}

			break;

		default:
			break;
		}

	}
}
