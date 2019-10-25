package edu.mass.doe.cap.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import edu.mass.doe.cap.file.io.FileSystemStorageService;
import edu.mass.doe.cap.file.io.StorageService;

/**
 * The Class CAPValidationUtil.
 */
public class CAPValidationUtil {
   

	/**
	 * Validate MEPID.
	 *
	 * @param mepid the mepid
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public static boolean validateMEPID(String mepid, String fieldName, BindingResult bindingResult) {
		String className = bindingResult.getObjectName();

		boolean result = true;

		if (mepid == null || mepid.isEmpty())
			result = false;
		if (result) {
			Pattern pattern = Pattern.compile("^5\\d{7}");

			Matcher matcher = pattern.matcher(mepid.toString());

			result = matcher.matches();

			if (!result)
				bindingResult.rejectValue(fieldName, "pattern." + className + "." + fieldName);
		}

		return result;

	}

	/**
	 * Validate required.
	 *
	 * @param value the value
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public static boolean validateRequired(Object value, String fieldName, BindingResult bindingResult) {
		String className = bindingResult.getObjectName();
		boolean result = true;

		if (value == null)
			result = false;

		if (result && value.getClass().getSimpleName().equals("String")) {
			if (((String) value).trim().isEmpty())
				result = false;
		}

		if (!result)
			bindingResult.rejectValue(fieldName, "required." + className + "." + fieldName);

		return result;

	}

	/**
	 * Validate required.
	 *
	 * @param value the value
	 * @param fieldName the field name
	 * @param errorCode the error code
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateRequired(Object value, String fieldName, String errorCode,
			BindingResult bindingResult, String msg) {
		String className = bindingResult.getObjectName();
		boolean result = true;

		if (value == null)
			result = false;

		if (result && value.getClass().getSimpleName().equals("String")) {
			if (((String) value).trim().isEmpty())
				result = false;
		}

		if (!result)
			bindingResult.rejectValue(fieldName, "required." + className + "." + errorCode, new Object[] { msg }, null);

		return result;

	}

	/**
	 * Validate date before.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateDateBefore(Date date1, Date date2, String fieldName, BindingResult bindingResult,
			Object[] msg) {
		boolean result = true;

		if (date1.before(date2)) {
			bindingResult.rejectValue(fieldName, "date.before", msg, null);
			result = false;
		}

		return result;

	}
	
	/**
	 * Validat time before.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validatTimeBefore(Date date1, Date date2, String fieldName, BindingResult bindingResult,
			Object[] msg) {
		boolean result = true;

		if (date1.before(date2)) {
			bindingResult.rejectValue(fieldName, "time.before", msg, null);
			result = false;
		}

		return result;

	}

	
	/**
	 * Validat observation time.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validatObservationTime(Date date1, Date date2, String fieldName, BindingResult bindingResult,
			Object[] msg) {
		boolean result = true;

		if (date1.before(date2)) {
			bindingResult.rejectValue(fieldName, "time.before", msg, null);
			result = false;
		}
		
		if (date1.equals(date2)) {
			bindingResult.rejectValue(fieldName, "time.equal", msg, null);
			result = false;
		}

		return result;

	}

	
	/**
	 * Validate cycle start date.
	 *
	 * @param date1 the date 1
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateCycleStartDate(Date date1, Date startDate,Date endDate, String fieldName, BindingResult bindingResult,
			Object[] msg) {
		boolean result = true;
		Object[] msg1=new Object[2];
		msg1[0]=msg[0];
		msg1[1]=msg[1];
		

		if (date1.before(startDate)) {
			bindingResult.rejectValue(fieldName, "date.before",msg1, null);
			result = false;
		}
		
		 	msg1=new Object[2];
			msg1[0]=msg[0];
			msg1[1]=msg.length>2?msg[2]:"Todays Date";
			
		
		if (date1.after(endDate)) {
			bindingResult.rejectValue(fieldName, "date.after", msg1, null);
			result = false;
		}

		return result;

	}

	
	/**
	 * Validate email.
	 *
	 * @param email the email
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateEmail(String email,String fieldName, BindingResult bindingResult,Object[] msg) {
		boolean result = true;
		
		if(email==null || email.isEmpty())
			return result;
		
		EmailValidator emailValidator = EmailValidator.getInstance();
		if(!emailValidator.isValid(email)){
			bindingResult.rejectValue(fieldName, "email.invalid", msg, null);
			result = false;
		}
			

		return result;
	}
	
	/**
	 * Validate related fields.
	 *
	 * @param email1 the email 1
	 * @param email2 the email 2
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateRelatedFields(String email1,String email2,String fieldName, BindingResult bindingResult,Object[] msg) {
		boolean result = true;
		
		if( email1==null || email2.isEmpty()||email2==null || email2.isEmpty())
			return result;
		
		
		if(!email1.equals(email2)){
			bindingResult.rejectValue(fieldName, "field.notmatching", msg, null);
			result = false;
		}
			

		return result;
	}
	
	
	/**
	 * Validate alphanumeric space.
	 *
	 * @param text the text
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateAlphanumericSpace(String text,String fieldName, BindingResult bindingResult,Object[] msg) {
		boolean result = true;
		
		if(text==null || text.isEmpty())
			return result;
		
		if(!StringUtils.isAlphanumericSpace(text)){
			bindingResult.rejectValue(fieldName, "text.invalid", msg, null);
			result = false;
		}
			

		return result;
	}
	
	/**
	 * Validate numeric.
	 *
	 * @param text the text
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateNumeric(String text,String fieldName, BindingResult bindingResult,Object[] msg) {
		boolean result = true;
		
		if(text==null || text.isEmpty())
			return result;
		
		if(!StringUtils.isNumeric(text)){
			bindingResult.rejectValue(fieldName, "numeric.invalid", msg, null);
			result = false;
		}
			

		return result;
	}
	
	
	/**
	 * Validate double.
	 *
	 * @param text the text
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateDouble(String text,String fieldName, BindingResult bindingResult,Object[] msg) {
		boolean result = true;
		
		if(text==null || text.isEmpty())
			return result;
		
		
		DoubleValidator doubleValidator = DoubleValidator.getInstance();
		
		if(!doubleValidator.isValid(text)){
			bindingResult.rejectValue(fieldName, "numeric.invalid", msg, null);
			result = false;
		}
			

		return result;
	}
	
	/**
	 * Validate message.
	 *
	 * @param text the text
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateMessage(String text,String fieldName, BindingResult bindingResult,Object[] msg) {
		boolean result = true;
		
		if(text==null||text.length()<1){
			bindingResult.rejectValue(fieldName, "message.required", msg, null);
			result=false;
		}
		
		if(result&&text.trim().isEmpty()){
			bindingResult.rejectValue(fieldName, "message.invalid", msg, null);
			result=false;
		
		}
		
		
		return result;
	}

	
	
	/**
	 * Validate text.
	 *
	 * @param text the text
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 */
	public static boolean validateText(String text,String fieldName, BindingResult bindingResult,Object[] msg) {
		boolean result = true;
		
		if(text==null || text.isEmpty())
			return result;
		
		Pattern pattern= Pattern.compile("[^\u0000-\u007F]|[@`~#$%^&*|]");
		Matcher matcher=pattern.matcher(text);
		if(matcher.find()){
			bindingResult.rejectValue(fieldName, "text.invalid", msg, null);
			result = false;
		}
			

		return result;
	}
	
