<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<script type="text/javascript">
function pageLoad(){
	$('#meetingDate1').datepicker();
	$('#meetingDate2').datepicker();
	$('#meetingDate3').datepicker();
	alertUser();
}
	
	function formSubmit(meetingNumber){
		$("#form2").attr("action", "/cap/threewaymeeting?cycleId=${cycleId}&meetingNumber="+meetingNumber); 
		$('#form2').submit();
	}
	
</script>

<form:form id="form2" method="POST" commandName="threewayMeeting">

	<jsp:include page="cycleInfo.jsp"></jsp:include>

	<div class="line"></div>



	<div class="form-group row">
		<div class="col-sm-1"></div>
		<div class="col-sm-4">
			<div class="col-form-label float-right font-weight-bold">First
				Three-Way Meeting</div>
		</div>
		<div class="col-sm-2">
			<form:input path="meetingDate1" cssClass="form-control"
				placeholder="mm/dd/yyyy" aria-label="First Three-Way Meeting" />
		</div>
		<div class="col-sm-4"></div>
		<div class="col-sm-1"></div>
	</div>

	<div class="form-group row" style="text-align: center">
		<div class="col-sm-12">
			<form:errors path="meetingDate1" cssClass="error" />
		</div>
	</div>


	<div class="form-group row" style="text-align: center">
	<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div style="text-align: left">
				<span class="font-weight-bold d-block">Notes:</span>
			</div>
			<div style="text-align: left">
				<p class="d-block">(optional)</p>
			</div>
			<form:textarea maxlength="4000" rows="3" cssClass="form-control"
				aria-label="first three way meeting notes" path="meetingNotes1" />
				<form:errors path="meetingNotes1" cssClass="error" />
		</div>
		<div class="col-sm-1"></div>
	</div>



	<div class="form-group row">
		<div class="col-sm-12" style="text-align: center">
			<div>
				<input type="submit" value="Save" class="btn btn-primary"
					onclick="formSubmit(1)" />
			</div>
		</div>
	</div>

	<div class="line"></div>

	<div class="form-group row">
		<div class="col-sm-1"></div>
		<div class="col-sm-4">
			<div class="col-form-label float-right font-weight-bold">Second
				Three-Way Meeting</div>
		</div>
		<div class="col-sm-2">
			<form:input path="meetingDate2" cssClass="form-control"
				placeholder="mm/dd/yyyy" aria-label="Second Three-Way Meeting" />
		</div>
		<div class="col-sm-4"></div>
		<div class="col-sm-1"></div>
	</div>
	<div class="form-group row" style="text-align: center">
		<div class="col-sm-12">
			<form:errors path="meetingDate2" cssClass="error" />
		</div>
	</div>

	<div class="form-group row" style="text-align: center">
	<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div style="text-align: left">
				<span class="font-weight-bold d-block">Notes:</span>
			</div>
			<div style="text-align: left">
				<p class="d-block">(optional)</p>
			</div>
			<form:textarea maxlength="4000" rows="3" cssClass="form-control"
				aria-label="Second three way meeting notes" path="meetingNotes2" />
				<form:errors path="meetingNotes2" cssClass="error" />
		</div>
		<div class="col-sm-1"></div>
	</div>

	<div class="form-group row">
		<div class="col-sm-12" style="text-align: center">
			<div>
				<input type="submit" value="Save" class="btn btn-primary"
					onclick="formSubmit(2)" />
			</div>
		</div>
	</div>

	<div class="line"></div>


	<div class="form-group row">
		<div class="col-sm-1"></div>
		<div class="col-sm-4">
			<div class="col-form-label float-right font-weight-bold">Third
				Three-Way Meeting</div>
		</div>
		<div class="col-sm-2">
			<form:input path="meetingDate3" cssClass="form-control"
				placeholder="mm/dd/yyyy" aria-label="Third Three-Way Meeting" />
		</div>
		<div class="col-sm-4"></div>
		<div class="col-sm-1"></div>
	</div>

	<div class="form-group row" style="text-align: center">
		<div class="col-sm-12">
			<form:errors path="meetingDate3" cssClass="error" />
		</div>
	</div>

	<div class="form-group row" style="text-align: center">
	<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div style="text-align: left">
				<span class="font-weight-bold d-block">Notes:</span>
			</div>
			<div style="text-align: left">
				<p class="d-block">(optional)</p>
			</div>
			<form:textarea maxlength="4000" rows="3" cssClass="form-control"
				aria-label="Third three way meeting notes" path="meetingNotes3" />
				<form:errors path="meetingNotes3" cssClass="error" />
		</div>
		<div class="col-sm-1"></div>
	</div>

	<div class="form-group row">
		<div class="col-sm-12" style="text-align: center">
			<div>
				<input type="submit" value="Save" class="btn btn-primary"
					onclick="formSubmit(3)" />
			</div>
		</div>
	</div>


</form:form>