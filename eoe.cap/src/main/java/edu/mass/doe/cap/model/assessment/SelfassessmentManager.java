package edu.mass.doe.cap.model.assessment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Element;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.dataservice.assessment.AssessmentElementLinkService;
import edu.mass.doe.cap.dataservice.assessment.AssessmentService;
import edu.mass.doe.cap.dataservice.assessment.AssessmentTypeService;
import edu.mass.doe.cap.dataservice.assessment.DimensionService;
import edu.mass.doe.cap.dataservice.assessment.ElementRatingService;
import edu.mass.doe.cap.dataservice.assessment.ElementTypeService;
import edu.mass.doe.cap.dataservice.assessment.RatingTypeService;
import edu.mass.doe.cap.dataservice.assessment.RubricElementService;
import edu.mass.doe.cap.dataservice.assessment.RubricMapService;
import edu.mass.doe.cap.dataservice.assessment.SelfassessmentAttributeService;
import edu.mass.doe.cap.dataservice.assessment.SelfassessmentElementService;
import edu.mass.doe.cap.dataservice.candidate.CycleService;
import edu.mass.doe.cap.dataservice.entity.Assessment;
import edu.mass.doe.cap.dataservice.entity.AssessmentElementLink;
import edu.mass.doe.cap.dataservice.entity.AssessmentType;
import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.entity.DimensionType;
import edu.mass.doe.cap.dataservice.entity.ElementRating;
import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.RubricElement;
import edu.mass.doe.cap.dataservice.entity.RubricMap;
import edu.mass.doe.cap.dataservice.entity.SelfAssessmentAttribute;
import edu.mass.doe.cap.dataservice.entity.SelfassessmentElement;
import edu.mass.doe.cap.dataservice.pojo.AssessmentInfo;
import edu.mass.doe.cap.dataservice.pojo.Attribute;
import edu.mass.doe.cap.dataservice.pojo.DimensionInfo;
import edu.mass.doe.cap.dataservice.pojo.ElementInfo;
import edu.mass.doe.cap.dataservice.pojo.RatingInfo;
import edu.mass.doe.cap.dataservice.pojo.SelfAssessmentAttributeInfo;
import edu.mass.doe.cap.dataservice.pojo.SelfAssessmentElementInfo;
import edu.mass.doe.cap.model.goal.GoalManager;

/**
 * The Class SelfassessmentManager.
 */
@Component
public class SelfassessmentManager {

	@Autowired
	private ElementTypeService elementTypeService;
	@Autowired
	private ElementRatingService elementRatingService;

	@Autowired
	private RatingTypeService ratingTypeService;

	@Autowired
	private DimensionService dimensionService;

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private CycleService cycleService;

	@Autowired
	private AssessmentTypeService assessmentTypeService;

	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	private RubricElementService rubricElementService;

	@Autowired
	private RubricMapService rubricMapService;



	@Autowired
	private SelfassessmentAttributeService selfassessmentAttributeService;

	@Autowired
	private SelfassessmentElementService slfassessmentElementService;
	
	@Autowired
	private GoalManager goalManager;

