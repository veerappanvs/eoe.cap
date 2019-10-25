package edu.mass.doe.cap.dataservice.batch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.assessment.DimensionService;
import edu.mass.doe.cap.dataservice.assessment.ElementRatingService;
import edu.mass.doe.cap.dataservice.assessment.ElementTypeService;
import edu.mass.doe.cap.dataservice.assessment.RatingTypeService;
import edu.mass.doe.cap.dataservice.entity.DimensionType;
import edu.mass.doe.cap.dataservice.entity.ElementRating;
import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.UploadedData;
import edu.mass.doe.cap.dataservice.entity.UploadedRating;
import edu.mass.doe.cap.dataservice.file.EvidencefileRepository;
import edu.mass.doe.cap.dataservice.pojo.DimensionInfo;
import edu.mass.doe.cap.dataservice.pojo.ElementInfo;
import edu.mass.doe.cap.dataservice.pojo.ImportedCapInfo;

/**
 * The Class UploadedDataService.
 */
@Repository
public class UploadedDataService {
	

	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	@Autowired
	private Environment env;
	
	@Autowired
	private RatingTypeService ratingTypeService;
	
	@Autowired
	private ElementTypeService elementTypeService;

	@Autowired
	private ElementRatingService elementRatingService;

	@Autowired
	private DimensionService dimensionService;

	
	/**
	 * Gets the imported cap info report.
	 *
	 * @param orgCode the org code
	 * @param fromYear the from year
	 * @param toYear the to year
	 * @return the imported cap info report
	 */
	public List<ImportedCapInfo> getImportedCapInfoReport(String orgCode,String fromYear,String toYear,String status) {
		List<ImportedCapInfo>  reports= new ArrayList<ImportedCapInfo>();
		
		Query q = entityManager.createNativeQuery(env.getProperty("cap.importedCapReport"));
		q.setParameter("orgCode", orgCode);
		q.setParameter("fromSchoolYear", fromYear);
		q.setParameter("toSchoolYear", toYear);
		q.setParameter("statusCode", status);
		List<Object[]> results=q.getResultList();
		
		for(Object[] object:results){
			ImportedCapInfo importedCapInfo = new ImportedCapInfo();
			importedCapInfo.setUploadId(object[1]!=null?((BigDecimal) object[1]).longValue():null);
			importedCapInfo.setDistrictName(object[2]!=null?(String) object[2]:null);
			importedCapInfo.setSchoolName((String) object[3]);
			importedCapInfo.setSchoolYear(object[4]!=null?(String.valueOf((BigDecimal) object[4])):null);
			importedCapInfo.setProgramName((String) object[5]);
			importedCapInfo.setCandidateMepid(object[6]!=null?((BigDecimal) object[6]).longValue():null);
			importedCapInfo.setCandidateName((String) object[7] +" "+ (String) object[8]);
			importedCapInfo.setCandidateEmail((String) object[9]);
			importedCapInfo.setSupervisorName((String) object[10]);
			importedCapInfo.setSupervisorEmail((String) object[11]);
			importedCapInfo.setPractitionerName((String) object[12]);
			importedCapInfo.setPractitionerEmail((String) object[13]);
			importedCapInfo.setImportDate((Date) object[14]);
			importedCapInfo.setStatus((String) object[15]);			
			if(importedCapInfo.getStatus()!=null && importedCapInfo.getStatus().equals("1"))
				importedCapInfo.setStatusDesc("Complete");
			else if(importedCapInfo.getStatus()!=null && importedCapInfo.getStatus().equals("2"))
				importedCapInfo.setStatusDesc("Incomplete");
			else if(importedCapInfo.getStatus()!=null && importedCapInfo.getStatus().equals("3"))
				importedCapInfo.setStatusDesc("Delete");
				
			reports.add(importedCapInfo);
		}
		
		return reports;
	}
	
