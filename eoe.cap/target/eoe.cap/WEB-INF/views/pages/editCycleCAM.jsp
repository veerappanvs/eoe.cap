<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<script type="text/javascript">

function setScrollPosition(){
	$('#scrollX').val(window.pageXOffset);
	$('#scrollY').val(window.pageYOffset);
}

function backToSo(orgCode){
	 $.get('resendemail?communicationId='+resendEmailid+'&cycleId='+cycleId, 
	 function( data ) {
		 location.reload();		  
		}
	 );
}


	function pageLoad() {
		window.scroll(${scrollX!=null?scrollX:0},${scrollY!=null?scrollY:0});
		alertUser();
		
		if(${capCycleInfo.reOpenErrorMessage != null}  ){
					$('#reOpenErrorModal').modal('show');

		}		


		$('#reOpenRadioYes').change(function() {
			  $('#reOpen').prop('disabled', false);
			});
		$('#reOpenRadioNo').change(function() {
			  $('#reOpen').prop('disabled', true);
			});
	}
	
	function assignSupervisor(supervisorName, supervisorId, userId) {
		$('#supervisordaPersonId').val(supervisorId);
		$('#supervisorName').text(supervisorName);
		$('#supervisorId').text(userId);
		$('#supervisorPersonId').val(supervisorId);
		
		path = '/cap/cycle/saveSupervisor?cycleId=${cycleId}&supervisorId='+ supervisorId;
		
		$.ajax({
			type : "GET",
			url : path,
			async : false,
			success : function(response) {
			}
		});

	}

	function loadProgramSupervisorModal(method, process, requestParams) {
		var parameters = [];

		if (requestParams.length > 0) {
			var processElements = requestParams.split(',');

			for (idx in processElements) {
				parameter = {
					name : processElements[idx].split('=')[0],
					value : processElements[idx].split('=')[1]
				}
				parameters.push(parameter);
			}
		}

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
			queryString = '&' + queryString

		path = '/cap/cycle/' + method + '?cycleId=' + ${cycleId	}
		+queryString;

		$.ajax({
			type : "GET",
			url : path,
			async : false,
			success : function(response) {

				result = $.parseHTML(response);

				$('#programSupervisor-section').text("");
				$('#programSupervisor-section').append(result[5]);
				$('#supervisorResult').dataTable();
			}
		});
	}

	function loadPractitionerModal(method, process, requestParams) {

		var parameters = [];

		if (requestParams.length > 0) {
			var processElements = requestParams.split(',');

			for (idx in processElements) {
				parameter = {
					name : processElements[idx].split('=')[0],
					value : processElements[idx].split('=')[1]
				}
				parameters.push(parameter);
			}

		}

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
			queryString = '&' + queryString

		path = '/cap/cycle/' + method + '?cycleId=' + ${cycleId}+queryString;

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

				postScript = $(response.responseText)[2].innerText;

				if (postScript.length != 0) {
					eval(postScript);
				}
			}

		});

	}

	function showEndingReasonDiv() {
		document.getElementById('wantToEndDiv').style.display = "block";
	}
</script>