	/**
	 * Gets the rubric info.
	 *
	 * @param cycleId the cycle id
	 * @return the rubric info
	 */
	public AssessmentInfo getRubricInfo(Long cycleId) {

		AssessmentInfo assessmentInfo = new AssessmentInfo();

		AssessmentType assessmentType = assessmentTypeService.findByPrimaryKey("1");

		assessmentInfo.setTypeCode(assessmentType.getTypeCode());

		assessmentInfo.setTypeDesc(assessmentType.getTypeDesc());

		CapCycle capCycle = cycleService.findByPrimaryKey(cycleId);

		List<ElementType> elements = elementTypeService.findAll();

		List<ElementInfo> elementInfos = new ArrayList<ElementInfo>();

		List<Attribute> reinAttributes = new ArrayList<Attribute>();

		List<Attribute> refineAttributes = new ArrayList<Attribute>();
		assessmentInfo.setReinAttributes(reinAttributes);
		assessmentInfo.setRefineAttributes(refineAttributes);

		elements.stream().forEach(elementType -> {

			ElementInfo elementInfo = new ElementInfo();
			elementInfo.setElementCode(elementType.getElementCode());
			elementInfo.setElementLabel(elementType.getLabel());
			elementInfo.setLongDesc(elementType.getLongDesc());
			elementInfo.setAltDesc(elementType.getAltDesc());

			List<ElementRating> ratings = elementRatingService.findAllByElementCode(elementType.getElementCode());
			List<RatingInfo> ratingInfos = new ArrayList<RatingInfo>();
			List<DimensionInfo> dimensionInfos = new ArrayList<DimensionInfo>();

			ratings.stream().forEach(elementRating -> {
				RatingInfo ratingInfo = new RatingInfo();
				ratingInfo.setLinkId(elementRating.getLinkId());
				ratingInfo.setElementCode(elementRating.getElementCode());
				ratingInfo.setLongDesc(elementRating.getLongDesc());
				ratingInfo.setShortDesc(ratingTypeService.findByPrimaryKey(elementRating.getRatingCode()).getDesc());
				ratingInfo.setRatingCode(elementRating.getRatingCode());
				ratingInfos.add(ratingInfo);
			});

			elementInfo.setRatingInfos(ratingInfos);

			dimensionService.findAll().stream().forEach(dimensionType -> {
				DimensionInfo dimensionInfo = new DimensionInfo();
				dimensionInfo.setDimensionCode(dimensionType.getDimensionCode());
				dimensionInfo.setDimensionDesc(dimensionType.getDesc());
				dimensionInfo.setDimensionLongDesc(dimensionType.getLongDesc());
				dimensionInfos.add(dimensionInfo);

			});

			elementInfo.setDimensions(dimensionInfos);

			elementInfos.add(elementInfo);
		});

		assessmentInfo.setElements(elementInfos);

		assessmentInfo.setCycleId(cycleId);

		Assessment assessment = assessmentService.findByCycleId(cycleId, "1");

		if (assessment != null) {
			assessmentInfo.setAssessmentId(assessment.getAssessmentId());
			assessmentInfo.setCycleId(cycleId);
			assessmentInfo.setTypeCode(assessment.getTypeCode());
			assessmentInfo.setLocked(assessment.getLocked());
			assessmentInfo.setCompleted(assessment.getCompleted() != null ? assessment.getCompleted() : 'N');
			assessmentInfo.setCalFeedback(assessment.getCalFeedback());
			assessmentInfo.setAddlObservationRequired(assessment.getAddlObservationRequired());
			assessmentInfo.setReinFeedback(assessment.getReinFeedback());
			assessmentInfo.setRefineFeedback(assessment.getRefineFeedback());
			assessmentInfo.setEvidenceFeedback(assessment.getEvidenceFeedback());
			assessmentInfo.setRecommendedfocus(assessment.getRecommendedfocus());
			assessmentInfo.setSpApproved(assessment.getSpApproved() != null ? assessment.getSpApproved() : 'N');
			assessmentInfo.setSpApprovedDate(assessment.getSpApprovedDate());
			assessmentInfo.setPsApproved(assessment.getPsApproved() != null ? assessment.getPsApproved() : 'N');
			assessmentInfo.setPsApprovedDate(assessment.getPsApprovedDate());

			List<RubricElement> rubricElements = assessment.getRubricElements();
			List<AssessmentElementLink> assessmentElementLinks = assessment.getAssessmentElementLinks();
			
			

			for (ElementInfo elementInfo : assessmentInfo.getElements()) {

				RubricElement rubricElement = rubricElements.stream()
						.filter(temp -> temp.getElementCode().equals(elementInfo.getElementCode())).findFirst()
						.orElse(null);

				if (rubricElement == null){
					continue;
				}

				elementInfo.setId(rubricElement.getId());
				elementInfo.setMetThreshHold(rubricElement.getMetThreshold());
				elementInfo.setRationale(rubricElement.getRationale());
				elementInfo.setCompleted(rubricElement.getComplete() != null ? rubricElement.getComplete() : 'N');

				for (DimensionInfo dimensionInfo : elementInfo.getDimensions()) {

					List<RubricMap> rubricMaps = assessment.getRubrics();

					RubricMap rubricMap = rubricMaps.stream()
							.filter(temp -> temp.getDimensionCode().equals(dimensionInfo.getDimensionCode()))
							.filter(temp -> elementRatingService.findByPrimaryKey(temp.getRatingElementLinkId())
									.getElementCode().equals(elementInfo.getElementCode()))
							.findFirst().orElse(null);

					if (rubricMap == null){
						continue;
					}

					ElementRating elementRating = elementRatingService
							.findByPrimaryKey(rubricMap.getRatingElementLinkId());

					dimensionInfo.setId(rubricMap.getMapId());
					dimensionInfo.setLinkId(rubricMap.getRatingElementLinkId());
					dimensionInfo.setRatingCode(elementRating.getRatingCode());
					dimensionInfo
							.setRatingDesc(ratingTypeService.findByPrimaryKey(elementRating.getRatingCode()).getDesc());

				}

			}
			
		
		}
		
		assessmentInfo.setGrowths(getAttributeInfos(assessmentInfo, "2"));
		assessmentInfo.setStrengths(getAttributeInfos(assessmentInfo, "1"));


		return assessmentInfo;

	}

