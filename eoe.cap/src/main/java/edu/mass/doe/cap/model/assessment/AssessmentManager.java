package edu.mass.doe.cap.model.assessment;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.dataservice.assessment.AssessmentElementLinkService;
import edu.mass.doe.cap.dataservice.assessment.AssessmentService;
import edu.mass.doe.cap.dataservice.assessment.AssessmentTypeService;
import edu.mass.doe.cap.dataservice.assessment.DimensionService;
import edu.mass.doe.cap.dataservice.assessment.ElementRatingService;
import edu.mass.doe.cap.dataservice.assessment.ElementTypeService;
import edu.mass.doe.cap.dataservice.assessment.EvidenceSourceFocusService;
import edu.mass.doe.cap.dataservice.assessment.ObservationFocusService;
import edu.mass.doe.cap.dataservice.assessment.RatingTypeService;
import edu.mass.doe.cap.dataservice.assessment.RubricElementService;
import edu.mass.doe.cap.dataservice.assessment.RubricMapService;
import edu.mass.doe.cap.dataservice.candidate.CycleService;
import edu.mass.doe.cap.dataservice.entity.Assessment;
import edu.mass.doe.cap.dataservice.entity.AssessmentElementLink;
import edu.mass.doe.cap.dataservice.entity.AssessmentType;
import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.entity.DimensionType;
import edu.mass.doe.cap.dataservice.entity.ElementRating;
import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.EvidenceSourceFocus;
import edu.mass.doe.cap.dataservice.entity.ObservationFocus;
import edu.mass.doe.cap.dataservice.entity.RatingType;
import edu.mass.doe.cap.dataservice.entity.RubricElement;
import edu.mass.doe.cap.dataservice.entity.RubricMap;
import edu.mass.doe.cap.dataservice.pojo.AssessmentInfo;
import edu.mass.doe.cap.dataservice.pojo.Attribute;
import edu.mass.doe.cap.dataservice.pojo.DimensionInfo;
import edu.mass.doe.cap.dataservice.pojo.ElementInfo;
import edu.mass.doe.cap.dataservice.pojo.EvidenceFileInfo;
import edu.mass.doe.cap.dataservice.pojo.EvidenceSource;
import edu.mass.doe.cap.dataservice.pojo.RatingInfo;
import edu.mass.doe.cap.model.file.EvidenceFileManager;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class AssessmentManager.
 */
@Component
public class AssessmentManager {

	private static Logger logger = LoggerFactory.getLogger(AssessmentManager.class);

	@Autowired
	private Environment env;

	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	private DimensionService dimensionService;

	@Autowired
	private AssessmentTypeService assessmentTypeService;

	@Autowired
	private RatingTypeService ratingTypeService;

	@Autowired
	private CycleService cycleService;

	@Autowired
	private ElementTypeService elementTypeService;

	@Autowired
	private ElementRatingService elementRatingService;

	@Autowired
	private RubricElementService rubricElementService;

	@Autowired
	private RubricMapService rubricMapService;
	
	@Autowired
	private EvidenceFileManager evidenceFileManager;
	
	@Autowired
	private AssessmentElementLinkService assessmentElementLinkService;
	
	@Autowired
	private ObservationFocusService observationFocusService;
	
