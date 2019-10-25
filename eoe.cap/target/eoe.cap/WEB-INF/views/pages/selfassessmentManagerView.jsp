<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<script>
	var res = '';

	function addRow(srcElement, attType) {

		var uri = '/cap/selfassessment/addrow?attType=' + attType
				+ '&cycleId=${assessmentInfo.cycleId}';

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
	
	function deleteRow(srcElement, attType,index) {

		var uri = '/cap/selfassessment/deleterow?attType=' + attType + '&cycleId=${assessmentInfo.cycleId}&attIndex='+index;

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
		alertUser();
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
					<thead>
						<tr>
							<th scope="col" colspan="${dimensions.size()+1 }">Self-Assessment Rubric Summary</th>
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

		<div class="line" style="margin: 10px 0; color: black"></div>		
					<div class="form-group row">
						<div class="col-sm-10">
							<div class="col-md-auto">
								<h6>Based on your Self-Assessment, briefly summarize your
									areas of strength and high-priority areas of growth.</h6>
							</div>
						</div>
						<div class="col-sm-2">
							<div style="float: right">
								<p>
									<a href="#" role="button"
										onclick="$('#collapseExample').collapse('show');return false;">
										<i class="far fa-plus-circle fa-2x" style="color: black"></i>
									</a> <a href="#" role="button"
										onclick="$('#collapseExample').collapse('hide');return false;">
										<i class="far fa-minus-circle fa-2x" style="color: black"></i>
									</a>

								</p>
							</div>
						</div>
					</div>

					<div class="form-group row">
						<div class="col-sm-12">
							<div class="collapse show" id="collapseExample" style="">
								<div class="card card-body">
									<div id="strengthTable">
										<table class="table white-background">
											<thead>
												<tr>
													<th scope="col">Area(s) of Strength</th>
													<th scope="col">Evidence/Rationale</th>
													<th scope="col" style="width: 30%">Element(s)</th>
												</tr>
											</thead>

											<c:forEach items="${assessmentInfo.strengths}" var="strength"
												varStatus="strengthStatus">
												<tr>
													<td><form:textarea cssClass="form-control" readonly="true"
															path="strengths[${ strengthStatus.index}].area" rows="5" 
															 maxLength="4000" />
															<form:errors  path="strengths[${ strengthStatus.index}].area" cssClass="error"  />
															</td>
													<td><form:textarea cssClass="form-control" readonly="true"
															path="strengths[${ strengthStatus.index}].rationale"
															rows="5"  maxLength="4000" />
														<form:errors  path="strengths[${ strengthStatus.index}].rationale" cssClass="error"  />	
															</td>
													<td>
														<table class="table white-background no-border left-aligned">
															<c:forEach items="${strength.elements}" step="3"
																var="element" varStatus="elementStatus">
																<tr>
																	<td ><form:checkbox label="${strength.elements.get(elementStatus.index).label }" disabled="true"
																			path="strengths[${strengthStatus.index}].elements[${elementStatus.index}].selected"
																			 />
																		</td>
																	<td ><form:checkbox label="${strength.elements.get(elementStatus.index+1).label }" disabled="true"
																			path="strengths[${strengthStatus.index}].elements[${(elementStatus.index+1)}].selected"
																			 />
																		</td>
																	<td ><form:checkbox label="${strength.elements.get(elementStatus.index+2).label }" disabled="true"
																			path="strengths[${strengthStatus.index}].elements[${(elementStatus.index+2)}].selected"
																			 />
																		</td>

																</tr>
															</c:forEach>
														</table>
													</td>
												</tr>
											</c:forEach>
										</table>
									</div>
									<div id="growthTable">

										<table class="table white-background">
											<thead>
												<tr>
													<th scope="col">Area(s) of Growth</th>
													<th scope="col">Evidence/Rationale</th>
													<th scope="col" style="width: 30%">Element(s)</th>
												</tr>
											</thead>

											<c:forEach items="${assessmentInfo.growths}" var="growth"
												varStatus="growthStatus">
												<tr>
													<td><form:textarea   readonly="true"
															path="growths[${ growthStatus.index}].area" rows="5"
															cssClass="form-control" maxLength="4000" />
															<form:errors  path="growths[${ growthStatus.index}].area" cssClass="error"  />	
															</td>
													<td><form:textarea  cssClass="form-control" readonly="true"
															path="growths[${ growthStatus.index}].rationale" rows="5"
															 maxLength="4000" />
															<form:errors  path="growths[${ growthStatus.index}].rationale" cssClass="error"  />	
															</td>
													<td>
														<table class="table white-background no-border left-aligned">
															<c:forEach items="${growth.elements}" step="3"
																var="element" varStatus="elementStatus">
																<tr>
													 				<td ><form:checkbox label="${growth.elements.get(elementStatus.index).label }" disabled="true"
																			path="growths[${growthStatus.index}].elements[${elementStatus.index}].selected"
																			 />
																		</td>
																	<td ><form:checkbox label="${growth.elements.get(elementStatus.index+1).label }" disabled="true"
																			path="growths[${growthStatus.index}].elements[${(elementStatus.index+1)}].selected"
																			 />
																		</td>
																	<td ><form:checkbox label="${growth.elements.get(elementStatus.index+2).label }" disabled="true"
																			path="growths[${growthStatus.index}].elements[${(elementStatus.index+2)}].selected"
																			 />
																		</td>

																</tr>
															</c:forEach>
														</table>
													</td>		
												</tr>



											</c:forEach>										
										</table>
									</div>
								</div>
							</div>
						</div>

		</div>
		<input type="hidden" name="scrollX" id="scrollX" value="" />
		<input type="hidden" name="scrollY" id="scrollY" value="" />
	
		<div class="form-group row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				
			</div>
			<div class="col-sm-4">
			<div style="margin: 10px">
					<input type="submit" value="Cancel" name="command" 	class="btn btn-primary" /> 
					<input type="submit" value="Next" 	name="command"  class="btn btn-primary" />
					<input type="hidden" value="0" 	name="prev" />
					<input type="hidden" value="0" 	name="curr" />
					<input type="hidden" value="1" 	name="nxt" />
				</div></div>
		</div>
	</form:form>