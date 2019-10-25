<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
	function initGroupCode(){	
		
		var groupCode=$('#initGroupCode').val();
		$('.multiselect-selected-text').text(groupCode.length>0?groupTypes:'Please select a value');
	}

	function pageLoad() {
		$('#observationDate').datepicker();
		$('#startTime').timepicker({
			defaultTime : false,
			template : false,
			showInputs : false,
			up : 'fas fa-sort-up',
			down : 'fas fa-sort-up-down'
		});
		$('#endTime').timepicker({
			defaultTime : false,
			template : false,
			showInputs : false,
			up : 'fas fa-sort-up',
			down : 'fas fa-sort-up-down'
		});

		$("[name='psspCompleted']")
				.on(
						"click",
						function(e) {
							
							
							var uri = '/cap/observation/changeObservationConductor?cycleId=${cycleInfo.cycleId}&observationNumber=${observationInfo.observationNumber}&typeCode=${observationInfo.observationTypecode}';

							$.ajax({
								type : "POST",
								url : uri,
								data: $('#form2').serialize(), 
								async : false,
								success : function(response) {
									$('#observationForm').text('');
									$('#observationForm').append($(response).find('#observationForm')[0].innerHTML);
								},
								complete : function(response) {
									pageLoad();
									autosize($('textarea'));
									textAreaInit();
								}

							});
		
					
							e.preventDefault();
						
				
						
						});
						
		 $('#groupCode').multiselect({
            numberDisplayed: 15,
            dropRight: true,
            allSelectedText:false,
            nonSelectedText: 'Please select a value',
            includeSelectAllOption: true,
            selectAllText:'Check All'
        });
		initGroupCode();
		
		alertUser();
	}
