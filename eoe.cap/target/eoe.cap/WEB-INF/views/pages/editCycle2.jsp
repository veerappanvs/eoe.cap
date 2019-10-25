<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
var noDataFoundforMEPID="<spring:message code="empty.mepid.result"/>";
var nopractitionerFound="<spring:message code="empty.practitioner.result"/>";



function saveSupervisor(event){
	
	
	
	var form = $('#form2');
	
	$
	.ajax({
		type : "POST",
		url : "/cap/cycle/editPractioner/",
		data:form.serialize(),
		success : function(
				response) {

			result = $
					.parseHTML(response);
			$('#practitioner-section')
					.text("");
			$('#practitioner-section')
					.append(result[1].children[2]);		
			
			$('#error-section-modal')
			.text("")
			;$('#error-section-modal')
			.append(result[1].children[0]);
			
			
			
			
		},
		 complete:function(response){
			 
				 postScript=  $(response.responseText);
			 
			 if(postScript.length!=0){
				 eval($(response.responseText).find('script')[0].innerText);
			 }
			 
			 $('#searchpractitioner')
				.click(
						function() {
							loadDataTable('/cap/cycle/practitioners','result','districtOrgId,practitionerInputMEPID',nopractitionerFound,'error-section-modal');

						});
				districtOrgTypeId=$('#districtOrgTypeId').val();
				districtOrgId=$('#districtOrgId').val();
				
				if(districtOrgTypeId=='' )
					return ;
				
				
				if(districtOrgTypeId==29){
					$('#srhOptions').hide();
					$('#districtOrgIdLabel').hide();
					$('#districtOrgId').hide();
					$('#searchpractitioner').hide();
					$('#outOfStateSection').show();
					$('#practitionerInputMEPID').hide();
					$('#practitionerInputMEPIDLabel').hide();
					$('#practitioner\\.firstName').prop("disabled", false);
					$('#practitioner\\.lastName').prop("disabled", false);
					$('#practitioner\\.state').prop("disabled", false);
					$('#practitioner\\.schoolName').prop("disabled", false);
					$('#dpractitioner\\.istrictName').prop("disabled", false);
					$('#practitionerInputMEPID').prop("disabled", false);
					
					return ;
				}else{
					
					$('#practitioner\\.firstName').prop("disabled", true);		
					$('#practitioner\\.lastName').prop("disabled", true);		
					$('#practitioner\\.state').prop("disabled", true);	
					$('#practitioner\\.schoolName').prop("disabled", true);		
					$('#practitioner\\.districtName').prop("disabled", true);
					
					$('#practitionerInputMEPID').show();
					$('#srhOptions').show();
					$('#districtOrgIdLabel').show();
					$('#districtOrgId').show();
					$('#districtOrgId').prop("disabled", false);
					$('#searchpractitioner').show();
					$('#searchpractitioner').prop("disabled", false);
					$('#outOfStateSection').hide();
					
					
				}
				
				
				
				$('#districtOrgId').children('option:not(:first)').remove();
				
				
				loadDropDown('/cap/cycle/districts', 'districtOrgId');
				
				$('#districtOrgId').val(districtOrgId);
				
				
				var mepid=$('#practitionerInputMEPID').val();	

				var canLoadpractitioner='${assignpractitioner}'!='';
				
				if(canLoadpractitioner)
				assignSupervisor(mepid);
			 
		 },
		error : function(msg,
				url, line) {
			$('#error-section-modal')
					.text(
							'Unknown Exception');
		}
	});
	
	
	
	
	
	
	return ;
}


	function assignSupervisor(mepid,orgId){
		
		$
		.ajax({
			type : "GET",
			url : "/cap/cycle/findpractitioner?mepid="+mepid+"&orgId="+orgId,
			success : function(
					response) {

				result = $
						.parseHTML(response);
				$('#resultWrapper')
						.text("");
				
				$('#resultWrapper')
						.append(result[5]);				

			},
			error : function(msg,
					url, line) {
				$('#resultWrapper')
						.text(
								'Unknown Exception');
			}
		});
		
	}

	

	function onSelectChange(path, elementId, targetElementId) {
		
		resetFormData('/cap/cycle/onOrgTypeChange');
		
		$('#resultWrapper').text('');

		var selectedValue = $('#' + elementId).val();
		
		if (selectedValue == '')
			return;
		
		
		if(selectedValue==29){
			$('#srhOptions').hide();
			$('#districtOrgIdLabel').hide();
			$('#districtOrgId').hide();
			$('#searchpractitioner').hide();
			$('#outOfStateSection').show();
			$('#practitionerInputMEPID').hide();
			$('#practitionerInputMEPIDLabel').hide();
			$('#practitioner\\.firstName').val('');
			$('#practitioner\\.firstName').prop("disabled", false);
			$('#practitioner\\.lastName').val('');
			$('#practitioner\\.lastName').prop("disabled", false);
			$('#practitionerstate').val('');
			$('#practitioner\\.state').prop("disabled", false);
			$('#practitioner\\.schoolName').val('');
			$('#practitioner\\.schoolName').prop("disabled", false);
			$('#practitioner\\.districtName').val('');
			$('#practitioner\\.districtName').prop("disabled", false);
			$('#practitionerInputMEPID').val('');
			$('#practitionerInputMEPID').prop("disabled", false);
			
			return;
		}else{
			$('#practitioner\\.firstName').val('');
			$('#practitioner\\.firstName').prop("disabled", true);
			
			$('#practitioner\\.lastName').val('');
			$('#practitioner\\.lastName').prop("disabled", true);
			
			$('#practitioner\\.state').val('');
			$('#practitioner\\.state').prop("disabled", true);
			
			$('#practitioner\\.schoolName').val('');
			$('#practitioner\\.schoolName').prop("disabled", true);
			
			$('#practitioner\\.districtName').val('');
			$('#practitioner\\.districtName').prop("disabled", true);

			$('#practitionerInputMEPID').val('');
			$('#practitionerInputMEPID').show();
			$('#srhOptions').show();
			$('#districtOrgIdLabel').show();
			$('#districtOrgId').show();
			$('#districtOrgId').prop("disabled", false);
			$('#searchpractitioner').show();
			$('#searchpractitioner').prop("disabled", false);
			
			$('#outOfStateSection').hide();
		}
			
		
		
	if(selectedValue==11){		
		return;
	}
			
	

		var targetElement = $('#' + targetElementId);
		var elementName = $('#' + elementId).attr("name");
		targetElement.val('');
		targetElement.children('option:not(:first)').remove();

		$.ajax({
			type : "GET",
			async : false,
			url : '/cap/cycle/' + path + '?' + elementName + '='
					+ selectedValue,
			success : function(response) {

				$.each(response, function(key, value) {
					targetElement.append($("<option></option>").attr("value",
							key).text(value));
				});
			},
			error : function(msg, url, line) {
				$('#error-section').text('Unknown Exception');
			}
		});
	}
		
		
