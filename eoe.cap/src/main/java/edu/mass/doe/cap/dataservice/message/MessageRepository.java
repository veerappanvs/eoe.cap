package edu.mass.doe.cap.dataservice.message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.Message;

/**
 * The Interface MessageRepository.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

	/**
	 * Find messages.
	 *
	 * @param cycleId the cycle id
	 * @return the list
	 */
	List<Message> findMessages(@Param("cycleId")Long cycleId);
}