</script>

	<form:form id="form2" method="POST" commandName="observationInfo" action="/cap/observation?cycleId=${cycleInfo.cycleId}&observationNumber=${observationInfo.observationNumber}&typeCode=${observationInfo.observationTypecode}"  >
		<div id="error-section" style="margin-bottom: 25px" class="error">
			<form:errors />
		</div>

		<jsp:include page="cycleInfo.jsp"></jsp:include>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div id="observationForm"  >
		
		<div class="form-group row">
			<div class="col-sm-12">
			<table class="table white-background">

					<tr>
						<td style="text-align: left"><b>Note:</b></br><b>*</b> Please coordinate with the other supervisor(s) before adding or updating
						the Calibrated Evidence, Content Topic/Lesson Objective, Start Time, End Time, Group Size and Observation Date, so that 
						you don't unintentionally over-write the information entered by the other user.</td>
					</tr>
				</table>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-4">
				<div class="col-form-label font-weight-bold" style="text-align: right"  >Who is/are conducting the Observation?</div>
			</div>
			<div class="col-sm-6" >
				<form:radiobutton path="psspCompleted" value="B"
					aria-label="PS-SP Completed" />
				<div class="col-form-label" style="display: inline-block;margin-left: 10px" >Program Supervisor and Supervising Practitioner (Joint Observation)</div>
			</div>
			<div class="col-sm-1"></div>
		</div>

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-4"></div>
			<div class="col-sm-6">
				<form:radiobutton path="psspCompleted" value="S"
					aria-label="SP Completed"
					cssStyle="${(observationInfo.supervisor and observationInfo.psspCompleted ne 'P')?'':'display:none'}" />
				<form:radiobutton path="psspCompleted" value="P"
					aria-label="PS Completed"
					cssStyle="${(observationInfo.practitioner and observationInfo.psspCompleted ne 'S')?'':'display:none'}" />
				<label class="col-form-label"  
					style="${(observationInfo.supervisor and observationInfo.psspCompleted ne 'P')?'':'display:none'}">
					&nbsp;&nbsp;&nbsp;Program Supervisor only</label> <label class="col-form-label"
					style="${(observationInfo.practitioner and observationInfo.psspCompleted ne 'S')?'':'display:none'}">
					&nbsp;&nbsp;&nbsp;Supervising Practitioner only</label>
			</div>
			<div class="col-sm-4">
				<form:errors path="psspCompleted" cssClass="error" />
			</div>
		</div>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div style="text-align: center; padding: 10px">
			<h5>${observationInfo.observationTypeDesc} Observation Form
				#${observationInfo.observationNumber} for
				${cycleInfo.candidateFirstName} ${cycleInfo.candidateLastName}</h5>
		</div>


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Observation
					Status:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label"
					style="color:${observationInfo.completed eq 'Y'?'green':'black'}">${observationInfo.statusDesc}</div>
			</div>
			<div class="col-sm-2"></div>
			<div class="col-sm-3">
				<div class="col-form-label"></div>
			</div>
			<div class="col-sm-1"></div>
		</div>





		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Focus
					Elements:</div>
			</div>
			<div class="col-sm-9">
				<div class="col-form-label">
					<c:forEach items="${observationInfo.evidences}" var="evidence"
						varStatus="status">
						<c:if test="${evidence.focusElement eq 1}">
				${evidence.elementDesc}<br />
						</c:if>
					</c:forEach>
				</div>
			</div>

		</div>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div style="text-align: center; padding: 10px">
			<h5>Observation Details</h5>
		</div>


		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Date
					of Observation* :</div>
			</div>
			<div class="col-sm-3">
				<form:input id="observationDate" path="observationDate"
					aria-label="Date of Observation" class="form-control "
					style="width: 200px" placeholder="mm/dd/yyyy" />
				<form:errors path="observationDate" cssClass="error" />
			</div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">Start
					Time* :</div>
			</div>
			<div class="col-sm-3">
				<form:input id="startTime" path="startTime" aria-label="Start Time"
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
				<form:select path="groupCode" 	multiple="multiple" items="${groupTypes}">
				</form:select>
				<input type="hidden" id="initGroupCode"  value="${observationInfo.groupCodeAsString}" >
				<form:errors path="groupCode" cssClass="error"  cssStyle="display:block"/>
			</div>
			<div class="col-sm-2">
				<div class="col-form-label float-right font-weight-bold">End
					Time* :</div>
			</div>
			<div class="col-sm-3">
				<form:input id="endTime" path="endTime" aria-label="End Time"
					class="form-control input-small" cssStyle="width:100px" />
				<form:errors path="endTime" cssClass="error" />
			</div>
			<div class="col-sm-1"></div>
		</div>

		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Content
					Topic/Lesson Objective:</div>
			</div>
		</div>
		<div class="col-sm-8"></div>


		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<form:textarea maxlength="255" path="topicObjective" 
					aria-label="Content Topic/Lesson Objective" cssStyle="width:100%"
					rows="2" cssClass="form-control" />
			</div>
		</div>
		<div class="col-sm-1"></div>

		<div class="line" style="margin: 10px 0; color: black"></div>

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
											readonly="${observationInfo.practitioner?'false':'true'}" />
										<form:errors path="evidences[${status.index}].spEvidence"
											cssClass="error" />
									</c:if> <c:if test="${observationInfo.psspCompleted ne 'B' }">
										<form:textarea maxlength="4000" rows="2"
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
											readonly="${observationInfo.supervisor?'false':'true'}" /> <form:errors
											path="evidences[${status.index}].psEvidence" cssClass="error" /></td>
								</tr>
								<tr>
									<td><div
										class="col-form-label ${evidence.focusElement eq 1 ?'font-weight-bold':''}">Calibrated Evidence${evidence.focusElement eq 1 ?'*':''}</div></td>
									<td><form:textarea maxlength="4000" rows="2"
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
										readonly="${observationInfo.practitioner  ?'false':'true'}" />
								</c:if> <c:if test="${observationInfo.psspCompleted ne 'B' }">
									<form:textarea maxlength="4000" rows="2"
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
										readonly="${observationInfo.supervisor?'false':'true'}" /></td>
							</tr>

							<tr>
								<td><div
										class="col-form-label font-weight-bold">Calibrated Feedback*</div></td>
								<td><form:textarea maxlength="4000" rows="2"
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
										readonly="${observationInfo.practitioner ?'false':'true'}" />
								</c:if> <c:if test="${observationInfo.psspCompleted ne 'B' }">
									<form:textarea maxlength="4000" rows="2"
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
										readonly="${observationInfo.supervisor?'false':'true'}" /></td>
							</tr>
							<tr>
								<td><div
										class="col-form-label font-weight-bold">Calibrated Feedback*</div></td>
								<td><form:textarea maxlength="4000" rows="2"
										cssClass="form-control" aria-label="Calibrated Feedback"
										path="calRefineFeedback" /></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="col-sm-1"></div>
		</div>
		
		</div>

		<c:if test="${observationInfo.enableCompletion}">
			<div class="form-group row">
				<div class="col-sm-5"></div>
				<div class="col-sm-2">
					<form:checkbox path="completeObservation" aria-label="Complete" />
					<b>Complete</b>
				</div>
				<div class="col-sm-5"></div>
			</div>

			<div class="form-group row">
				<div class="col-sm-1"></div>
				<div class="col-sm-10">
					<b>Note:</b> Please check this box if this Observation Form is
					complete. This will give the Teacher Candidate access to view this
					Form. When both supervisors complete an observation, both must mark the form "Complete" in order to share it with the Teacher Candidate.
					
				</div>
				<div class="col-sm-1"></div>
			</div>
		</c:if>

		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center;">
				<div style="margin: 50px">
					<input type="submit" value="Cancel" name="Cancel"
						class="btn btn-primary" /> <input type="submit" value="Save"
						name="Save" class="btn btn-primary" />
				</div>
			</div>
		</div>


	</form:form>
