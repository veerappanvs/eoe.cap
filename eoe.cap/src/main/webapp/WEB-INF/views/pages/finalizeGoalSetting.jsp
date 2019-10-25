<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authentication var="user" property="principal" />


<script>


function addRow(srcElement) {

	var uri = '/cap/goal/addrow?cycleId=${goalPlanInfo.cycleId}';

	$.ajax({
		type : "POST",
		url : uri,
		data: $('#form2').serialize(), 
		async : false,
		success : function(response) {
			$('#' + srcElement).text('');
			$('#' + srcElement).append($(response).find('#implementationPlans')[0]);
			
		},
		complete : function(response) {
			autosize($('textarea'));
			textAreaInit();
		}

	});

	return false;

}


function deleteRow(srcElement, idx) {

	var uri = '/cap/goal/deleterow?cycleId=${goalPlanInfo.cycleId}&idx='+idx;

	$.ajax({
		type : "POST",
		url : uri,
		data: $('#form2').serialize(), 
		async : false,
		success : function(response) {
			$('#' + srcElement).text('');
			$('#' + srcElement).append($(response).find('#implementationPlans')[0]);
		},
		complete : function(response) {
			autosize($('textarea'));
			textAreaInit();
		}

	});

	return false;

}

	function pageLoad() {		
		window.scroll(${scrollX!=null?scrollX:0},${scrollY!=null?scrollY:0});
		alertUser();
	}
</script>

	<form:form id="form2" method="POST" commandName="goalPlanInfo">
		<div id="error-section" style="margin-bottom: 25px" class="error">
			<form:errors />
		</div>


		<jsp:include page="cycleInfo.jsp"></jsp:include>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div style="text-align: center; padding: 10px">
			<h5>Finalized Goal and Implementation Plan</h5>
		</div>

		<div class="line" style="margin: 10px 0; color: black"></div>


		<div class="form-group row">
			<div class="col-sm-12">
				<b>CAP Professional Practice Goal:</b>&nbsp;&nbsp;Based on the
				Candidate's Self-Assessment and feedback from the Program Supervisor
				and Supervising Practitioner, the Candidate has set the following
				S.M.A.R.T Professional Practice Goal. During the first Three-Way
				Meeting, the Triad will meet to finalize this Goal.
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-12">
				<form:textarea cssClass="form-control" path="goalDesc"
					aria-label="Please enter the Professional Practice Goal" rows="5" />
				<form:errors path="goalDesc" cssClass="error" />
			</div>
		</div>


		<div class="line" style="margin: 10px 0; color: black"></div>



		<div class="form-group row">
			<div class="col-sm-12">
				<h6>Identify the Essential Element(s) that are the focus of
					this Goal:</h6>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-12">

				<table class="table white-background no-border left-aligned">
					<c:forEach items="${goalPlanInfo.elements}" step="3" var="element"
						varStatus="elementStatus">
						<tr>
							<td style="border: initial; text-align: initial;"><form:checkbox
									path="elements[${elementStatus.index}].selected"
									label="${goalPlanInfo.elements.get(elementStatus.index).desc}"
									aria-label="Please check element if applicable" /></td>
							<td style="border: initial; text-align: initial;"><form:checkbox
									path="elements[${(elementStatus.index+1)}].selected"
									label="${goalPlanInfo.elements.get(elementStatus.index+1).desc}"
									aria-label="Please check element if applicable" /></td>
							<td style="border: initial; text-align: initial;"><form:checkbox
									path="elements[${(elementStatus.index+2)}].selected"
									label="${goalPlanInfo.elements.get(elementStatus.index+2).desc}"
									aria-label="Please check element if applicable" /></td>

						</tr>
					</c:forEach>
				</table>

			</div>
		</div>

		<!--
		<div class="line" style="margin: 10px 0; color: black"></div>

		<div class="form-group row">
			<div class="col-sm-12">
				<h6>Teacher Candidate entries from Self-Assessment</h6>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background">
					<tr>
						<td>
							<div class="form-group row">
								<div class="col-sm-12">
									<h6>What actions will you take to achieve the Goal?</h6>
								</div>
							</div>

							<div class="form-group row">
								<div class="col-sm-12">
									<textarea class="form-control" rows="5" 
										readonly="readonly">${goalPlanInfo.prelimGoalPlanInfo.action }</textarea>
								</div>
							</div>

							<div class="form-group row">
								<div class="col-sm-12">
									<h6>What Actions/Supports/Resources will you need from
										your program supervisor and supervising Practitioner?</h6>
								</div>
							</div>

							<div class="form-group row">
								<div class="col-sm-12">
									<textarea class="form-control" rows="5" 
										readonly="readonly">${goalPlanInfo.prelimGoalPlanInfo.support }</textarea>
								</div>
							</div>

							<div class="form-group row">
								<div class="col-sm-12">
									<h6>When will I achieve this goal?(Realistic, Timed
										Prompt)</h6>
								</div>
							</div>

							<div class="form-group row">
								<div class="col-sm-12">
									<textarea class="form-control" rows="5" 
										readonly="readonly">${goalPlanInfo.prelimGoalPlanInfo.achievementTime }</textarea>
								</div>
							</div>


						</td>

					</tr>
				</table>
			</div>
		</div>

		-->
		<div class="line" style="margin: 10px 0; color: black"></div>

		<div class="form-group row">
			<div class="col-sm-12">
				<h6>
					<b>Implementation Plan:</b> In support of attaining the Goal(s),
					the Candidate, Program Supervisor and Supervising Practitioner
					agree on the following actions.
				</h6>
			</div>
		</div>


