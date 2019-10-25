<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:useBean id="doeCommonData"
	class="edu.mass.doe.doecommon.startup.DoeCommonData"
	scope="application" />
<% String vision_css_url = doeCommonData.getMainCSSFileURL().trim(); %>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
<title><tiles:getAsString name="title" /></title>
<tiles:importAttribute name="page" toName="pageName" />
<script type="text/javascript" src="/cap/script/jquery-3.4.0.min.js"></script>
<script type="text/javascript" src="/cap/script/popper.min.js"></script>
<script type="text/javascript" src="/cap/script/bootstrap.min.js"></script>
<script type="text/javascript" src="/cap/script/jquery.cookie-1.4.1.js"></script>

<link rel="stylesheet" type="text/css"
	href="/cap/css/datatables.min.css" />

<link rel="stylesheet" type="text/css"
	href="/cap/css/bootstrap-datepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="/cap/css/bootstrap-timepicker.min.css" />
<script type="text/javascript" src="/cap/script/datatables.min.js"></script>
<script type="text/javascript"
	src="/cap/script/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="/cap/script/bootstrap-timepicker.min.js"></script>
<script type="text/javascript" src="/cap/script/cap.js"></script>
<script type="text/javascript" src="/cap/script/util.js"></script>
<script type="text/javascript" src="/cap/script/autosize.min.js"></script>

<link rel="stylesheet" href="/cap/css/vision.css">

<link rel="stylesheet" href="/cap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/cap/css/fontawesome.css" />
<link rel="stylesheet" type="text/css" href="/cap/css/bootstrap-datepicker.min.css" />
<link rel="stylesheet" type="text/css" href="/cap/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/fixedheader/3.1.5/css/fixedHeader.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/cap/css/cap.css" />

<script type="text/javascript">
var pageName='${pageName}';
var timeoutUserNotice=${timeoutUserNotice};
var sessionTimeout=${pageContext.session.maxInactiveInterval};
var sessionTimeoutCountdown=${sessionScope.sessionTimeoutCountdown};
var containsUnsavedUserChanges=${containsUnsavedUserChanges};
var isReportsMenuActive= ${not empty activateReportsMenu};
</script>
</head>
<body>
	<div>
		<%=doeCommonData.getHeaderFileOne()%>
	</div>
	<sec:authentication var="user" property="principal" />

	<div class="wrapper" style="width:${not empty wrapper_width?wrapper_width:'100%'}">
		<!-- Sidebar Holder -->
		<nav id="sidebar"   class="no-print">
			<div class="sidebar-header">
				<img src="/cap/images/capLogo200-WhtBgrnd.png" alt="CAP Online Tool" />
			</div>

			<tiles:insertAttribute name="menu" />

		</nav>

		<!-- Page Content Holder -->
		<div id="content">
			<nav class="navbar navbar-default">
				<div class="container-fluid">				
					<div class="navbar-header ">
						<h4>Welcome ${user.name}</h4>
					</div>

					<div class="collapse show" id="bs-example-navbar-collapse-1">
						<ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
							<sec:authorize
								access="hasAnyRole('ROLE_MANAGER', 'ROLE_SUPERVISOR', 'ROLE_PRACTITIONER', 'ROLE_ADMIN')">
								<li class="nav-item"><h4>
										<a class="nav-link p-2" href="#">${user.orgName}</a>
									</h4></li>
							</sec:authorize>
							<c:if test="${displayMessage}">	<li class="nav-item"><a href="/cap/message?cycleId=${cycleId}" aria-label="Please click here to open messages"><span><i class="fas fa-envelope"></i></span></a></li></c:if>
						</ul>
					</div>
				</div>
			</nav>

			<div>
				<div
					style="min-height: calc(100% - 250px); background: white; padding: 10px;">
					<div id="sessionTimeoutPolicy"  style="display:none">
						<div style="float: left; padding: 0 10 0 0">
							<span style="color: red"><i
								class="fa fa-exclamation-triangle fa-2x" aria-hidden="true"></i></span>
						</div>
						<div>
							<div style="color: red">Please be advised that the CAP
								Online Platform will automatically log out after an hour of
								inactivity. This may lead to a loss of unsaved data. To prevent
								this, please save your work frequently and especially if you
								leave this browser tab/window.</div>
						</div>
					</div>
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
	</div>
	<div>
		<%=doeCommonData.getFooter()%>
	</div>
	
	<jsp:include page="sessionAlert.jsp"></jsp:include>
		
</body>
</html>
