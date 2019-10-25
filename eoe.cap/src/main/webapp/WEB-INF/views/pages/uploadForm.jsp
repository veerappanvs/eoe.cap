<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript" src="/cap/script/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="/cap/script/buttons.flash.min.js"></script>
<script type="text/javascript" src="/cap/script/jszip.min.js"></script>
<script type="text/javascript" src="/cap/script/pdfmake.min.js"></script>
<script type="text/javascript" src="/cap/script/vfs_fonts.js"></script>
<script type="text/javascript" src="/cap/script/buttons.flash.min.js"></script>
<script type="text/javascript" src="/cap/script/buttons.html5.min.js"></script>
<script type="text/javascript" src="/cap/script/buttons.print.min.js"></script>
<script type="text/javascript" src="/cap/script/dataTables.fixedHeader.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">



function loadProgressbar(maxFoldersize,folderSize){

	var size=maxFoldersize - folderSize;
	var progresspercent=(folderSize/maxFoldersize);
	progresspercent=progresspercent%1? progresspercent.toFixed(2): progresspercent;
	$('#progressbar').css('width', (progresspercent)*100 + '%');
 	$("#totFileSizeMsg").html(size%1? size.toFixed(2): size);
}

	function pageLoad() {

	$(document).on(
				'change',
				':file',
				function() {
					$('.error').remove("");
					
					    if(this.files[0].size > (1048576 * ${maxFoldersize})){
					       this.value = "";
					      $(this).after('<span class="error" ><spring:message code ="cap.file.max.exceed"  arguments="${maxFoldersize}" /></span>');
					       return;
					    }
						
					var input = $(this), numFiles = input.get(0).files ? input
							.get(0).files.length : 1, label = input.val()
							.replace(/\\/g, '/').replace(/.*\//, '');

							input[0].nextElementSibling.innerText = label;

				});
				
			 var table = $('#resultTable').DataTable({
						fixedHeader:true,						
						"columnDefs": [
										{ "width": "120px", "targets": 2 },
										{ "width": "80px", "targets": 3 },
										{ "width": "100px", "targets": 4 },
										{ "width": "90px", "targets": 5 },
										{ "width": "80px", "targets": 6 }],
						"language": {
      							"emptyTable": "No uploaded file data exist."}
    }
									);
			
			 	alertUser();
			 	loadProgressbar(${maxFoldersize}, ${folderSize});
			 	
	}
	
	
	
</script>


	<form:form id="form2" method="POST" enctype="multipart/form-data"
		commandName="evidenceFileInfo">


		
		<jsp:include page="cycleInfo.jsp"></jsp:include>


		<div class="line" style="margin: 10px 0; color: black"></div>


		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<h4>Upload Evidence Files</h4>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-2"></div>
			<div class="col-sm-4">
				<div class="custom-file">
					<input type="file" class="custom-file-input" id="file" name="file">
					<div class="custom-file-label" for="file">Choose file</div>
					<form:errors path="file" cssClass="error" />
				</div>				
			<div class="progress" style="margin-top:40px;border:1px solid">
				<div id="progressbar" class="progress-bar" role="progressbar" aria-valuenow="0"
					aria-valuemin="0" aria-valuemax="100" style="width:0">					
				</div>
			</div>
			<div style="text-align: center"><b>Total File size: </b><span id="totFileSizeMsg" >{0}</span> MB free of ${maxFoldersize} MB</div>
			</div>
		<div class="col-sm-4">The maximum amount of data space for each
			cycle is ${maxFoldersize} MB. Once you have reached ${maxFoldersize} MB of data you will no
			longer be able to upload files. Only the following formats are
			allowed: .doc(x), .xls(x), .ppt(x), .pdf, .jpg, .png, .bmp, .gif,
			.mp3, .wav, .txt</div>
		<div class="col-sm-2"></div>
		</div>
		  

		<div style="margin: 50px 0; color: black"></div>
		<input type="hidden" value="${cycleId}">
		
		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-4">Select Types(s) of Evidence:</div>
			<div class="col-sm-7"></div>
		</div>


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">

				<table class="table white-background no-border left-aligned">
					<c:forEach items="${evidenceFileInfo.evidenceTypes}" step="3"
						var="element" varStatus="elementStatus">
						<tr>
							<td ><form:checkbox label="${evidenceFileInfo.evidenceTypes.get(elementStatus.index).label}"
									path="evidenceTypes[${elementStatus.index}].selected" disabled="${!canUpload }"
									 />
								</td>
							<td >
							<form:checkbox disabled="${!canUpload }"
									path="evidenceTypes[${(elementStatus.index+1)}].selected" label="${evidenceFileInfo.evidenceTypes.get(elementStatus.index+1).label}"
									 />
								</td>
							<td >
							<c:if test="${(elementStatus.index+2) lt evidenceFileInfo.evidenceTypes.size() }">
									<form:checkbox disabled="${!canUpload }"
										path="evidenceTypes[${(elementStatus.index+2)}].selected"
										label="${evidenceFileInfo.evidenceTypes.get(elementStatus.index+2).label}" />
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-sm-1"></div>
		</div>

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-4">Tag File to Essential Element(s):</div>
			<div class="col-sm-7"></div>
		</div>


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">

				<table  class="table white-background no-border left-aligned">
					<c:forEach items="${evidenceFileInfo.attributes}" step="3"
						var="element" varStatus="elementStatus">
						<tr>
							<td ><form:checkbox label="${evidenceFileInfo.attributes.get(elementStatus.index).label}"
									path="attributes[${elementStatus.index}].selected" disabled="${!canUpload }"
									 />
								</td>
							<td ><form:checkbox disabled="${!canUpload }"
									path="attributes[${(elementStatus.index+1)}].selected" label="${evidenceFileInfo.attributes.get(elementStatus.index+1).label}"
									 />
								</td>
							<td ><form:checkbox disabled="${!canUpload }"
									path="attributes[${(elementStatus.index+2)}].selected" label="${evidenceFileInfo.attributes.get(elementStatus.index+2).label}"
									 />
								</td>

						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-sm-1"></div>
		</div>

		<c:if test="${canUpload }">
			<div class="form-group row">
				<div class="col-sm-12" style="text-align: center">
					<input type="submit" value="Upload" class="btn btn-primary" />
				</div>
			</div>
		</c:if>
		<div class="line" style="margin: 10px 0; color: black"></div>

		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<h4>Current Uploaded Evidence File</h4>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-12">
				<table id="resultTable"  class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th>File Name</th>
							<th>Type of Evidence</th>
							<th>Tagged Element(s)</th>
							<th>File Owner</th>
							<th>Uploaded Date</th>
							<th>File Size (mb)</th>
							<th>Action</th>							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${evidenceFiles}" var="file">
							<tr id="${file.fileId}">
								<td ><a
									href="/cap/tagfile/download?cycleId=${cycleId }&amp;fileId=${file.fileId}"
									onclick="downloadFile false;">${file.internalFileName }</a></td>
							
								<td><c:forEach items="${file.evidenceTypes}" var="attribute"
										varStatus="attStatus">${attribute.label}${attStatus.last?'':', '}</c:forEach></td>
										
								<td><c:forEach items="${file.attributes}" var="attribute"
										varStatus="attStatus">${attribute.label}${attStatus.last?'':', '}</c:forEach></td>
										
								<td>${file.personName }</td>
								<td><fmt:formatDate pattern="MM/dd/yyyy"
										value="${file.createdDate}" /></td>
								<td>${file.fileSizeInDisk}</td>
								<td><input type="submit" value="Delete " name="delete" onclick="$('#fileId').val(${file.fileId});" class="btn btn-primary" style="${file.canDelete?'':'display:none'}" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<input type="hidden" name="fileId" id="fileId" value="0" />
	</form:form>