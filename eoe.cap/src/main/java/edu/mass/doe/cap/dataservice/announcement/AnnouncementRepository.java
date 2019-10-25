package edu.mass.doe.cap.dataservice.announcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.Announcement;
import edu.mass.doe.cap.dataservice.entity.Message;

/**
 * The Interface AnnouncementRepository.
 */
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>{
	
	/**
	 * Find announcement.
	 *
	 * @return the announcement
	 */
	Announcement findAnnouncement();
}
