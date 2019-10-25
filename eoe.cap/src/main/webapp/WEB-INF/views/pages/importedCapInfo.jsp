<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	function pageLoad() {

		if(${importedCapInfo.schoolYear}  >= getCurrentSchoolyear()) { 
				$("#deleteCycle").show();
		}
		

		
	}
</script>
<title></title>
</head>
<body>
	<form:form id="form2" method="POST" commandName="importedCapInfo">

		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Teacher
					Candidate Name:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${importedCapInfo.candidateName}</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Teacher
					Candidate MEPID:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${importedCapInfo.candidateMepid}</div>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Supervising
					Practitioner:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${importedCapInfo.practitionerName}</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Supervising
					Practitioner email:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${importedCapInfo.practitionerEmail}</div>
			</div>
		</div>


		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Program
					Supervisor:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${importedCapInfo.supervisorName}</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Practicum
					School:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${importedCapInfo.schoolName}</div>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Date
					of Import:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">
					<fmt:formatDate pattern="MM/dd/yyyy"
						value="${importedCapInfo.importDate}" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Practicum
					District:</div>
			</div>
			<div class="col-sm-3">
				<div class="col-form-label">${importedCapInfo.districtName}</div>
			</div>
		</div>

		<div class="line"></div>


		<div class="form-group row">
			<div class="col-sm-12">
				<table class="table white-background">
					<thead>
						<tr>
							<th scope="col" colspan="${dimensions.size()+1 }">Formative
								Rubric Summary</th>
						</tr>
						<tr>
							<th scope="col">Element</th>
							<c:forEach items="${dimensions}" var="dimension">
								<th scope="col">${dimension.dimensionDesc }</th>
							</c:forEach>
						</tr>
					</thead>

					<c:forEach items="${importedCapInfo.formativeElements}"
						var="element">
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
					<thead>
						<tr>
							<th scope="col" colspan="${dimensions.size()+1 }">Summative
								Rubric Summary</th>
						</tr>
						<tr>
							<th scope="col">Element</th>
							<c:forEach items="${dimensions}" var="dimension">
								<th scope="col">${dimension.dimensionDesc }</th>
							</c:forEach>
						</tr>
					</thead>

					<c:forEach items="${importedCapInfo.summativeElements}"
						var="element">
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
			<div class="col-sm-3">
				<div class="col-form-label float-right font-weight-bold">Ready to Teach? :</div>
			</div>
			<div class="col-sm-3">  
			       <div class="col-form-label">${importedCapInfo.readyToTeach}</div> </div>
		</div>
		
		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center;">
				<div  style="margin: 50px">
					<button id="deleteCycle" type="button" class="btn btn-primary" data-toggle="modal"  style="display:none"
						data-target="#exampleModal">Delete</button>
					<input type="submit" value="Back" name="Back"
						class="btn btn-primary" />
				</div>
			</div>
		</div>

		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div  class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Delete</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
						</button>
					</div>
					<div class="modal-body">Do you want to permanently delete
						this record?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							data-dismiss="modal">No</button>
						<input type="submit" name="Delete" value="Yes" style="margin-left:10px"
							class="btn btn-primary">
					</div>
				</div>
			</div>
		</div>

	</form:form>
</body>
</html>
