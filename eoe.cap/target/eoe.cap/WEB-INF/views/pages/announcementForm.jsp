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

	<form:form id="form2" method="POST" commandName="announcementInfo">
		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<h4>Announcement</h4>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-12">
				<span>News and Announcement:</span>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-12">
				<div class="message-container">
					<p>${message.text}</p>
					<c:if test="${message.announcementId ne null}">
						<span class="time-right">Posted on <fmt:formatDate
								pattern="MMM dd, yyyy - hh:mm a" value="${message.postedDate}" /></span>
					</c:if>
				</div>
			</div>
		</div>

		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<c:if test="${message.announcementId ne null}">
				<div class="form-group row">
					<div class="col-sm-12" style="text-align: center">
						<input type="submit" name="delete" value="Delete Post"
							class="btn btn-primary" />
					</div>
				</div>
			</c:if>
		</sec:authorize>

		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<div class="line" style="margin: 10px 0; color: black"></div>

			<div class="form-group row">
				<div class="col-sm-12" style="text-align: center">
					<h4>Post Announcement</h4>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-12">
					<form:textarea path="text" cssClass="form-control" rows="5"
						maxlength="4000" aria-label="Please enter an announcement you wish to post."/>
					<form:errors path="text" cssClass="error" />
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-12" style="text-align: center">
					<input type="submit" value="Post" class="btn btn-primary" />
				</div>
			</div>
		</sec:authorize>


	</form:form>

</body>
</html>