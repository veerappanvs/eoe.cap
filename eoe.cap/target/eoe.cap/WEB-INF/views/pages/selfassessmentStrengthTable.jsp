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

	<form:form id="form2" method="POST" commandName="assessmentInfo">
	<div  id="implementationPlans">
	
								<table class="table white-background">
								<thead>
									<tr>
										<th scope="col">Area(s) of Strength</th>
										<th scope="col">Evidence/Rationale</th>
										<th scope="col" style="width: 30%" >Element(s)</th>
										<th scope="col" style="width: 3%"></th>
									</tr>
								</thead>

								<c:forEach items="${assessmentInfo.strengths}" var="strength"
									varStatus="strengthStatus">
									<tr>
										<td><form:textarea
												path="strengths[${ strengthStatus.index}].area" rows="5"
												cssClass="form-control" maxLength="4000" /></td>
										<td><form:textarea
												path="strengths[${ strengthStatus.index}].rationale" rows="5"
												cssClass="form-control" maxLength="4000" /></td>
										<td>
											<table class="table">
												<c:forEach items="${strength.elements}" step="3"
													var="element" varStatus="elementStatus">
													<tr>
														<td style="border: initial; text-align: initial;"><form:checkbox
																path="strengths[${strengthStatus.index}].elements[${elementStatus.index}].selected"
																cssStyle="margin-right:5px" />
															${strength.elements.get(elementStatus.index).label }</td>
														<td style="border: initial; text-align: initial;"><form:checkbox
																path="strengths[${strengthStatus.index}].elements[${(elementStatus.index+1)}].selected"
																cssStyle="margin-right:5px" />
															${strength.elements.get(elementStatus.index+1).label }</td>
														<td style="border: initial; text-align: initial;"><form:checkbox
																path="strengths[${strengthStatus.index}].elements[${(elementStatus.index+2)}].selected"
																cssStyle="margin-right:5px" />
															${strength.elements.get(elementStatus.index+2).label }</td>

													</tr>
												</c:forEach>
											</table>
										</td>
											<td><a href="#" role="button"  
											onclick="deleteRow('strengthTable','1',${strengthStatus.index});return false;">
												<i class="far fa-trash-alt fa-1x" style="color: black;margin-left: -4px;"></i>
										</a></td>
									</tr>
								</c:forEach>
								<c:if test="${assessmentInfo.canAddStrengths}">
								<tr>
									<td colspan="4"><input type="button" value="Add Row"
										name="Add" class="btn btn-primary"
										onclick="addRow('strengthTable','1')" /></td>
								</tr>
								</c:if>
							</table>
							</div>
	</form:form>