<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/fixedheader/3.1.5/js/dataTables.fixedHeader.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

function pageLoad() {
			
			var table = $('#resultDatatable').DataTable(
					{						
						fixedHeader: true,        
						"columnDefs": [
										{"targets": [ 4 ], "orderData": [13]}    ,
										{ "width": "120px", "targets": 0 },
										{ "width": "120px", "targets": 1 },
										{ "width": "160px", "targets": 2 },
										{ "width": "120px", "targets": 3 },
										{ "width": "120px", "targets": 4 },
										{ "width": "120px", "targets": 5 },
										{ "width": "120px", "targets": 6 },
										{ "width": "120px", "targets": 7 },
										{ "width": "160px", "targets": 8 },
										{ "width": "120px", "targets": 9 },
										{ "width": "130px", "targets": 10 },
										{ "width": "120px", "targets": 11 },
										{ "width": "120px", "targets": 12 },
										{ "width": "130px", "targets": 13 },
										{ "width": "30px", "targets": 14 },
										{ "width": "30px", "targets": 15 },
										{ "width": "30px", "targets": 16 },
										{ "width": "30px", "targets": 17 },
										{ "width": "30px", "targets": 18 },
										{ "width": "30px", "targets": 19 },
										{ "width": "30px", "targets": 20 },
										{ "width": "30px", "targets": 21 },
										{ "width": "30px", "targets": 22 },
										{ "width": "30px", "targets": 23 },
										{ "width": "30px", "targets": 24 },
										{ "width": "30px", "targets": 25 },
										{ "width": "30px", "targets": 26 },
										{ "width": "30px", "targets": 27 },
										{ "width": "30px", "targets": 28 },
										{ "width": "30px", "targets": 29 },
										{ "width": "30px", "targets": 30 },
										{ "width": "30px", "targets": 31 },
										{ "width": "30px", "targets": 32 },
										{ "width": "30px", "targets": 33 },
										{ "width": "30px", "targets": 34 },
										{ "width": "30px", "targets": 35 },
										{ "width": "30px", "targets": 36 },
										{ "width": "30px", "targets": 37 },
										{ "width": "30px", "targets": 38 },
										{ "width": "30px", "targets": 39 },
										{ "width": "30px", "targets": 40 },
										{ "width": "30px", "targets": 41 },
										{ "width": "30px", "targets": 42 },
										{ "width": "30px", "targets": 43 },
										{ "width": "30px", "targets": 44 },
										{ "width": "30px", "targets": 45 },
										{ "width": "30px", "targets": 46 },
										{ "width": "30px", "targets": 47 },
										{ "width": "30px", "targets": 48 },
										{ "width": "30px", "targets": 49 },
										{ "width": "120px", "targets": 50 },
										{ "width": "120px", "targets": 51 }
			                          ]});
			 
			 var buttons = new $.fn.dataTable.Buttons(table, {
			     buttons: [
			        {
                		extend: 'excel',
                		title: 'CAP Cycles Annual Report',
                		text: '<i class="fal fa-file-excel fa-1x"></i>',
						orientation: 'landscape',
						pageSize: 'LEGAL',
                		className: 'btn btn-primary'
            	}]
			}).container().appendTo($('#exportData'));			
		}

</script>
	

<div style="position: relative;">
<div id="error-section" style="margin-bottom: 25px"></div>
<div style="text-align:center;width:1000px;">
	<h5>CAP Cycles Annual Report</h5>
</div>


