
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	function assignSupervisor(supervisorName,supervisorId) {
		$('#supervisordaPersonId').val(supervisorId);
		$('#supervisorName').text(supervisorName);
	}
	
	
	function pageLoad() {
				$('#findsupervisors')
						.click(
								function() {
									jQuery.support.cors = true;
									$.ajax({
												type : "GET",
												url : '/cap/candidate/supervisor',
												async : false,
												success : function(
														response) {

													result = $
															.parseHTML(response);
													$('#supervisorsList')
															.text("");
													$('#supervisorsList')
															.append(
																	result[5]);
													$(
															'#supervisorResult')
															.dataTable();

												},
												error : function(msg,
														url, line) {
													$('#supervisorsList')
															.text(
																	'Unknown Exception');
												}
											});
								});
			}
	
	
	
</script>
<title></title>
<tiles:putAttribute name="title" value="Teacher Candidate Search" />
</head>
<body>
	<form:form id="form2" method="POST" commandName="candidateEnrollment">

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
						<form:hidden id="supervisordaPersonId"  path="supervisordaPersonId"   />
						<a href="#" id="findsupervisors" class="badge badge-primary">Change</a> 
					</div>
				</div>
					
				<div class="form-group row">					
					<div class="col-sm-3">
						<div class="col-form-label float-right font-weight-bold">Cycle Start Date:</div>
					</div>
					<div class="col-sm-3">
						<div class="col-form-label"><fmt:formatDate pattern="MM/dd/yyyy" value="${candidateEnrollment.cycleStartDate}" /></div>
					</div>
					<div class="col-sm-3">
					</div>
					<div class="col-sm-3">
					</div>
				</div>

			</div>
			<div>


				<div class="line" style="margin: 10px 0; color: black"></div>

				<div style="text-align: center; padding: 10px">
					<h5>Assign Program Supervisor</h5>
				</div>
				<div id="notes" class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<span style="font-size: .9rem;"><b>Note:</b> 
					To confirm the Program Supervisor listed above, click "Assign" and proceed to the next step. To assign a different Program Supervisor, click "Change" next to the Program Supervisor name.  All of the Program Supervisors in your Organization will be listed below.  Select the correct Program Supervisor and click "Assign" to proceed to the next step.  If a Program Supervisor is not listed, see the FAQs page for instructions on assigning this permission.
					<p>&nbsp</p>
				</div>
				<div class="col-sm-2"></div>
				</div>

				<div>
					<div  id="supervisorsList" style="margin-left:auto;margin-right:auto;width:75%" ></div>
				
					<form>						
						<div>
							<div style="text-align: right; margin: 50px">
								<input type="hidden" value="2" name="_page" /> 
								<input type="submit" value="Back" name="_command" class="btn btn-primary" /> 								
								<input type="submit" value="Cancel" name="_command"class="btn btn-primary" />
								<input type="submit" value="Assign" name="_command" class="btn btn-primary" /> 
							</div>
						</div>
					</form>

				</div>
			</div>
			<div class="line" style="margin: 10px 0; color: black"></div>


	</form:form>

</body>
</html>