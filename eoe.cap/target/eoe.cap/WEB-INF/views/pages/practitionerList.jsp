<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EOE Spring Sample</title>
<body>
	<form:form id="form1" method="POST"   commandName="${commandName}">
		<form:errors path="practitioner.districtOrgTypeId" cssClass="error" id="practitioner.districtOrgTypeId" />
		<form:errors path="practitioner.districtOrgId" cssClass="error"  id="practitioner.districtOrgId" />
		<form:errors path="practitionerInputMEPID" cssClass="error" id="practitionerInputMEPID" />


		<c:if test="${practitioners!=null}">
		<table id="resultDatatable"
			class="table table-hover table-bordered table-striped">
			<thead>
				<tr>
					<th><strong>Name</strong></th>
					<th><strong>School Name</strong></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${practitioners}" var="supervisor">
					<tr>
						<td><a href="#"
							onclick="assignSupervisor(${supervisor.mepid},${supervisor.schoolOrgId})">${supervisor.name}</a></td>
						<td>${supervisor.schoolName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>
	</form:form>
</body>
</html>
