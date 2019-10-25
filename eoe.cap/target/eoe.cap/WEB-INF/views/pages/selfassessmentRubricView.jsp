<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	var res = '';

	function addRow(srcElement, attType) {

		var uri = '/cap/selfassessment/addrow?attType=' + attType
				+ '&cycleId=${assessmentInfo.cycleId}';

		$.ajax({
			type : "POST",
			url : uri,
			data : $('#form2').serialize(),
			async : false,
			success : function(response) {
				$('#' + srcElement).text('');
				$('#' + srcElement).append(
						$(response).find('#implementationPlans')[0]);
			}

		});

		return false;

	}

	function deleteRow(srcElement, attType, index) {

		var uri = '/cap/selfassessment/deleterow?attType=' + attType
				+ '&cycleId=${assessmentInfo.cycleId}&attIndex=' + index;

		$.ajax({
			type : "POST",
			url : uri,
			data : $('#form2').serialize(),
			async : false,
			success : function(response) {
				$('#' + srcElement).text('');
				$('#' + srcElement).append($(response).find('table')[0]);
			}

		});

		return false;

	}
	function pageLoad() {
	}
</script>
<title></title>
</head>
<body>
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
		<sec:authorize access="hasRole('ROLE_CANDIDATE')">
			<div class="form-group row">
				<div class="col-sm-12">
					<div style="margin: 50px; text-align: right;">
						<c:if test="${displayEdit}">
							<input type="submit" value="Edit Rubric" name="edit"
								class="btn btn-primary" />
						</c:if>
					</div>
				</div>
			</div>
		</sec:authorize>

	</form:form>
</body>
</html>
