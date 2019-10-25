package edu.mass.doe.cap.model.file;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.EOEAdminUserInfo;
import edu.mass.doe.cap.dataservice.assessment.ElementTypeService;
import edu.mass.doe.cap.dataservice.candidate.CycleService;
import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.EvidenceFile;
import edu.mass.doe.cap.dataservice.entity.EvidenceType;
import edu.mass.doe.cap.dataservice.entity.FileElement;
import edu.mass.doe.cap.dataservice.file.EvidenceFileService;
import edu.mass.doe.cap.dataservice.file.EvidenceTypeService;
import edu.mass.doe.cap.dataservice.file.FileElementService;
import edu.mass.doe.cap.dataservice.pojo.Attribute;
import edu.mass.doe.cap.dataservice.pojo.EvidenceFileInfo;
import edu.mass.doe.cap.file.io.FileSystemStorageService;
import edu.mass.doe.cap.file.io.StorageService;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class EvidenceFileManager.
 */
@Component
public class EvidenceFileManager {

	private static Logger logger = LoggerFactory.getLogger(EvidenceFileManager.class);

	@Autowired
	private EvidenceFileService evidenceFileService;

	@Autowired
	private CycleService cycleService;

	@Autowired
	private ElementTypeService elementTypeService;

	@Autowired
	private FileElementService fileElementService;
	
	@Autowired
	private EvidenceTypeService evidenceTypeService;

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;
	
	

	@Autowired
	private FileSystemStorageService storageService;
	

	@Autowired
	private Environment env;