<div id="implementationPlans">
		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">				
					<table style="width: 100%">
						<thead>
							<tr>
								<th style="border: 0; text-align: center">Action</th>
								<th style="border: 0; text-align: center">Related
									Evidence/Artifact(s)</th>
								<th style="border: 0; text-align: center">Supports/Resources</th>
								<th style="border: 0; text-align: center">Timeline/Frequency</th>
								<th style="border: 0; text-align: center">Delete</th>
							</tr>
						</thead>
						<c:forEach items="${goalPlanInfo.implementationPlanInfos}"
							var="implementationPlan" varStatus="status">
							<tr>
								<td><form:textarea cssClass="form-control"
										path="implementationPlanInfos[${status.index}].actionEvidence"
										rows="3" aria-label="Please enter action" /> <form:errors
										path="implementationPlanInfos[${status.index}].actionEvidence"
										cssClass="error" /></td>
								<td><form:textarea cssClass="form-control"
										path="implementationPlanInfos[${status.index}].actionDesc"
										rows="3" aria-label="Please enter Related Evidence/Artifact" />
									<form:errors
										path="implementationPlanInfos[${status.index}].actionDesc"
										cssClass="error" /></td>
								<td><form:textarea cssClass="form-control"
										path="implementationPlanInfos[${status.index}].actionSupport"
										rows="3"
										aria-label="Please enter required supports and resources" />
									<form:errors
										path="implementationPlanInfos[${status.index}].actionSupport"
										cssClass="error" /></td>
								<td><form:textarea cssClass="form-control"
										path="implementationPlanInfos[${status.index}].actionTimeline"
										rows="3" aria-label="Please enter Timeline" /> <form:errors
										path="implementationPlanInfos[${status.index}].actionTimeline"
										cssClass="error" /></td>
								<td style="text-align: center;"><a href="#" role="button"
									style="display: ${goalPlanInfo.implementationPlanInfos.size() lt 2?'none':'initial'}"
									onclick="deleteRow('implementationPlans',${status.index});return false;">
										<i class="far fa-trash-alt fa-1x"
										style="color: black; margin-left: -4px;"></i>
								</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			
			
			<c:if test="${goalPlanInfo.canAddAction}">
		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<input type="button" value="Add Action Step" name="Add"
					class="btn btn-primary" onclick="addRow('implementationPlans')" />
			</div>
		</div>
	</c:if>
	
		</div>



	
		<div class="line" style="margin: 10px 0; color: black"></div>


		<div class="form-group row">
			<div class="col-sm-12">
				<h6>
					<b>Measure of Student Learning:</b> In addition to attaining the
					Professional Practice Goal, the Candidate will also be assessed
					based in part on their impact on student learning. The Supervising
					Practitioner, in coordination with the Program Supervisor, has set
					the following measures of student learning.
				</h6>
			</div>
		</div>


		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background">
					<thead>
						<tr>
							<th>Measure of Student Learning</th>
							<th style="width: 15%">Anticipated Student Learning Gains</th>
							<th>Parameters</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="3"><form:textarea cssClass="form-control"
									path="learningMeasure" rows="12"
									aria-label="Please enter the Measure of Student Learning" /> <form:errors
									path="learningMeasure" cssClass="error" /></td>
							<td>High</td>
							<td><form:textarea cssClass="form-control" maxlength="255"
									path="goalLearningMeasureInfos[0].parameter" rows="3"
									aria-label="Please enter the High parameter" /> <form:errors
									path="goalLearningMeasureInfos[0].parameter" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Moderate</td>
							<td><form:textarea cssClass="form-control" maxlength="255"
									path="goalLearningMeasureInfos[1].parameter" rows="3"
									aria-label="Please enter the Moderate parameter" /> <form:errors
									path="goalLearningMeasureInfos[1].parameter" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Low</td>
							<td><form:textarea cssClass="form-control" maxlength="255"
									path="goalLearningMeasureInfos[2].parameter" rows="3"
									aria-label="Please enter the Low parameter" /> <form:errors
									path="goalLearningMeasureInfos[2].parameter" cssClass="error" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<sec:authorize access="hasAnyRole('ROLE_SUPERVISOR','ROLE_MANAGER')">

			<c:if test="${goalPlanInfo.enableCompletion}">
				<div class="form-group row">
					<div class="col-sm-5"></div>
					<div class="col-sm-2">
						<form:checkbox path="completeGoal" cssStyle="margin-right:10px" />
						<div class="col-form-label font-weight-bold">Complete</div>
					</div>
					<div class="col-sm-5"></div>
				</div>

				<div class="form-group row">
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						<b>Note: </b>Please check this box if the Final Goals and
						Implementation Plan is complete.
					</div>
					<div class="col-sm-1"></div>
				</div>
			</c:if>
		</sec:authorize>


		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<input type="submit" value="Save" name="command"
					class="btn btn-primary" />
			</div>
		</div>

		<input type="hidden" name="cycleId" value="${cycleId }">


	</form:form>

