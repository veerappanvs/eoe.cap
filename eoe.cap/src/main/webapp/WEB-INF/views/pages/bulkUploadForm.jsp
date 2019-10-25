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
		$(document)
				.on(
						'change',
						':file',
						function() {
							$('.error').remove();

							if (this.files[0].size > 52428800) {
								this.value = "";
								$(this)
										.after(
												'<span class="error" >Only 50 MB of Data is allowed to upload.</span>');
								return;
							}

							var input = $(this), numFiles = input.get(0).files ? input
									.get(0).files.length
									: 1, label = input.val()
									.replace(/\\/g, '/').replace(/.*\//, '');

							input[0].nextElementSibling.innerText = label;

						});

	}
</script>
</head>
<body>

	<form:form id="form2" method="POST" enctype="multipart/form-data"
		commandName="batchInfo">


		<div class="form-group row">
			<div class="col-sm-12" style="text-align: center">
				<h4>Upload Cycles</h4>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<b>Note:</b>
				<ul>
					<li>Please use the Verify Cycle Information tool to validate information for a cycle.</li>
					<li>Please download the template for uploading CAP Cycle Rating.</li>
					<li>Please ensure that the information entered is identical to the information shown in the Inquiry tool search results.</li>
					<li>Please only enter MEPIDs for SPs working in Pubic, Public Charter and Collaborative school districts. Please do not enter MEPIDs for SPs working in Private or Special Ed Schools.</li>
					<li>Only completed cycle records (i.e., with ratings) will be consumed for Annual CAP Cycle Reports at the end of the school year, please see the FAQs for more information.</li>
					<li>Users must use the downloaded template (.csv format) to upload cycle records, due to the required upload format.</li>
				</ul>
			</div>
			<div class="col-sm-1"></div>
		</div>
		
		<div class="form-group row">
			<div class="col-sm-1"></div>
			<div class="col-sm-8"><a href="/cap/batchload/template"  class="btn btn-primary" style="color:white" >Download Template</a></div>
			<div class="col-sm-1"></div>
		</div>

		<div class="form-group row" style="margin-top: 50px">
			<div class="col-sm-2"></div>
			<div class="col-sm-4">
				<div class="custom-file" >
					<input type="file" class="custom-file-input" id="file" name="file">
					<div class="custom-file-label" for="file">Choose file</div>
				</div>
				<form:errors path="file" cssClass="error" />				
			</div>
			<div class="col-sm-4">
				<input type="submit" value="Upload" class="btn btn-primary" />
			</div>
			<div class="col-sm-2"></div>
		</div>

		<c:if test="${batchInfo.errcount eq 0 }">
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-4">
					<span>Upload Successful! Please click on the Imported Cycles link to view your data.</span>
				</div>
				<div class="col-sm-6"></div>
			</div>
		</c:if>

		<c:if test="${batchInfo.errcount gt 0 }">
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-4">
					<span style="color: red">Please download the error file to
						see the list of errors in the uploaded file. Please update the
						information in the original file and upload again. Please ensure
						that the data entered is accurate and is in correct format.</span>
				</div>
				<div class="col-sm-6"></div>
			</div>

			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-4">
					<a href="/cap/batchload/errorLog?batchId=${batchInfo.batchId}"
						class="btn btn-primary" style="background-color: red;colore:white">Download
						Error File</a>
				</div>
				<div class="col-sm-6"></div>
			</div>
		</c:if>
		<div class="line" style="margin: 10px 0; color: black"></div>


	</form:form>

</body>
</html>
