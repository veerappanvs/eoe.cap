<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<script>
	function initGroupCode(){	
		
		var groupCode=$('#initGroupCode').val();
		$('.multiselect-selected-text').text(groupCode.length>0?groupTypes:'Please select a value');
	}

	function pageLoad() {
		
	 $('#groupCode').multiselect({
            numberDisplayed: 15,
            dropRight: true,
            allSelectedText:false,
            nonSelectedText: 'Please select a value',
            includeSelectAllOption: true,
            selectAllText:'Check All'
        });
		initGroupCode();
		
	}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title></title>
</head>
<body>
	<form:form id="form2" method="POST" commandName="observationInfo"
		>
		<div id="error-section" style="margin-bottom: 25px" class="error">
			<form:errors path="*" />
		</div>



		<jsp:include page="cycleInfo.jsp"></jsp:include>

		<div class="line" ></div>


		<div style="text-align: center; padding: 10px">
			<h5>${observationInfo.observationTypeDesc} Observation Form #${observationInfo.observationNumber} for Observation ${cycleInfo.candidateFirstName} ${cycleInfo.candidateLastName}</h5>
		</div>


		

		


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Focus Elements:</div>
			</div>
			<div class="col-sm-9">
				<div class="col-form-label">
				<c:forEach 	items="${observationInfo.evidences}" var="evidence" varStatus="status"  >
				<c:if test="${evidence.focusElement eq 1}" >
				${evidence.elementDesc}<br/>
				</c:if>
				</c:forEach>				
				</div>
			</div>

		</div>

		<div class="line" ></div>

		<div style="text-align: center; padding: 10px">
			<h5>Observation Details</h5>
		</div>


				<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Date
					of Observation* :</div>
			</div>
			<div class="col-sm-3">
				<form:input id="observationDate" path="observationDate" disabled="true"
					aria-label="Date of Observation" class="form-control "
					style="width: 200px" placeholder="mm/dd/yyyy" />
				<form:errors path="observationDate" cssClass="error" />
			</div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Start
					Time* :</div>
			</div>
			<div class="col-sm-3">
				<form:input id="startTime" path="startTime" aria-label="Start Time" disabled="true"
					class="form-control input-small" cssStyle="width:100px" />
				<form:errors path="startTime" cssClass="error" />
			</div>
			<div class="col-sm-1"></div>
		</div>


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Group
					Size* :</div>
			</div>
			<div class="col-sm-3">
				<form:select path="groupCode"  multiple="multiple"  disabled="true" items="${groupTypes}">
				</form:select>
				<input type="hidden" id="initGroupCode"  value="${observationInfo.groupCodeAsString}" >
				<form:errors path="groupCode" cssClass="error"
					cssStyle="display:block" />
			</div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">End
					Time* :</div>
			</div>
			<div class="col-sm-3">
				<form:input id="endTime" path="endTime" aria-label="End Time" disabled="true"
					class="form-control input-small" cssStyle="width:100px" />
				<form:errors path="endTime" cssClass="error" />
			</div>
			<div class="col-sm-1"></div>
		</div>


		

		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Content Topic/Lesson Objective:</div>
			</div>
		</div>
		<div class="col-sm-8"></div>
		
		
		<div class="form-group row">
		<div class="col-sm-1"></div>
			<div class="col-sm-10">
			<form:textarea path="topicObjective"  cssStyle="width:100%" rows="2" cssClass="form-control"  readonly="true" aria-label="Topic Objective"  maxlength="255" />
			</div>
		</div>
		<div class="col-sm-1"></div>
		
		<div class="line" ></div>

