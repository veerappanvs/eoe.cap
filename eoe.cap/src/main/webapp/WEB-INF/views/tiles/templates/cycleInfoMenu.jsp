
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authentication var="user" property="principal" />



<ul class="list-unstyled components">
	<li id="home"><a href="/cap">Home</a></li>
	<li><a href="/cap/announcement">Announcements</a></li>
	<sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_SUPERVISOR')">
		<li id="startCycle"><a href="/cap/candidate">Start Cycle</a></li>
	</sec:authorize>

	<sec:authorize
		access="hasAnyRole('ROLE_CANDIDATE','ROLE_SUPERVISOR','ROLE_PRACTITIONER','ROLE_MANAGER')">
		<li><a data-toggle="collapse" aria-expanded="true"			href="#cycleSubMenu">Cycle Steps</a>
			<ul class="collapse list-unstyled show" id="cycleSubMenu">
				<li><a id="editCycle" href="/cap/cycle?cycleId=${cycleId}">CAP Form</a></li>
				
				<sec:authorize access="hasAnyRole('ROLE_CANDIDATE','ROLE_SUPERVISOR','ROLE_MANAGER')">
					<li><a id="summativeAssessment"	href="/cap/selfassessmentrubric?cycleId=${cycleId}">Rubric Summary</a></li>
				</sec:authorize>
				
				<li><a id="selfAssessment" href="/cap/selfassessment?cycleId=${cycleId}">Self-Assessment</a></li>
				
				<li><a id="finalizedGoal" href="/cap/goal?cycleId=${cycleId}">Finalized Goal and Implementation Plan</a></li>
				<c:forEach items="${observationsLinks}" var="link">
					<li><a
						id="${link.observationNumber}${link.observationTypeCode}"
						href="/cap/observation?cycleId=${link.cycleId}&amp;observationNumber=${link.observationNumber}&amp;typeCode=${link.observationTypeCode}">${link.typeDesc}
							${link.observationNumber}</a></li>
				</c:forEach>
				
				<li><a id="threewaaymeeting" href="/cap/threewaymeeting?cycleId=${cycleId}">Three-Way Meetings</a></li>
				
				<c:if test="${displayFormative}">
					<li><a id="formativeAssessment"	href="/cap/formative?cycleId=${cycleId}&amp;typeCode=2">Formative Assessment</a></li>
				</c:if>
				
				<c:if test="${displaySummative}">
					<li><a id="summativeAssessment"	href="/cap/summative?cycleId=${cycleId}&amp;typeCode=3">Summative Assessment</a></li>
				</c:if>
				
						
				<li><a id="uploadFile" href="/cap/tagfile?cycleId=${cycleId}">Upload Evidence Files</a></li>
				
				<sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_ADMIN', 'ROLE_SUPERVISOR')">
					<li><a href="/cap/correspondence/forcycle?cycleId=${cycleId}">Correspondence History</a></li>
				</sec:authorize>
	
			</ul></li>
			
	</sec:authorize>
	
	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
		<li><a data-toggle="collapse" aria-expanded="true"			href="#cycleSubMenu">Cycle Steps</a>
			<ul class="collapse list-unstyled show" id="cycleSubMenu">
				<li><a id="editCycle" href="/cap/cycle?cycleId=${cycleId}">CAP Form</a></li>
				<li><a href="/cap/correspondence/forcycle?cycleId=${cycleId}">Correspondence History</a></li>
			</ul>
		</li>
	</sec:authorize>
	
	
	<sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')">
		<li><a data-toggle="collapse" aria-expanded="true" 	href="#reportsSubMenu" id="reportsMainMenu" >Reports</a>
			<ul class="collapse list-unstyled" id="reportsSubMenu" >
			<sec:authorize access="hasAnyRole('ROLE_MANAGER')"><li><a href="/cap/batchload" >Upload Cycles</a></li></sec:authorize>
			<li><a href="/cap/verifycycle">Verify Cycle  Information</a></li>
			<li><a href="/cap/reports">Annual Reports</a></li>
			<sec:authorize access="hasAnyRole('ROLE_MANAGER')"><li><a href="/cap/importedCapList">Imported Cycles</a></li></sec:authorize>
			</ul>
		</li>
	</sec:authorize>
	

	
	<li><a href="http://www.doe.mass.edu/edprep/cap/onlineplatform.html">FAQ</a></li>
	<li><a href="/cap/logout">Log Out</a></li>
</ul>
