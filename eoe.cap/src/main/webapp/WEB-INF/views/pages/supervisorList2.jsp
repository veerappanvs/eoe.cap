<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EOE Spring Sample</title>
<body>
<form:form id="programSupervisorForm" method="POST" commandName="capCycleInfo">
<div class="form-group row ">
	<div class="col-sm-12">
	<table id="supervisorResult" class="table table-hover table-bordered table-striped"> 
		<thead>
			<tr>
				<th><strong>Program Supervisor</strong></th>
				<th><strong>User Id</strong></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${supervisors}" var="supervisor">
				<tr>
					<td><a href="#" onclick="assignSupervisor('<spring:escapeBody javaScriptEscape="true" >${supervisor.name}</spring:escapeBody>','${supervisor.daPersonId}','<spring:escapeBody javaScriptEscape="true" >${supervisor.userId}</spring:escapeBody>');$('#programSupervisorListModal').modal('hide');return false;">${supervisor.name}</a></td>
					<td>${supervisor.userId}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>	
</div>
</form:form>	
</body>
</html>