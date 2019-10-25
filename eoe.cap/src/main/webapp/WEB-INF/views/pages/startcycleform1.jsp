
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>

var noDataFoundforMEPID="<spring:message code="empty.mepid.result"/>";
var noDataFoundforPrograms="<spring:message code="empty.programs.result"/>";


function startCycle(programCompleterId){
	
	$('#programCompleterId').val(programCompleterId);
	jQuery('#form1').attr('method', "post").submit();
	
}

function showCycle(programCompleterId, cycleId, fileUploadId){
	
	if(cycleId > 0){
		  $('#alertModal').modal('show');
		//$(location).attr('href', '/cap/cycle?cycleId='+cycleId);
	}
	else if (fileUploadId > 0 ){
		  $('#alertModal').modal('show');
	}
}

function pageLoad() {
			loadDropDownWithSortOrder('candidate/programs','selectProgramId');
			
			
			var sepid = '${candidateEnrollment.selectProgramId}';
			var inmepid = '${candidateEnrollment.inputmepid}';
			
			if(! (sepid =='' ) ){
				$('#selectProgramId').val(sepid);
				$('#inputmepid').val('');
				loadDataTable('candidate/programid','result','selectProgramId',noDataFoundforPrograms)	;
			}
			if(! inmepid == '' ){
				$('#selectProgramId').val('');
				$('#inputmepid').val(inmepid);
				loadDataTable('candidate/mepid','result','inputmepid',noDataFoundforMEPID)	;
			}
			
			
			
			$('#searchByProgram').click(function() {
				$('#inputmepid').val('');
				loadDataTable('candidate/programid','result','selectProgramId',noDataFoundforPrograms)	
				
			});
			
			$('#searchByMEPID').click(function() {
				$('#selectProgramId').val('');
				loadDataTable('candidate/mepid','result','inputmepid',noDataFoundforMEPID)	
				
			});

			
			}




	
	
</script>
<title></title>
</head>
<body>
	<div id="error-section" style="margin-bottom:25px" ></div>
	<div id="alertModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered modal-sm" style="min-width: 400px">
			<div class="modal-content">
				<div class="modal-header">	
						<h5 class="modal-title">Cycle already exists!<h5>
				</div>
				<div class="modal-body">
					The candidate has an existing cycle for the same program in the current school year. Please check with your Cap Manger for more details.
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal">Ok</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<form:form id="form1"  method="GET" commandName="candidateEnrollment">
		<div style="text-align: center; padding: 10px">
			<span style="font-size: 1.3rem; font-weight: bold">Teacher Candidate Search</span></br></br>
			<p>
			<span style="font-size: 1rem;"><b>Note:</b> Search by the Teacher Candidate's program or MEPID, then select the name of the Teacher Candidate to add to the Cycle.</span>
			</p>
			<p>&nbsp;</p>
		</div>

		<div class="form-group row"> 
		<div class="col-sm-1">
				</div>  
			<div class="col-sm-4">
				<form:select id="selectProgramId" path="selectProgramId"
					 class="form-control form-control-sm" aria-label="Please select a program" >
					 <form:option value="">Select</form:option>
				</form:select>
				<form:errors path="selectProgramId"  />
			</div>
			<div class="col-sm-2">
				<input id="searchByProgram" type="button" class="btn btn-primary"
					value="Search By Program">
			</div>
			<div class="col-sm-2">
				<form:input id="inputmepid" path="inputmepid" class="form-control form-control-sm"  maxlength="8" aria-label="Please enter a MEPID" />
			</div>
			<div class="col-sm-2">
				<button id="searchByMEPID" type="button" class="btn btn-primary">Search By MEPID</button>
			</div>
			<div class="col-sm-1">
				</div>
		</div>


		<div id="resultWrapper"></div>
		<form:hidden id="programCompleterId" path="programCompleterId" />
		
		<input type="hidden" value="0" name="_page" />
		<input type="hidden" value="Next" name="_command" />
	</form:form>

</body>
</html>