	/**
	 * Validate file.
	 *
	 * @param file the file
	 * @param cycleId the cycle id
	 * @param storageService the storage service
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean validateFile(MultipartFile file,Long cycleId,StorageService storageService,Long maxFileSize,String fieldName, BindingResult bindingResult,Object[] msg) throws IOException {
		boolean result = true;

		List<String> contentTypes = Arrays.asList("application/pdf","application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document","application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation","audio/mpeg","audio/mp3","audio/wav","text/css","text/html","text/csv","text/plain","image/png","image/bmp","image/jpeg","image/gif");
		
		
		if(file.isEmpty() ){
			bindingResult.rejectValue(fieldName, "cap.file.missing", msg, null);
			result=false;
		}
		
		if(result &&!contentTypes.contains(file.getContentType())){
			bindingResult.rejectValue(fieldName, "cap.file.invalid", msg, null);
			result=false;
		}
		
		if(result &&file.getSize()<1){
			bindingResult.rejectValue(fieldName, "cap.file.empty", msg, null);
			result=false;
		}
		
		
		
		if(result){			
			
			Long size=storageService.repoSize(cycleId)+file.getSize();
			
			if(size > maxFileSize){
				bindingResult.rejectValue(fieldName, "cap.folder.max.exceed", msg, null);
				result=false;
			}

		}
		
			
		return result;
	}
	
	
	/**
	 * Validate CAP file.
	 *
	 * @param file the file
	 * @param fieldName the field name
	 * @param bindingResult the binding result
	 * @param msg the msg
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean validateCAPFile(MultipartFile file,String fieldName, BindingResult bindingResult,Object[] msg) throws IOException {
		boolean result = true;

		List<String> contentTypes = Arrays.asList("text/csv","application/vnd.ms-excel");
		
		
		
		if(file.getSize()==0){
			bindingResult.rejectValue(fieldName, "cap.file.missing", msg, null);
			result=false;
		}
		
		if(result &&!contentTypes.contains(file.getContentType())){
		bindingResult.rejectValue(fieldName, "cap.bulk.upload.invalid", msg, null);
			result=false;
		}
		
		if(result &&file.getSize()<1){
			bindingResult.rejectValue(fieldName, "cap.file.empty", msg, null);
			result=false;
		}
			
		return result;
	}
	
	

}
