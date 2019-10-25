<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script>
	
function loadPractitionerModal(method, process) {

	var parameters = [];

	if (process.length > 0) {
		var processElements = process.split(',');
		for (element in processElements) {
			parameter = {
				name : $('#' + processElements[element]).attr("id"),
				value : $('#' + processElements[element]).val()
			}
			parameters.push(parameter);
		}
	}

	var queryString = jQuery.param(parameters);

	if (queryString != '')
		queryString =  queryString

	path = '/cap/verifycycle/' + method + '?'+queryString;
	console.log('path = '+path)	;
	$.ajax({
		type : "GET",
		url : path,
		async : false,
		success : function(response) {

			result = $.parseHTML(response);

			$('#practitioner-section').text("");
			$('#practitioner-section').append(result[5].childNodes);

		},
		complete : function(response) {

			$('#searchpractitioner')
			.click(
					function() {
						fetchSupervisor();

					});
		}

	});

}


	function fetchSupervisor() {
		
		var schoolOrgId=$('#schoolOrgId').val();
		var districtOrgId=$('#districtOrgId').val();
		var orgTypeId=$('#districtOrgTypeId').val();
		var mepid=$('#practitionerInputMEPID').val();
		var update = 'resultSp';
		$('.error').remove();
		$.ajax({
			type : "GET",
			async : false,
			url : "verifycycle/findpractitioner?practitionermepid=" + mepid + "&schoolOrgId="+ schoolOrgId+"&districtOrgId="+districtOrgId
					+"&districtOrgTypeId="+orgTypeId,
			success : function(response) {
			
				$('#practitioner-section').text("");
				$('#practitioner-section').append($(response).find('#practitionerInfo'));
				
					/*
					result=$(response).find('#'+update+'Datatable');				
					$('#'+ update+'Wrapper' ).append(result);
					result.dataTable({																		
								"language": {
							      "emptyTable": noDataFoundforPrograms
						    } });					
					*/
				

			},
			error : function(msg, url, line) {
				$('#resultSpWrapper').text('Unknown Exception');
			},
			complete : function(response) {
				$('#searchpractitioner')
				.click(
						function() {
							fetchSupervisor();

						});
			}
		});

	}


</script>

<form:form id="practitionerForm" method="POST" 	commandName="verifyCycleCandidateEnrollment"> 
<div id="practitionerInfo" >


			<div id="notes" class="form-group row">
				<div class="col-sm-1"></div>
				<div class="col-sm-8">
					<span style="font-size: .9rem;"><b>Note:</b> 
					<ul>
					<li>Information about Supervising Practitioners from Out of State or Private Schools cannot be validated through this tool.</li>
					<li>To find information about the Supervising Practitioner select their District (includes Charter and Collaborative Schools), enter MEPID and School.</li>
					<li>Only District/Charter School/Collaborative staff who are actively employed can be found.</li>
					<li>To ensure accuracy of data, you may copy paste the entire SP Information row and use it as it is to fill the upload spreadsheet.</li>
					<li>Please contact the employing District, Charter School, or Collaborative, if there are no results matching your search criteria.</li>
					</ul>
					</span>
					<p>&nbsp</p>
				</div>
				<div class="col-sm-1"></div>
			</div>