<div style="text-align:left;width:1000px;padding-top:25px;padding-top:25px;padding-bottom:25px;">
	<form:form id="form1"  method="POST" commandName="cycleReportInfo" action="/cap/reports/getReport">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-2">
					<div class="col-form-label float-right font-weight-bold">Select School Year:</div>
				</div>
				<div class="col-sm-2">
					<form:select id="selectSchoolYear" path="selectSchoolYear"
						class="form-control custom-select-lg" aria-label="Please Select School Year">
						<%-- <form:option value="" label="Select" /> --%>
						<form:options items="${years}" />
					</form:select>
					<form:errors path="selectSchoolYear" cssClass="error" />
				</div>
				<div class="col-sm-2">
					<div class="col-form-label float-right font-weight-bold">Select Organization:</div>
				</div>
				<div class="col-sm-2">
					<form:select id="selectOrganization" path="selectOrganization"
						class="form-control custom-select-lg" aria-label="Please Select an Organization">
						<form:option value="1" label="All" /> 
						<form:options items="${organizationMap}" />
					</form:select>
					<form:errors path="selectOrganization" cssClass="error" /> 
				</div>
				<div class="col-sm-2"></div>
			</div>
		</sec:authorize>
		
		<sec:authorize access="hasRole('ROLE_MANAGER')">
			<div class="form-group row">
				<div class="col-sm-4"></div>
				<div class="col-sm-2">
					<div class="col-form-label float-right font-weight-bold">Select School Year:</div>
				</div>
				<div class="col-sm-2">
					<form:select id="selectSchoolYear" path="selectSchoolYear"
						class="form-control custom-select-lg" aria-label="Please Select School Year">
						<form:option value="0" label="All" />
						<form:options items="${years}" />
					</form:select>
					<form:errors path="schoolYear" cssClass="error" />
				</div>
				
				<div class="col-sm-4"></div>
			</div>
		</sec:authorize>
		<div class="form-group row">
			<div class="col-sm-2"></div>
			<div class="col-sm-3"></div>
			<div class="col-sm-2">
				<div style="margin: 50px">
					<input type="submit" value="Submit" name="generateReport" class="btn btn-primary" />
				</div>
			</div>
			<div class="col-sm-3"></div>
			<div class="col-sm-2"></div>
		</div>
	</form:form>