	/**
	 * Gets the imported data info.
	 *
	 * @param orgCode the org code
	 * @param uploadId the upload id
	 * @return the imported data info
	 */
	public ImportedCapInfo getImportedDataInfo(String orgCode,Long uploadId) {
		
		Query q = entityManager.createNativeQuery(env.getProperty("cap.importedCapInfo"));
		q.setParameter("orgCode", orgCode);
		q.setParameter("uploadId", uploadId);
		Object[] object=(Object[]) q.getSingleResult();
		ImportedCapInfo importedCapInfo = new ImportedCapInfo();
		if(object!=null){
		importedCapInfo.setUploadId(object[1]!=null?((BigDecimal) object[1]).longValue():null);
		importedCapInfo.setDistrictName(object[2]!=null?(String) object[2]:null);
		importedCapInfo.setSchoolName((String) object[3]);
		importedCapInfo.setSchoolYear(object[4]!=null?(String.valueOf((BigDecimal) object[4])):null);
		importedCapInfo.setProgramName((String) object[5]);
		importedCapInfo.setCandidateMepid(object[6]!=null?((BigDecimal) object[6]).longValue():null);
		importedCapInfo.setCandidateName((String) object[7] +" "+ (String) object[8]);
		importedCapInfo.setCandidateEmail((String) object[9]);
		importedCapInfo.setSupervisorName((String) object[10]);
		importedCapInfo.setSupervisorEmail((String) object[11]);
		importedCapInfo.setPractitionerName((String) object[12]);
		importedCapInfo.setPractitionerEmail((String) object[13]);
		importedCapInfo.setImportDate((Date) object[14]);
		importedCapInfo.setStatus((String) object[15]);
		String readyToTeach = (String)object[16];
		if(readyToTeach == null || (readyToTeach != null && readyToTeach.isEmpty())) {
			readyToTeach = "NA";
		} else if(readyToTeach.equalsIgnoreCase("Y")) {
			readyToTeach = "Yes";
		} else {
			readyToTeach = "No";
		}
		importedCapInfo.setReadyToTeach(readyToTeach);
		
		
		if(importedCapInfo.getStatus()!=null && importedCapInfo.equals("1"))
			importedCapInfo.setStatusDesc("Complete");
		else if(importedCapInfo.getStatus()!=null && importedCapInfo.equals("2"))
			importedCapInfo.setStatusDesc("Incomplete");
		else if(importedCapInfo.getStatus()!=null && importedCapInfo.equals("3"))
			importedCapInfo.setStatusDesc("Delete");
		
		List<ElementInfo> summativeElementInfos = new ArrayList<ElementInfo>();

		List<ElementInfo> formativeElementInfos = new ArrayList<ElementInfo>();

		List<ElementType> elements = elementTypeService.findAll();
		
		UploadedData uploadedData = findByPrimaryKey(uploadId);
		
		for (ElementType elementType : elements) {
			ElementInfo elementInfo = new ElementInfo();
			elementInfo.setElementCode(elementType.getElementCode());
			elementInfo.setElementLabel(elementType.getLabel());
			elementInfo.setLongDesc(elementType.getLongDesc());
			elementInfo.setAltDesc(elementType.getAltDesc());
			List<DimensionInfo> summativeDimensionInfos = new ArrayList<DimensionInfo>();
			elementInfo.setDimensions(summativeDimensionInfos);
			summativeElementInfos.add(elementInfo);
			elementInfo = new ElementInfo();
			elementInfo.setElementCode(elementType.getElementCode());
			elementInfo.setElementLabel(elementType.getLabel());
			elementInfo.setLongDesc(elementType.getLongDesc());
			elementInfo.setAltDesc(elementType.getAltDesc());
			List<DimensionInfo> formativeDimensionInfos = new ArrayList<DimensionInfo>();
			elementInfo.setDimensions(formativeDimensionInfos);
			formativeElementInfos.add(elementInfo);

			List<UploadedRating> uploadedFormativeRatings = new ArrayList<UploadedRating>();
			List<UploadedRating> uploadedSummativeeRatings = new ArrayList<UploadedRating>();

			uploadedData.getRatings().stream().forEach(uploadedRating -> {
				if (uploadedRating.getAsmtTypeCode().equals("2"))
					uploadedFormativeRatings.add(uploadedRating);
			});

			uploadedData.getRatings().stream().forEach(uploadedRating -> {
				if (uploadedRating.getAsmtTypeCode().equals("3"))
					uploadedSummativeeRatings.add(uploadedRating);
			});

			for (DimensionType dimensionType : dimensionService.findAll()) {
				DimensionInfo dimensionInfo = new DimensionInfo();
				dimensionInfo.setDimensionCode(dimensionType.getDimensionCode());
				dimensionInfo.setDimensionDesc(dimensionType.getDesc());
				dimensionInfo.setDimensionLongDesc(dimensionType.getLongDesc());

				UploadedRating uploadedRating = uploadedSummativeeRatings.stream()
						.filter(temp -> temp.getDimensionCode().equals(dimensionType.getDimensionCode()))
						.filter(temp -> elementRatingService.findByPrimaryKey(temp.getLinkId()).getElementCode()
								.equals(elementType.getElementCode()))
						.findFirst().orElse(null);
				ElementRating rating=null;
				if(uploadedRating!=null){
				rating = elementRatingService.findByPrimaryKey(uploadedRating.getLinkId());
				dimensionInfo.setRatingCode(rating.getRatingCode());
				dimensionInfo.setRatingDesc(ratingTypeService.findByPrimaryKey(rating.getRatingCode()).getDesc());

				}else{
					dimensionInfo.setRatingCode("");
					dimensionInfo.setRatingDesc("");
				}
				
				summativeDimensionInfos.add(dimensionInfo);

				dimensionInfo = new DimensionInfo();
				dimensionInfo.setDimensionCode(dimensionType.getDimensionCode());
				dimensionInfo.setDimensionDesc(dimensionType.getDesc());
				dimensionInfo.setDimensionLongDesc(dimensionType.getLongDesc());
				
				uploadedRating = uploadedFormativeRatings.stream()
						.filter(temp -> temp.getDimensionCode().equals(dimensionType.getDimensionCode()))
						.filter(temp -> elementRatingService.findByPrimaryKey(temp.getLinkId()).getElementCode()
								.equals(elementType.getElementCode()))
						.findFirst().orElse(null);
			if(uploadedRating!=null){
				rating = elementRatingService.findByPrimaryKey(uploadedRating.getLinkId());

				dimensionInfo.setRatingCode(rating.getRatingCode());
				dimensionInfo.setRatingDesc(ratingTypeService.findByPrimaryKey(rating.getRatingCode()).getDesc());				
				}else{
					dimensionInfo.setRatingCode("");
					dimensionInfo.setRatingDesc("");
				}
				
				formativeDimensionInfos.add(dimensionInfo);

			}

			importedCapInfo.setSummativeElements(summativeElementInfos);
			importedCapInfo.setFormativeElements(formativeElementInfos);
		}
		
		}
		
		return importedCapInfo;
	}
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the uploaded data
	 */
	public UploadedData findByPrimaryKey(Long primaryKey) {
		return entityManager.find(UploadedData.class, primaryKey);
	}
	
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the uploaded data
	 */
	@Transactional(value=TxType.REQUIRED)
	public UploadedData create(UploadedData obj) {
		entityManager.persist(obj);
		return obj;
		
	}
		
	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the uploaded data
	 */
	@Transactional(value=TxType.REQUIRED)
	public UploadedData update(UploadedData obj) {
		entityManager
		.merge(obj);		
		return obj;
	}
	
	/**
	 * Delete.
	 *
	 * @param primaryKey the primary key
	 */
	@Transactional(value=TxType.REQUIRED)
	public void delete(Long primaryKey) {
		
		UploadedData uploadedData = 
				entityManager
				.find(UploadedData.class, primaryKey);
		entityManager
		.remove(uploadedData);
		
	}



}
