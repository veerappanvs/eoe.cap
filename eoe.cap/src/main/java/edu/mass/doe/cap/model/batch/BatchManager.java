package edu.mass.doe.cap.model.batch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.EOEAuthorization.exception.NoPersonFoundException;
import edu.mass.doe.cap.dataservice.assessment.AssessmentService;
import edu.mass.doe.cap.dataservice.assessment.AssessmentTypeService;
import edu.mass.doe.cap.dataservice.assessment.DimensionService;
import edu.mass.doe.cap.dataservice.assessment.ElementRatingService;
import edu.mass.doe.cap.dataservice.assessment.ElementTypeService;
import edu.mass.doe.cap.dataservice.assessment.RatingTypeService;
import edu.mass.doe.cap.dataservice.batch.BatchService;
import edu.mass.doe.cap.dataservice.batch.BatchStagingService;
import edu.mass.doe.cap.dataservice.batch.UploadedDataService;
import edu.mass.doe.cap.dataservice.batch.UploadedRatingService;
import edu.mass.doe.cap.dataservice.entity.Batch;
import edu.mass.doe.cap.dataservice.entity.BatchStaging;
import edu.mass.doe.cap.dataservice.entity.DimensionType;
import edu.mass.doe.cap.dataservice.entity.ElementRating;
import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.UploadedData;
import edu.mass.doe.cap.dataservice.entity.UploadedRating;
import edu.mass.doe.cap.dataservice.pojo.BatchInfo;
import edu.mass.doe.cap.dataservice.pojo.CandidateInfo;
import edu.mass.doe.cap.dataservice.pojo.CapData;
import edu.mass.doe.cap.dataservice.pojo.DimensionInfo;
import edu.mass.doe.cap.dataservice.pojo.ElementInfo;
import edu.mass.doe.cap.dataservice.pojo.ImportedCapInfo;
import edu.mass.doe.cap.dataservice.pojo.RatingInfo;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;
import edu.mass.doe.cap.model.assessment.AssessmentManager;
import edu.mass.doe.cap.model.candidate.CandidateManager;
import edu.mass.doe.cap.security.EOEUser;

/**
 * The Class BatchManager.
 */
@Component
public class BatchManager {
	private static Logger logger = LoggerFactory.getLogger(BatchManager.class);


	@Autowired
	private BatchService batchservice;

	@Autowired
	private BatchStagingService batchStagingService;

	@Autowired
	private ElementTypeService elementTypeService;

	@Autowired
	private ElementRatingService elementRatingService;

	@Autowired
	private DimensionService dimensionService;

	@Autowired
	private CandidateManager candidateManager;

	@Autowired
	private UploadedDataService uploadedDataService;

	@Autowired
	private UploadedRatingService uploadedRatingService;

	@Autowired
	private RatingTypeService ratingTypeService;

