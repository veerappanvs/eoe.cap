<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Supervising practitioner</title>
<body>
	<form:form id="practitionerForm" method="POST"
		commandName="candidateEnrollment">

		<form:errors path="practitioner.districtOrgTypeId" cssClass="error"
			id="practitioner.districtOrgTypeId" />
		<form:errors path="practitioner.districtOrgId" cssClass="error"
			id="practitioner.districtOrgId" />
		<form:errors path="practitioner.schoolOrgId" cssClass="error"
			id="practitioner.schoolOrgId" />
		<form:errors path="practitionerInputMEPID" cssClass="error"
			id="practitionerInputMEPID" />


		<div id="practitionerInfo">

			<c:if
				test="${not empty noPractitionerFound and noPractitionerFound }">
				<div class="form-group row ">
					<div class="col-sm-12">
						<table id="resultDatatable"
							class="table table-hover table-bordered table-striped">
							<tbody>
								<tr>
									<td colspan="2">There are no results matching your search
										criteria.</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</c:if>



			<c:if
				test="${not empty noPractitionerFound and !noPractitionerFound }">

				<div class="form-group row">
					<div class="col-sm-3"></div>
					<div class="col-sm-3">
						<div class="col-form-label float-right font-weight-bold">Supervising
							Practitioner:</div>
					</div>
					<div class="col-sm-3">
						<div class="col-form-label">${supervisorInfo.firstName}
							${supervisorInfo.lastName}</div>
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
						<div class="col-form-label">${supervisorInfo.districtName}</div>
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
						<div class="col-form-label">${supervisorInfo.schoolName}</div>
					</div>
					<div class="col-sm-3"></div>
				</div>


				<c:if test="${!setEmail}">
					<div class="form-group row">
						<div class="col-sm-3"></div>
						<div class="col-sm-3">
							<div class="col-form-label float-right font-weight-bold">Email:</div>
						</div>
						<div class="col-sm-3">
							<form:input id="practitioner.email" path="practitioner.email"
								cssClass="form-control" />
							<form:errors path="practitioner.email" cssClass="error" />
						</div>
						<div class="col-sm-3"></div>
					</div>

				</c:if>

				<c:if test="${setEmail}">
					<div class="form-group row">
						<div class="col-sm-1"></div>
						<div class="col-sm-2">
							<div class="col-form-label float-right font-weight-bold">Email:</div>
						</div>
						<div class="col-sm-3">
							<form:input id="practitioner.email" path="practitioner.email"
								cssClass="form-control" />
							<form:errors path="practitioner.email" cssClass="error" />
						</div>
						<div class="col-sm-2">
							<div class="col-form-label">Confirm Email</div>
						</div>
						<div class="col-sm-3">
							<form:input id="practitioner.confirmEmail"
								path="practitioner.confirmEmail" cssClass="form-control" />
							<form:errors path="practitioner.confirmEmail" cssClass="error" />
						</div>
						<div class="col-sm-1"></div>
					</div>
				</c:if>
			</c:if>
		</div>

	</form:form>

</body>
</html>