<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/fixedheader/3.1.5/js/dataTables.fixedHeader.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">


function initDatatTable(){
	var table = $('#resultDatatable').DataTable(
			{
				fixedHeader: true,
				"columns" : [ {
					"width" : "40px"
				}, {
					"width" : "60px"
				}, {
					"width" : "50px"
				}, {
					"width" : "40px"
				}, {
					"width" : "20px"
				}, {
					"width" : "20px"
				}, {
					"width" : "20px"
				}, {
					"width" : "20px"
				}, {
					"width" : "20px"
				}, {
					"width" : "20px"
				}, {
					"width" : "20px"
				}, {
					"width" : "20px"
				} ]
					});
	 
	 var buttons = new $.fn.dataTable.Buttons(table, {
	     buttons: [
	        {
        extend: 'excel',
        title: 'CAP Cycles',
        text: '<i class="fal fa-file-excel fa-1x"></i>',
		orientation: 'landscape',
		pageSize: 'LEGAL',
        className: 'btn btn-primary',				
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
            extend: 'pdf',
            title: 'CAP Cycles',
            text: '<i class="fal fa-file-pdf fa-1x"></i>',
			orientation: 'landscape',
			pageSize: 'LEGAL',
            className: 'btn btn-primary',
            customize: function ( doc ) {
				doc.styles.tableBodyEven.alignment = 'center';
				doc.styles.tableBodyOdd.alignment = 'center';
            	}
        	},
        	{
                extend: 'print',
                title: 'CAP Cycles',
                text: '<i class="fal fa-print fa-1x"></i>',
				orientation: 'landscape',
				pageSize: 'LEGAL',
                className: 'btn btn-primary'
            	}
	    ]
	}).container().appendTo($('#exportData'));
	
}
function pageLoad() {
			
			initDatatTable();
			 $("#selectYear").click(function(){
					$(this).attr('href', "/cap/home/getPsView?selectedYear="+$("#capyears").val()+"&selectedStatus="+$("#statusTypes").val());
				});
				
				$("#statusTypes").change(function(){		
			if($("#statusTypes").val()=== 'Open'){
				$("#capyears").val('0');
				$("#capyears").attr("disabled","disabled");
			}else{
				$("#capyears").removeAttr("disabled");
				$("#capyears").val("${currentYear}");
			}
		});
				
				
		}
		
		