	/**
	 * Gets the attribute infos.
	 *
	 * @param assessmentInfo the assessment info
	 * @param typeCode the type code
	 * @return the attribute infos
	 */
	private List<SelfAssessmentAttributeInfo> getAttributeInfos(AssessmentInfo assessmentInfo, String typeCode) {

		List<SelfAssessmentAttributeInfo> strengthAttributesInfos = new ArrayList<SelfAssessmentAttributeInfo>();
		List<ElementType> elementsTypes = elementTypeService.findAll();

		
		List<SelfAssessmentAttribute> attributes = selfassessmentAttributeService
				.findAttributes(assessmentInfo.getCycleId(),typeCode);
		
		attributes = attributes != null ? attributes : new ArrayList<SelfAssessmentAttribute>();
		int size = attributes.size() ;

		int idx = 0;
		do {

			SelfAssessmentAttributeInfo selfAssessmentAttributeInfo = new SelfAssessmentAttributeInfo();
			selfAssessmentAttributeInfo.setTypeCode(typeCode);

			SelfAssessmentAttribute selfAssessmentAttribute = size > 0 ? attributes.get(idx)
					: new SelfAssessmentAttribute();
			selfAssessmentAttributeInfo.setId(selfAssessmentAttribute.getId());
			selfAssessmentAttributeInfo.setArea(selfAssessmentAttribute.getArea());
			selfAssessmentAttributeInfo.setRationale(selfAssessmentAttribute.getRationale());
			selfAssessmentAttributeInfo.setAttributeNumber(selfAssessmentAttribute.getAttributeNumber());
			List<SelfAssessmentElementInfo> elementsInfos = new ArrayList<SelfAssessmentElementInfo>();
			selfAssessmentAttributeInfo.setElements(elementsInfos);

			List<SelfassessmentElement> elements = selfAssessmentAttribute.getSelfassessmentElements();

			elementsTypes.stream().forEach(elementType -> {
				SelfAssessmentElementInfo selfAssessmentElementInfo = new SelfAssessmentElementInfo();
				selfAssessmentElementInfo.setCode(elementType.getElementCode());
				selfAssessmentElementInfo.setLabel(elementType.getLabel());
				elementsInfos.add(selfAssessmentElementInfo);

				if (elements != null) {
					SelfassessmentElement selfassessmentElement = elements.stream()
							.filter(temp -> temp.getElementCode().equals(elementType.getElementCode())).findFirst()
							.orElse(null);
					if (selfassessmentElement != null) {
						selfAssessmentElementInfo.setId(selfassessmentElement.getId());
						selfAssessmentElementInfo.setSelected(true);
					}
				}

			});

			strengthAttributesInfos.add(selfAssessmentAttributeInfo);
			idx++;
		} while (size > idx);

		return strengthAttributesInfos;

	}


	
	
	/**
	 * Complete rubric.
	 *
	 * @param assessmentInfo the assessment info
	 */
	public void completeRubric(AssessmentInfo assessmentInfo){
		Assessment	assessment = assessmentService.findByPrimaryKey(assessmentInfo.getAssessmentId());
		assessment.setLocked('Y');
		assessmentService.update(assessment);
	}
	
