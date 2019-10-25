<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">



	function pageLoad() {
		alertUser();
	}
	
	
	
</script>
</head>
<body>

	<form:form id="form2" method="POST" commandName="messageInfo">

		<jsp:include page="cycleInfo.jsp"></jsp:include>


		<div class="line" style="margin: 10px 0; color: black"></div>

		<div class="form-group row">
			<div class="col-sm-12">
				<form:textarea path="message" cssClass="form-control" rows="2"  maxlength="250" aria-label="Please enter a message you wish to send"/>
				<form:errors path="message" cssClass="error"  />
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<input type="submit" value="Send" class="btn btn-primary" />
			</div>
		</div>

		<div class="line" style="margin: 10px 0; color: black"></div>

		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<h4>Messages</h4>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-12">
				<c:forEach var="message" items="${messages}">
					<div class="message-container">
						<p>${message.message}</p>
						<span class="time-right">From ${message.messageFrom} - <fmt:formatDate	pattern="MMM dd, yyyy - hh:mm a" value="${message.messageDate}" /></span>
					</div>
				</c:forEach>
			</div>
		</div>

	</form:form>

</body>
</html>