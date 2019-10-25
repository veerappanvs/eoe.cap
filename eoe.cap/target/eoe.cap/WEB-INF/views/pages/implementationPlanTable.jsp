<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
<head>
</head>
<body>
	<form:form id="form2" method="POST" commandName="goalPlanInfo">
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
	</form:form>
</body>
</html>