	/**
	 * Save rubric.
	 *
	 * @param assessmentInfo the assessment info
	 */
	@Transactional(value = TxType.REQUIRES_NEW)
	public void saveRubric(AssessmentInfo assessmentInfo) {

		Assessment assessment = assessmentService.findByCycleId(assessmentInfo.getCycleId(), "1");

		if (assessment== null){
			assessment = new Assessment();
			assessment.setEffdate(new Date());
			assessment.setCapCycle(cycleService.findByPrimaryKey(assessmentInfo.getCycleId()));
			assessment.setTypeCode("1");
		}

		assessment.setLocked(assessmentInfo.getLocked() != null ? assessmentInfo.getLocked() : 'N');
		assessment.setCompleted(assessmentInfo.getCompleted() != null ? assessmentInfo.getCompleted() : 'N');
		assessment.setCalFeedback(assessmentInfo.getCalFeedback());
		assessment.setAddlObservationRequired(assessmentInfo.getAddlObservationRequired());
		assessment.setReinFeedback(assessmentInfo.getReinFeedback());
		assessment.setRefineFeedback(assessmentInfo.getRefineFeedback());
		assessment.setEvidenceFeedback(assessmentInfo.getEvidenceFeedback());
		assessment.setRecommendedfocus(assessmentInfo.getRecommendedfocus());
		assessment.setSpApproved(assessmentInfo.getSpApproved());
		assessment.setSpApprovedDate(assessmentInfo.getSpApprovedDate());
		assessment.setPsApproved(assessmentInfo.getPsApproved());
		assessment.setPsApprovedDate(assessmentInfo.getPsApprovedDate());

		if (assessment.getAssessmentId() != null)
			assessmentService.update(assessment);
		else
			assessment = assessmentService.create(assessment);

		assessmentInfo.setAssessmentId(assessment.getAssessmentId());
	
		for(ElementInfo elementInfo:assessmentInfo.getElements()){

			RubricElement rubricElement = rubricElementService.findRubricElementByAssessment(assessmentInfo.getAssessmentId(), elementInfo.getElementCode());

			if (rubricElement== null){
				rubricElement = new RubricElement();
				rubricElement.setAssessment(assessment);
				rubricElement.setEffDate(new Date());
				rubricElement.setElementCode(elementInfo.getElementCode());
			}
			
			for(DimensionInfo dimensionInfo:elementInfo.getDimensions()){
				
				ElementRating elementRating = elementRatingService.findByElementAndRating(elementInfo.getElementCode(),
						dimensionInfo.getRatingCode());

				 Long mapId= rubricMapService.findRubricMapByAssessment(assessmentInfo.getAssessmentId(), dimensionInfo.getDimensionCode(),elementInfo.getElementCode());

				 RubricMap rubricMap=mapId!=null?rubricMapService.findByPrimaryKey(mapId):null;
		

				if (dimensionInfo.getRatingCode() == null || dimensionInfo.getRatingCode().isEmpty()) {
					if (rubricMap != null) {
						rubricMap.setExpDate(new Date());
					}else return;
				}

				
				if (rubricMap== null)
					{
					rubricMap = new RubricMap();
					rubricMap.setAssessment(assessment);
					rubricMap.setEffdate(new Date());
					rubricMap.setDimensionCode(dimensionInfo.getDimensionCode());
				}
				
				if (elementRating != null) {
					rubricMap.setRatingElementLinkId(elementRating.getLinkId());
						}

				

				if (rubricMap.getMapId() != null)
					rubricMapService.update(rubricMap);
				else
					rubricMapService.create(rubricMap);

			}

			rubricElement.setComplete(elementInfo.getCompleted());
			rubricElement.setRationale(elementInfo.getRationale());
			rubricElement.setMetThreshold(elementInfo.getMetThreshHold());

			if (rubricElement.getId() != null)
				rubricElementService.update(rubricElement);
			else
				rubricElementService.create(rubricElement);

		}

		entityManager.flush();
		entityManager.clear();

	}

	/**
	 * Save attribute.
	 *
	 * @param assessmentInfo the assessment info
	 * @param typeCode the type code
	 */
	private void saveAttribute(AssessmentInfo assessmentInfo, String typeCode) {

		Assessment assessment = assessmentService.findByPrimaryKey(assessmentInfo.getAssessmentId());

		List<SelfAssessmentAttributeInfo> attributeInfos = typeCode.equals("1") ? assessmentInfo.getStrengths()
				: assessmentInfo.getGrowths();
		List<SelfAssessmentAttributeInfo> deleteAttributeInfos = typeCode.equals("1")
				? assessmentInfo.getDeletedStrengths() : assessmentInfo.getDeletedGrowths();

		deleteAttributeInfos.stream().forEach(attributeInfo -> {

			SelfAssessmentAttribute attribute = selfassessmentAttributeService.findByPrimaryKey(attributeInfo.getId());
			attribute.setExpDate(new Date());

			List<SelfassessmentElement> selfassessmentElement = attribute.getSelfassessmentElements();

			selfassessmentElement.stream().forEach(assessmentElement -> {
				assessmentElement.setExpDate(new Date());
				slfassessmentElementService.update(assessmentElement);

			});

			selfassessmentAttributeService.update(attribute);

		});

		
		for (SelfAssessmentAttributeInfo attributeInfo : attributeInfos) {
			SelfAssessmentAttribute selfassessmentAttribute = selfassessmentAttributeService.findByAssessment(assessment.getAssessmentId(),typeCode,attributeInfo.getAttributeNumber()!=null?attributeInfo.getAttributeNumber():new Long(attributeInfos.indexOf(attributeInfo)));

			if (selfassessmentAttribute== null) {
				selfassessmentAttribute = new SelfAssessmentAttribute();
				selfassessmentAttribute.setEffDate(new Date());
				selfassessmentAttribute.setAssessment(assessment);
				selfassessmentAttribute.setTypeCode(typeCode);
			}
			
			selfassessmentAttribute.setAttributeNumber(new Long(attributeInfos.indexOf(attributeInfo)));
			selfassessmentAttribute.setArea(attributeInfo.getArea());
			selfassessmentAttribute.setRationale(attributeInfo.getRationale());

			if (selfassessmentAttribute.getId() != null)
				selfassessmentAttributeService.update(selfassessmentAttribute);
			else
				selfassessmentAttribute = selfassessmentAttributeService.create(selfassessmentAttribute);

			

			for (SelfAssessmentElementInfo elementInfo : attributeInfo.getElements()) {

				SelfassessmentElement selfassessmentElement=slfassessmentElementService.findByselfAssessmentAttribute(selfassessmentAttribute.getId(), elementInfo.getCode());

				if (!elementInfo.isSelected() && selfassessmentElement!= null) {
					selfassessmentElement.setExpDate(new Date());
					slfassessmentElementService.update(selfassessmentElement);
					continue;
				}

				if (elementInfo.isSelected() && selfassessmentElement == null) {
					selfassessmentElement = new SelfassessmentElement();
					selfassessmentElement.setEffDate(new Date());
					selfassessmentElement.setElementCode(elementInfo.getCode());
					selfassessmentElement.setSelfAssessmentAttribute(selfassessmentAttribute);
					slfassessmentElementService.create(selfassessmentElement);
				}

			}

		}

	}

	
	/**
	 * Save assessment.
	 *
	 * @param assessmentInfo the assessment info
	 */
	@Transactional(value = TxType.REQUIRES_NEW)

