<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="modal fade" id="assessment-warn-dialog" tabindex="-1"
	data-keyboard="false" data-backdrop="static" role="dialog"
	aria-labelledby="globalUserPrompt" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-sm"
		role="document" style="min-width: 400px">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Unable to save changes</h5>
			</div>
			<div class="modal-body">
				<div>
					<div style="float: left; padding: 0 10 5 0">
						<span style="color: red"><i
							class="fa fa-exclamation-triangle fa-2x" aria-hidden="true"></i></span>
					</div>
					<div>
						<div id="timeout-question">Your changes will not be saved. The Program Supervisor has completed the form and is now read-only. Please contact the Program Supervisor If you would like to edit the form.</div>
					</div>
				</div>
			</div>
			<div class="modal-footer" style="text-align: center">
				<a  class="btn btn-primary" href="${param.atype}?cycleId=${cycleId}&typeCode=${param.typeCode}" >Ok</a>				
			</div>
		</div>
	</div>
</div>