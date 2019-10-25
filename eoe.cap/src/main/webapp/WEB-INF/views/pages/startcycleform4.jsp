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

	path = '/cap/candidate/' + method + '?'+queryString;

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
		
		$('.error').remove();
		$.ajax({
			type : "GET",
			async : false,
			url : "candidate/findpractitioner?practitionermepid=" + mepid + "&schoolOrgId="+ schoolOrgId+"&districtOrgId="+districtOrgId
					+"&districtOrgTypeId="+orgTypeId,
			success : function(response) {

				$('#practitioner-section').text("");
				$('#practitioner-section').append($(response).find('#practitionerInfo'));
				

			},
			error : function(msg, url, line) {
				$('#resultWrapper').text('Unknown Exception');
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
	
	function onDistrictChange(){
		$('#resultWrapper').text('');
		var targetElement = $('#schoolOrgId');
		targetElement.val('');
		targetElement.children('option:not(:first)').remove();
		var selectedValue = $('#districtOrgId').val();
		
		if (selectedValue == '')
			return;
		
		$.ajax({
			type : "GET",
			async : false,
			url : '/cap/candidate/' + schools + '?' + practitioner.districtOrgId + '='
					+ selectedValue,
			success : function(response) {

				$.each(response, function( index, map) {
					targetElement.append($("<option></option>").attr("value",
							map.key).text(map.value));
				})
			},
			error : function(msg, url, line) {
				$('#error-section').text('Unknown Exception');
			}
		});
		
	}

	function onSelectChange(path, elementId, targetElementId) {
		$('.error').remove();

		resetFormData('candidate/onOrgTypeChange');

		$('#resultWrapper').text('');

		var selectedValue = $('#' + elementId).val();

		if (selectedValue == '')
			return;

		if (selectedValue == 29 || selectedValue == 11) {
			$('#srhOptions').hide();
			$('#districtOrgIdLabel').hide();
			$('#districtOrgId').hide();
			$('#schoolOrgId').hide();
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
			$('#outOfStateWaiver').show();

			return;
		} else {
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

			$('#practitionerInputMEPIDLabel').show();
			$('#practitionerInputMEPID').val('');
			$('#practitionerInputMEPID').show();
			$('#srhOptions').show();
			$('#districtOrgIdLabel').show();
			$('#schoolOrgIdLabel').show();
			$('#districtOrgId').show();
			$('#districtOrgId').prop("disabled", false);
			$('#schoolOrgId').show();
			$('#schoolOrgId').prop("disabled", false);
			$('#searchpractitioner').show();
			$('#searchpractitioner').prop("disabled", false);
			$('#outOfStateWaiver').hide();
			$('#outOfStateSection').hide();
		}

		if (selectedValue == 11) {
			return;
		}

		var targetElement = $('#' + targetElementId);
		var elementName = $('#' + elementId).attr("name");
		targetElement.val('');
		targetElement.children('option:not(:first)').remove();

		$.ajax({
			type : "GET",
			async : false,
			url : '/cap/candidate/' + path + '?' + elementName + '='
					+ selectedValue,
			success : function(response) {

				$.each(response, function( index, map) {
					targetElement.append($("<option></option>").attr("value",
							map.key).text(map.value));
				})
			},
			error : function(msg, url, line) {
				$('#error-section').text('Unknown Exception');
			}
		});
	}

	function pageLoad() {

		$('#searchpractitioner')
				.click(
						function() {
							fetchSupervisor();

						});

		

	}

	function loadPractitionerTable(path, update, process, emptyMessage,
			errorSection) {
		$('.error').remove();

		if (typeof (errorSection) === 'undefined')
			errorSection = 'error-section';

		$('#' + errorSection).text("");

		var processElements = process.split(',');

		var parameters = [];

		for (element in processElements) {
			parameter = {
				name : $('#' + processElements[element]).attr("name"),
				value : $('#' + processElements[element]).val()
			}
			parameters.push(parameter);
		}

		var queryString = jQuery.param(parameters);

		if (queryString != '')
			queryString = '?' + queryString;

		var uri = path + queryString;

		jQuery.param(parameters);

		var elementName = $('#' + process).attr("name");

		jQuery.support.cors = true;
		$.ajax({
			type : "GET",
			url : uri,
			async : false,
			success : function(response) {

				$('#' + update + 'Wrapper').text("");

				result = $(response).find('#' + update + 'Datatable');

				if (result.length == 0) {
					result = $(response).children()[0];

					if (result.id.includes('districtOrgTypeId')) {
						$('#districtOrgTypeId').after(result);
					}

					if (result.id.includes('practitioner.districtOrgId')) {
						$('#districtOrgId').after(result);
					}

					if (result.id.includes('practitionerInputMEPID')) {
						$('#practitionerInputMEPID').after(result);
					}

					return;
				}

				$('#' + update + 'Wrapper').append(result);

				result.dataTable({
					"language" : {
						"emptyTable" : emptyMessage
					}
				});

			},
			error : function(msg, url, line) {
				$('#table-section').text('Unknown Exception');
			}
		});

	}
</script>
<title></title>
<tiles:putAttribute name="title" value="Teacher Candidate Search" />
</head>
<body>
	<form:form id="form2" method="POST" commandName="candidateEnrollment">
		<div id="error-section" style="margin-bottom: 25px" class="error">
		</div>


		<div style="text-align: center; padding: 10px">
			<h5>Teacher Candidate Information</h5>
		</div>
		<div class="line" style="margin: 10px 0; color: black"></div>
		<div>

			<div class="form-group row">
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Teacher
						Candidate:</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label">${candidateEnrollment.candidateName}</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Teacher
						Candidate MEPID:</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label">${candidateEnrollment.mepid}</div>
				</div>

			</div>


			<div class="form-group row">
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Program:</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label">${candidateEnrollment.programName}</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Program
						Supervisor:</div>
				</div>
				<div class="col-sm-3">
					<div id="supervisorName" class="col-form-label">${candidateEnrollment.supervisorName}</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Cycle
						Start Date:</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label"><fmt:formatDate
							pattern="MM/dd/yyyy"
							value="${candidateEnrollment.cycleStartDate}" /></div>
				</div>
				<div class="col-sm-3"></div>
				<div class="col-sm-3"></div>
			</div>

		</div>
		<div>


			<div class="line" style="margin: 10px 0; color: black"></div>

			<div style="text-align: center; padding: 10px">
				<h5>Assign Supervising Practitioner</h5>
			</div>

			<div id="notes" class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<span style="font-size: 1rem;"><b>Note:</b> 
					<ul>
					<li>For Supervising Practitioners working in Massachusetts Districts, Charter Schools, or Collaboratives, search for the Supervising Practitioner by selecting his/her Organization Type and District/Charter School/Collaborative, and then entering his/her MEPID and School.</li>
					<li>Only District/Charter School/Collaborative staff who are actively employed can be assigned as a Supervising Practitioner in the CAP Online Platform.</li>
					<li>Please contact the employing District, Charter School, or Collaborative, if there are no results matching your search criteria.</li>
					<li>For Supervising Practitioners working outside of Massachusetts public schools, you will be prompted to enter his or her information manually. Supervising Practitioners outside of Massachusetts public schools will not have access to the CAP Online Platform.</li>
					</ul>
					</span>
					<p>&nbsp</p>
				</div>
				<div class="col-sm-2"></div>
			</div>


			<div id="practitioner-section">
				<jsp:include page="practitioner.jsp"></jsp:include>				
			</div>



			<div>
				<div style="text-align: right; margin: 50px">
					<input type="hidden" value="3" name="_page" /> <input
						type="submit" value="Back" name="_command" class="btn btn-primary" />
					<input type="submit" value="Cancel" name="_command"
						class="btn btn-primary" /> <input type="submit" value="Assign"
						name="_command" class="btn btn-primary" />
				</div>
			</div>

		</div>
		</div>
		<div class="line" style="margin: 10px 0; color: black"></div>



	</form:form>

</body>