	/**
	 * Upload staging.
	 *
	 * @param fileName the file name
	 * @param records the records
	 * @param batchInfo the batch info
	 */
	public void uploadStaging(String fileName, List<CapData> records, BatchInfo batchInfo) {

		Long personId = ((EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPersonId();

		Batch batch = new Batch();
		batch.setCode("101");
		batch.setPersonId(personId);
		batch.setFileName(fileName);
		batch.setInternalFileName(fileName);
		batch.setEffDate(new Date());
		List<BatchStaging> batchRecords = new ArrayList<BatchStaging>();
		batch.setRecords(batchRecords);

		records.stream().forEach(record -> {
			BatchStaging batchRecord = new BatchStaging();
			batchRecord.setBatch(batch);
			batchRecord.setEffDate(new Date());
			boolean isrecIncomplete=false;
			batchRecord.setOrganizationName(record.getOrganizationName());
			isrecIncomplete=record.getOrganizationName()==null||record.getOrganizationName().isEmpty();
			batchRecord.setCandidateMEPId(record.getCandidateMEPID());
			isrecIncomplete=(record.getOrganizationName()==null||record.getOrganizationName().isEmpty())||isrecIncomplete;
			batchRecord.setCandidateFirstname(record.getCandidateFirstname());
			isrecIncomplete=(record.getCandidateFirstname()==null||record.getCandidateFirstname().isEmpty())||isrecIncomplete;
			batchRecord.setCandidateLastname(record.getCandidateLastname());
			isrecIncomplete=(record.getCandidateLastname()==null||record.getCandidateLastname().isEmpty())||isrecIncomplete;
			batchRecord.setCandidateEmail(record.getCandidateEmailid());
			isrecIncomplete=(record.getCandidateEmailid()==null||record.getCandidateEmailid().isEmpty())||isrecIncomplete;
			batchRecord.setPsName(record.getPsName());
			isrecIncomplete=(record.getPsName()==null||record.getPsName().isEmpty())||isrecIncomplete;
			batchRecord.setPsEmail(record.getPsEmailid());
			isrecIncomplete=(record.getPsEmailid()==null||record.getPsEmailid().isEmpty())||isrecIncomplete;
			batchRecord.setSpName(record.getSpName());
			isrecIncomplete=(record.getSpName()==null||record.getSpName().isEmpty())||isrecIncomplete;
			batchRecord.setSpMEPID(record.getSpMEPID());
			//isrecIncomplete=(record.getSpMEPID()==null||record.getSpMEPID().isEmpty())||isrecIncomplete;
			//Commented as a part of Out Of State
			batchRecord.setSpEmail(record.getSpEmailid());
			isrecIncomplete=(record.getSpEmailid()==null||record.getSpEmailid().isEmpty())||isrecIncomplete;
			batchRecord.setDistrictName(record.getPracticumDistrict());
			isrecIncomplete=(record.getPracticumDistrict()==null||record.getPracticumDistrict().isEmpty())||isrecIncomplete;
			batchRecord.setSchoolName(record.getPracticumSchool());
			isrecIncomplete=(record.getPracticumSchool()==null||record.getPracticumSchool().isEmpty())||isrecIncomplete;
			batchRecord.setSchoolYear(record.getSchoolYear());
			isrecIncomplete=(record.getSchoolYear()==null||record.getSchoolYear().isEmpty())||isrecIncomplete;
			batchRecord.setProgramName(record.getProgram());
			isrecIncomplete=(record.getProgram()==null||record.getProgram().isEmpty())||isrecIncomplete;
			batchRecord.setF1a4q(record.getF1a4q());
			isrecIncomplete=(record.getF1a4q()==null||record.getF1a4q().isEmpty()||record.getF1a4q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF1a4s(record.getF1a4s());
			isrecIncomplete=(record.getF1a4s()==null||record.getF1a4s().isEmpty()||record.getF1a4s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF1a4c(record.getF1a4c());
			isrecIncomplete=(record.getF1a4c()==null||record.getF1a4c().isEmpty()||record.getF1a4c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF1b2q(record.getF1b2q());
			isrecIncomplete=(record.getF1b2q()==null||record.getF1b2q().isEmpty()||record.getF1b2q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF1b2s(record.getF1b2s());
			isrecIncomplete=(record.getF1b2s()==null||record.getF1b2s().isEmpty()||record.getF1b2s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF1b2c(record.getF1b2c());
			isrecIncomplete=(record.getF1b2c()==null||record.getF1b2c().isEmpty()||record.getF1b2c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF2a3q(record.getF2a3q());
			isrecIncomplete=(record.getF2a3q()==null||record.getF2a3q().isEmpty()||record.getF2a3q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF2a3s(record.getF2a3s());
			isrecIncomplete=(record.getF2a3s()==null||record.getF2a3s().isEmpty()||record.getF2a3s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF2a3c(record.getF2a3c());
			isrecIncomplete=(record.getF2a3c()==null||record.getF2a3c().isEmpty()||record.getF2a3c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF2b1q(record.getF2b1q());
			isrecIncomplete=(record.getF2b1q()==null||record.getF2b1q().isEmpty()||record.getF2b1q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF2b1s(record.getF2b1s());
			isrecIncomplete=(record.getF2b1s()==null||record.getF2b1s().isEmpty()||record.getF2b1s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF2b1c(record.getF2b1c());
			isrecIncomplete=(record.getF2b1c()==null||record.getF2b1c().isEmpty()||record.getF2b1c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF2d2q(record.getF2d2q());
			isrecIncomplete=(record.getF2d2q()==null||record.getF2d2q().isEmpty()||record.getF2d2q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF2d2s(record.getF2d2s());
			isrecIncomplete=(record.getF2d2s()==null||record.getF2d2s().isEmpty()||record.getF2d2s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF2d2c(record.getF2d2c());
			isrecIncomplete=(record.getF2d2c()==null||record.getF2d2c().isEmpty()||record.getF2d2c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF4a1q(record.getF4a1q());
			isrecIncomplete=(record.getF4a1q()==null||record.getF4a1q().isEmpty()||record.getF4a1q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF4a1s(record.getF4a1s());
			isrecIncomplete=(record.getF4a1s()==null||record.getF4a1s().isEmpty()||record.getF4a1s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setF4a1c(record.getF4a1c());
			isrecIncomplete=(record.getF4a1c()==null||record.getF4a1c().isEmpty()||record.getF4a1c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS1a4q(record.getS1a4q());
			isrecIncomplete=(record.getS1a4q()==null||record.getS1a4q().isEmpty()||record.getS1a4q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS1a4s(record.getS1a4s());
			isrecIncomplete=(record.getS1a4s()==null||record.getS1a4s().isEmpty()||record.getS1a4s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS1a4c(record.getS1a4c());
			isrecIncomplete=(record.getS1a4c()==null||record.getS1a4c().isEmpty()||record.getS1a4c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS1b2q(record.getS1b2q());
			isrecIncomplete=(record.getS1b2q()==null||record.getS1b2q().isEmpty()||record.getS1b2q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS1b2s(record.getS1b2s());
			isrecIncomplete=(record.getS1b2s()==null||record.getS1b2s().isEmpty()||record.getS1b2s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS1b2c(record.getS1b2c());
			isrecIncomplete=(record.getS1b2c()==null||record.getS1b2c().isEmpty()||record.getS1b2c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS2a3q(record.getS2a3q());
			isrecIncomplete=(record.getS2a3q()==null||record.getS2a3q().isEmpty()||record.getS2a3q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS2a3s(record.getS2a3s());
			isrecIncomplete=(record.getS2a3s()==null||record.getS2a3s().isEmpty()||record.getS2a3s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS2a3c(record.getS2a3c());
			isrecIncomplete=(record.getS2a3c()==null||record.getS2a3c().isEmpty()||record.getS2a3c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS2b1q(record.getS2b1q());
			isrecIncomplete=(record.getS2b1q()==null||record.getS2b1q().isEmpty()||record.getS2b1q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS2b1s(record.getS2b1s());
			isrecIncomplete=(record.getS2b1s()==null||record.getS2b1s().isEmpty()||record.getS2b1s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS2b1c(record.getS2b1c());
			isrecIncomplete=(record.getS2b1c()==null||record.getS2b1c().isEmpty()||record.getS2b1c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS2d2q(record.getS2d2q());
			isrecIncomplete=(record.getS2d2q()==null||record.getS2d2q().isEmpty()||record.getS2d2q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS2d2s(record.getS2d2s());
			isrecIncomplete=(record.getS2d2s()==null||record.getS2d2s().isEmpty()||record.getS2d2s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS2d2c(record.getS2d2c());
			isrecIncomplete=(record.getS2d2c()==null||record.getS2d2c().isEmpty()||record.getS2d2c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS4a1q(record.getS4a1q());
			isrecIncomplete=(record.getS4a1q()==null||record.getS4a1q().isEmpty()||record.getS4a1q().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS4a1s(record.getS4a1s());
			isrecIncomplete=(record.getS4a1s()==null||record.getS4a1s().isEmpty()||record.getS4a1s().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setS4a1c(record.getS4a1c());
			isrecIncomplete=(record.getS4a1c()==null||record.getS4a1c().isEmpty()||record.getS4a1c().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setReadyToTeach(record.getReadyToteach());
			isrecIncomplete=(record.getReadyToteach()==null||record.getReadyToteach().isEmpty()||record.getReadyToteach().equalsIgnoreCase("I"))||isrecIncomplete;
			batchRecord.setStatusCode(isrecIncomplete?"2":"1");
			batchRecords.add(batchRecord);

		});

		Batch btch = batchservice.create(batch);
		int errCount = batchservice.load(btch).intValue();
		batchInfo.setBatchId(btch.getUploadId());
		batchInfo.setErrcount(errCount);
	}

	/**
	 * Download error.
	 *
	 * @param batchId the batch id
	 * @return the list
	 */
	public List<CapData> downloadError(Long batchId) {
		Batch batch = batchservice.findByPrimaryKey(batchId);

		List<BatchStaging> records = batch.getRecords();
		List<CapData> capRecords = new ArrayList<CapData>();

		records.stream().forEach(record -> {

			CapData batchRecord = new CapData();
			batchRecord.setOrganizationName(record.getOrganizationName());
			batchRecord.setCandidateMEPID(record.getCandidateMEPId());
			batchRecord.setCandidateFirstname(record.getCandidateFirstname());
			batchRecord.setCandidateLastname(record.getCandidateLastname());
			batchRecord.setCandidateEmailid(record.getCandidateEmail());
			batchRecord.setPsName(record.getPsName());
			batchRecord.setPsEmailid(record.getPsEmail());
			batchRecord.setSpName(record.getSpName());
			batchRecord.setSpMEPID(record.getSpMEPID());
			batchRecord.setSpEmailid(record.getSpEmail());
			batchRecord.setPracticumDistrict(record.getDistrictName());
			batchRecord.setPracticumSchool(record.getSchoolName());
			batchRecord.setSchoolYear(record.getSchoolYear());
			batchRecord.setProgram(record.getProgramName());
			batchRecord.setF1a4q(record.getF1a4q());
			batchRecord.setF1a4s(record.getF1a4s());
			batchRecord.setF1a4c(record.getF1a4c());
			batchRecord.setF1b2q(record.getF1b2q());
			batchRecord.setF1b2s(record.getF1b2s());
			batchRecord.setF1b2c(record.getF1b2c());
			batchRecord.setF2a3q(record.getF2a3q());
			batchRecord.setF2a3s(record.getF2a3s());
			batchRecord.setF2a3c(record.getF2a3c());
			batchRecord.setF2b1q(record.getF2b1q());
			batchRecord.setF2b1s(record.getF2b1s());
			batchRecord.setF2b1c(record.getF2b1c());
			batchRecord.setF2d2q(record.getF2d2q());
			batchRecord.setF2d2s(record.getF2d2s());
			batchRecord.setF2d2c(record.getF2d2c());
			batchRecord.setF4a1q(record.getF4a1q());
			batchRecord.setF4a1s(record.getF4a1s());
			batchRecord.setF4a1c(record.getF4a1c());
			batchRecord.setS1a4q(record.getS1a4q());
			batchRecord.setS1a4s(record.getS1a4s());
			batchRecord.setS1a4c(record.getS1a4c());
			batchRecord.setS1b2q(record.getS1b2q());
			batchRecord.setS1b2s(record.getS1b2s());
			batchRecord.setS1b2c(record.getS1b2c());
			batchRecord.setS2a3q(record.getS2a3q());
			batchRecord.setS2a3s(record.getS2a3s());
			batchRecord.setS2a3c(record.getS2a3c());
			batchRecord.setS2b1q(record.getS2b1q());
			batchRecord.setS2b1s(record.getS2b1s());
			batchRecord.setS2b1c(record.getS2b1c());
			batchRecord.setS2d2q(record.getS2d2q());
			batchRecord.setS2d2s(record.getS2d2s());
			batchRecord.setS2d2c(record.getS2d2c());
			batchRecord.setS4a1q(record.getS4a1q());
			batchRecord.setS4a1s(record.getS4a1s());
			batchRecord.setS4a1c(record.getS4a1c());
			batchRecord.setReadyToteach(record.getReadyToTeach());	

			List<String> errors = new ArrayList<String>();
			batchRecord.setErrors(errors);
			record.getErrors().forEach(error -> {
				errors.add(error.getDesc());
			});

			capRecords.add(batchRecord);

		});

		return capRecords;
	}

	/**
	 * Delete uploaded data.
	 *
	 * @param importId the import id
	 */
	public void deleteUploadedData(Long importId) {
		UploadedData uploadedData = uploadedDataService.findByPrimaryKey(importId);

		uploadedData.getRatings().stream().forEach(uploadedRating -> {
			uploadedRating.setExpDate(new Date());
			uploadedRatingService.update(uploadedRating);
		});

		uploadedData.setFUP_STATUS_CODE("3");
		uploadedData.setExpDate(new Date());

		uploadedDataService.update(uploadedData);
	}

	/**
	 * Gets the imported cap report.
	 *
	 * @param orgCode the org code
	 * @param year the year
	 * @return the imported cap report
	 * @throws RemoteException the remote exception
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 * @throws NoPersonFoundException the no person found exception
	 */
	public List<ImportedCapInfo> getImportedCapReport(String orgCode,String year,String status)
			throws RemoteException, NesterException, NoOrganizationFoundException, NoPersonFoundException {
		List<ImportedCapInfo> records = new ArrayList<ImportedCapInfo>();
		Calendar schoolEndDate = Calendar.getInstance();
		schoolEndDate.setTime(new Date());
		schoolEndDate.set(Calendar.MONTH, Calendar.JUNE);
		schoolEndDate.set(Calendar.DAY_OF_MONTH, 30);
		
		Calendar todaysDate = Calendar.getInstance();
		todaysDate.setTime(new Date());
		int yr= todaysDate.get(Calendar.YEAR);
		yr=todaysDate.compareTo(schoolEndDate)>0?yr+1:yr;
		String fromYear=year;
		String toYear=year;
		
		if(year.equals("0"))
			fromYear=String.valueOf(yr-2);
		
		if(year.equals("0"))
			toYear=String.valueOf(yr);
		
		records = uploadedDataService.getImportedCapInfoReport(orgCode, fromYear, toYear,status);

		return records;
	}

	/**
	 * Gets the uploaded data info.
	 *
	 * @param orgCode the org code
	 * @param uploadId the upload id
	 * @return the uploaded data info
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 * @throws RemoteException the remote exception
	 * @throws NoPersonFoundException the no person found exception
	 */
	public ImportedCapInfo getUploadedDataInfo(String orgCode,Long uploadId)
			throws NesterException, NoOrganizationFoundException, RemoteException, NoPersonFoundException {
		return uploadedDataService.getImportedDataInfo(orgCode, uploadId);
	}
	
}
