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

<title></title>
<tiles:putAttribute name="title" value="Teacher Candidate Search" />
</head>
<body>
	<form:form id="form2" method="POST" commandName="candidateEnrollment">
		<div id="error-section" style="margin-bottom: 25px" class="error">
			<form:errors path="*" />
		</div>

			<div style="text-align: center; padding: 10px">
				<h5>Teacher Candidate Information</h5>
			</div>
			
			<div style="text-align: center; padding: 10px">
				<b>Note:</b> Review the Teacher Candidate and CAP cycle information below. To start the Cycle press 'Start Cycle'. 
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
					<div class="col-sm-6"></div>
				</div>
			</div>

			<div class="line" style="margin: 10px 0; color: black"></div>




			<div class="form-group row">
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Program
						Supervisor:</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label">${candidateEnrollment.supervisorName}</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Supervising
						Practitioner:</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label">${candidateEnrollment.practitioner.firstName} ${candidateEnrollment.practitioner.lastName}</div>
				</div>
			</div>


			<div class="form-group row">
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Practicum
						District:</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label">${candidateEnrollment.practitioner.districtName}</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">Practicum School:</div>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label">${candidateEnrollment.practitioner.schoolName}</div>
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
				<div>
					<div>
						<div style="text-align: right; margin: 50px">
							<input type="hidden" value="4" name="_page" /> <input
								type="submit" value="Back" name="_command"
								class="btn btn-primary" /> <input type="submit" value="Cancel"
								name="_command" class="btn btn-primary" /> <input type="submit"
								value="Start Cycle" name="_command" class="btn btn-primary" />
						</div>
					</div>

				</div>

			
	</form:form>
</body>
</html>
