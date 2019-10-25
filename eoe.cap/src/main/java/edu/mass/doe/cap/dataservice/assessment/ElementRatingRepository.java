package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import edu.mass.doe.cap.dataservice.entity.ElementRating;
import edu.mass.doe.cap.dataservice.entity.ElementType;

/**
 * The Interface ElementRatingRepository.
 */
public interface ElementRatingRepository extends JpaRepository<ElementRating, String>{
	 
 	/**
 	 * Find all by element code.
 	 *
 	 * @param elementCode the element code
 	 * @return the list
 	 */
 	List<ElementRating> findAllByElementCode(@Param("elementCode")String elementCode);
	 
	 /**
 	 * Find all by element code.
 	 *
 	 * @return the list
 	 */
 	List<ElementRating> findAllByElementCode();

	 
	 /**
 	 * Find link id.
 	 *
 	 * @param elementCode the element code
 	 * @param ratingCode the rating code
 	 * @return the element rating
 	 */
 	ElementRating findLinkId(@Param("elementCode")String elementCode,@Param("ratingCode")String ratingCode);


}
 