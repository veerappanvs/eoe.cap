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
		window.scroll(${scrollX!=null?scrollX:0},${scrollY!=null?scrollY:0});
		alertUser();
		if(${not empty cannotSave})
			$('#assessment-warn-dialog').modal('show');
	}
</script>

	<form:form id="form2" method="POST" commandName="assessmentInfo"  action="formative" >
		<div id="error-section" style="margin-bottom: 25px" class="error">
			<form:errors />
		</div>


	<jsp:include page="cycleInfo.jsp"></jsp:include>
		

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div style="text-align: center; padding: 10px">
			<h5>${assessmentInfo.typeDesc} Assessment for
				${cycleInfo.candidateFirstName} ${cycleInfo.candidateLastName}</h5>
		</div>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div class="form-group row">
			<div class="col-sm-12">
				<div style="border: 1px solid; padding: 20px">
					<div class="form-group row">
						<div class="col-sm-12">
							<b>Instructions:</b>  The Program Supervisor and Supervising
							Practitioner jointly complete the ${assessmentInfo.typeDesc}
							Assessment.  Using available evidence Supervisors assign ratings 
							and provide rationale for the ratings, being sure to include 
							evidence to support points being made in the rationale.
						</div>
					</div>
					<c:forEach items="${dimensions}" var="dimension">
						<div class="form-group row" style="margin-left: 20px">
							<div class="col-sm-12">
								<li><b>${dimension.dimensionDesc}:  </b>${dimension.dimensionLongDesc}</li>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>




		<div class="line" style="margin: 10px 0; color: black"></div>

		<c:forEach items="${assessmentInfo.elements}" var="element"
			varStatus="elementIndex">
			<div id="${element.elementCode}">
				<div class="form-group row"
					style="padding: 20px; margin: 2px; background: aliceBlue">
					<div class="col-sm-4 font-weight-bold">${element.altDesc }</div>
					<div class="col-sm-5"></div>
					<div class="col-sm-3"><b>Progress:</b> ${element.completed eq 'Y' ? 'Complete':'Incomplete' }</div>
				</div>

				<div class="form-group row">
					<div class="col-sm-12">
						<table class="table white-background">
							<thead>
								<tr>
									<c:forEach items="${element.ratingInfos}" var="ratingInfo">
										<th scope="col">${ratingInfo.shortDesc}</th>
									</c:forEach>
								</tr>
							</thead>
							<tr>
								<c:forEach items="${element.ratingInfos}" var="ratingInfo">
									<td>${ratingInfo.longDesc}</td>
								</c:forEach>
							</tr>


						</table>


					</div>
				</div>


				<div class="form-group row">
					<div class="col-sm-6">
						<table class="table white-background">
							<thead>
								<tr>
									<th scope="col" style="text-align: center">Dimension</th>
									<th scope="col" style="text-align: center">Rating</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${element.dimensions}" var="dimension"
									varStatus="dimensionIndex">
									<tr>
										<td style="text-align: center"><b>${dimension.dimensionDesc}</b></td>
										<td style="text-align: center"><form:select
												path="elements[${elementIndex.index }].dimensions[${dimensionIndex.index }].ratingCode"
												cssClass="form-control" aria-label="Please select a rating">
												<form:option value="">Select</form:option>
												<form:options items="${ratingTypes}" />
											</form:select></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="col-sm-6">
						<table class="table white-background">
							<thead>
								<tr>
									<th scope="col" style="text-align: center; width: 60%">Tagged
										Evidence Files</th>
									<th scope="col" style="text-align: center">Types of
										Evidence</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${element.files.size() lt 1}">
									<tr>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								<c:forEach items="${element.files}" var="file">
									<tr>
										<td><a style="display: block; margin-top: 10px"
											href="/cap/tagfile/download?cycleId=${cycleId }&amp;fileId=${file.fileId}"
											onclick="downloadFile false;">${file.internalFileName }</a></td>
										<td><c:forEach items="${file.evidenceTypes}"
												var="attribute" varStatus="attStatus">${attribute.label}${attStatus.last?'':', '}</c:forEach>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>




				<div class="form-group row" style="padding: 20px">
					<div class="col-sm-12">
						<div class="form-group row">
							<div class="col-sm-12">
								<h6>Sources of Evidence</h6>
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-sm-4">
								<div class="col-form-label">
								<b>Announced Observation 1</b></br>
								Observation Date: <fmt:formatDate pattern="MM/dd/yyyy"	value="${assessmentInfo.announcedObservation1.observationDate}" /></br>
								Calibrated	Evidence:
								</div>
							</div>
							<div class="col-sm-4">
								<div class="col-form-label">
								<b>Unannounced Observation 1</b></br>
								Observation Date: <fmt:formatDate pattern="MM/dd/yyyy"	value="${assessmentInfo.unAnnouncedObservation.observationDate}" /></br>
								Calibrated Evidence:
								</div>

							</div>
							<div class="col-sm-4">
								<div class="col-form-label">
								<b>Announced Observation 2</b></br>
								Observation Date: <fmt:formatDate pattern="MM/dd/yyyy" value="${assessmentInfo.announcedObservation2.observationDate}" /></br>
								Calibrated Evidence:
								</div>
							</div>
						</div>						

						<div class="form-group row">
							<div class="col-sm-4">
								<c:forEach
									items="${assessmentInfo.announcedObservation1.evidences}"
									var="evidence">
									<c:if test="${evidence.elementCode eq element.elementCode }">
										<textarea rows="5" class="form-control" readonly="readonly" aria-label="Calibrated evidence for announced observation 1" >${evidence.calEvidence}</textarea>
									</c:if>
								</c:forEach>
							</div>
							<div class="col-sm-4">
								<c:forEach
									items="${assessmentInfo.unAnnouncedObservation.evidences}"
									var="evidence">
									<c:if test="${evidence.elementCode eq element.elementCode }">
										<textarea rows="5" class="form-control" readonly="readonly"   aria-label="Calibrated evidence for unannounced observation 1" >${evidence.calEvidence}</textarea>
									</c:if>
								</c:forEach>
							</div>
							<div class="col-sm-4">
								<c:forEach
									items="${assessmentInfo.announcedObservation2.evidences}"
									var="evidence">
									<c:if test="${evidence.elementCode eq element.elementCode }">
										<textarea rows="5" class="form-control" readonly="readonly" aria-label="Calibrated evidence for announced observation 1" >${evidence.calEvidence}</textarea>
									</c:if>
								</c:forEach>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm-12">
								<h6>${element.altDesc} Evidence/Rationale (Insert evidence to support rating here):</h6>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm-12">
								<form:textarea cols="132" rows="5" maxlength="4000" cssClass="form-control"
									path="elements[${elementIndex.index}].rationale" aria-label="Please enter Rating Rationale"/>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12" style="text-align: center">
					<input type="submit" value="Save"
						name="Save${element.elementCode }" class="btn btn-primary"
						onclick="setScrollPosition('${element.elementCode }');" />
				</div>
			</div>


		</c:forEach>

		<div class="line" style="margin: 10px 0; color: black"></div>
		<div class="form-group row">
			<div class="col-sm-12">
				<h5>Evidence-Based Feedback</h5>
			</div>
		</div>



		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background">
					<thead>
						<tr>
							<th scope="col" colspan="${dimensions.size()+1 }">Summary of
								Formative Assessment Ratings</th>
						</tr>
						<tr>
							<th scope="col">Element</th>
							<c:forEach items="${dimensions}" var="dimension">
								<th scope="col">${dimension.dimensionDesc }</th>
							</c:forEach>
						</tr>
					</thead>

					<c:forEach items="${assessmentInfo.elements}" var="element">
						<tr>
							<td>${element.altDesc }</td>
							<c:forEach items="${element.dimensions}" var="dimension">
								<td>${dimension.ratingDesc }</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>




		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background">
					<tr>
						<td width="25%"><b>Reinforcement Area(s)</b></td>
						<td>
							<table class="table  white-background no-border left-aligned">
								<c:forEach items="${assessmentInfo.reinAttributes }"
									var="attribute" varStatus="status"
									end="${(assessmentInfo.reinAttributes.size()/2)+(assessmentInfo.reinAttributes.size()%2)-1 }">
									<tr>
										<td><form:checkbox aria-label="Click here to choose reinforcement area ${assessmentInfo.reinAttributes.get(status.index).label }"
												path="reinAttributes[${status.index}].selected"
												label="${assessmentInfo.reinAttributes.get(status.index).label }" />
										</td>
										<td><c:if
												test="${status.index+3 lt assessmentInfo.reinAttributes.size()  }">
												<form:checkbox aria-label="Click here to choose reinforcement area ${assessmentInfo.reinAttributes.get(status.index+3).label}"
													path="reinAttributes[${status.index+3}].selected"
													label="${assessmentInfo.reinAttributes.get(status.index+3).label}" />
											</c:if></td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>

					<tr>
						<td width="25%"><b>Evidence-Based Feedback to Candidate</b></td>
						<td><form:textarea path="reinFeedback" rows="5" cssClass="form-control"
								maxlength="4000" aria-label="Please enter Evidence-Based Feedback" /></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background ">
					<tr>
						<td width="25%"><b>Refinement Area(s)</b></td>
						<td>
							<table class="table  white-background no-border left-aligned">
								<c:forEach items="${assessmentInfo.refineAttributes }"
									var="attribute" varStatus="status"
									end="${(assessmentInfo.refineAttributes.size()/2)+(assessmentInfo.refineAttributes.size()%2)-1 }">
									<tr>
										<td><form:checkbox aria-label="Click here to choose refinement area ${assessmentInfo.reinAttributes.get(status.index).label}"
												path="refineAttributes[${status.index}].selected"
												label="${assessmentInfo.reinAttributes.get(status.index).label }" />
										</td>
										<td><c:if
												test="${status.index+3 lt assessmentInfo.refineAttributes.size()  }">
												<form:checkbox aria-label="Click here to choose refinement area ${assessmentInfo.reinAttributes.get(status.index).label}"
													path="refineAttributes[${status.index+3}].selected"
													label="${assessmentInfo.refineAttributes.get(status.index+3).label}" />
											</c:if></td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>

					<tr>
						<td><b>Evidence-Based Feedback to Candidate</b></td>
						<td><form:textarea path="refineFeedback" rows="5"  cssClass="form-control"
								maxlength="4000" aria-label="Please enter Evidence-Based Feedback" /></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-4" style="text-align: right">
				<div class="col-form-label">Require additional (Fifth)
					observation?</div>
			</div>
			<div class="col-sm-2">
				<form:select path="addlObservationRequired" cssClass="form-control" aria-label="Please select if additional (Fifth)
					observation is required">
					<form:option value="N">No</form:option>
					<form:option value="Y">Yes</form:option>
				</form:select>
			</div>
			<div class="col-sm-5"></div>
		</div>

		<div class="line" style="margin: 10px 0; color: black"></div>
		<c:if test="${assessmentInfo.canSignOff }">
			<div class="form-group row">
				<div class="col-sm-5"></div>
				<div class="col-sm-2">
					<form:checkbox path="assignmentSigned" />
					<div class="col-form-label font-weight-bold">Complete</div>
				</div>
				<div class="col-sm-5"></div>
			</div>

			<c:if test="${assessmentInfo.psApproved eq 'Y' }">
				<div class="form-group row">
					<div class="col-sm-4"></div>
					<div class="col-sm-4">
						<div class="col-form-label">The Program Supervisor has
							marked this Formative Assessment as "Finished".</div>
					</div>
					<div class="col-sm-4"></div>
				</div>

			</c:if>

			<div class="form-group row">
				<div class="col-sm-1"></div>
				<div class="col-sm-10">
					<div class="col-form-label"><b>Note:</b> Please check this box if this
						Formative Assessment is complete.  This will give the Teacher
						Candidate access to this Form.
					</div>
				</div>
				<div class="col-sm-1"></div>
			</div>


		</c:if>



		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center;">
				<div >
					<input type="submit" value="Cancel" name="cancel"
						class="btn btn-primary" /> <input type="submit" value="Save"
						name="Save" class="btn btn-primary"
						onclick="setScrollPosition('ALL');" />
				</div>
			</div>
		</div>
		
		
		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10" >
				<b>Note:</b>  The option to complete this Form and share it with the Teacher Candidate will only become available when all required components of the Form are completed. Please use the Progress statuses above to identify and revisit any incomplete sections.
			</div>
			<div class="col-sm-1"></div>
		</div>
		
		<input type="hidden" name="cycleId" id="cycleId" value="${cycleId}" />
		<input type="hidden" name="typeCode" id="typeCode" value="2" />
		<input type="hidden" name="elementCode" id="elementCode" value="ALL" />
		<input type="hidden" name="scrollX" id="scrollX" value="0" />
		<input type="hidden" name="scrollY" id="scrollY" value="0" />
		
		<jsp:include page="assessmentWarning.jsp">
			<jsp:param name="atype" value="formative" />
			<jsp:param name="typeCode" value="3" />
		</jsp:include>
	</form:form>