</div>
<div id="exportData" style="text-align: right; padding: 10px"  ></div>
		<table id="resultDatatable" class="table table-hover table-bordered table-striped">				
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
				<thead>
					<tr>
						<th title="Organization Name"  ><strong>Organization Name</strong></th>
						<th title="Practicum District"><strong>Practicum District</strong></th>
						<th title="Practicum School Name"><strong>Practicum School Name</strong></th>
						<th title="SchoolYear"><strong>School Year</strong></th>		
						<th title="Program Description"><strong>Program Description</strong></th>
						<th title="Candidate MEPID"><strong>Candidate MEPID</strong></th>
						<th title="Candidate First Name"><strong>Candidate First Name</strong></th>
						<th title="Candidate Last Name"><strong>Candidate Last Name</strong></th>
						<th title="Candidate Email Address"><strong>Candidate Email Address</strong></th>
						<th title="Supervising Practitioner Name"><strong>SP Name</strong></th>
						<th title="Supervising Practitioner Email Address"><strong>SP Email Address</strong></th>
						<th title="Supervising Practitioner MEPID"><strong>SP MEPID</strong></th>
						<th title="Program Supervisor Name"><strong>PS Name</strong></th>
						<th title="Program Supervisor  Email Address"><strong>PS Email Address</strong></th>	
						<th title="F1A4Q"><strong>F1A4Q</strong></th>
						<th title="F1A4S"><strong>F1A4S</strong></th>
						<th title="F1A4C"><strong>F1A4C</strong></th>
						<th title="F1B2Q"><strong>F1B2Q</strong></th>
						<th title="F1B2S"><strong>F1B2S</strong></th>
						<th title="F1B2C"><strong>F1B2C</strong></th>
						<th title="F2A3Q"><strong>F2A3Q</strong></th>
						<th title="F2A3S"><strong>F2A3S</strong></th>
						<th title="F2A3C"><strong>F2A3C</strong></th>
						<th title="F2B1Q"><strong>F2B1Q</strong></th>
						<th title="F2B1S"><strong>F2B1S</strong></th>
						<th title="F2B1C"><strong>F2B1C</strong></th>
						<th title="F2D2Q"><strong>F2D2Q</strong></th>
						<th title="F2D2S"><strong>F2D2S</strong></th>
						<th title="F2D2C"><strong>F2D2C</strong></th>
						<th title="F4A1Q"><strong>F4A1Q</strong></th>
						<th title="F4A1S"><strong>F4A1S</strong></th>
						<th title="F4A1C"><strong>F4A1C</strong></th>
						<th title="S1A4Q"><strong>S1A4Q</strong></th>
						<th title="S1A4S"><strong>S1A4S</strong></th>
						<th title="S1A4C"><strong>S1A4C</strong></th>		
						<th title="S1B2Q"><strong>S1B2Q</strong></th>
						<th title="S1B2S"><strong>S1B2S</strong></th>
						<th title="S1B2C"><strong>S1B2C</strong></th>
						<th title="S2A3Q"><strong>S2A3Q</strong></th>
						<th title="S2A3S"><strong>S2A3S</strong></th>
						<th title="S2A3Q"><strong>S2A3C</strong></th>
						<th title="S2B1Q"><strong>S2B1Q</strong></th>
						<th title="S2B1S"><strong>S2B1S</strong></th>
						<th title="S2B1C"><strong>S2B1C</strong></th>						
						<th title="S2D2Q"><strong>S2D2Q</strong></th>
						<th title="S2D2S"><strong>S2D2S</strong></th>
						<th title="S2D2C"><strong>S2D2C</strong></th>					
						<th title="S4A1Q"><strong>S4A1Q</strong></th>
						<th title="S4A1S"><strong>S4A1S</strong></th>
						<th title="S4A1C"><strong>S4A1C</strong></th>					
						<th title="Ready To Teach"><strong>Ready To Teach</strong></th>
						<th title="Data Source"><strong>Data Source</strong></th>						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reportInfoList}" var="reportInfo">
						<tr>
							<td>${reportInfo.orgName}</td>
							<td>${reportInfo.practicumDistrict}</td>						
							<td>${reportInfo.practicumSchoolName}</td>
							<td>${reportInfo.schoolYear}</td>
							<td>${reportInfo.programDescription}</td>
							<td>${reportInfo.candidateMEPID}</td>
							<td>${reportInfo.candidateFirstName}</td>						
							<td>${reportInfo.candidateLastName}</td>
							<td>${reportInfo.candidateEmailAddress}</td>
							<td>${reportInfo.spName}</td>
							<td>${reportInfo.spEmailAddress}</td>
							<td>${reportInfo.spMEPID}</td>
							<td>${reportInfo.psName}</td>
							<td>${reportInfo.psEmailAddress}</td>	
							<td>${reportInfo.elementF1A4Q}</td>
							<td>${reportInfo.elementF1A4S}</td>
							<td>${reportInfo.elementF1A4C}</td>
													
							<td>${reportInfo.elementF1B2Q}</td>
							<td>${reportInfo.elementF1B2S}</td>
							<td>${reportInfo.elementF1B2C}</td>
							
							<td>${reportInfo.elementF2A3Q}</td>
							<td>${reportInfo.elementF2A3S}</td>						
							<td>${reportInfo.elementF2A3C}</td>
							
							<td>${reportInfo.elementF2B1Q}</td>
							<td>${reportInfo.elementF2B1S}</td>
							<td>${reportInfo.elementF2B1C}</td>
							
							<td>${reportInfo.elementF2D2Q}</td>
							<td>${reportInfo.elementF2D2S}</td>								
							<td>${reportInfo.elementF2D2C}</td>
							
							<td>${reportInfo.elementF4A1Q}</td>						
							<td>${reportInfo.elementF4A1S}</td>
							<td>${reportInfo.elementF4A1C}</td>
							
							<td>${reportInfo.elementS1A4Q}</td>
							<td>${reportInfo.elementS1A4S}</td>
							<td>${reportInfo.elementS1A4C}</td>	
												
							<td>${reportInfo.elementS1B2Q}</td>
							<td>${reportInfo.elementS1B2S}</td>
							<td>${reportInfo.elementS1B2C}</td>
							
							<td>${reportInfo.elementS2A3Q}</td>
							<td>${reportInfo.elementS2A3S}</td>
							<td>${reportInfo.elementS2A3C}</td>	
							
							<td>${reportInfo.elementS2B1Q}</td>
							<td>${reportInfo.elementS2B1S}</td>
							<td>${reportInfo.elementS2B1C}</td>		
											
							<td>${reportInfo.elementS2D2Q}</td>
							<td>${reportInfo.elementS2D2S}</td>
							<td>${reportInfo.elementS2D2C}</td>
							
							<td>${reportInfo.elementS4A1Q}</td>
							<td>${reportInfo.elementS4A1S}</td>						
							<td>${reportInfo.elementS4A1C}</td>
							
						    <td>${reportInfo.readyToTeach}</td>						
							<td>${reportInfo.dataSource}</td>		
						</tr>
					</c:forEach>
				</tbody>
			</sec:authorize>						
		</table>
			<p>&nbsp</p>
			<p>
				<b>Footnote(s):</b>
				<ul>
					<li>This Report only shows completed Cycles.</li>
				</ul>	 
			</p>
</div>