</script>
</head>
<body>
	<div id="error-section" style="margin-bottom: 25px"></div>
		
		<div style="text-align: center; padding: 10px">
			<h5>CAP Cycles</h5>
		</div>

	<div class="form-group row">
		<div class="col-sm-12" style="text-align: center;">
			<div class="form-group row">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<div class="col-form-label float-right font-weight-bold">Cycle
						Status:</div>
				</div>
				<div class="col-sm-3" >
					<select id="statusTypes" class="form-control custom-select-sm"
						style="width: 150px"
						aria-label="Please select a CAP completion school year">
						<option value="0"
							${'0' eq selectedStatus?'selected="selected"':'' }>All</option>
						<c:forEach items="${statusTypes}" var="type">
							<option value="${type.key }"
								${type.key eq selectedStatus?'selected="selected"':'' }>${type.value }</option>
						</c:forEach>
						<option value="Open"
							${'Open' eq selectedStatus?'selected="selected"':'' }>Open</option>
					</select>
				</div>
				<div class="col-sm-3">
					<div class="col-form-label float-right font-weight-bold">CAP
						Completion School Year:</div>
				</div>
				<div class="col-sm-2" >
					<select id="capyears" ${'Open' eq selectedStatus?'disabled':'' }
						class="form-control custom-select-sm float-left"
						aria-label="Please select a CAP completion school year">
						<option value="0" ${'0' eq selectedYear?'selected="selected"':'' }>All</option>
						<c:forEach items="${years}" var="year">
							<option value="${year.key }"
								${year.key eq selectedYear?'selected="selected"':'' }>${year.value }</option>
						</c:forEach>
					</select>
				</div>			
				<div class="col-sm-1"></div>
			</div>

		</div>
	</div>

	<div class="form-group row">
		<div class="col-sm-12" style="text-align: center;">
			<a id="selectYear" class="btn btn-primary" href="#">Submit</a>
		</div>
	</div>


	<div id="exportData" style="text-align: right; padding: 10px"  ></div>
	
		<table id="resultDatatable" class="table table-hover table-bordered table-striped">			
			<sec:authorize access="hasRole('ROLE_MANAGER')">
			<thead>
				<tr>
					<th title="Teacher Candidate"><strong>Teacher Candidate</strong></th>
					<th title="Program"><strong>Program</strong></th>
					<th title="Supervising Practitioner"><strong>Supervising Practitioner</strong></th>
					<th title="Practicum School"><strong>Practicum School</strong></th>
					<th title="Self-Assessment"><strong>Self Asmt.</strong></th>
					<th title="Announced Observation 1"><strong>Ann. Obs 1</strong></th>
					<th title="Finalized Goal"><strong>Fin. Goal</strong></th>
					<th title="Unannounced Observation 1"><strong>Unann. Obs 1</strong></th>
					<th title="Announced Observation 2"><strong>Ann. Obs 2</strong></th>
					<th title="Formative Assessment"><strong>Form. Asmt</strong></th>
					<th title="Unannounced Observation 2"><strong>Unann. Obs 2</strong></th>
					<th title="Summative Assessment"><strong>Summ. Asmt</strong></th>					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${capCycles}" var="capCycle">
					<tr>
						<td><a href="/cap/cycle?cycleId=${capCycle.cycleId}" >${capCycle.candidateFirstName} ${capCycle.candidateLastName}</a></td>
						<td>${capCycle.programName}</td>						
						<td>${capCycle.practitioner.spName}</td>
						<td>${capCycle.practicumSchoolName}</td>
						
						<c:choose>
								<c:when test="${capCycle.selfAssessment eq 'P'}">
									<td bgcolor="#7EF9FF" onMouseOver="this.style.color='black'">P</td>
								</c:when>
								<c:when test="${capCycle.selfAssessment eq 'Y'}">
									<td bgcolor="#b4ecb4" onMouseOver="this.style.color='black'">Y</td>
								</c:when>	
								<c:otherwise>	
									<td>N</td>
								</c:otherwise>
						</c:choose>	

						<c:choose>
								<c:when test="${capCycle.announcedObs1 eq 'P'}">
									<td bgcolor="#7EF9FF" onMouseOver="this.style.color='black'">P</td>
								</c:when>
								<c:when test="${capCycle.announcedObs1 eq 'Y'}">
									<td bgcolor="#b4ecb4" onMouseOver="this.style.color='black'">Y</td>
								</c:when>	
								<c:otherwise>	
									<td>N</td>
								</c:otherwise>
						</c:choose>	
						
						<c:choose>
								<c:when test="${capCycle.goalPlan eq 'P'}">
									<td bgcolor="#7EF9FF" onMouseOver="this.style.color='black'">P</td>
								</c:when>
								<c:when test="${capCycle.goalPlan eq 'Y'}">
									<td bgcolor="#b4ecb4" onMouseOver="this.style.color='black'">Y</td>
								</c:when>	
								<c:otherwise>	
									<td>N</td>
								</c:otherwise>
						</c:choose>	
						
						<c:choose>
								<c:when test="${capCycle.unAnnouncedObs1 eq 'P'}">
									<td bgcolor="#7EF9FF" onMouseOver="this.style.color='black'">P</td>
								</c:when>
								<c:when test="${capCycle.unAnnouncedObs1 eq 'Y'}">
									<td bgcolor="#b4ecb4" onMouseOver="this.style.color='black'">Y</td>
								</c:when>	
								<c:otherwise>	
									<td>N</td>
								</c:otherwise>
						</c:choose>
						
						<c:choose>
								<c:when test="${capCycle.announcedObs2 eq 'P'}">
									<td bgcolor="#7EF9FF" onMouseOver="this.style.color='black'">P</td>
								</c:when>
								<c:when test="${capCycle.announcedObs2 eq 'Y'}">
									<td bgcolor="#b4ecb4" onMouseOver="this.style.color='black'">Y</td>
								</c:when>	
								<c:otherwise>	
									<td>N</td>
								</c:otherwise>
						</c:choose>
						
						<c:choose>
								<c:when test="${capCycle.formativeAssessment eq 'P'}">
									<td bgcolor="#7EF9FF" onMouseOver="this.style.color='black'">P</td>
								</c:when>
								<c:when test="${capCycle.formativeAssessment eq 'Y'}">
									<td bgcolor="#b4ecb4" onMouseOver="this.style.color='black'">Y</td>
								</c:when>	
								<c:otherwise>	
									<td>N</td>
								</c:otherwise>
						</c:choose>
						<c:choose>
								<c:when test="${capCycle.unAnnouncedObs2 eq 'P'}">
									<td bgcolor="#7EF9FF" onMouseOver="this.style.color='black'">P</td>
								</c:when>
								<c:when test="${capCycle.unAnnouncedObs2 eq 'Y'}">
									<td bgcolor="#b4ecb4" onMouseOver="this.style.color='black'">Y</td>
								</c:when>	
								<c:otherwise>	
									<td>N</td>
								</c:otherwise>
						</c:choose>
						
						<c:choose>
								<c:when test="${capCycle.summativeAssessment eq 'P'}">
									<td bgcolor="#7EF9FF" onMouseOver="this.style.color='black'">P</td>
								</c:when>
								<c:when test="${capCycle.summativeAssessment eq 'Y'}">
									<td bgcolor="#b4ecb4" onMouseOver="this.style.color='black'">Y</td>
								</c:when>	
								<c:otherwise>	
									<td>N</td>
								</c:otherwise>
						</c:choose>
						
					</tr>
				</c:forEach>
			</tbody>
			</sec:authorize>
		</table>
		
		<p>&nbsp</p>
		<p>
			<b>Footnote(s):</b>
			<ul>
				<li>Y - The Form/Assessment is completed.</li>
				<li>N - The Form/Assessment is not completed.</li>
				<li>P - The Form/Assessment is yet to be completed by the other Role.</li>
			</ul>	
		</p>
	
</body>
</html>