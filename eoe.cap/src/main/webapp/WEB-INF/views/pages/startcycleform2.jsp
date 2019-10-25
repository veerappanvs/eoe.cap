<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	var schoolYear = '${candidateEnrollment.schoolYear}';

	function pageLoad() {
		$('#cycleStartDate').datepicker({});
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
						<div class="col-form-label">${candidateEnrollment.supervisorName}</div>
					</div>
				</div>

			</div>
			<div>

				<div class="line" style="margin: 10px 0; color: black"></div>

				<div style="text-align: center; padding: 10px">
					<h5>Cycle Information</h5>
				</div>
				<div>
					<form>
					<div style="text-align: center; padding: 10px">
						<span style="font-size: 1rem;"><b>Note:</b> Enter the Cycle Start Date and then click "Next" to proceed to the next step.</span>
					</div>
					<p>&nbsp</p>
					
					<div class="form-group row">
					<div class="col-sm-3"></div>
						<div class="col-sm-3">
							<div class="col-form-label float-right font-weight-bold">Cycle Start
								Date*:</div>
						</div>
						<div class="col-sm-3">
							<form:input id="cycleStartDate" path="cycleStartDate"
								class="form-control " style="width: 200px"
								placeholder="mm/dd/yyyy" aria-label="Please enter the cycle start date"/>
							<form:errors path="cycleStartDate" cssClass="error" />
						</div>	
						<div class="col-sm-3"></div>					
					</div>
					<div>
							<div style="text-align: right; margin: 50px">
								<input type="hidden" value="1" name="_page" /> 
								<input type="submit" value="Back" name="_command" class="btn btn-primary" /> 								
								<input type="submit" value="Cancel" name="_command"class="btn btn-primary" />
								<input type="submit" value="Next" name="_command" class="btn btn-primary" /> 
							</div>
						</div>

					</form>
				</div>
			</div>
			<p>&nbsp</p>
			<p>
				<b>Footnote:</b>
				<ul>
					<li>The assigned Program Supervisor defaults to the person creating the Cycle. You will be able to confirm or change this information on the next page.</li>
			
				</ul>	
			</p>
			<div class="line" style="margin: 10px 0; color: black"></div>



	</form:form>

</body>
</html>