	@Autowired
	private EvidenceSourceFocusService evidenceSourceFocusService;
	
	
	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	/**
	 * Gets the assessment info.
	 *
	 * @param cycleId the cycle id
	 * @param typeCode the type code
	 * @return the assessment info
	 * @throws Exception the exception
	 */
	public AssessmentInfo getAssessmentInfo(Long cycleId, String typeCode) throws Exception {

		AssessmentInfo assessmentInfo = new AssessmentInfo();

		AssessmentType assessmentType = assessmentTypeService.findByPrimaryKey(typeCode);

		assessmentInfo.setTypeCode(assessmentType.getTypeCode());

		assessmentInfo.setTypeDesc(assessmentType.getTypeDesc());

		CapCycle capCycle = cycleService.findByPrimaryKey(cycleId);
		
		
		
		if(assessmentInfo.getTypeCode().equals("3")){
			assessmentInfo.setReadyToTeach(capCycle.getReadyToTeach());
			}

		List<ElementType> elements = elementTypeService.findAll();

		List<ElementInfo> elementInfos = new ArrayList<ElementInfo>();

		List<Attribute> reinAttributes = new ArrayList<Attribute>();

		List<Attribute> refineAttributes = new ArrayList<Attribute>();
		assessmentInfo.setReinAttributes(reinAttributes);
		assessmentInfo.setRefineAttributes(refineAttributes);
		List<ObservationFocus> observationFoci = observationFocusService.findAll();
		List<EvidenceSourceFocus> evidenceSourceFoci = evidenceSourceFocusService.findAll();
		

		for (ElementType elementType : elements) {

			ElementInfo elementInfo = new ElementInfo();
			elementInfo.setElementCode(elementType.getElementCode());
			elementInfo.setElementLabel(elementType.getLabel());
			elementInfo.setLongDesc(elementType.getLongDesc());
			elementInfo.setAltDesc(elementType.getAltDesc());
			
			
			
			List<EvidenceFileInfo> files=evidenceFileManager.getEvidenceFiles(cycleId);
			Map<String,EvidenceSource> evidenceSources=getEvidenceSource(cycleId);
			EvidenceSource evidenceSource=evidenceSources.get(elementType.getElementCode());
			elementInfo.setEvidenceSources(evidenceSource);
			List<String> text = new ArrayList<String>(); 
			
			ObservationFocus observationFocus=observationFoci.stream().filter(temp->
				temp.getElementCode().equals(elementInfo.getElementCode()) && temp.getObservationNumber()==01&&temp.getTypeCode().equals("01")).findFirst().orElse(null);
			
			if(observationFocus!=null){
				evidenceSource.setAnnounced1Focus(true);
				text.add("Announced Observation 1");
			}
			
			observationFocus=observationFoci.stream().filter(temp->
			temp.getElementCode().equals(elementInfo.getElementCode()) && temp.getObservationNumber()==01&&temp.getTypeCode().equals("02")).findFirst().orElse(null);
		
			if(observationFocus!=null){
			evidenceSource.setUnAnnounced1Focus(true);
			text.add("Unannounced Observation 1");
			}
			
			observationFocus=observationFoci.stream().filter(temp->
			temp.getElementCode().equals(elementInfo.getElementCode()) && temp.getObservationNumber()==02&&temp.getTypeCode().equals("01")).findFirst().orElse(null);
		
			if(observationFocus!=null){
			evidenceSource.setAnnounced2Focus(true);
			text.add("Announced Observation 2");
			}
			
			
			
			observationFocus=observationFoci.stream().filter(temp->
			temp.getElementCode().equals(elementInfo.getElementCode()) && temp.getObservationNumber()==02&&temp.getTypeCode().equals("02")).findFirst().orElse(null);
		
			if(observationFocus!=null){
			evidenceSource.setUnAnnounced2Focus(true);
			text.add("Unannounced Observation 2");
			}
			
			EvidenceSourceFocus evidenceSourceFocus=evidenceSourceFoci.stream().filter(temp->
			temp.getElementCode().equals(elementInfo.getElementCode()) && temp.getEvidenceCode().equals("MSL")).findFirst().orElse(null);
			
			if(evidenceSourceFocus!=null){
				evidenceSource.setMslFocus(true);
				text.add("Measure of Student Learning");
			}
		
			evidenceSourceFocus=evidenceSourceFoci.stream().filter(temp->
			temp.getElementCode().equals(elementInfo.getElementCode()) && temp.getEvidenceCode().equals("SFD")).findFirst().orElse(null);
			
			if(evidenceSourceFocus!=null){
				evidenceSource.setSlfFocus(true);
				text.add("Student Feedback");
			}
			
			evidenceSourceFocus=evidenceSourceFoci.stream().filter(temp->
			temp.getElementCode().equals(elementInfo.getElementCode()) && temp.getEvidenceCode().equals("CAF")).findFirst().orElse(null);
			
			if(evidenceSourceFocus!=null){
				evidenceSource.setCafFocus(true);
				text.add("Candidate Artifacts");
			}
			
			evidenceSourceFocus=evidenceSourceFoci.stream().filter(temp->
			temp.getElementCode().equals(elementInfo.getElementCode()) && temp.getEvidenceCode().equals("PPG")).findFirst().orElse(null);
			
			if(evidenceSourceFocus!=null){
				evidenceSource.setPpgFocus(true);
				text.add("Professional Practice Goals");
			}
			
			
			Iterator<String> iterator = text.iterator();
			String reqText=iterator.hasNext()?iterator.next():"";
			
			while(iterator.hasNext()){
				String val=iterator.next();
				if(iterator.hasNext())
					reqText=reqText+" ,"+val;
				else
					reqText=reqText+" and the "+val+".";
				
			}
			
			evidenceSource.setText(reqText);

			
			files.stream().forEach(temp->{
				Attribute attribute= 					
						temp.getAttributes().stream().filter(
						temp2->temp2.getCode().equals(elementInfo.getElementCode())).findFirst().orElse(null);
				
				if(attribute!=null){
					elementInfo.getFiles().add(temp);
					
					Attribute evidenceType=temp.getEvidenceTypes().stream().filter(
							temp2->temp2.getCode().equals("SFD")).findFirst().orElse(null);
					if(evidenceType!=null)
						evidenceSource.setSlf(true);
					
					evidenceType=temp.getEvidenceTypes().stream().filter(
							temp2->temp2.getCode().equals("CAF")).findFirst().orElse(null);
					if(evidenceType!=null)
						evidenceSource.setCaf(true);
					
					
					
				}
			});


			Attribute attribute = new Attribute();
			attribute.setCode(elementType.getElementCode());
			attribute.setLabel(elementType.getAltDesc());
			attribute.setTypeCode("4");
			reinAttributes.add(attribute);

			attribute = new Attribute();
			attribute.setCode(elementType.getElementCode());
			attribute.setLabel(elementType.getAltDesc());
			attribute.setTypeCode("3");
			refineAttributes.add(attribute);

			List<ElementRating> ratings = elementRatingService.findAllByElementCode(elementType.getElementCode());
			List<RatingInfo> ratingInfos = new ArrayList<RatingInfo>();
			List<DimensionInfo> dimensionInfos = new ArrayList<DimensionInfo>();

			for (ElementRating rating : ratings) {
				RatingInfo ratingInfo = new RatingInfo();
				ratingInfo.setLinkId(rating.getLinkId());
				ratingInfo.setElementCode(rating.getElementCode());
				ratingInfo.setLongDesc(rating.getLongDesc());
				ratingInfo.setShortDesc(ratingTypeService.findByPrimaryKey(rating.getRatingCode()).getDesc());
				ratingInfo.setRatingCode(rating.getRatingCode());
				ratingInfos.add(ratingInfo);
			}

			for (DimensionType dimensionType : dimensionService.findAll()) {
				DimensionInfo dimensionInfo = new DimensionInfo();
				dimensionInfo.setDimensionCode(dimensionType.getDimensionCode());
				dimensionInfo.setDimensionDesc(dimensionType.getDesc());
				dimensionInfo.setDimensionLongDesc(dimensionType.getLongDesc());
				dimensionInfos.add(dimensionInfo);
				if (typeCode.equals("3")) {
					Assessment formativeAssessment = assessmentService.findByCycleId(cycleId, "2");
					if (formativeAssessment != null) {
						
					RubricElement formativeRubricElement =	formativeAssessment.getRubricElements().stream().filter(temp->temp.getElementCode().equals(elementInfo.getElementCode())).findFirst().orElse(null);
						if(formativeRubricElement!=null)
						elementInfo.setFormativeRationale(formativeRubricElement.getRationale());
						
					RubricMap	rubricMap = formativeAssessment.getRubrics().stream()
								.filter(temp -> temp.getDimensionCode().equals(dimensionInfo.getDimensionCode()))
								.filter(temp -> elementRatingService.findByPrimaryKey(temp.getRatingElementLinkId())
										.getElementCode().equals(elementInfo.getElementCode()))
								.findFirst().orElse(null);
					ElementRating elementRating=null;
						if (rubricMap != null){
							elementRating = elementRatingService
									.findByPrimaryKey(rubricMap.getRatingElementLinkId());
						dimensionInfo.setFormativeRatingCode(elementRating.getRatingCode());
						dimensionInfo.setFormativeRatingDesc(ratingTypeService.findByPrimaryKey(elementRating.getRatingCode()).getDesc());
						}
					}

				}
				
			}

			elementInfo.setDimensions(dimensionInfos);

			elementInfo.setRatingInfos(ratingInfos);

			elementInfos.add(elementInfo);
		}

		assessmentInfo.setElements(elementInfos);

		Assessment assessment = assessmentService.findByCycleId(cycleId, typeCode);

		if (assessment != null) {

			assessmentInfo.setAssessmentId(assessment.getAssessmentId());
			assessmentInfo.setCycleId(cycleId);
			assessmentInfo.setTypeCode(assessment.getTypeCode());
			assessmentInfo.setLocked(assessment.getLocked());
			assessmentInfo.setCompleted(assessment.getCompleted()!=null?assessment.getCompleted():'N');
			assessmentInfo.setCalFeedback(assessment.getCalFeedback());
			assessmentInfo.setAddlObservationRequired(assessment.getAddlObservationRequired());
			assessmentInfo.setReinFeedback(assessment.getReinFeedback());
			assessmentInfo.setRefineFeedback(assessment.getRefineFeedback());
			assessmentInfo.setEvidenceFeedback(assessment.getEvidenceFeedback());
			assessmentInfo.setRecommendedfocus(assessment.getRecommendedfocus());
			assessmentInfo.setSpApproved(assessment.getSpApproved()!=null?assessment.getSpApproved():'N');
			assessmentInfo.setSpApprovedDate(assessment.getSpApprovedDate());
			assessmentInfo.setPsApproved(assessment.getPsApproved()!=null?assessment.getPsApproved():'N');
			assessmentInfo.setPsApprovedDate(assessment.getPsApprovedDate());

			List<RubricElement> rubricElements = assessment.getRubricElements();
			List<AssessmentElementLink> assessmentElementLinks = assessment.getAssessmentElementLinks();

			
			
			for (ElementInfo elementInfo : assessmentInfo.getElements()) {

				RubricElement rubricElement = rubricElements.stream()
						.filter(temp -> temp.getElementCode().equals(elementInfo.getElementCode())).findFirst()
						.orElse(null);
				
				if(rubricElement==null)
					continue;

				elementInfo.setId(rubricElement.getId());
				elementInfo.setMetThreshHold(rubricElement.getMetThreshold());
				elementInfo.setRationale(rubricElement.getRationale());
				elementInfo.setCompleted(rubricElement.getComplete()!=null?rubricElement.getComplete():'N');


				
				for (DimensionInfo dimensionInfo : elementInfo.getDimensions()) {

					List<RubricMap> rubricMaps = assessment.getRubrics();

					RubricMap rubricMap = rubricMaps.stream()
							.filter(temp -> temp.getDimensionCode().equals(dimensionInfo.getDimensionCode()))
							.filter(temp -> elementRatingService.findByPrimaryKey(temp.getRatingElementLinkId())
									.getElementCode().equals(elementInfo.getElementCode()))
							.findFirst().orElse(null);

					if (rubricMap == null)
						continue;

					ElementRating elementRating = elementRatingService
							.findByPrimaryKey(rubricMap.getRatingElementLinkId());
					
					dimensionInfo.setId(rubricMap.getMapId());
					dimensionInfo.setLinkId(rubricMap.getRatingElementLinkId());
					dimensionInfo.setRatingCode(elementRating.getRatingCode());
					dimensionInfo.setRatingDesc(ratingTypeService.findByPrimaryKey(elementRating.getRatingCode()).getDesc());
					

				}

				for (Attribute attribute : assessmentInfo.getReinAttributes()) {
					AssessmentElementLink assessmentElementLink = assessmentElementLinks.stream()
							.filter(temp -> temp.getElementCode().equals(attribute.getCode()))
							.filter(temp -> temp.getAttributeType().equals(attribute.getTypeCode())).findFirst()
							.orElse(null);
					if (assessmentElementLink != null) {
						attribute.setId(assessmentElementLink.getLinkId());
						attribute.setSelected(true);
					}

				}

				for (Attribute attribute : assessmentInfo.getRefineAttributes()) {
					AssessmentElementLink assessmentElementLink = assessmentElementLinks.stream()
							.filter(temp -> temp.getElementCode().equals(attribute.getCode()))
							.filter(temp -> temp.getAttributeType().equals(attribute.getTypeCode())).findFirst()
							.orElse(null);
					if (assessmentElementLink != null) {
						attribute.setId(assessmentElementLink.getLinkId());
						attribute.setSelected(true);
					}

				}

			}
		}

		return assessmentInfo;
	}