function	pageLoad() {
	
	$('#savePractitioner')
	.click(
			function(event) {
				saveSupervisor(event);

			});
	
	$('#searchpractitioner')
	.click(
			function() {
				loadDataTable('/cap/cycle/practitioners','result','districtOrgId,practitionerInputMEPID',nopractitionerFound,'error-section-modal');

			});
	
	var districtOrgTypeId = '${capCycleInfo.practitioner.districtOrgTypeId}';
	var districtOrgId = '${capCycleInfo.practitioner.districtOrgId}';
	
	
	if(districtOrgTypeId=='' )
		return;
	
	
	if(districtOrgTypeId==29){
		$('#srhOptions').hide();
		$('#districtOrgIdLabel').hide();
		$('#districtOrgId').hide();
		$('#searchpractitioner').hide();
		$('#outOfStateSection').show();
		$('#practitionerInputMEPID').hide();
		$('#practitionerInputMEPIDLabel').hide();
		$('#practitioner\\.firstName').prop("disabled", false);
		$('#practitioner\\.lastName').prop("disabled", false);
		$('#practitioner\\.state').prop("disabled", false);
		$('#practitioner\\.schoolName').prop("disabled", false);
		$('#dpractitioner\\.istrictName').prop("disabled", false);
		$('#practitionerInputMEPID').prop("disabled", false);
		
		return;
	}else{
		
		$('#practitioner\\.firstName').prop("disabled", true);		
		$('#practitioner\\.lastName').prop("disabled", true);		
		$('#practitioner\\.state').prop("disabled", true);	
		$('#practitioner\\.schoolName').prop("disabled", true);		
		$('#practitioner\\.districtName').prop("disabled", true);
		
		$('#practitionerInputMEPID').show();
		$('#srhOptions').show();
		$('#districtOrgIdLabel').show();
		$('#districtOrgId').show();
		$('#districtOrgId').prop("disabled", false);
		$('#searchpractitioner').show();
		$('#searchpractitioner').prop("disabled", false);
		$('#outOfStateSection').hide();
		
		
	}
	
	
	
	$('#districtOrgId').children('option:not(:first)').remove();
	
	
	loadDropDown('/cap/cycle/districts', 'districtOrgId');
	
	$('#districtOrgId').val(districtOrgId);
	
	
	var mepid=$('#practitionerInputMEPID').val();	

	var canLoadpractitioner='${assignpractitioner}'!='';
	
	if(canLoadpractitioner)
	assignSupervisor(mepid);
	
	
	
	
				
}
		


	
</script>
<title></title>
</head>
<body>
	<form:form id="form2" method="POST" commandName="capCycleInfo"  action="/cap/cycle" >
		<div id="error-section" style="margin-bottom: 25px" class="error">
			<form:errors path="*" />
		</div>

		<div style="text-align: center; padding: 10px">
			<h5>${capCycleInfo.candidateLastName}'s Program Information</h5>
		</div>

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Program:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${capCycleInfo.programName}</div>
			</div>
			<div class="col-sm-6"></div>
		</div>


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Teacher Candidate
					MEPID:</div>
			</div>
			<div class="col-sm-2">
				<div class="col-form-label">${capCycleInfo.candidateMEPID}</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Supervising
					Practitioner MEPID:</div>
			</div>
			<div class="col-sm-3">
				<div id="practionerMEPID"  class="col-form-label">${capCycleInfo.practitioner.mepid}</div>
				<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#exampleModalCenter" data-backdrop="static"
			data-keyboard="false">Edit</button>
			</div>
			<div class="col-sm-1"></div>
		</div>
		
		
		

		<div class="form-group row">
			<div class="col-sm-5">
				<div class="col-form-label float-right font-weight-bold">Have any of the
					components of the approved programs been waived? </div>
			</div>
			<div class="col-sm-3">
				<form:select id="waived" path="waived" cssStyle=" width:100px"
					class="form-control custom-select-lg">
					<form:option value="" label="Select" />
					<form:option value="Y" label="Yes" />
					<form:option value="N" label="No" />
				</form:select>
				<form:errors path="waived" cssClass="error" />
			</div>
			<div class="col-sm-4"></div>
		</div>

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Practium
					Information:</div>
			</div>
			<div class="col-sm-3">
				<form:select id="practicumTypeCode" path="practicumTypeCode"
					class="form-control custom-select-lg">
					<form:option value="" label="Select" />
					<form:options items="${practicumTypes}" />
				</form:select>
				<form:errors path="schoolYear" cssClass="error" />
			</div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">School Year:</div>
			</div>
			<div class="col-sm-3">
				<form:select id="cycleYear" path="schoolYear"
					class="form-control custom-select-lg">
					<form:option value="" label="Select" />
					<form:options items="${schooYearMap}" />
				</form:select>
				<form:errors path="schoolYear" cssClass="error" />
			</div>
			<div class="col-sm-1"></div>
		</div>


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Program Area and
					Grad Level:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label"><form:input class="form-control " path="programAreaGradLevel"/></div>
			</div>
			<div class="col-sm-6"></div>
		</div>


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Practicum
					District:</div>
			</div>
			<div class="col-sm-3">
				<div id="practionerDistrict" class="col-form-label">${capCycleInfo.practitioner.districtName}</div>
			</div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Practicum School:</div>
			</div>
			<div class="col-sm-3">
				<div id="practionerSchool"  class="col-form-label">${capCycleInfo.practitioner.schoolName}</div>
			</div>
			<div class="col-sm-1"></div>
		</div>


		<div class="line" style="margin: 10px 0; color: black"></div>




		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Practicum/Equivalent
					Seminar Course Title:</div>
			</div>
			<div class="col-sm-2"><form:input  class="form-control " path="courseTitle"   /> </div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Practicum/Equivalent
					Course Number:</div>
			</div>
			<div class="col-sm-3"><form:input class="form-control "  path="courseNumber"/></div>
		</div>


		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Grad level Of
					Practicum Students:</div>
			</div>
			<div class="col-sm-2"><form:input class="form-control" path="gradLevel"/></div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Total Number of
					Practicum Hours:</div>
			</div>
			<div class="col-sm-3"><form:input class="form-control" path="practicumHours"/> </div>
		</div>


		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Number of Hours
					assumed full responsibility in the role:</div>
			</div>
			<div class="col-sm-2"><form:input class="form-control" path="hoursFullResponsibility"/></div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Credit Hours:</div>
			</div>
			<div class="col-sm-3"><form:input class="form-control" path="creditHours"/> </div>
		</div>


							<div>
							<div style="text-align: right; margin: 50px">								
								<input type="submit" value="Save" name="Save" class="btn btn-primary" /> 
							</div>
						</div>

		<!-- Button trigger modal -->
		


		<!-- Modal -->
		<div class="modal fade" id="exampleModalCenter" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Assign Supervising Practitioner</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
						</button>
					</div>
					<div class="modal-body">
					<div id="error-section-modal" style="margin-bottom: 25px" class="error">
					</div>
					<div id="practitioner-section">
					<%@ include file="/WEB-INF/views/pages/editpractitioner.jsp" %>
					</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button id="savePractitioner" type="button" class="btn btn-primary">Assign</button>
					</div>
				</div>
			</div>
		</div>


	</form:form>
</body>
</html>