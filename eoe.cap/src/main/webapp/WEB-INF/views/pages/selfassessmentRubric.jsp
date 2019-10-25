<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		
		var activeTab='${assessmentInfo.activeElementCode}';
		
		$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {			 
			var activeTab=e.target.id.split('-')[0];
			$('#activeElementCode').val(activeTab);
			
			})
			
			$('#'+activeTab+'-tab').tab('show');
		
		window.scroll(${scrollX!=null?scrollX:0},${scrollY!=null?scrollY:0});
		alertUser();
		
	}
</script>

	<form:form id="form2" method="POST"
		commandName="assessmentInfo">
		<div id="error-section" style="margin-bottom: 25px" class="error">
			<form:errors />
		</div>

		<form:hidden path="activeElementCode" />

		<jsp:include page="cycleInfo.jsp"></jsp:include>
		
		<div class="line" style="margin: 10px 0; color: black"></div>

<div class="form-group row">
			<div class="col-sm-12">
		<div class="cap-tabs">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<c:forEach items="${assessmentInfo.elements}"
					var="element" varStatus="elementIndex">				
					<li class="nav-item" style="width: 120px;margin-bottom:10px"><a
						class="nav-link ${ (( elementIndex.first  && !assessmentInfo.enableCompletion ) || (elementIndex.last && assessmentInfo.enableCompletion)) ?'active':'' }"
						id="${element.elementCode}-tab" data-toggle="tab"
						href="#${element.elementCode}-info" role="tab"
						aria-controls="${element.elementCode}-info" aria-selected="true">
						<span style="font-size:.9rem" >${element.altDesc}</span></a></li>
				</c:forEach>

			</ul>


			<div class="tab-content" id="myTabContent">
				<c:forEach items="${assessmentInfo.elements}"
					var="element" varStatus="elementIndex">
					<div
						class="tab-pane fade ${(( elementIndex.first  && !assessmentInfo.enableCompletion ) || (elementIndex.last && assessmentInfo.enableCompletion)) ?'show active':'' }"
						id="${element.elementCode}-info" role="tabpanel"
						aria-labelledby="${element.elementCode}-tab">

						<table class="table white-background">
							<thead>
								<tr>
									<th scope="col" style="text-align: center">${element.altDesc }</th>

									<c:forEach items="${element.ratingInfos}" var="ratingInfo">
										<th scope="col">${ratingInfo.shortDesc}<p style="font-weight:normal" >${ratingInfo.longDesc}</p></th>
									</c:forEach>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${element.dimensions}" var="dimension"
									varStatus="dimensionIndex">
									<tr>
										<td style="text-align: center"><b>${dimension.dimensionDesc}</b>
											<p>${dimension.dimensionLongDesc}</p></td>
										<c:forEach items="${element.ratingInfos}" var="ratingInfo">
											<td style="text-align: center"><form:radiobutton
													path="elements[${elementIndex.index }].dimensions[${dimensionIndex.index }].ratingCode"
													value="${ratingInfo.ratingCode }" aria-label="Please select rating if applicable"/></td>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div style="margin: 50px;text-align: right;">
							<c:if test="${!elementIndex.first }" ><input type="button" value="Back" name="Back" class="btn btn-primary"  onclick="$('#${assessmentInfo.elements[elementIndex.index-1].elementCode}-tab').tab('show');" /></c:if>
							<input type="submit" value="Cancel" name="Cancel" class="btn btn-primary" />
							<c:if test="${!elementIndex.last}" ><input type="button" value="Next" name="Next" class="btn btn-primary"  onclick="$('#${assessmentInfo.elements[elementIndex.index+1].elementCode}-tab').tab('show');" /></c:if>
							<c:if test="${elementIndex.last}" ><input type="submit" value="Save" name="Save" class="btn btn-primary" /></c:if>
							<c:if test="${elementIndex.first && assessmentInfo.enableCompletion}" ><input type="submit" value="Complete Rubric" name="complete" class="btn btn-primary" /></c:if>
						</div>

					</div>
				</c:forEach>
			</div>
		</div>
</div>
</div>


	</form:form>

