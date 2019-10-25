<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:useBean id="doeCommonData"
	class="edu.mass.doe.doecommon.startup.DoeCommonData"
	scope="application" />
<%
	String vision_css_url = doeCommonData.getMainCSSFileURL().trim();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Role</title>
<link rel="stylesheet" href="/cap/css/vision.css">
<link rel="stylesheet" href="/cap/css/bootstrap.min.css">
<script type="text/javascript" src="/cap/script/jquery-3.4.0.min.js"></script>
<script type="text/javascript" src="/cap/script/popper.min.js"></script>
<script type="text/javascript" src="/cap/script/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/cap/css/datatables.min.css" />
<link rel="stylesheet" type="text/css" href="/cap/css/main.css" />
<link rel="stylesheet" type="text/css" href="/cap/css/fontawesome.css" />
<link rel="stylesheet" type="text/css"
	href="/cap/css/bootstrap-datepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="/cap/css/bootstrap-timepicker.min.css" />
<script type="text/javascript" src="/cap/script/datatables.min.js"></script>
<script type="text/javascript"
	src="/cap/script/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="/cap/script/bootstrap-timepicker.min.js"></script>
<script type="text/javascript" src="/cap/script/util.js"></script>
<script type="text/javascript" src="/cap/script/jquery.cookie-1.4.1.js"></script>
<script type="text/javascript">
var timeoutUserNotice=${timeoutUserNotice};
var sessionTimeout=${pageContext.session.maxInactiveInterval}
var sessionTimeoutCountdown=${sessionScope.sessionTimeoutCountdown}
function pageLoad() {
	$('input[name^="selectedRole"]').change(function () {   
		 $("#roleSubmit").attr("disabled", false);
	});
}
</script>
<script type="text/javascript" src="/cap/script/cap.js"></script>
</head>
<body>
	<div>
		<%=doeCommonData.getHeaderFileOne()%>
	</div>

	<div class="wrapper">
		<div id="content">
			<div
				style="min-height: calc(100% - 250px); background: white; padding: 10px;">
				<form action="/cap/role" method="post">
					<div class="form-group row">
						<div class="col-sm-12" >
							<h4>Select Role to proceed</h4>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm-12" style="margin-left: 100px">
							<c:forEach items="${roles}" var="role" varStatus="status">
								<div class="form-group row">
									<div class="col-sm-12">
									<c:if test="${role eq 'ROLE_ADMIN' }">
									<input id="${status.index }role" class="form-check-input"
											type="radio" value="${role}" name="selectedRole"> <label
											class="form-check-label" for="${status.index }role">CAP Admin Manager</label>
									</c:if>
									
									<c:if test="${role eq 'ROLE_CANDIDATE' }">
									<input id="${status.index }role" class="form-check-input"
											type="radio" value="${role}" name="selectedRole"> <label
											class="form-check-label" for="${status.index }role">Teacher Candidate</label>
									</c:if>
									
									<c:if test="${role eq 'ROLE_MANAGER' }">
									<input id="${status.index }role" class="form-check-input"
											type="radio" value="${role}" name="selectedRole"> <label
											class="form-check-label" for="${status.index }role">CAP Manager</label>
									</c:if>
									
									<c:if test="${role eq 'ROLE_SUPERVISOR' }">
									<input id="${status.index }role" class="form-check-input"
											type="radio" value="${role}" name="selectedRole"> <label
											class="form-check-label" for="${status.index }role">Program Supervisor</label>
									</c:if>
									
									<c:if test="${role eq 'ROLE_PRACTITIONER' }">
									<input id="${status.index }role" class="form-check-input"
											type="radio" value="${role}" name="selectedRole"> <label
											class="form-check-label" for="${status.index }role">Supervising Practitioner</label>
									</c:if>
																	
										
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-sm-12" style="margin-left: 100px">
							<input id="roleSubmit" type="submit" value="Submit" name="Submit" disabled="false"
								class="btn btn-primary" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div>
		<%=doeCommonData.getFooter()%>
	</div>
</body>
</html>