
package edu.mass.doe.cap.controllers;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.cap.dataservice.batch.DataReader;
import edu.mass.doe.cap.dataservice.entity.BatchError;
import edu.mass.doe.cap.dataservice.pojo.BatchInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapData;
import edu.mass.doe.cap.dataservice.pojo.EvidenceFileInfo;
import edu.mass.doe.cap.file.io.FileSystemStorageService;
import edu.mass.doe.cap.file.io.StorageService;
import edu.mass.doe.cap.model.batch.BatchManager;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CAPValidationUtil;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class BulkUploadController.
 */
@Controller
@RequestMapping("batchload")
@SessionAttributes("batchInfo")
public class BulkUploadController {

	@Autowired
	private DataReader dataReader;

	@Autowired
	private BatchManager batchManager;

	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * Load meeting.
	 *
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping
	public String loadMeeting(Model model) throws Exception {

		BatchInfo batchInfo = new BatchInfo();
		model.addAttribute("batchInfo", batchInfo);
		return ".bulkUploadForm";
	}

	/**
	 * Download template.
	 *
	 * @param model the model
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping("template")
	@ResponseBody
	public ResponseEntity<Resource> downloadTemplate(Model model) throws Exception {
		Resource resource = resourceLoader.getResource("classpath:BatchuploadTemplate.csv");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "BatchuploadTemplate.csv" + "\"")
				.header(HttpHeaders.CONTENT_TYPE, "text/csv").body(resource);
	}
	
	@ModelAttribute
	public void init(Model model) throws Exception {
		model.addAttribute("activateReportsMenu", true);
		
	}

	/**
	 * Handle download error file.
	 *
	 * @param batchId the batch id
	 * @param model the model
	 * @return the http entity
	 * @throws UnexpectedInputException the unexpected input exception
	 * @throws ParseException the parse exception
	 * @throws Exception the exception
	 */
	@RequestMapping("errorLog")
	@ResponseBody
	public HttpEntity<byte[]> handleDownloadErrorFile(@RequestParam("batchId") Long batchId, Model model) throws UnexpectedInputException, ParseException, Exception {
		
		List<CapData> capData = new ArrayList<CapData>();
		ByteArrayOutputStream io = new ByteArrayOutputStream();
		
		Workbook workbook =  buildExcelDocument(batchManager.downloadError(batchId));
		workbook.write(io);

		if (null != workbook && null != io) {
			io.close();
		}
		
		

		byte[] documentContent = io.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.ms-excel"));
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"error_"+batchId+".xls\"");
		headers.setContentLength(documentContent.length);
		return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);
	}

	/**
	 * Handle file upload.
	 *
	 * @param batchInfo the batch info
	 * @param model the model
	 * @param result the result
	 * @return the string
	 * @throws UnexpectedInputException the unexpected input exception
	 * @throws ParseException the parse exception
	 * @throws Exception the exception
	 */
	@PostMapping
	public String handleFileUpload(@ModelAttribute("batchInfo") BatchInfo batchInfo, Model model, BindingResult result)
			throws UnexpectedInputException, ParseException, Exception {
		List<CapData> records = new ArrayList<CapData>();
		if (CAPValidationUtil.validateCAPFile(batchInfo.getFile(), "file", result, new Object[] {})) {
			try {
				records = dataReader.read(batchInfo.getFile());
			} catch (ParseException p) {
				result.rejectValue("file", "cap.bulk.upload.invalid.template", new Object[] {}, null);
				return ".bulkUploadForm";
			}
			
			
			if(records.size()<1){
				result.rejectValue("file", "cap.file.empty", new Object[] {}, null);
				return ".bulkUploadForm";
			}
			
			String fileName = batchInfo.getFile().getOriginalFilename();
			int idx = fileName.lastIndexOf("\\") > -1 ? fileName.lastIndexOf("\\") + 1 : 0;

			batchManager.uploadStaging(fileName.substring(idx, fileName.length()), records,batchInfo);
		}
		return ".bulkUploadForm";
	}

	/**
	 * Builds the excel document.
	 *
	 * @param records the records
	 * @return the workbook
	 * @throws Exception the exception
	 */
	public Workbook buildExcelDocument(List<CapData> records) throws Exception {

		
		Workbook workbook = loadErrorGlossarySheet();
		workbook.setActiveSheet(0);
		workbook.setSelectedTab(0);
		
		//Add content to the  default error excel file
		HSSFSheet sheet = (HSSFSheet)workbook.getSheet("Error Log");
		sheet.setDefaultColumnWidth(30);	
		
		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Sans-serif");
		//style.setFillForegroundColor(HSSFColor.BLUE.index);
		//style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);

		// create header row
		HSSFRow header = sheet.createRow(0);
		
		int idx =0;
		
		header.createCell(idx).setCellValue("Data Source	Error");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("Organization");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("Candidate MEPID");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("Candidate First Name");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("Candidate Last Name");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("Candidate E-Mail Address");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("SP Name");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("SP MEPID");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("SP E-Mail Address");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("Practicum District");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("Practicum School");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("PS Name");
		header.getCell(idx++).setCellStyle(style);


		header.createCell(idx).setCellValue("PS E-Mail Address");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("School Year");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("Program");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F1A4Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F1A4S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F1A4C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F1B2Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F1B2S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F1B2C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F2A3Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F2A3S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F2A3C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F2B1Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F2B1S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F2B1C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F2D2Q");
		header.getCell(idx++).setCellStyle(style);
		
		header.createCell(idx).setCellValue("F2D2S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F2D2C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F4A1Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F4A1S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("F4A1C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S1A4Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S1A4S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S1A4C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S1B2Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S1B2S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S1B2C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S2A3Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S2A3S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S2B1Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S2B1S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S2B1C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S2D2Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S2B1C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S2D2S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S2D2C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S4A1Q");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S4A1S");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("S4A1C");
		header.getCell(idx++).setCellStyle(style);

		header.createCell(idx).setCellValue("Ready To Teach");
		header.getCell(idx++).setCellStyle(style);


		style = workbook.createCellStyle();
		font = workbook.createFont();
		font.setFontName("Sans-serif");		
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		style.setFont(font);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		int rowCount = 1;
	
		
		for (CapData record : records) {
			
			for (String error : record.getErrors()) {
				idx=0;
				HSSFRow aRow = sheet.createRow(rowCount++);
				aRow.createCell(idx).setCellValue(error);
				sheet.setColumnWidth(idx, 65280);
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getOrganizationName());
				aRow.getCell(idx++).setCellStyle(style);			
				aRow.createCell(idx).setCellValue(record.getCandidateMEPID());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getCandidateFirstname());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getCandidateLastname());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getCandidateEmailid());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getSpName());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getSpMEPID());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getSpEmailid());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getPracticumDistrict());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getPracticumSchool());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getPsName());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getPsEmailid());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getSchoolYear());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getProgram());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF1a4q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF1a4s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF1a4c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF1b2q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF1b2s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF1b2c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF2a3q());
				aRow.getCell(idx++).setCellStyle(style);				
				aRow.createCell(idx).setCellValue(record.getF2a3s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF2a3c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF2b1q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF2b1s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF2b1c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF2d2q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF2d2s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF2d2c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF4a1q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF4a1s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getF4a1c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS1a4q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS1a4s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS1a4c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS1b2q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS1b2s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS1b2c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS2a3q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS2a3s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS2a3c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS2b1q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS2b1s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS2b1c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS2d2q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS2d2s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS2d2c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS4a1q());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS4a1s());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getS4a1c());
				aRow.getCell(idx++).setCellStyle(style);
				aRow.createCell(idx).setCellValue(record.getReadyToteach());
				aRow.getCell(idx++).setCellStyle(style);
			

			}
			
			if(record.getErrors().size()>1){
			for(int i=1;i<=51;i++){
				sheet.addMergedRegion(new CellRangeAddress(rowCount-(record.getErrors().size()),rowCount-1,i,i));
			}
			}

		}

		return workbook;
	}
	
	
	@Cacheable("glossary")
	public Workbook loadErrorGlossarySheet() throws FileNotFoundException, IOException{

		Workbook  workbook = new HSSFWorkbook( resourceLoader.getResource("classpath:error.xls").getInputStream() );
		return workbook;
		
		
	}
	
	
}
