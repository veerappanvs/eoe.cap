<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
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

<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script>



<script>
var noDataFoundforMEPID="<spring:message code="verifycycle.empty.mepid.result"/>";
var noDataFoundforPrograms="<spring:message code="verifycycle.empty.programs.result"/>";


function initdatatable(){
	

	var resultDatatable = '';
	
	if ( $.fn.dataTable.isDataTable( '#resultTcDatatable' ) ) {
		resultDatatable = $('#resultTcDatatable').DataTable();
		console.log ('datatable already populated');
	}
	else {
		resultDatatable = $('#resultTcDatatable').DataTable();
			console.log ('datatable assigned new');
	}
	
	console.log('resultDatatable '+resultDatatable)
				$('#exportTcData').text('');
		var buttons = new $.fn.dataTable.Buttons(resultDatatable, {
			buttons : [ 
			
			{
				extend: 'csv',
				title : 'CAP Cycles',
				text: '<i class="fal fa-file-excel fa-1x"></i>',
				className : 'btn btn-primary',
				exportOptions: {
					columns : [ 0, 3, 4, 5, 6, 1, 2  ]					
				}
			}]
		}).container().appendTo($('#exportTcData'));
}



$(function() {
	
$('#verifyType').change(function() {
			
    opt = $(this).val();
    if (opt=="tcandprogopt") {

		resetSp();
		$('#practitioner-section').hide();
		$('#tcandprograminformation-section').show();
		


    }else if (opt == "spandpactopt") {

		resetTc();
		loadPractitionerModal('modifyDistrictType','districtOrgTypeId');
		$('#practitionerInputMEPID').val('');		
		$('#tcandprograminformation-section').hide();		
		$('#practitioner-section').show();
    }
	else {
		resetAll();
		
	}

	});
});


function resetTc(){
	
		$('#exportTcData').text('');			
		$('#selectProgramId').val('');
		$('#inputmepid').val('');	
		$('#resultTcWrapper').text('');	
		$('#error-section').text('');	 

}

function resetSp(){
		
		$('#districtOrgTypeId').val('');
		$('#practitionerInputMEPID').val('');		
		$('#districtOrgId').val('');		
		$('#schoolOrgId').val('');	
		$('#resultSpWrapper').text('');		
		//$('#practitioner-section').text("");	
}

function resetAll(){
	
		resetTc();
		resetSp();
		$('#verifyType').val(0);
		$('#tcandprograminformation-section').hide();
		$('#practitioner-section').hide();

}


function pageLoad() {
			console.log('loading the cerify cycle page');
			resetAll();	
			loadDropDownWithSortOrder('verifycycle/sponsoringorgs','selectSponsoringOrgId');
			loadDropDownWithSortOrder('verifycycle/programs','selectProgramId');
			
			
			$('#selectSponsoringOrgId').change(function() {
							
				//$('#resultTcWrapper').text('');			
				//$('#selectProgramId').val('');
				//$('#inputmepid').val('');
				//$('#verifyType').val(0);
				resetAll();
				var uri = 'verifycycle/setsponsoringid?selectSponsoringOrgId='+$('#selectSponsoringOrgId').val();
				$.ajax({
					type : "GET",
					url : uri,
					 async: false,
					success : function(response) {
						console.log('Set the org id  complete');
						loadDropDownWithSortOrder('verifycycle/programs','selectProgramId');
					},
					error : function(response) {
						console.log('Error occured while setting the org Id for the SO');
					}
				});
				
				
			});
					
			$('#selectProgramId').change(function() {
					$('#tcandprograminferror-section').hide();
					console.log('selectProgramId selected ' +$('#selectProgramId').val());
					loadDataTable('verifycycle/programid','resultTc','selectProgramId',noDataFoundforPrograms);
					$('#inputmepid').val('');
					initdatatable();
			});

					
			inputmepid.oninput = function(){
				$('#tcandprograminferror-section').hide();
				$('#selectProgramId').val('');
				console.log('oninput mepid selected. Value='+$('#inputmepid').val()+' length='+$('#inputmepid').val().length);
				if($('#inputmepid').val().length  == 8){
						console.log('mepid selected. Value='+$('#inputmepid').val()+' length='+$('#inputmepid').val().length);
						console.log($('#inputmepid').val().length);
						loadDataTable('verifycycle/mepid','resultTc','inputmepid',noDataFoundforMEPID);	
											initdatatable();	
					}
					else{
						loadDataTable('verifycycle/mepid','resultTc','inputmepid',noDataFoundforMEPID);	
					}
			};	
			
							$('#searchpractitioner')
				.click(
						function() {
							fetchSupervisor();

						});
			
}
			
	
	
</script>
<title></title>
</head>
<body>
	<div id="error-section" style="margin-bottom:25px" ></div>
	<form:form id="verifyCycleForm"  method="GET" commandName="verifyCycleCandidateEnrollment">
		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<h4>Verify Cycle Information</h4>
			</div>
		</div>
		<div style="text-align: center; padding: 10px">
			<p>
			<span style="font-size: .9rem;"><b>Note:</b><sec:authorize access="hasAnyRole('ROLE_ADMIN')"> Verify cycle information data for a particular organization.</sec:authorize><sec:authorize access="hasAnyRole('ROLE_MANAGER')"> Verify cycle information data in the Cap Upload Spreadsheets to ensure smooth upload process.</sec:authorize></span>
			</p>
		</div>
		
		<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">

		<div class="form-group row"> 
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<div class="col-sm-3">
				<div class="col-form-label-bold-head float-right font-weight-bold">Select Sponsoring Organization:</div>
			</div>
			<div class="col-sm-3">
				<form:select id="selectSponsoringOrgId" path="selectSponsoringOrgId"
					 class="form-control form-control-sm" aria-label="Please select" >
					 <form:option value="">All</form:option>
				</form:select>
			</div>
		</sec:authorize>
			<div class="col-sm-3">
				<div class="col-form-label-bold-head float-right font-weight-bold ">What would you like to verify?</div>
			</div>
			
			<div class="col-sm-3" >
				  <select class="form-control" id="verifyType">
					   <option value="0">Please select</option>       
					   <option value="tcandprogopt">TC and Program Information</option>
					   <option value="spandpactopt">SP and Practicum Information</option>
				  </select>
			</div>


		</div>
		</sec:authorize>
		<div id="tcandprograminformation-section"  style="display:none">
			<jsp:include page="verifyTc.jsp"></jsp:include>	
		</div>
		<div id="practitioner-section" style="display:none" >
				<jsp:include page="verifyPractitioner.jsp"></jsp:include>				
		</div>
		<div id="resultTcWrapper"  class="float-center"></div>

		<div id="resultSpWrapper"  class="float-center"></div>


		<form:hidden id="programCompleterId" path="programCompleterId" />
		<input id="pageId" type="hidden" value="0" name="_page" />
		<input type="hidden" value="Next" name="_command" />
	</form:form>
</body>
</html>