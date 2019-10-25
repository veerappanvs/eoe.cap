<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<script>

function setScrollPosition(elementCode){
	$('#scrollX').val(window.pageXOffset);
	$('#scrollY').val(window.pageYOffset);
	$('#elementCode').val(elementCode);
	
}
	function pageLoad() {
	
		$('#goal-tab').tab('show');
		
		window.scroll(${scrollX!=null?scrollX:0},${scrollY!=null?scrollY:0});
	}
</script>

	<form:form id="form2" method="POST" commandName="assessmentInfo">
		<div id="error-section" style="margin-bottom: 25px" class="error">
			<form:errors />
		</div>

		<form:hidden path="activeElementCode" />

		<jsp:include page="cycleInfo.jsp"></jsp:include>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background">
					<tr>
						<td style="text-align: left">Candidates are required to
							develop a professional practice goal that is specific,
							actionable, and measurable. In addition, this goal must be
							accompanied by an action plan with benchmarks to assess progress.
							The S.M.A.R.T. goals framework is a useful tool that candidates
							and supervisors can use to craft an effective goal and action
							plan.</td>
					</tr>
				</table>
			</div>
		</div>



		<div class="line" style="margin: 10px 0; color: black"></div>

		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background">
					<tr>
						<td style="text-align: left;">
							<p>The key characteristics of S.M.A.R.T. goals are as
								follows:</p>
							<p>
								<span style="color: orange; font-weight: bold">S =
									Specific and Strategic - </span>Goals should be specific so that at
								the end of the practicum, candidates and supervisors can
								determine whether the goal has been achieved. Goals should also
								be strategic, i.e., serve an important purpose for students, the
								school, and/or the district.
							</p>
							<p>
								<span style="color: orange; font-weight: bold">M =
									Measurable - </span>Goals should be measurable so that progress toward
								a goal can be evaluated and managed.
							</p>
							<p>
								<span style="color: orange; font-weight: bold">A = Action
									Oriented - </span>Goals have active, not passive verbs. The action
								steps attached to the goals indicate who is doing what.
							</p>
							<p>
								<span style="color: orange; font-weight: bold">R=
									Rigorous, Realistic, and Results Focused (the 3 Rs) - </span>Goals
								should make clear what will be different as a result of
								achieving the goal. A goal needs to describe a realistic yet
								ambitious result. It needs to stretch the candidate toward
								improvement, but it should not be out of reach.
							</p>
							<p>
								<span style="color: orange; font-weight: bold">T = Timed
									and Tracked - </span>A goal needs to have a final deadline, as well as
								interim deadlines by when key actions will be completed and
								benchmarks will be achieved. Tracking the progress on both
								action steps and outcome benchmarks is important, as they help
								candidates know whether they are on track to achieve the goal,
								and give candidates information they need to make midcourse
								corrections.
							</p>
						</td>

					</tr>
				</table>
			</div>
		</div>

		<div class="line" style="margin: 10px 0; color: black"></div>
		<div class="form-group row">

			<div class="col-sm-12">
				<table class="table white-background">
					<tr>
						<td><h6>Using your Self-Assessment, identify a focus
								area for your professional practice goal.</h6></td>
					</tr>
					<tr>
						<td>
							<table class="table white-background no-border left-aligned">
								<c:forEach items="${assessmentInfo.goalPlanInfo.elements}"
									step="3" var="element" varStatus="elementStatus">
									<tr>
										<td style="border: initial; text-align: initial;"><form:checkbox label="${assessmentInfo.goalPlanInfo.elements.get(elementStatus.index).desc}"
												path="goalPlanInfo.elements[${elementStatus.index}].selected" disabled="true"
												 />
											</td>
										<td style="border: initial; text-align: initial;"><form:checkbox label="${assessmentInfo.goalPlanInfo.elements.get(elementStatus.index+1).desc}"
												path="goalPlanInfo.elements[${(elementStatus.index+1)}].selected" disabled="true"
												 />
											</td>
										<td style="border: initial; text-align: initial;"><form:checkbox label="${assessmentInfo.goalPlanInfo.elements.get(elementStatus.index+2).desc}"
												path="goalPlanInfo.elements[${(elementStatus.index+2)}].selected" disabled="true"
												 />
											</td>

									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<td><h6>Why is this topic/focus area important?.</h6></td>
					</tr>
					<tr>
						<td><form:textarea path="goalPlanInfo.importance" readonly="true"
								cssClass="form-control" maxLength="4000" rows="5"  />
							<form:errors path="goalPlanInfo.importance" cssClass="error" />
						</td>
					</tr>
				</table>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background">
					<tr>
						<td style="width: 25%">Draft Professional Practice Goal:</td>
						<td><form:textarea path="goalPlanInfo.goalDesc" readonly="true"
								cssClass="form-control" maxLength="4000" rows="5" />
							<form:errors path="goalPlanInfo.goalDesc" cssClass="error" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<table class="table">
								<tr>
									<td style="border: none"><h6>What actions will you take to achieve the Goal?</h6></td>
									<td style="border: none"><h6>What
											Actions/Supports/Resources will you need from your Program
											Supervisor and Supervising Practitioner?</h6></td>
								</tr>
								<tr>
									<td style="border: none"><form:textarea readonly="true"
											cssClass="form-control" path="goalPlanInfo.action"
											maxLength="4000" rows="5"  /> <form:errors
											path="goalPlanInfo.action" cssClass="error" /></td>
									<td style="border: none"><form:textarea readonly="true"
											cssClass="form-control" path="goalPlanInfo.support"
											maxLength="4000" rows="5" /> <form:errors
											path="goalPlanInfo.support" cssClass="error" /></td>
								</tr>
							</table>
						</td>
					<tr>
				</table>
			</div>
		</div>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background" style="table-layout: auto;">
					<tr>
						<td colspan="3">
							<h6>S.M.A.R.T. Analysis</h6>
							<p style="font-weight: initial">AnalysisUse the following
								table to evaluate whether your goal is S.M.A.R.T. (and make any
								necessary adjustments to your draft goal statement).</p>
						</td>

					</tr>
					<tr>
						<td style="width: 5%">
							<h5>S</h5>
						</td>
						<td style="width: 45%"><p style="font-weight: bold">Is the goal specific and
								strategic?</p>
							<p>What specific skills, knowledge, or practice will I
								acquire or develop through achieving this goal? Does is serve an
								important purpose for my students?</p></td>
						<td><form:textarea path="goalPlanInfo.skillsAcquired" readonly="true"
								cssClass="form-control" maxLength="4000" rows="5" />
								<form:errors path="goalPlanInfo.skillsAcquired" cssClass="error" />
								</td>
					</tr>

					<tr>
						<td style="width: 5%">
							<h5>M</h5>
						</td>
						<td><p style="font-weight: bold">Is it measurable?</p>
							<p>How will I track progress and evaluate success?</p></td>
						<td><form:textarea path="goalPlanInfo.successEval" readonly="true"
								cssClass="form-control" maxLength="4000" rows="5" />
								<form:errors path="goalPlanInfo.successEval" cssClass="error" />
								</td>
					</tr>

					<tr>
						<td style="width: 5%">
							<h5>A</h5>
						</td>
						<td><p style="font-weight: bold">Is it action-oriented?</p>
							<p>How will I demonstrate progress toward this goal? (Include
								potential sources of evidence demonstrating goal progress)</p></td>
						<td><form:textarea path="goalPlanInfo.progressMeasure" readonly="true"
								cssClass="form-control" maxLength="4000" rows="5" />
								<form:errors path="goalPlanInfo.progressMeasure" cssClass="error" />								
								</td>
					</tr>

					<tr>
						<td style="width: 5%">
							<h5>R</h5>
						</td>
						<td><p style="font-weight: bold">Does it have the 3 R s
								(Rigorous, Realistic, and Results-Focused)?</p>
							<p>Is this goal both realistic and ambitious?</p></td>
						<td><form:textarea path="goalPlanInfo.realisticMeasure" readonly="true"
								cssClass="form-control" maxLength="4000" rows="5" />
								<form:errors path="goalPlanInfo.realisticMeasure" cssClass="error" />
								</td>
					</tr>

					<tr>
						<td style="width: 5%">
							<h5>T</h5>
						</td>
						<td><p style="font-weight: bold">Is it timed?</p>
							<p>When will I achieve this goal?</p></td>
						<td><form:textarea path="goalPlanInfo.achievementTime" readonly="true"
								cssClass="form-control" maxLength="4000" rows="5" />
								<form:errors path="goalPlanInfo.achievementTime" cssClass="error" />
								</td>
					</tr>



				</table>
			</div>
		</div>


		<input type="hidden" name="elementCode" id="elementCode" value="ALL" />
		<input type="hidden" name="scrollX" id="scrollX" value="" />
		<input type="hidden" name="scrollY" id="scrollY" value="" />


		<div class="form-group row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div style="margin: 10px">
					<input type="submit" value="Back" name="command"
						class="btn btn-primary" /> <input type="submit" value="Cancel"
						name="command" class="btn btn-primary" /> <input type="submit"
						value="Next" name="command" class="btn btn-primary" /> <input
						type="hidden" value="0" name="prev" /> <input type="hidden"
						value="1" name="curr" /> <input type="hidden" value="2"
						name="nxt" />
				</div>
			</div>
		</div>
	</form:form>

