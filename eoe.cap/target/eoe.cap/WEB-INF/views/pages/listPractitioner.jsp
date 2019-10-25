<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div style="margin-bottom: 25px" class="error">
	<form:errors path="*" />
</div>
<div id="postScript">
	<script type="text/javascript">
$('#practionerList').text('');
$('#practionerList').append($('#practitioner-section')[0].innerHTML);
$('#practionerListModal').modal('hide');
</script>
</div>

<form:form id="form1" method="POST" commandName="${commandName}">
	<table class="table white-background">
		<thead>
			<tr>
				<th>Name</th>
				<th>MEPID</th>
				<th>Practicum School</th>
				<th style="width: 12%">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${capCycleInfo.practitioners }" var="practitioner"
				varStatus="status">
				<tr>
					<td>${practitioner.firstName} ${practitioner.lastName}</td>
					<td>${practitioner.mepid}</td>
					<td>${practitioner.districtName}:${practitioner.schoolName}</td>
					<td style="text-align: center"><button type="button"
							class="btn btn-primary" data-toggle="modal"
							data-target="#practionerListModal" data-backdrop="static"
							onclick="loadPractitionerModal('loadPractitioner','','index=${status.index}');"
							data-keyboard="false">Change</button> <c:if
							test="${capCycleInfo.practitioners.size() gt 1 }">
							<button type="button" class="btn btn-primary float-right"
								style="margin-top: 10px; display: block"
								onclick="loadPractitionerModal('deletePractitioner','','index=${status.index}');saveForm();">Delete</button>
						</c:if></td>

				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" style="text-align: center; border: none"><c:if
						test="${capCycleInfo.practitioners.size() lt 2}">
						<button type="button" class="btn btn-primary" style="margin: 10px"
							data-toggle="modal" data-target="#practionerListModal"
							data-backdrop="static"
							onclick="loadPractitionerModal('addPractitioner','','index=${capCycleInfo.practitioners.size()}');"
							data-keyboard="false">Add Practitioner</button>
					</c:if></td>
			</tr>
		</tbody>

	</table>
</form:form>
