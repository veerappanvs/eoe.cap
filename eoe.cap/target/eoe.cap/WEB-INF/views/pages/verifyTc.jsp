<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


	<form:form id="form1" method="POST" commandName="verifyCycleCandidateEnrollment">
			<form:errors path="*" cssClass="error" />

			<div id="notes" class="form-group row">
				<div class="col-sm-1"></div>
				<div class="col-sm-8">
					<span style="font-size: .9rem;"><b>Note:</b> 
					<ul>
					<li>Please ensure that the program you are looking for is a valid program in Early ID.</li>
					<li>Please ensure that the Teacher Candidates are enrolled in the selected program in Early ID.</li>
					<li>Please contact the Teacher Candidate to verify their name and MEPID in ELAR, if there are no matching results.</li>
					<li>To ensure accuracy of data, you may export the TC search results and use the information as it is to fill the upload spreadsheet.</li>
					<li>Please review the FAQs for more information.</li>
					</ul>
					</span>
					<p>&nbsp</p>
				</div>
				<div class="col-sm-1"></div>
			</div>


			<div class="form-group row"> 
				<div class="col-sm-1">
					</div>    
				<div class="col-sm-2 col-form-label float-right font-weight-bold">Search By Program :</div>
				<div class="col-sm-6">
					<form:select id="selectProgramId" path="selectProgramId"
						 class="form-control form-control-sm" aria-label="Please select a program" >
						 <form:option value="">Select</form:option>
					</form:select>
					<form:errors path="selectProgramId"  />
				</div>
				<div class="col-sm-3">
				</div>
			</div>
			<div class="form-group row"> 
				<div class="col-sm-1" ></div>

				<div class="col-sm-3 col-form-label font-weight-bold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;or</div>
				<div class="col-sm-8" ></div>
			</div>

			
			<div class="form-group row"> 
				<div class="col-sm-1">
					</div>  
				<div class="col-sm-2  col-form-label float-right font-weight-bold">Search By MEPID :</div>
				<div class="col-sm-6">
					<form:input id="inputmepid" path="inputmepid" class="form-control form-control-sm"  maxlength="8" aria-label="Please enter a MEPID" />
				</div>

				<div class="col-sm-3">
				</div>
			</div>
			
			
			
			
		<form:errors path="*" cssClass="error" />

<div id="exportTcData" style="text-align: right; padding: 10px"></div>	
				<c:if test="${candidates!=null}">	
					
					<table id="resultTcDatatable"		class="table table-hover table-bordered table-striped">
						<thead>
							<tr>
								<th style="display:none;"><strong>Organization</strong></th>
								<th style="display:none;"><strong>School Year</strong></th>
								<th><strong>Program</strong></th>
								<th><strong>Candidate MEPID</strong></th>
								<th><strong>Candidate First Name</strong></th>
								<th><strong>Candidate Last Name</strong></th>
								<th><strong>Candidate E-Mail Address</strong></th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${candidates}" var="candidate">
								<tr>
									<td style="display:none;">${candidate.orgName}</td>
									<td style="display:none;">${candidate.year}</td>
									<td>${candidate.programName}</td>
									<td>${candidate.mepid}</td>
									<td>${candidate.firstName}</td>
									<td>${candidate.lastName}</td>
									<td>${candidate.email}</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>


	</form:form>
