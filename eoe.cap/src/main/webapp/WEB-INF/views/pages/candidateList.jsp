<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EOE Spring Sample</title>
<body>
	<form:form id="form1" method="POST" commandName="candidateEnrollment">
		<form:errors path="*" cssClass="error" />

		<c:if test="${candidates!=null}">
			<table id="resultDatatable"
				class="table table-hover table-bordered table-striped">
				<thead>
					<tr>
						<th><strong>Teacher Candidate</strong></th>
						<th><strong>MEPID</strong></th>
						<th><strong>Program Name</strong></th>
						<th><strong>Program Status</strong></th>
					</tr>
				</thead>
				<tbody>



					<c:forEach items="${candidates}" var="candidate">
						<c:choose>
							<c:when test="${candidate.cycleId > 0 || candidate.fileUploadId > 0 }">
								<tr style="background-color:#e59393; color:black">
									<td ><a href="#"
										onclick="showCycle(${candidate.programCompleterId}, ${candidate.cycleId}, ${candidate.fileUploadId})">${candidate.candidateName}</a></td>
									<td>${candidate.mepid}</td>
									<td>${candidate.programName}</td>
									<td>${candidate.programStatus}</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><a href="#"
										onclick="startCycle(${candidate.programCompleterId})">${candidate.candidateName}</a></td>
									<td>${candidate.mepid}</td>
									<td>${candidate.programName}</td>
									<td>${candidate.programStatus}</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</form:form>
</body>
</html>