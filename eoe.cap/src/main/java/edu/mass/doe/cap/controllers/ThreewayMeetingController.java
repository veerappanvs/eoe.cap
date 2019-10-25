package edu.mass.doe.cap.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.ThreewayMeetingInfo;
import edu.mass.doe.cap.util.CAPValidationUtil;

/**
 * The Class ThreewayMeetingController.
 */
@Controller
@RequestMapping("threewaymeeting")
@SessionAttributes("threewayMeeting")
public class ThreewayMeetingController extends BaseCycleInformation {

	/**
	 * Load meeting.
	 *
	 * @param cycleId the cycle id
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping
	public String loadMeeting(@RequestParam("cycleId") Long cycleId, Model model) throws Exception {

		ThreewayMeetingInfo meeting = threewayMeetingManager.getMeetingsByCycleId(cycleId);

		model.addAttribute("threewayMeeting", meeting);
		
		String page=".threewaymeetingView";
		
		if(cycleManager.getCycleInfo(cycleId).getCycleStatusCode()!=null)
			return page;

		return ".threewaymeeting";
	}

	/**
	 * Save meeting.
	 *
	 * @param meeting the meeting
	 * @param cycleId the cycle id
	 * @param meetingNumber the meeting number
	 * @param model the model
	 * @param result the result
	 * @return the string
	 */
	@PostMapping
	public String saveMeeting(@ModelAttribute("threewayMeeting") ThreewayMeetingInfo meeting,
			@RequestParam("cycleId") Long cycleId, @RequestParam("meetingNumber") Long meetingNumber, Model model,
			BindingResult result) {

		if (validate(meeting, meetingNumber, model, result)){
			try{
				threewayMeetingManager.saveMeeting(meeting, meetingNumber);
				}catch(Exception e){
					threewayMeetingManager.saveMeeting(meeting, meetingNumber);
				}

			return "redirect:/threewaymeeting?cycleId="+cycleId;
		}
		
		meeting = threewayMeetingManager.getMeetingsByCycleId(cycleId);
		


		return ".threewaymeeting";
	}

	/**
	 * Load cycle info.
	 *
	 * @param cycleId the cycle id
	 * @param model the model
	 * @throws Exception the exception
	 */
	@ModelAttribute
	public void loadCycleInfo(@RequestParam("cycleId") Long cycleId, Model model) throws Exception {

		CapCycleInfo cycleInfo = cycleManager.getCycleInfo(cycleId);

		model.addAttribute("cycleInfo", cycleInfo);

	}

	/**
	 * Validate.
	 *
	 * @param meeting the meeting
	 * @param meetingNumber the meeting number
	 * @param model the model
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validate(ThreewayMeetingInfo meeting, Long meetingNumber, Model model, BindingResult bindingResult) {
		boolean result = true;

		CapCycleInfo cycleInfo = (CapCycleInfo) model.asMap().get("cycleInfo");
		DateTime cycleStartDate = new DateTime(cycleInfo.getStartDate());
		DateTime tempendDate = cycleStartDate.plusDays(365);
		
		Date endDate=new Date(tempendDate.getMillis());

		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		switch (meetingNumber.intValue()) {
		case 1:
			endDate=meeting.getMeetingDate2()!=null?meeting.getMeetingDate2():endDate;
			
			result = CAPValidationUtil.validateRequired(meeting.getMeetingDate1(), "meetingDate1", bindingResult);

			result = result && CAPValidationUtil.validateCycleStartDate(meeting.getMeetingDate1(),
					cycleInfo.getStartDate(), endDate, "meetingDate1", bindingResult,
					meeting.getMeetingDate2()!=null?new Object[] {"First Three-Way Meeting", "Cycle Start Date","Second Three-Way Meeting" }:new Object[] { "First Three-Way Meeting", "Cycle Start Date",sdf.format(endDate) });
	
			break;

		case 2:
			endDate=meeting.getMeetingDate3()!=null?meeting.getMeetingDate3():endDate;
			result = CAPValidationUtil.validateRequired(meeting.getMeetingDate1(), "meetingDate1", bindingResult);
			result = result&&CAPValidationUtil.validateRequired(meeting.getMeetingDate2(), "meetingDate2", bindingResult);
			result = result && CAPValidationUtil.validateCycleStartDate(meeting.getMeetingDate2(),
					meeting.getMeetingDate1(), endDate, "meetingDate2", bindingResult,
					meeting.getMeetingDate3()!=null?new Object[] { "Second Three-Way Meeting", "First Three-Way Meeting","Third Three-Way Meeting" }:new Object[] { "Second Three-Way Meeting", "First Three-Way Meeting",sdf.format(endDate) }); 
			

			break;
		case 3:
			Date observationDate=observationManager.getMaxObservationDate(meeting.getCapCycleId());
			
			
			
			result = CAPValidationUtil.validateRequired(meeting.getMeetingDate2(), "meetingDate2", bindingResult);
			result = result&&CAPValidationUtil.validateRequired(meeting.getMeetingDate3(), "meetingDate3", bindingResult);
			result = result && CAPValidationUtil.validateCycleStartDate(meeting.getMeetingDate3(),
					observationDate!=null&&observationDate.after(meeting.getMeetingDate2())?observationDate:meeting.getMeetingDate2(), endDate, "meetingDate3", bindingResult,
					new Object[] { "Third Three-Way Meeting",observationDate!=null&&observationDate.after(meeting.getMeetingDate2())?sdf.format(observationDate):"Second Three-Way Meeting",sdf.format(endDate) });

			break;

		default:
			break;
		}

		return result;
	}

}