	/**
	 * Gets the evidence files.
	 *
	 * @param cycleId the cycle id
	 * @return the evidence files
	 * @throws Exception the exception
	 */
	public List<EvidenceFileInfo> getEvidenceFiles(Long cycleId) throws Exception {

		List<EvidenceFileInfo> filesInfo = new ArrayList<EvidenceFileInfo>();

		List<EvidenceFile> files = evidenceFileService.findFilesByCycle(cycleId);

		for (EvidenceFile evidenceFile : files) {
			EvidenceFileInfo evidenceFileInfo = new EvidenceFileInfo();
			evidenceFileInfo.setFileId(evidenceFile.getFileId());
			evidenceFileInfo.setExternalFileName(evidenceFile.getExternalFileName());
			evidenceFileInfo.setInternalFileName(evidenceFile.getInternalFileName());
			BigDecimal size=new BigDecimal(evidenceFile.getFileSize());
			BigDecimal onembInBytes=new BigDecimal("1048576");
			BigDecimal sizeInmb=size.divide(onembInBytes).setScale(2, RoundingMode.DOWN);
			evidenceFileInfo.setFileSizeInDisk(sizeInmb.doubleValue());
			evidenceFileInfo.setPersonId(evidenceFile.getPersonId());
			evidenceFileInfo.setCreatedDate(evidenceFile.getEffdate());
			evidenceFileInfo
					.setPersonName(DirAdminAccessUtil.getPerson(evidenceFile.getPersonId().intValue()).getName());
			filesInfo.add(evidenceFileInfo);
			
			boolean canDelete=false;
			String storedUserRoleCode =DirAdminAccessUtil.getUserInfo(DirAdminAccessUtil.getUserIDByDAPersonID(evidenceFile.getPersonId().intValue()), env.getProperty("app.name")).getUserRoles()[0];

			//only TC can delete his file
			if(storedUserRoleCode.equals("3")){
				canDelete=((EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getPersonId().equals(evidenceFile.getPersonId());
			}else if((CapUtil.isManager()||CapUtil.isSupervisor())&&(storedUserRoleCode.equals("723")||storedUserRoleCode.equals("722"))){
				canDelete=true;
			}else if(CapUtil.ispractitioner()&&storedUserRoleCode.equals("724")){
				canDelete=true;
			}
			
			
			if(cycleService.findByPrimaryKey(cycleId).getCstStatusCode()!=null)
				canDelete=false;
			
				evidenceFileInfo.setCanDelete(canDelete);
			List<Attribute> attributes = new ArrayList<Attribute>();
			List<Attribute> evidenceTypes = new ArrayList<Attribute>();
			
			List<String> elementCodes = new ArrayList<String>();
			List<String> typeCodes = new ArrayList<String>();
			
			for (FileElement fileElement : evidenceFile.getElements()) {
				if(!fileElement.getEvidenceCode().equals("N/A")&&!typeCodes.contains(fileElement.getEvidenceCode()))
					typeCodes.add(fileElement.getEvidenceCode());
				
				if(fileElement.getElementCode()!=null&&!elementCodes.contains(fileElement.getElementCode()))
					elementCodes.add(fileElement.getElementCode());
				
			}

			for (String elementCode : elementCodes) {
				Attribute attribute = new Attribute();
				attribute.setLabel(elementTypeService.findByPrimaryKey(elementCode).getLabel());
				attribute.setCode(elementCode);
				attributes.add(attribute);
			}
			
			for (String typeCode : typeCodes) {
				Attribute attribute = new Attribute();
				attribute.setLabel(evidenceTypeService.findByPrimaryKey(typeCode).getTypeDesc());
				attribute.setCode(typeCode);
				evidenceTypes.add(attribute);
			}
			
			evidenceFileInfo.setAttributes(attributes);
			evidenceFileInfo.setEvidenceTypes(evidenceTypes);

		}

		return filesInfo;

	}

	/**
	 * Save.
	 *
	 * @param evidenceFileInfo the evidence file info
	 * @param file the file
	 */
	@Transactional(value = TxType.REQUIRES_NEW)
	public void save(EvidenceFileInfo evidenceFileInfo, MultipartFile file) {

		EvidenceFile evidenceFile = new EvidenceFile();
		evidenceFile.setCapCycle(cycleService.findByPrimaryKey(evidenceFileInfo.getCycleId()));
		evidenceFile.setEffdate(new Date());
		evidenceFile.setExternalFileName(file.getContentType());
		String fileName=file.getOriginalFilename();
		int idx=fileName.lastIndexOf("\\")>-1?fileName.lastIndexOf("\\")+1:0;
		evidenceFile.setInternalFileName(fileName.substring(idx, fileName.length()));
		evidenceFile.setPersonId(
				((EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPersonId());
		evidenceFile.setFileSize(file.getSize());
		evidenceFile = evidenceFileService.create(evidenceFile);
		final Long id = evidenceFile.getFileId();
		
		
		List<FileElement> fe= new ArrayList<FileElement>();
		
		for(Attribute elementInfo : evidenceFileInfo.getAttributes()){
			boolean isFirst=true;
			if (!elementInfo.isSelected())
				continue;
			FileElement fileElement = new FileElement();
			fileElement.setEvidenceFile(evidenceFileService.findByPrimaryKey(id));
			fileElement.setElementCode(elementInfo.getCode());
			fileElement.setEvidenceCode("N/A");
			fileElement.setEffdate(new Date());
			
			for(Attribute typeInfo : evidenceFileInfo.getEvidenceTypes()){
				if (!typeInfo.isSelected())
					continue;
				
				if(isFirst){
					isFirst=false;
					fileElement.setEvidenceCode(typeInfo.getCode());
					fe.add(fileElement);
					}else{
						fileElement = new FileElement();
						fileElement.setEvidenceFile(evidenceFileService.findByPrimaryKey(id));
						fileElement.setElementCode(elementInfo.getCode());
						fileElement.setEvidenceCode(typeInfo.getCode());
						fileElement.setEffdate(new Date());
						fe.add(fileElement);
					}
			}
			
			if(fe.size()<1)
			fe.add(fileElement);
		}
		
		if(fe.size()<1){
		
		evidenceFileInfo.getEvidenceTypes().stream().forEach(temp -> {

			if (!temp.isSelected())
				return;
			
				FileElement fileElement = new FileElement();
				fileElement.setEvidenceFile(evidenceFileService.findByPrimaryKey(id));
				fileElement.setEvidenceCode(temp.getCode());
				fileElement.setEffdate(new Date());
				fe.add(fileElement);
			
		});
		}
		
		fe.stream().forEach(temp -> {
			fileElementService.create(temp);
		});
		

		storageService.store(file, evidenceFileInfo.getCycleId(), id);

		entityManager.flush();
		entityManager.clear();
	}

	/**
	 * Delete.
	 *
	 * @param fileId the file id
	 * @param cycleId the cycle id
	 */
	@Transactional(value = TxType.REQUIRES_NEW)
	public void delete(Long fileId, Long cycleId) {

		EvidenceFile evidenceFile = evidenceFileService.findByPrimaryKey(fileId);
		evidenceFile.getElements().stream().forEach(attribute -> {
			fileElementService.delete(attribute.getElementId());
		});
		evidenceFileService.delete(fileId);
		storageService.deleteFile(fileId.toString(), cycleId);
		entityManager.flush();
		entityManager.clear();
	}

	/**
	 * Gets the repository file.
	 *
	 * @return the repository file
	 */
	public void getRepositoryFile() {

	}

	/**
	 * Ge attribute info.
	 *
	 * @return the list
	 */
	public List<Attribute> geAttributeInfo() {

		List<ElementType> elementsTypes = elementTypeService.findAll();

		List<Attribute> attributeInfos = new ArrayList<Attribute>();

		elementsTypes.stream().forEach(elementType -> {
			Attribute attribute = new Attribute();
			attribute.setCode(elementType.getElementCode());
			attribute.setLabel(elementType.getAltDesc());
			attributeInfos.add(attribute);
		});

		return attributeInfos;
	}
	
	/**
	 * Gets the evidence types.
	 *
	 * @return the evidence types
	 */
	public List<Attribute> getEvidenceTypes() {

		List<EvidenceType> elementsTypes = evidenceTypeService.findAllEvidencesType();

		List<Attribute> attributeInfos = new ArrayList<Attribute>();

		elementsTypes.stream().forEach(elementType -> {
			Attribute attribute = new Attribute();
			attribute.setCode(elementType.getTypeCode());
			attribute.setLabel(elementType.getTypeDesc());
			attributeInfos.add(attribute);
		});

		return attributeInfos;
	}
	
	public BigDecimal folderSizeInMb(Long cycleId,long maxSize){
		return evidenceFileService.folderSizeInMb(cycleId, maxSize);
	}


}
