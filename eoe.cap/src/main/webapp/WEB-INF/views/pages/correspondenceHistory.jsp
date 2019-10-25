<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


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
var noDataFound="<spring:message code="correspondence.empty.result"/>";

function reSendEmail(resendEmailid, cycleId){
	 console.log('resending');
	 $.get('resendemail?communicationId='+resendEmailid+'&cycleId='+cycleId, 
	 function( data ) {
		 location.reload();		  
		}
	 );
}

function showEmailContent(communicationId, cycleId){
	
		$.get( 'emailbody?communicationId='+communicationId+'&cycleId='+cycleId, function( data ) {
				if(data){
					var win = window.open('', '_blank', '', 'true');
					win.document.body.innerHTML = data;
				}
				console.log('Email body loaded');
				});
}

function initDatatTable(){
	

	var resultDatatable = '';
	
	if ( $.fn.dataTable.isDataTable( '#resultDatatable' ) ) {
		resultDatatable = $('#resultDatatable').DataTable();
		console.log ('datatable already populated');
	}
	else {
		resultDatatable = $('#resultDatatable').DataTable();
			console.log ('datatable assigned new');
	}
	
	console.log('resultDatatable '+resultDatatable)
				$('#exportData').text('');
		var buttons = new $.fn.dataTable.Buttons(resultDatatable, {
			buttons : [ 
			
				{
					extend : 'excel',
					title : 'CAP Correspondences',
					text : '<i class="fal fa-file-excel fa-1x"></i>',
					className : 'btn btn-primary',
					exportOptions : {
						  columns : [ 0, 1, 2, 3 ]
					},				
					 customize: function(xlsx) {
							var ocellXfs = $('cellXfs', xlsx.xl['styles.xml']);
							ocellXfs.append('<xf numFmtId="0" fontId="2" fillId="0" borderId="0" applyFont="1" applyFill="1" applyBorder="1" xfId="0" applyAlignment="1"><alignment horizontal="center" /></xf>')
							var sheet = xlsx.xl.worksheets['sheet1.xml'];
							$('row c', sheet).attr( 's', '51' ); 
							$('c',$('row', sheet)[0]).attr('s','67');
							$('c',$('row', sheet)[1]).attr('s','67');
						  }
				},
				
				
				{
					extend : 'pdf',
					title : 'CAP Correspondences',
					text : '<i class="fal fa-file-pdf fa-1x"></i>',
					orientation : 'landscape',
					pageSize: 'LEGAL',
					className : 'btn btn-primary',		
					exportOptions: {
						columns : [ 0, 1, 2, 3  ]					
					},					
					customize: function ( doc ) {
						doc.styles.tableBodyEven.alignment = 'center';
						doc.styles.tableBodyOdd.alignment = 'center';
					}
					
					
				},

				{
					extend : 'print',
					title : 'CAP Correspondences',
					text : '<i class="fal fa-print fa-1x"></i>',
					orientation : 'landscape',
					pageSize : 'LEGAL',
					className : 'btn btn-primary', 
					exportOptions: {
						columns : [ 0, 1, 2, 3  ]					
					}					
				} 
			
			]
		}).container().appendTo($('#exportData'));
}

function pageLoad() {
			
		initDatatTable();					
}
	 	
	
	
</script>
<title></title>
</head>
<body>
	<div id="error-section" style="margin-bottom:25px" ></div>
	<form:form id="correspondenceHistoryForm"  method="GET" commandName="capCycleInfo">
	
			<div class="form-group row">
	<div class="col-sm-1"></div>
	<div class="col-sm-2">
		<div class="col-form-label float-right font-weight-bold">Program
			Name:</div>
	</div>
	<div class="col-sm-3">
		<div class="col-form-label">${capCycleInfo.programName}</div>
	</div>
	<div class="col-sm-3">
		<div class="col-form-label float-right font-weight-bold"
			style="text-align: right">Supervising Practitioner:</div>
	</div>
	<div class="col-sm-3">
		<div class="col-form-label"> 
		<c:forEach items="${capCycleInfo.practitioners}" var="practitioner">
			${practitioner.firstName} ${practitioner.lastName}<br>
		</c:forEach>
		</div>
	</div>
</div>


<div class="form-group row">
	<div class="col-sm-1"></div>
	<div class="col-sm-2">
		<div class="col-form-label float-right font-weight-bold">Teacher
			Candidate:</div>
	</div>
	<div class="col-sm-3">
		<div class="col-form-label">${capCycleInfo.candidateFirstName}
			${capCycleInfo.candidateLastName}</div>
	</div>
	<div class="col-sm-3">
		<div class="col-form-label float-right font-weight-bold">Program
			Superviser:</div>
	</div>
	<div class="col-sm-3">
		<div class="col-form-label">${capCycleInfo.supervisorFirstName}
			${capCycleInfo.supervisorLastName}</div>
	</div>
</div>


<div class="form-group row">
	<div class="col-sm-1"></div>
	<div class="col-sm-2">
		<div class="col-form-label float-right font-weight-bold">Cycle
			Start Date:</div>
	</div>
	<div class="col-sm-3">
		<div class="col-form-label"><fmt:formatDate
				pattern="MM/dd/yyyy" value="${capCycleInfo.startDate}" /></div>
	</div>
	<div class="col-sm-3"></div>
	<div class="col-sm-3"></div>
</div>

<hr>

		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<h4>Correspondence History</h4>
			</div>
		</div>	
		

		<div id="exportData" style="text-align: right; padding: 10px"></div>
				<div id="resultWrapper"></div>

	
				<c:if test="${capCycleInfo.correspondenceInfo!=null}">	
					
					<table id="resultDatatable"	class="table table-hover table-bordered table-striped">
						<thead>
							<tr>
								<th><strong>Recipient Name</strong></th>
								<th><strong>Topic</strong></th>
								<th><strong>Email Address</strong></th>
								<th><strong>Sent Date</strong></th>
								<th><strong>Action</strong></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${capCycleInfo.correspondenceInfo}" var="correspondence">
								<tr>
								    <td><a href="#" onclick="showEmailContent(${correspondence.capEmailId},  ${cycleId} )"	>${correspondence.fullName}</a></td>
									<td>${fn:substringAfter(correspondence.emailSubject, 'CAP Online Platform: ' )}</td>
									<td>${correspondence.toEmailAddr}</td>
									<td><fmt:formatDate pattern="MM/dd/yyyy - hh:mm a"  value="${correspondence.lastUdateDate}" />  </td>
									<td><input id="resend" type="button" class="btn btn-primary" onclick="reSendEmail(${correspondence.capEmailId}, ${cycleId} )"	value="Resend" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if> 
		
		
	</form:form>
</body>
</html>