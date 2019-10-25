package edu.mass.doe.cap.model.announcement;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.dataservice.announcement.AnnouncementService;
import edu.mass.doe.cap.dataservice.entity.Announcement;
import edu.mass.doe.cap.dataservice.pojo.AnnouncementInfo;

/**
 * The Class AnnouncementManager.
 */
@Component
public class AnnouncementManager {

	private static Logger logger = LoggerFactory.getLogger(AnnouncementManager.class);
	
	@Autowired(required = true)
	private AnnouncementService announcementService;
	
	/**
	 * Gets the announcement.
	 *
	 * @return the announcement
	 */
	public AnnouncementInfo getAnnouncement() {
		AnnouncementInfo announcementInfo = new AnnouncementInfo();
		Announcement announcement= announcementService.getAnnouncement();
	if(announcement!=null){
		announcementInfo.setAnnouncementId(announcement.getAnnouncementId());
		announcementInfo.setText(announcement.getText());
		announcementInfo.setPrevText(announcement.getText());
		announcementInfo.setPostedDate(announcement.getEffDate());
	}
		return announcementInfo;
	}
	
	/**
	 * Delete.
	 *
	 * @param announcementInfo the announcement info
	 */
	public void delete(AnnouncementInfo announcementInfo){
		Announcement announcement= announcementService.getAnnouncement();
		if(announcement!=null)
		{
			announcement.setExpDate(new Date());
			announcementService.update(announcement);
		}
		
	}
	
	/**
	 * Update.
	 *
	 * @param announcementInfo the announcement info
	 */
	public void update(AnnouncementInfo announcementInfo){
		Announcement announcement= announcementService.getAnnouncement();
		if(announcement!=null)
			
		{
			announcement.setExpDate(new Date());
			announcementService.update(announcement);
		}
		announcement=new Announcement();
		announcement.setText(announcementInfo.getText());
		announcement.setEffDate(new Date());
		announcementService.create(announcement);
	}
	
}
