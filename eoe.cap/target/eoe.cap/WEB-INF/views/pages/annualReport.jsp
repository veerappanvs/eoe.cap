
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>

function pageLoad() {
	
}
	
</script>
<title></title>
</head>
<body>
	<div id="error-section" style="margin-bottom:25px" ></div>
	<div style="text-align: center; padding: 10px">
		<span style="font-size: 1.3rem; font-weight: bold">CAP Cycle Report</span>
	</div>
	<div>&nbsp;</div>
	
	<form:form id="form1"  method="POST" commandName="cycleReportInfo" action="/cap/reports/getReport">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-2">
					<div class="col-form-label float-right font-weight-bold">Select School Year:</div>
				</div>
				<div class="col-sm-2">
					<form:select id="selectSchoolYear" path="selectSchoolYear"
						class="form-control custom-select-lg" aria-label="Please Select School Year">
						<form:option value="0" label="All" /> 
						<form:options items="${schoolYearMap}" />
					</form:select>
					<form:errors path="selectSchoolYear" cssClass="error" />
				</div>
				<div class="col-sm-2">
					<div class="col-form-label float-right font-weight-bold">Select Organization:</div>
				</div>
				<div class="col-sm-2">
					<form:select id="selectOrganization" path="selectOrganization"
						class="form-control custom-select-lg" aria-label="Please Select an Organization">
						<form:option value="1" label="All" /> 
						<form:options items="${organizationMap}" />
					</form:select>
					<form:errors path="selectOrganization" cssClass="error" /> 
				</div>
				<div class="col-sm-2"></div>
			</div>
		</sec:authorize>
		
		<sec:authorize access="hasRole('ROLE_MANAGER')">
			<div class="form-group row">
				<div class="col-sm-4"></div>
				<div class="col-sm-2">
					<div class="col-form-label float-right font-weight-bold">Select School Year:</div>
				</div>
				<div class="col-sm-2">
					<form:select id="selectSchoolYear" path="selectSchoolYear"
						class="form-control custom-select-lg" aria-label="Please Select School Year">
						<form:option value="" label="Select" /> 
						<form:options items="${schoolYearMap}" />
					</form:select>
					<form:errors path="schoolYear" cssClass="error" />
				</div>
				
				<div class="col-sm-4"></div>
			</div>
		</sec:authorize>
		<div class="form-group row">
			<div class="col-sm-2"></div>
			<div class="col-sm-3"></div>
			<div class="col-sm-2">
				<div style="margin: 50px">
					<input type="submit" value="Submit" name="generateReport" class="btn btn-primary" />
				</div>
			</div>
			<div class="col-sm-3"></div>
			<div class="col-sm-2"></div>
		</div>
	</form:form>
</body>
</html>