<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<div style="margin-bottom: 25px" class="error">
	<form:errors path="*" />
</div>
<div id="postScript"></div>
<form:form id="practitionerForm" method="POST"
	commandName="capCycleInfo">
	<div style="margin-bottom: 25px" class="error">
		<form:errors path="" />
	</div>
	<c:if test="${!(showResult or loadPractitionerInfo)}">

		<div id="searchDistrict" class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Organization
					Type*:</div>
			</div>
			<div class="col-sm-3">
				<form:select id="districtOrgTypeId"
					path="practitioner.districtOrgTypeId"
					class="form-control custom-select-lg" aria-label="Please select an Organization Type"
					onchange="loadPractitionerModal('modifyDistrictType','districtOrgTypeId','index=${index}')">
					<form:option value="" label="Select" />
					<option
						<c:if test="${capCycleInfo.practitioner.districtOrgTypeId eq 12}">selected="selected"</c:if>
						value="12">Charter District</option>
					<option
						<c:if test="${capCycleInfo.practitioner.districtOrgTypeId eq 3}">selected="selected"</c:if>
						value="3">Collaborative</option>
					<option
						<c:if test="${capCycleInfo.practitioner.districtOrgTypeId eq 5}">selected="selected"</c:if>
						value="5">Public School District</option>
					 <option
						<c:if test="${capCycleInfo.practitioner.districtOrgTypeId eq 11}">selected="selected"</c:if>
						value="11">Private (Non-Public/Non-Special Ed) Schools</option>
					<option
						<c:if test="${capCycleInfo.practitioner.districtOrgTypeId eq 29}">selected="selected"</c:if>
						value="29">Out-of-state School</option>
				</form:select>
				<div id="outOfStateWaiver"
						class="col-form-label  font-weight-bold" style="display: ${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'initial':'none'}"">Waiver
						Required</div>				
				<form:errors path="practitioner.districtOrgTypeId" cssClass="error"  />
			</div>



			<div class="col-sm-3">
				<div id="districtOrgIdLabel"
					class="col-form-label float-right font-weight-bold"
					style="display:${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}">
					Practicum District*:</div>
			</div>
			<div class="col-sm-3">
				<form:select id="districtOrgId" path="practitioner.districtOrgId"
					onchange="loadPractitionerModal('modifyDistrict','districtOrgTypeId,districtOrgId','index=${index}')"
					cssStyle="display:${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}"
					class="form-control custom-select-lg" aria-label="Please select a Practicum District">
					<form:option value="" label="Select" />
					<form:options items="${districts}"   />
				</form:select>
				<form:errors path="practitioner.districtOrgId" cssClass="error"  />
			</div>
		</div>




		<div id="searchMEPID" class="form-group row">
			<div class="col-sm-3">
				<div id="practitionerInputMEPIDLabel"
					class="col-form-label float-right font-weight-bold"
					style="display:${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}">Supervising
					Practitioner's MEPID*:</div>
			</div>
			<div class="col-sm-3">
				<form:input id="mepid" path="practitioner.mepid" cssClass="form-control" aria-label="Please enter Practitioner's MEPID"
					cssStyle="display:${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'} " />
					<form:errors path="practitioner.mepid" cssClass="error"  />
			</div>
			<div class="col-sm-3">
			<div id="practitionerInputMEPIDLabel"
					class="col-form-label float-right font-weight-bold"
					style="display:${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}">Practicum School*:</div>
			</div>
			<div class="col-sm-3">
			<form:select id="schoolOrgId" path="practitioner.schoolOrgId"
					cssStyle="display:${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}"
					class="form-control custom-select-lg" aria-label="Please select a Practicum School">
					<form:option value="" label="Select" />
					<form:options items="${schools}"   />
				</form:select>
				<form:errors path="practitioner.schoolOrgId" cssClass="error"  />
			</div>
		</div>
		
		
		<c:if test="${not empty noPractitionerFound and noPractitionerFound}">
		<div class="form-group row ">
			<div class="col-sm-12">
				<table id="resultDatatable"
					class="table table-hover table-bordered table-striped">
					<tbody>
						<tr><td colspan="2" >There are no results matching your search criteria.</td></tr>
					</tbody>
				</table>
			</div>
		</div>

	</c:if>


		<div class="form-group row " style="text-align: center">
			<div class="col-sm-12">
				<button type="button" class="btn btn-secondary" data-dismiss="modal"  onclick="saveForm();"
					style="display:${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}">Close</button>
				<input id="searchpractitioner" type="button" class="btn btn-primary"
					value="Search" 
					style="display:${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'none':'initial'}"
					onclick="loadPractitionerModal('loadPractitionerInfo','mepid,districtOrgId,districtOrgTypeId,schoolOrgId','index=${index}')">
			</div>

		</div>


		<div id="outOfStateSection"
			style="display:${capCycleInfo.practitioner.districtOrgTypeId eq 29 or capCycleInfo.practitioner.districtOrgTypeId eq 11 ? 'initial':'none'}">
			<div class="form-group row">
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">First
						Name*:</div>
				</div>
				<div class="col-sm-3">
					<form:input path="practitioner.firstName" cssClass="form-control" maxlength="30" />
					<form:errors path="practitioner.firstName" cssClass="error"  />
				</div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Last
						Name*:</div>
				</div>
				<div class="col-sm-3">
					<form:input path="practitioner.lastName" cssClass="form-control" maxlength="30" />
					<form:errors path="practitioner.lastName"  cssClass="error"  />
				</div>
			</div>


			<div class="form-group row">
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Practicum
						State*:</div>
				</div>
				<div class="col-sm-3">
					<form:input path="practitioner.state"  cssClass="form-control" maxlength="256"/>
					<form:errors path="practitioner.state"  cssClass="error"  />
				</div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Practicum
						District*:</div>
				</div>
				<div class="col-sm-3">
					<form:input path="practitioner.districtName" cssClass="form-control" maxlength="256" />
					<form:errors path="practitioner.districtName"  cssClass="error"  />
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Practicum
						School*:</div>
				</div>
				<div class="col-sm-3">
					<form:input path="practitioner.schoolName" cssClass="form-control" maxlength="256"  />
					<form:errors path="practitioner.schoolName"  cssClass="error"  />
				</div>
				<div class="col-sm-6"></div>

			</div>

			<div class="form-group row " style="text-align: center">
				<div class="col-sm-12">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="loadPractitionerModal('savePractitioner','practitioner\\.schoolName,practitioner\\.districtName,practitioner\\.state,practitioner\\.lastName,practitioner\\.firstName','index=${index}')"
						>Assign</button>

				</div>
			</div>
		</div>


	</c:if>

	


	<c:if test="${loadPractitionerInfo}">
		<div class="form-group row">
			<div class="col-sm-2"></div>
			<div class="col-sm-4">
				<div class="col-form-label float-right font-weight-bold">Supervising
					Practitioner:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${capCycleInfo.practitioner.firstName} ${capCycleInfo.practitioner.lastName}</div>
			</div>
			<div class="col-sm-3"></div>
		</div>

		<div class="form-group row">
			<div class="col-sm-3"></div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Practicum
					District:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${capCycleInfo.practitioner.districtName}</div>
			</div>
			<div class="col-sm-3"></div>
		</div>

		<div class="form-group row">
			<div class="col-sm-3"></div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Practicum
					School:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${capCycleInfo.practitioner.schoolName}</div>
			</div>
			<div class="col-sm-3"></div>
		</div>


		<c:if test="${capCycleInfo.practitioner.daPersonId!=null}">
			<div class="form-group row">
				<div class="col-sm-3"></div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Email:</div>
				</div>
				<div class="col-sm-3">
					<form:input id="practitioner.email" class="form-control"
						path="practitioner.email" />
						<form:errors path="practitioner.email"  cssClass="error"  />
				</div>
				<div class="col-sm-3"></div>
			</div>

		</c:if>

		<c:if test="${capCycleInfo.practitioner.daPersonId==null}">
		<div class="form-group row">
				<div class="col-sm-3"></div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Email:</div>
				</div>
				<div class="col-sm-3">
					<form:input id="practitioner.email" class="form-control"
						path="practitioner.email" />
						<form:errors path="practitioner.email"  cssClass="error"  />
				</div>
				<div class="col-sm-3"></div>
			</div>
			
			<div class="form-group row">
				<div class="col-sm-3"></div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Confirm Email:</div>
				</div>
				<div class="col-sm-3">
						<form:input class="form-control"
						path="practitioner.confirmEmail" />
					<form:errors path="practitioner.confirmEmail"  cssClass="error"  />
				</div>
				<div class="col-sm-3"></div>
			</div>
			
		</c:if>

		<div class="form-group row" style="text-align: center">
			<div class="col-sm-12">
				<button type="button" class="btn btn-secondary" data-dismiss="modal"  onclick="saveForm();" >Close</button>
				<button type="button" class="btn btn-primary" onclick="loadPractitionerModal('savePractitioner','practitioner\\.email${capCycleInfo.practitioner.daPersonId==null?',practitioner\\\\.confirmEmail':''}','index=${index}')"
						>Assign</button>
			</div>
		</div>
	</c:if>



</form:form>
