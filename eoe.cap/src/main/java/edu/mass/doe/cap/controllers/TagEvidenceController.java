package edu.mass.doe.cap.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.EvidenceFileInfo;
import edu.mass.doe.cap.file.io.FileSystemStorageService;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CAPValidationUtil;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class TagEvidenceController.
 */
@Controller
@RequestMapping("tagfile")
@SessionAttributes("evidenceFileInfo")
public class TagEvidenceController extends BaseCycleInformation {

	@Autowired
	private FileSystemStorageService storageService;
	
	@Autowired
	private Environment env;

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
		model.addAttribute("evidenceFileInfo", getFileInfo());
		
		return ".uploadForm";
	}

	/**
	 * Gets the file info.
	 *
	 * @return the file info
	 */
	private EvidenceFileInfo getFileInfo() {
		EvidenceFileInfo evidenceFileInfo = new EvidenceFileInfo();
		evidenceFileInfo.setAttributes(evidenceFileManager.geAttributeInfo());
		evidenceFileInfo.setEvidenceTypes(evidenceFileManager.getEvidenceTypes());
		return evidenceFileInfo;
	}

	/**
	 * Load file.
	 *
	 * @param cycleId the cycle id
	 * @param model the model
	 * @throws Exception the exception
	 */
	@ModelAttribute
	public void loadFile(@RequestParam("cycleId") Long cycleId,Model model) throws Exception {
		model.addAttribute("evidenceFiles", evidenceFileManager.getEvidenceFiles(cycleId));
		CapCycleInfo cycleInfo = cycleManager.getCycleInfo(cycleId);
		model.addAttribute("cycleInfo", cycleInfo);
		model.addAttribute("canUpload", cycleInfo.getCycleStatusCode()==null);
		BigDecimal maxFoldersize=new BigDecimal(env.getRequiredProperty("cap.max.folder.size"));
		BigDecimal foldersize=evidenceFileManager.folderSizeInMb(cycleId, new Long(env.getRequiredProperty("cap.max.folder.size")));
		model.addAttribute("maxFoldersize",maxFoldersize);
		model.addAttribute("folderSize",foldersize);
	}

	
	
	
	/**
	 * Handle file upload.
	 *
	 * @param evidenceFileInfo the evidence file info
	 * @param result the result
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MaxUploadSizeExceededException the max upload size exceeded exception
	 */
	@PostMapping
	public String handleFileUpload(HttpServletRequest request,@RequestParam("fileId") Long fileId,@ModelAttribute("evidenceFileInfo") EvidenceFileInfo evidenceFileInfo,
			Model model, BindingResult result)
			throws IOException, MaxUploadSizeExceededException {
		
		if(request.getParameter("delete")!=null){
			return handleFileDelete(fileId, evidenceFileInfo.getCycleId(), model, result);
		}
		
		if (validate(evidenceFileInfo, result)) {
			evidenceFileManager.save(evidenceFileInfo, evidenceFileInfo.getFile());
			return "redirect:tagfile?cycleId=" + evidenceFileInfo.getCycleId();
		}
		return ".uploadForm";

	}

	/**
	 * Validate.
	 *
	 * @param evidenceFileInfo the evidence file info
	 * @param bindingResult the binding result
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private boolean validate(EvidenceFileInfo evidenceFileInfo, BindingResult bindingResult)
			throws IOException {
		boolean result = true;

		result = CAPValidationUtil.validateFile(evidenceFileInfo.getFile(), evidenceFileInfo.getCycleId(), storageService,(Long.parseLong( env.getProperty("cap.max.folder.size"))*1048576), "file",
				bindingResult, new Object[] { env.getProperty("cap.max.folder.size") });

		return result;
	}
	
	/**
	 * Handle file down load.
	 *
	 * @param fileId the file id
	 * @param cycleId the cycle id
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping("download")
    @ResponseBody
	public ResponseEntity<Resource> handleFileDownLoad(@RequestParam("fileId")Long fileId,@RequestParam("cycleId")Long cycleId) throws Exception {
		
		List<EvidenceFileInfo> evidenceFiles=evidenceFileManager.getEvidenceFiles(cycleId);
		
		String fileName="";
		String contentType="";
		
		for(EvidenceFileInfo evidenceFileInfo:evidenceFiles){
			if(evidenceFileInfo.getFileId().equals(fileId)){
				fileName=evidenceFileInfo.getInternalFileName();
				contentType=evidenceFileInfo.getExternalFileName();
				break;
			}
		}
		if(fileName.length()<1)
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Resource file = storageService.loadAsResource(fileId.toString(), cycleId);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
		
	}
	
	
	/**
	 * Handle file delete.
	 *
	 * @param fileId the file id
	 * @param cycleId the cycle id
	 * @return the response entity
	 */
	public String handleFileDelete(@RequestParam("fileId") Long fileId, @RequestParam("cycleId") Long cycleId, Model model,BindingResult result) {
		

		try{
		List<EvidenceFileInfo> evidenceFiles=evidenceFileManager.getEvidenceFiles(cycleId);
		
		
		Long personId=null;
		
		for(EvidenceFileInfo evidenceFileInfo:evidenceFiles){
			if(evidenceFileInfo.getFileId().equals(fileId)){				
				personId=evidenceFileInfo.getPersonId();
				break;
			}
		}
		if(personId==null)
			throw new Exception("File not found");
		
		boolean canDelete=false;
		String storedUserRoleCode =DirAdminAccessUtil.getUserInfo(DirAdminAccessUtil.getUserIDByDAPersonID(personId.intValue()), env.getProperty("app.name")).getUserRoles()[0];

		if(storedUserRoleCode.equals("3")){
			canDelete=((EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
			.getPersonId().equals(personId);
		}else if(CapUtil.isManager()||CapUtil.isSupervisor()&&(storedUserRoleCode.equals("723")||storedUserRoleCode.equals("722"))){
			canDelete=true;
		}else if(CapUtil.ispractitioner()&&storedUserRoleCode.equals("724")){
			canDelete=true;
		}
		
		if(cycleManager.getCycleInfo(cycleId).getCycleStatusCode()!=null)
			canDelete= false;
		
		if(!canDelete)
			throw new Exception("Cannot delete");
		
		evidenceFileManager.delete(fileId, cycleId);
		}catch(Exception e){
			result.rejectValue("file", "Unable to delete file", new Object[] {}, null);
			return ".uploadForm";
		}
		
		
		return "redirect:tagfile?cycleId=" + cycleId;
		
	}
	

	
}
