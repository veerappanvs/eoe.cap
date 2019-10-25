<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div style="text-align: center; padding: 10px">
			<span style="font-size: 1.3rem; font-weight: bold">CAP Form</span></br>			
		</div>


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10" >
				<div style="text-align: center;" ><b>Note: </b>To be completed by the Program
					Supervisor and Teacher Candidate.</div>
			</div>
			<div class="col-sm-1"></div>
		</div>

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-3" style="padding-right: 0px;">
				<div class="font-weight-bold" style="text-align: right">Program: </div>
			</div>
			<div class="col-sm-7" style="padding-left:0">
				<div  style="text-align:left">${capCycleInfo.programName}</div>
			</div>
			<div class="col-sm-1"></div>
		</div>


		<div class="form-group row">
		<div class="col-sm-1"></div>
			<div class="col-sm-3" style="padding-right: 0px;">
				<div class="col-form-label  font-weight-bold"  style="text-align: right;" >Practicum/Equivalent Course Number*:</div>
			</div>
			<div class="col-sm-2">
				<form:input class="form-control " path="courseNumber" aria-label="Please enter Practicum Course Number" maxlength="255" />
				<form:errors path="courseNumber" cssClass="error" />
			</div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold" style="text-align: right;"  >Credit Hours*:</div>
			</div>
			<div class="col-sm-2">
				<form:input class="form-control" path="creditHours" aria-label="Please enter number of Credit Hours" maxlength="10" />
				<form:errors path="creditHours" cssClass="error" />
			</div>
			<div class="col-sm-1"></div>
		</div>


		<div class="form-group row">
		<div class="col-sm-1"></div>
			<div class="col-sm-3" style="padding-right: 0px;">
				<div class="font-weight-bold"
					style="text-align: right">Practicum/Equivalent Seminar Course
					Title*:</div>
			</div>
			<div class="col-sm-2">
				<form:input class="form-control " path="courseTitle"
					aria-label="Please enter Practicum Course Title" maxlength="255" />
				<form:errors path="courseTitle" cssClass="error" />
			</div>
			<div class="col-sm-3" style="padding-right: 0px;">
				<div class="col-form-label float-right font-weight-bold">Grade
					level of Practicum Students*:</div>
			</div>
			<div class="col-sm-2">
				<form:select path="gradLevel" 
					multiple="multiple" items="${gradeLevel}">
				</form:select>
				<input type="hidden" id="initGradLevel"  value="${capCycleInfo.gradLevelAsString }" >
				<form:errors path="gradLevel" cssClass="error" />
			</div>
			<div class="col-sm-1"></div>
		</div>

		<div class="form-group row">
		<div class="col-sm-1"></div>
			<div class="col-sm-3" style="padding-right: 0px;">
				<div class="col-form-label float-right font-weight-bold">Total Number of Practicum Hours:</div>
			</div>
			<div class="col-sm-2">
				<form:input class="form-control" path="practicumHours" aria-label="Please enter Practicum Hours"  maxlength="10"  />
				<form:errors path="practicumHours" cssClass="error" />
			</div>
			
			<div class="col-sm-3" style="padding-right: 0px;">
				<div class="font-weight-bold"
					style="text-align: right">Number of Hours assumed full responsibility in the role:</div>
			</div>
			<div class="col-sm-2">
				<form:input class="form-control" path="hoursFullResponsibility" aria-label="Please enter number of hours"   maxlength="10" />
				<form:errors path="hoursFullResponsibility" cssClass="error" />
			</div>	
			<div class="col-sm-1"></div>		
		</div>


		<div class="form-group row">
		<div class="col-sm-1"></div>
			<div class="col-sm-3" style="padding-right: 0px;" >
				<div class="col-form-label float-right font-weight-bold">Practicum Type:</div>
			</div>
			<div class="col-sm-2">
				<form:select id="practicumTypeCode" path="practicumTypeCode"
					class="form-control custom-select-lg" aria-label="Please select Practicum Type">
					<form:options items="${practicumTypes}" />
				</form:select>
				<form:errors path="practicumTypeCode" cssClass="error" />
			</div>
			<div class="col-sm-3" style="padding-right: 0px;" >
				<div class="col-form-label float-right font-weight-bold">Cycle Completion Year:</div>
			</div>
			<div class="col-sm-2">
			<c:if test="${capCycleInfo.schoolYear ne null }" ><div class="col-form-label">${capCycleInfo.schoolYear-1} - ${capCycleInfo.schoolYear}</div>	</c:if>			
			</div>
			<div class="col-sm-1"></div>
		</div>
		
		
		<div class="form-group row">
		<div class="col-sm-1"></div>
			<div class="col-sm-7">
				<div class="col-form-label float-right font-weight-bold">Have
					any of the components of the approved programs been waived? </div>
			</div>
			<div class="col-sm-3">
				<form:select id="waived" path="waived" cssStyle=" width:100px"
					class="form-control custom-select-lg" aria-label="Please select if any components have been waived">
					<form:option value="N" label="No" />
					<form:option value="Y" label="Yes" />
				</form:select>
				<form:errors path="waived" cssClass="error" />
			</div>
			<div class="col-sm-1"></div>
		</div>

		<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_SUPERVISOR', 'ROLE_CANDIDATE')">
			<div class="form-group row">
				<div class="col-sm-12" style="text-align: center">
					<input type="submit" value="Save" name="saveCycleInfo"
						onclick="setScrollPosition();" class="btn btn-primary" />
				</div>
			</div>
		</sec:authorize>