<div  style="max-width:1366px">

	<div id="reOpenErrorModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered modal-sm" style="min-width: 400px">
			<div class="modal-content">
				<div class="modal-header">	
						<h5 class="modal-title">Error!<h5>
				</div>
				<div class="modal-body">
					<span class="glyphicon glyphicon-warning-sign"></span> ${capCycleInfo.reOpenErrorMessage}
				</div>
				<div class="modal-footer">					 
					<button class="btn btn-primary"  align="center" data-dismiss="modal">Ok</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

	<form:form id="form2" method="POST" commandName="capCycleInfo"
		action="/cap/cycle">

		<jsp:include page="capViewInfo.jsp"></jsp:include>
				

		<div class="line" style="margin: 10px 0; color: black"></div>


		<div style="text-align: center; margin: 40px">
			<span style="font-size: 1.3rem; font-weight: bold">Teacher
				Candidate Information</span>
		</div>

		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Name:</div>
			</div>
			<div class="col-sm-2">
				<div class="col-form-label">${capCycleInfo.candidateFirstName}
					${capCycleInfo.candidateLastName}</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label  float-right font-weight-bold">MEPID:</div>
			</div>
			<div class="col-sm-4">
				<div class="col-form-label">${capCycleInfo.candidateMEPID}</div>
			</div>

		</div>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div style="text-align: center; margin: 40px">
			<span style="font-size: 1.3rem; font-weight: bold">Program
				Supervisor Information</span>
		</div>

		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right  font-weight-bold">Name:</div>
			</div>
			<div class="col-sm-2">
				<div id="supervisorName" class="col-form-label">${capCycleInfo.supervisor.firstName}
					${capCycleInfo.supervisor.lastName}</div> <input id="supervisorPersonId"
					type="hidden" value="${capCycleInfo.supervisor.daPersonId}"
					name="supervisor.daPersonId">
			</div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">UserId:&nbsp;</div>
			</div>
			<div class="col-sm-4">
				<div id="supervisorId" class="col-form-label">${capCycleInfo.supervisor.userId}</div>
			</div></div>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div style="text-align: center; margin: 40px">
			<span style="font-size: 1.3rem; font-weight: bold">Supervising
				Practitioner Information</span>
		</div>

		<div id="practionerList">
			<div class="form-group row">
				<div class="col-sm-12">
					<table class="table white-background">
						<thead>
							<tr>
								<th>Name</th>
								<th>MEPID</th>
								<th>Practicum School</th>							
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${capCycleInfo.practitioners }"
								var="practitioner" varStatus="status">
								<tr>
									<td>${practitioner.firstName} ${practitioner.lastName}</td>
									<td>${practitioner.mepid}</td>
									<td>${practitioner.districtName}:${practitioner.schoolName}</td>								
								</tr>
							</c:forEach>
							
						</tbody>

					</table>
				</div>
			</div>


		</div>


		<div class="line" style="margin: 10px 0; color: black"></div>


		<div style="text-align: center; margin: 40px">
			<span style="font-size: 1.3rem; font-weight: bold">Cycle
				Status</span>
		</div>
	
		<div class="form-group row">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<div class="col-form-label "><b>Status:&nbsp;</b>
					<c:if test="${empty capCycleInfo.cycleStatus &&  empty capCycleInfo.reOpenDate}"> Open</c:if>
					<c:if test="${empty capCycleInfo.cycleStatus && not empty capCycleInfo.reOpenDate}"> Reopened on: <fmt:formatDate pattern="MM/dd/yyyy" value="${capCycleInfo.reOpenDate}" /></c:if>
		
					<c:if test="${not empty capCycleInfo.cycleStatus}"> 
						<c:forEach items="${statusReasonTypes}" var="entry">
							<c:if test="${entry.key == capCycleInfo.cycleStatus}">
								<c:out value="${entry['value']}" />
							</c:if>
						</c:forEach>
					
					</c:if>
				</div>
			</div>
		</div>
		
		<c:if test="${empty capCycleInfo.cycleStatus}">
		<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-10">
					<span style="font-size: 1rem; font-weight: bold">Incomplete Assessments and/or Observations:</span>
				</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<c:forEach var="iws" items="${fn:split(capCycleInfo.incompleteWorksString, ',')}">
						${iws}</br>
				</c:forEach>
			</div>
		</div>
		</c:if>
		
 		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<c:if test="${allowCycleReOpen}">
				<!-- This option is only available to CAP ADMIN if reOpenDate field is null -->
					<div class="form-group row">
						<div class="col-sm-2"></div>
						<div class="col-sm-3">
							<div class="col-form-label font-weight-bold">Do you want to reopen the cycle?</div>&nbsp;&nbsp;
							<form:radiobutton path = "reOpenCycleFlag" name="reOpenRadioYes" id="reOpenRadioYes" 	value = "Y"  aria-label="Yes"/>&nbsp;<b>Yes</b>&nbsp;&nbsp;
							<form:radiobutton path = "reOpenCycleFlag" name="reOpenRadioNo" id="reOpenRadioNo" value = "N" checked="true" aria-label="No"/>&nbsp;<b>No</b>
						</div>	
						<div class="col-sm-5"></div>
						<div class="col-sm-2"></div>
					</div>
			</c:if>
		</sec:authorize>  
		
		<div class="modal fade" id="practionerListModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Assign
							Supervising Practitioner</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div id="error-section-modal" style="margin-bottom: 25px"
							class="error"></div>
						<div id="practitioner-section"></div>
					</div>

				</div>
			</div>
		</div>

		<div class="modal fade" id="programSupervisorListModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-lg"
				role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Re-assign
							Program Supervisor</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div id="error-section-modal" style="margin-bottom: 25px"
							class="error"></div>
						<div id="programSupervisor-section"></div>
					</div>
				</div>
			</div>
		</div>
		<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_SUPERVISOR')">
			<div class="form-group row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4">
					<div style="margin: 50px">
						<input type="submit" value="Cancel" name="Cancel"
							class="btn btn-primary" /> <input type="submit" value="Save"
							name="endCycle" class="btn btn-primary" />
					</div>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</sec:authorize>
		
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			
			<c:if test="${not allowCycleReOpen && not empty capCycleInfo.cycleStatus  }">
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-10">
					<div style="color:red"><b>Note :<br>1.	Auto-closed cycle cannot be re-opened.<br>
						2.	Cycles closed with a status of "Completed CAP" in the previous school years cannot be re-opened. &nbsp;</b>
					</div>
				</div>
			</div>				
			</c:if>		
			<c:if test="${allowCycleReOpen}">
				<div class="form-group row">
					<div class="col-sm-4"></div>
					<div class="col-sm-4">
						<div style="margin: 50px">
							
								<input type="hidden" value="Reopen" name="reOpenCycle" class="btn btn-primary" />						
								<input type="submit" class="btn btn-primary"  id="reOpen"  	value="Re-Open" disabled /> 
							
							<input type="button" class="btn btn-primary"   onclick="location.href='/cap/listcamcycles?orgCode=${capCycleInfo.orgCode}';"  	value="Back" /> 
						</div>
					</div>
					<div class="col-sm-4"></div>
				</div>
			</c:if>
			
		</sec:authorize>
		<c:if test="${not allowCycleReOpen}">
			<div class="form-group row">
			<div class="col-sm-12" style="text-align: center;">
				<input type="button" class="btn btn-primary"   onclick="location.href='/cap/listcamcycles?orgCode=${capCycleInfo.orgCode}';"  	value="Back" /> 
			</div>
			</div>
		</c:if>
		
		<input type="hidden" name="cycleId" value="${cycleId}">
		<input type="hidden" name="scrollX" id="scrollX" value="0" />
		<input type="hidden" name="scrollY" id="scrollY" value="0" />
	</form:form>
</div>