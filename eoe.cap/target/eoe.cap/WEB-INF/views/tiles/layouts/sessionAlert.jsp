<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="modal fade" id="timeout-dialog" tabindex="-1"
	data-keyboard="false" data-backdrop="static" role="dialog"
	aria-labelledby="globalUserPrompt" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-sm"
		role="document" style="min-width: 400px">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="globalUserPromptTitle">Your session
					is about to expire!</h5>
			</div>
			<div class="modal-body">
				<div id="timeout-dialog">
					<div style="float: left; padding: 0 10 5 0">
						<span style="color: red"><i
							class="fa fa-exclamation-triangle fa-2x" aria-hidden="true"></i></span>
					</div>
					<div>
						<div id="timeout-question">You will be logged out in <span id="timeout-countdown">{0}</span>
							.Click "Stay Logged In" to keep the session active for
							another hour or click "Log Out" to end session. Please note that
							clicking "Log Out" or not taking any action will result in loss
							of unsaved data.</div>
					</div>
				</div>
			</div>
			<div class="modal-footer" style="text-align: center">
				<button id="keep-alive-button" type="button" class="btn btn-primary"
					style="margin-right: 10px">Stay Logged In</button>
				<button id="sign-out-button" type="button" class="btn btn-secondary"
					data-dismiss="modal">Log out.</button>
			</div>
		</div>
	</div>
</div>