<c:if
	test="${!(verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 29 or verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 11) or verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq null}">
	<div class="form-group row">
		<div class="col-sm-3">
			<div class="col-form-label float-right font-weight-bold">Organization
				Type*:</div>
		</div>
		<div class="col-sm-3">
			<form:select id="districtOrgTypeId"
				path="practitioner.districtOrgTypeId"
				class="form-control custom-select-lg"
				aria-label="Please select an Organization Type"
				onchange="loadPractitionerModal('modifyDistrictType','districtOrgTypeId')">
				<form:option value="" label="Select" />
				<option
					<c:if test="${verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 12}">selected="selected"</c:if>
					value="12">Charter District</option>
				<option
					<c:if test="${verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 3}">selected="selected"</c:if>
					value="3">Collaborative</option>
				<option
					<c:if test="${verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 5}">selected="selected"</c:if>
					value="5">Public School District</option>
			</form:select>
			<form:errors path="practitioner.districtOrgTypeId" cssClass="error" />
			

		</div>
		<div class="col-sm-3">
			<div id="districtOrgIdLabel"
				class="col-form-label float-right font-weight-bold"
				style="display:${verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 29 or verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}">
				Practicum District*:</div>
		</div>
		<div class="col-sm-3">
			<form:select id="districtOrgId" path="practitioner.districtOrgId"
				onchange="loadPractitionerModal('modifyDistrict','districtOrgTypeId,districtOrgId','index=${index}')"
				cssStyle="display:${verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 29 or verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}"
				class="form-control custom-select-lg"
				aria-label="Please select a Practicum District">
				<form:option value="" label="Select" />
				<form:options items="${districts}" />
			</form:select>
			<form:errors path="practitioner.districtOrgId" cssClass="error" />
		</div>
	</div>


	<div id="searchMEPID" class="form-group row">
		<div class="col-sm-3">
			<div id="practitionerInputMEPIDLabel"
				class="col-form-label float-right font-weight-bold"
				style="display:${verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 29 or verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}">Supervising
				Practitioner's MEPID*:</div>
		</div>
		<div class="col-sm-3">
			<form:input id="practitionerInputMEPID" path="practitionerInputMEPID"
				cssClass="form-control"
				aria-label="Please enter Practitioner's MEPID"
				cssStyle="display:${verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 29 or verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'} " />
			<form:errors path="practitionerInputMEPID" cssClass="error" />
		</div>
		<div class="col-sm-3">
			<div id="practitionerInputMEPIDLabel"
				class="col-form-label float-right font-weight-bold"
				style="display:${verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 29 or verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}">Practicum
				School*:</div>
		</div>
		<div class="col-sm-3">
			<form:select id="schoolOrgId" path="practitioner.schoolOrgId"
				cssStyle="display:${verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 29 or verifyCycleCandidateEnrollment.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}"
				class="form-control custom-select-lg"
				aria-label="Please select a Practicum School">
				<form:option value="" label="Select" />
				<form:options items="${schools}" />
			</form:select>
			<form:errors path="practitioner.schoolOrgId" cssClass="error" />
		</div>
	</div>

	<div class="form-group row " style="text-align: center">
		<div class="col-sm-12">
			<input id="searchpractitioner" type="button" class="btn btn-primary"
				value="Search"  >
		</div>
	</div>


	<c:if test="${not empty noPractitionerFound and noPractitionerFound }">
		<div class="form-group row ">
			<div class="col-sm-12">
				<table id="resultSperrorDatatable"
					class="table table-bordered table-greyed">
					<tbody>
						<tr>
							<td colspan="2">There are no results matching your search criteria. Please ensure that the information entered is accurate and correct.</td>
						</tr>
						<tr>
							<td colspan="2">Please contact the employing District, Charter School, or Collaborative for more information.</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>



	<c:if test="${(not empty noPractitionerFound and !noPractitionerFound) or verifyCycleCandidateEnrollment.practitioner.displaySupervisorInfo }">  
		<div id="exportSpData" style="text-align: right; padding: 10px"  ></div>

		<table id="resultSpDatatable"	class="table datatable table-hover table-bordered table-striped ">
			<thead>
				<tr>
					<th><strong>Supervising Practitioner's Name</strong></th>
					<th><strong>MEPID</strong></th>
					<th><strong>Email Address</strong></th>
					<th><strong>Practicum District</strong></th>
					<th><strong>Practicum School</strong></th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>${verifyCycleCandidateEnrollment.practitioner.firstName} ${verifyCycleCandidateEnrollment.practitioner.lastName}</td>
						<td>${verifyCycleCandidateEnrollment.practitionerInputMEPID}</td>
						<td>${verifyCycleCandidateEnrollment.practitioner.email}</td>
						<td>${verifyCycleCandidateEnrollment.practitioner.districtName}</td>
						<td>${verifyCycleCandidateEnrollment.practitioner.schoolName}</td>
					</tr>
			</tbody>
		</table>



	</c:if> 

</c:if>
</div>
</form:form>