<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<table class="table white-background">
					<thead>
						<tr>
							<th scope="col">Element</th>
							<c:if test="${observationInfo.psspCompleted eq 'B'}">
								<th scope="col">Role</th>
							</c:if>
							<th scope="col"  style="width:${observationInfo.psspCompleted eq 'B' ?'60%':'70%'}" >Evidence</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${observationInfo.evidences}" var="evidence"
							varStatus="status">

							<tr>
								<td rowspan="${observationInfo.psspCompleted eq 'B'?'3':'1' }"><div
										class="col-form-label ${evidence.focusElement eq 1 ?'font-weight-bold':''}">${evidence.elementDesc}${evidence.focusElement eq 1 ?'*':''}</div>
								</td>
								<c:if test="${observationInfo.psspCompleted eq 'B' }">
									<td>Supervising Practitioner Evidence</td>
								</c:if>
								<td><c:if test="${observationInfo.psspCompleted eq 'B' }">
										<form:textarea maxlength="4000" rows="2"
											cssClass="form-control"
											aria-label="Supervising Practitioner Evidence"
											path="evidences[${status.index}].spEvidence"
											readonly="true" />
										<form:errors path="evidences[${status.index}].spEvidence"
											cssClass="error" />
									</c:if> <c:if test="${observationInfo.psspCompleted ne 'B' }">
										<form:textarea maxlength="4000" rows="2" readonly="true"
											cssClass="form-control" aria-label="Calibrated Evidence"
											path="evidences[${status.index}].calEvidence" />
										<form:errors path="evidences[${status.index}].calEvidence"
											cssClass="error" />
									</c:if></td>

							</tr>

							<c:if test="${observationInfo.psspCompleted eq 'B' }">
								<tr>
									<td>Program Supervisor Evidence</td>
									<td><form:textarea maxlength="4000" rows="2"
											cssClass="form-control"
											aria-label="Program Supervisor Evidence"
											path="evidences[${status.index}].psEvidence"
											readonly="true" /> <form:errors
											path="evidences[${status.index}].psEvidence" cssClass="error" /></td>
								</tr>
								<tr>
									<td><div
										class="col-form-label ${evidence.focusElement eq 1 ?'font-weight-bold':''}">Calibrated Evidence${evidence.focusElement eq 1 ?'*':''}</div></td>
									<td><form:textarea maxlength="4000" rows="2" readonly="true"
											cssClass="form-control" aria-label="Calibrated Evidence"
											path="evidences[${status.index}].calEvidence" /> <form:errors
											path="evidences[${status.index}].calEvidence"
											cssClass="error" /></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-1"></div>
		</div>


		<div class="line" style="margin: 10px 0; color: black"></div>



		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-11">
				<h5>Focused Feedback</h5>
			</div>
		</div>



		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<table class="table white-background">
					<thead>
						<tr>
							<th scope="col">Element</th>
							<c:if test="${observationInfo.psspCompleted eq 'B' }">
								<th scope="col">Role</th>
							</c:if>
							<th scope="col"   style="width:${observationInfo.psspCompleted eq 'B' ?'60%':'70%'}">Feedback</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="${observationInfo.psspCompleted eq 'B' ?'3':'1'}"><div
									class="col-form-label font-weight-bold">Reinforcement
									Area/Action*</div></td>
							<c:if test="${observationInfo.psspCompleted eq 'B' }">
								<td>Supervising Practitioner Feedback</td>
							</c:if>
							<td><c:if test="${observationInfo.psspCompleted eq 'B' }">
									<form:textarea maxlength="4000" rows="2"
										cssClass="form-control"
										aria-label="Supervising Practitioner Feedback"
										path="spRefineFeedback"
										readonly="true" />
								</c:if> <c:if test="${observationInfo.psspCompleted ne 'B' }">
									<form:textarea maxlength="4000" rows="2" readonly="true"
										cssClass="form-control" aria-label="Calibrated Feedback"
										path="calReinFeedback" />
								</c:if></td>
						</tr>
						<c:if test="${observationInfo.psspCompleted eq 'B' }">
							<tr>
								<td>Program Supervisor Feedback</td>
								<td><form:textarea maxlength="4000" rows="2"
										cssClass="form-control"
										aria-label="Program Supervisor Feedback" path="psReinFeedback"
										readonly="true" /></td>
							</tr>

							<tr>
								<td><div
										class="col-form-label font-weight-bold">Calibrated Feedback*</div></td>
								<td><form:textarea maxlength="4000" rows="2" readonly="true"
										cssClass="form-control" aria-label="Calibrated Feedback"
										path="calReinFeedback" /></td>
							</tr>
						</c:if>

						<tr>
							<td rowspan="${observationInfo.psspCompleted eq 'B' ?'3':'1'}">
								<div class="col-form-label font-weight-bold">Refinement
									Area/Action*</div>
							</td>
							<c:if test="${observationInfo.psspCompleted eq 'B' }">
								<td>Supervising Practitioner Feedback</td>
							</c:if>
							<td><c:if test="${observationInfo.psspCompleted eq 'B' }">
									<form:textarea maxlength="4000" rows="2" 
										cssClass="form-control"
										aria-label="Supervising Practitioner Feedback"
										path="spReinFeedback"
										readonly="true" />
								</c:if> <c:if test="${observationInfo.psspCompleted ne 'B' }">
									<form:textarea maxlength="4000" rows="2" readonly="true"
										cssClass="form-control" aria-label="Calibrated Feedback"
										path="calRefineFeedback" />
								</c:if></td>
						</tr>
						<c:if test="${observationInfo.psspCompleted eq 'B' }">
							<tr>
								<td>Program Supervisor Feedback</td>
								<td><form:textarea maxlength="4000" rows="2"
										cssClass="form-control"
										aria-label="Program Supervisor Feedback"
										path="psRefineFeedback"
										readonly="true" /></td>
							</tr>
							<tr>
								<td><div
										class="col-form-label font-weight-bold">Calibrated Feedback*</div></td>
								<td><form:textarea maxlength="4000" rows="2" readonly="true"
										cssClass="form-control" aria-label="Calibrated Feedback"
										path="calRefineFeedback" /></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="col-sm-1"></div>
		</div>

		<sec:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_SUPERVISOR')">
			<c:if test="${(observationInfo.completed eq 'Y')}">
				<div class="form-group row">
					<div class="col-sm-12">
						<div style="margin: 50px; text-align: center;">
							<c:if test="${displayEdit}">
								<input type="submit" value="Edit Observation" name="edit"
									class="btn btn-primary" />
							</c:if>
						</div>
					</div>
				</div>
			</c:if>
		</sec:authorize>

	</form:form>
</body>
</html>