	/**
	 * Save.
	 *
	 * @param assessmentInfo the assessment info
	 * @param elementCode the element code
	 * @throws Exception the exception
	 */
	@Transactional(value = TxType.REQUIRES_NEW)
	public void save(AssessmentInfo assessmentInfo,String elementCode) throws Exception{

		try{
		Assessment assessment = assessmentService.findByCycleId(assessmentInfo.getCycleId(), assessmentInfo.getTypeCode());
		
		if(CapUtil.ispractitioner()&&(assessmentInfo.getCompleted()!=null && assessmentInfo.getCompleted()=='N')&& (assessment!=null &&assessment.getCompleted()!=null && assessment.getCompleted()=='Y') ){
			throw new OptimisticLockException();
		}


		if (assessment== null){		
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
		
		if(CapUtil.ispractitioner()){
		assessment.setSpApproved(assessmentInfo.getSpApproved());
		assessment.setSpApprovedDate(assessmentInfo.getSpApprovedDate());
		}
		
		if(CapUtil.isSupervisor()||CapUtil.isManager()){
		assessment.setPsApproved(assessmentInfo.getPsApproved());
		assessment.setPsApprovedDate(assessmentInfo.getPsApprovedDate());
		assessment.setSpApproved(assessmentInfo.getSpApproved());
		assessment.setSpApprovedDate(assessmentInfo.getSpApprovedDate());
		}

		if (assessment.getAssessmentId() != null)
			assessmentService.update(assessment);
		else
			assessment = assessmentService.create(assessment);

		assessmentInfo.setAssessmentId(assessment.getAssessmentId());
		assessmentInfo.getElements().forEach(elementInfo -> {
			
			if(elementCode!=null && !elementCode.equals(elementInfo.getElementCode()))
				return;

			RubricElement rubricElement = rubricElementService.findRubricElementByAssessment(assessmentInfo.getAssessmentId(), elementInfo.getElementCode());

			if (rubricElement == null){
				rubricElement = new RubricElement();
				rubricElement.setAssessment(assessmentService.findByPrimaryKey(assessmentInfo.getAssessmentId()));
				rubricElement.setEffDate(new Date());
				rubricElement.setElementCode(elementInfo.getElementCode());
			}
			
			elementInfo.getDimensions().forEach(dimensionInfo -> {
				
				ElementRating elementRating = elementRatingService.findByElementAndRating(elementInfo.getElementCode(),
						dimensionInfo.getRatingCode());
				
				 Long mapId= rubricMapService.findRubricMapByAssessment(assessmentInfo.getAssessmentId(), dimensionInfo.getDimensionCode(),elementInfo.getElementCode());

				 RubricMap rubricMap=mapId!=null?rubricMapService.findByPrimaryKey(mapId):null;
				 
				if (dimensionInfo.getRatingCode() == null || dimensionInfo.getRatingCode().isEmpty()){
					if (rubricMap != null){
						rubricMap.setExpDate(new Date());
					}else
						return;
				}
				
				

				if (rubricMap== null){
					rubricMap = new RubricMap();
					rubricMap.setAssessment(assessmentService.findByPrimaryKey(assessmentInfo.getAssessmentId()));
					rubricMap.setEffdate(new Date());
					rubricMap.setDimensionCode(dimensionInfo.getDimensionCode());
				} 

	
				if (elementRating != null) {
					rubricMap.setRatingElementLinkId(elementRating.getLinkId());
				}
				
				if (rubricMap.getMapId()!= null)
					rubricMapService.update(rubricMap);
				else
					rubricMapService.create(rubricMap);

			});

			rubricElement.setComplete(elementInfo.getCompleted());
			rubricElement.setRationale(elementInfo.getRationale());
			rubricElement.setMetThreshold(elementInfo.getMetThreshHold());

			if (elementInfo.getId() != null)
				rubricElementService.update(rubricElement);
			else
				rubricElementService.create(rubricElement);

		});

		assessmentInfo.getReinAttributes().forEach(attribute -> {

			AssessmentElementLink assessmentElementLink=assessmentElementLinkService.findAssessmentElementLinkByAssessment(assessmentInfo.getAssessmentId(), attribute.getCode(), attribute.getTypeCode());

			if (assessmentElementLink== null){
				assessmentElementLink = new AssessmentElementLink();
				assessmentElementLink.setAttributeType(attribute.getTypeCode());
				assessmentElementLink.setEffdate(new Date());
				assessmentElementLink.setElementCode(attribute.getCode());
				assessmentElementLink
						.setAssessment(assessmentService.findByPrimaryKey(assessmentInfo.getAssessmentId()));
			}

			if (attribute.getId() != null && !attribute.isSelected()) {
				assessmentElementLink.setExpDate(new Date());
				assessmentElementLinkService.update(assessmentElementLink);
			}

			if (attribute.getId() == null && attribute.isSelected()) {
				assessmentElementLinkService.create(assessmentElementLink);
			}

		});

		assessmentInfo.getRefineAttributes().forEach(attribute -> {

			AssessmentElementLink assessmentElementLink=assessmentElementLinkService.findAssessmentElementLinkByAssessment(assessmentInfo.getAssessmentId(), attribute.getCode(), attribute.getTypeCode());
		

			if (assessmentElementLink== null){
				assessmentElementLink = new AssessmentElementLink();
				assessmentElementLink.setAttributeType(attribute.getTypeCode());
				assessmentElementLink.setEffdate(new Date());
				assessmentElementLink.setElementCode(attribute.getCode());
				assessmentElementLink
						.setAssessment(assessmentService.findByPrimaryKey(assessmentInfo.getAssessmentId()));
			}

			if (attribute.getId() != null && !attribute.isSelected()) {
				assessmentElementLink.setExpDate(new Date());
				assessmentElementLinkService.update(assessmentElementLink);
			}

			if (attribute.getId() == null && attribute.isSelected()) {
				assessmentElementLinkService.create(assessmentElementLink);
			}

		});
		
		if(assessmentInfo.getTypeCode().equals("3")){
		CapCycle cycle=	cycleService.findByPrimaryKey(assessmentInfo.getCycleId());
		cycle.setReadyToTeach(assessmentInfo.getReadyToTeach());
		cycleService.update(cycle);
		
		}
		
		}catch(Exception e){
			entityManager.flush();
			entityManager.clear();
			throw e;
		}
		
		entityManager.flush();
		entityManager.clear();

	}

	/**
	 * Gets the alldimensions.
	 *
	 * @return the alldimensions
	 */
	public List<DimensionInfo> getAlldimensions() {

		List<DimensionInfo> dimensionInfos = new ArrayList<DimensionInfo>();

		for (DimensionType dimesionType : dimensionService.findAll()) {
			DimensionInfo dimensionInfo = new DimensionInfo();
			dimensionInfo.setDimensionCode(dimesionType.getDimensionCode());
			dimensionInfo.setDimensionDesc(dimesionType.getDesc());
			dimensionInfo.setDimensionLongDesc(dimesionType.getLongDesc());
			dimensionInfos.add(dimensionInfo);
		}

		return dimensionInfos;
	}

	/**
	 * Gets the all rating types.
	 *
	 * @return the all rating types
	 */
	public Map<String, String> getAllRatingTypes() {

		Map<String, String> types = new LinkedHashMap<String, String>();

		for (RatingType type : ratingTypeService.findAll()) {
			types.put(type.getRatingCode(), type.getDesc());
		}

		return types;
	}
	
/**
 * Unlock asssessment.
 *
 * @param id the id
 */
public void unlockAsssessment(Long id){
		Assessment assessment = assessmentService.findByPrimaryKey(id);
		assessment.setLocked('N');
		assessment.setCompleted('N');
		assessment.setSpApproved('N');
		assessment.setSpApprovedDate(null);
		assessment.setPsApproved('N');
		assessment.setPsApprovedDate(null);
		assessmentService.update(assessment);
		
	}

/**
 * Gets the evidence source.
 *
 * @param cycleId the cycle id
 * @return the evidence source
 */
public Map<String,EvidenceSource> getEvidenceSource(Long cycleId){
	return assessmentService.getEvidenceSource(cycleId);
}

public boolean assessmentIsComplete(Long cycleId,String typeCode) {
	return assessmentService.assessmentIsComplete(cycleId, typeCode);
}

}