	public void saveAssessment(AssessmentInfo assessmentInfo) {	
		
		Assessment assessment = assessmentService.findByCycleId(assessmentInfo.getCycleId(), assessmentInfo.getTypeCode());

		if (assessment== null) {
			assessment = new Assessment();
			assessment.setEffdate(new Date());
			assessment.setCapCycle(cycleService.findByPrimaryKey(assessmentInfo.getCycleId()));
			assessment.setTypeCode(assessmentInfo.getTypeCode());
		}

		assessment.setLocked(assessmentInfo.getLocked() != null ? assessmentInfo.getLocked() : 'N');
		assessment.setCompleted(assessmentInfo.getCompleted() != null ? assessmentInfo.getCompleted() : 'N');
		assessment.setCalFeedback(assessmentInfo.getCalFeedback());
		assessment.setAddlObservationRequired(assessmentInfo.getAddlObservationRequired());
		assessment.setReinFeedback(assessmentInfo.getReinFeedback());
		assessment.setRefineFeedback(assessmentInfo.getRefineFeedback());
		assessment.setEvidenceFeedback(assessmentInfo.getEvidenceFeedback());
		assessment.setRecommendedfocus(assessmentInfo.getRecommendedfocus());
		assessment.setSpApproved(assessmentInfo.getSpApproved());
		assessment.setSpApprovedDate(assessmentInfo.getSpApprovedDate());
		assessment.setPsApproved(assessmentInfo.getPsApproved());
		assessment.setPsApprovedDate(assessmentInfo.getPsApprovedDate());

		if (assessmentInfo.getAssessmentId() != null)
			assessmentService.update(assessment);
		else
			assessment = assessmentService.create(assessment);
		
		saveAttribute(assessmentInfo, "1");
		saveAttribute(assessmentInfo, "2");
		goalManager.save(assessmentInfo);

	}

	/**
	 * Gets the self assessment element info.
	 *
	 * @return the self assessment element info
	 */
	public List<SelfAssessmentElementInfo> getSelfAssessmentElementInfo() {

		List<ElementType> elementsTypes = elementTypeService.findAll();

		List<SelfAssessmentElementInfo> selfAssessmentElementInfos = new ArrayList<SelfAssessmentElementInfo>();

		elementsTypes.stream().forEach(elementType -> {
			SelfAssessmentElementInfo selfAssessmentElementInfo = new SelfAssessmentElementInfo();
			selfAssessmentElementInfo.setCode(elementType.getElementCode());
			selfAssessmentElementInfo.setLabel(elementType.getLabel());
			selfAssessmentElementInfos.add(selfAssessmentElementInfo);
		});

		return selfAssessmentElementInfos;
	}
	
	/**
	 * Unlock self assessmet.
	 *
	 * @param id the id
	 */
	public void unlockSelfAssessmet(Long id){
		Assessment assessment=assessmentService.findByPrimaryKey(id);
		
		assessment.setCompleted('N');
		assessment.setLocked('N');
		assessmentService.update(assessment);
	}

}
