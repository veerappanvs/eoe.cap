<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/fixedheader/3.1.5/js/dataTables.fixedHeader.js"></script>

<script type="text/javascript">

function pageLoad() {
			
	var table = $('#resultDatatable').DataTable(
			{
				fixedHeader:true,
				"columnDefs": [
								{"targets": [ 5 ],
								  "orderData": [5]},
								{
								    "targets": [ -1 ],
								    "visible": false,
								    "searchable": false										    
								}			                                                             
	                          ]});
	
			 var buttons = new $.fn.dataTable.Buttons(table, {
			     buttons: [
			        {
                extend: 'excel',
                title: 'Organization Usage Report',
                text: '<i class="fal fa-file-excel fa-1x"></i>',
                className: 'btn btn-primary',
                columns: [ 0, 1, 2, 3,4,5,6 ]
            	},
            	
            	{
                    extend: 'pdf',
                    title: 'Organization Usage Report',
                    text: '<i class="fal fa-file-pdf fa-1x"></i>',
					orientation: 'landscape',
					pageSize: 'LEGAL',
                    className: 'btn btn-primary',
                    columns: [ 0, 1, 2, 3,4,5,6 ]
                	},
                	
                	{
                        extend: 'print',
                        title: 'Organization Usage Report',
                        text: '<i class="fal fa-print fa-1x"></i>',
						orientation: 'landscape',
						pageSize: 'LEGAL',
                        className: 'btn btn-primary',
                        columns: [ 0, 1, 2, 3,4,5,6 ]
                    	}
			    ]
			}).container().appendTo($('#exportData'));
			 
			 jQuery('#selectedYear').change( function() {
				 $("#form").submit();
		    });

		}

</script>

<div id="error-section" style="margin-bottom: 25px"></div>

<div style="text-align: center; padding: 10px">
	<h5>Organization Usage Report</h5>
</div>

<div class="form-group row">
	<div class="col-sm-4"></div>
	<div class="col-sm-2">
		<div class="col-form-label float-right font-weight-bold">CAP
			Completion Year:</div>
	</div>
	<div class="col-sm-2">
		<form:form id="form" method="GET">
			<select id="selectedYear" name="selectedYear"
				class="form-control custom-select-sm float-left"
				aria-label="Please select a CAP completion year">
				<c:forEach items="${years}" var="year">
					<option value="${year.key }"
						${year.key eq selectedYear?'selected="selected"':'' }>${year.value }</option>
				</c:forEach>
			</select>
		</form:form>
	</div>
	<div class="col-sm-4"></div>
</div>


<div id="exportData" style="text-align: right; padding: 10px"  ></div>
		

		<table id="resultDatatable" class="table table-hover table-bordered table-striped">	
		 	<sec:authorize access="hasRole('ROLE_ADMIN')">
				<thead>
					<tr>
						<th title="Organization Name"><strong>Organization Name</strong></th>
						<th title="Total Cycles"><strong>Total Cycles</strong></th>
						<th title="Total Open Cycles"><strong>Total Open Cycles</strong></th>
						<th title="Total Registered Candidates"><strong>Total Registered Candidates</strong></th>
						<th title="Total Program Supervisors"><strong>Total Program Supervisors</strong></th>
						<th title="Last Activity Date"><strong>Last Activity Date</strong></th>	
						<th title="School Year"><strong>School Year</strong></th>				
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${capAdminView}" var="caInfo">
						<tr>
							<td><a href="/cap/listcamcycles?orgCode=${caInfo.orgCode}" >${caInfo.orgName}</a></td>
							<td>${caInfo.totalCycles}</td>
							<td>${caInfo.totalOpenCycles} </td>
							<td>${caInfo.totalCandidates} </td>
							<td>${caInfo.totalProgramSupervisors} </td>						
							<td><fmt:formatDate pattern="MM/dd/yyyy" value="${caInfo.latestActDate}" /></td>
							<td>${caInfo.schoolYear} </td>
						</tr>
					</c:forEach>
				</tbody>
			</sec:authorize>	
		</table>
			<p>&nbsp</p>
				
			<p>
				<b>Footnote(s):</b>
				<ul>
					<li>Each Organization name is a hyperlink to view open and closed Cycles of that Organization</li>
					<li>Default School Year is the current School Year.</li>
					<li>The Last Activity Date column will provide the most recent date of activity of that Organization.  The intent is to know when was the last time the Organization has used the CAP Platform.</li>
				</ul>	
			</p>
		