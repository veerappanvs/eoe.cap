<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div style="margin-bottom: 25px" class="error">
	<form:errors path="*" />
</div>
<div id="postScript"></div>
<form:form id="practitionerForm" method="POST"
	commandName="verifyCycleCandidateEnrollment">
	<div style="margin-bottom: 25px" class="error">
		<form:errors path="" />
	</div>
	<div id="practitionerInfo" >
	<jsp:include page="verifyPractitioner.jsp"></jsp:include>
	</div>
</form:form>