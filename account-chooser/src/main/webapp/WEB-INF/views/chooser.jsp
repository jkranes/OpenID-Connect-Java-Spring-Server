<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.mitre.account_chooser.OIDCServer"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="issuers" type="java.util.Map"
	scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Account Chooser</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Account Chooser GUI">
<meta name="author" content="nemonik">
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="resources/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="resources/bootstrap/css/docs.css" rel="stylesheet">

<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<form name="chooser" class="form-horizontal" action="selected" method="get">
					<div class="control-group">
						<label class="control-label" for="select01">Account:</label>
						<div class="controls">
							<select name="issuer">
								<%
									Iterator entries = issuers.entrySet().iterator();

									while (entries.hasNext()) {
										Map.Entry entry = (Map.Entry) entries.next();

										String id = (String) entry.getKey();
										OIDCServer issuer = (OIDCServer) entry.getValue();										
								%>
								<option value="<%= id %>"><%= issuer.getName() %></option>
								<%
									}
								%>
							</select>
							<p class="help-block">Select the Account you'd like to authenticate with.</p>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<input name="redirect_uri" type="hidden" value="${redirect_uri}">
							<input name="client_id" type="hidden" value="${client_id}">
						</div>
					</div>
					<div class="form-actions">
						<a class="btn btn-primary" href="javascript: submitForm('selected')">Submit</a>
						<a class="btn" href="javascript: submitForm('cancel')">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--/container-->

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="resources/bootstrap/js/jquery.js"></script>
	<script src="resources/bootstrap/js/bootstrap-transition.js"></script>
	<script src="resources/bootstrap/js/bootstrap-alert.js"></script>

	<script src="resources/bootstrap/js/bootstrap-modal.js"></script>
	<script src="resources/bootstrap/js/bootstrap-dropdown.js"></script>
	<script src="resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script src="resources/bootstrap/js/bootstrap-tab.js"></script>
	<script src="resources/bootstrap/js/bootstrap-tooltip.js"></script>
	<script src="resources/bootstrap/js/bootstrap-popover.js"></script>

	<script src="resources/bootstrap/js/bootstrap-button.js"></script>
	<script src="resources/bootstrap/js/bootstrap-collapse.js"></script>
	<script src="resources/bootstrap/js/bootstrap-carousel.js"></script>
	<script src="resources/bootstrap/js/bootstrap-typeahead.js"></script>

	<script type="text/javascript" language="JavaScript">
		// <![CDATA [
		  
		function submitForm(action){
			$('.form-horizontal').attr( 'action' , action  );
			$('.form-horizontal').submit();
		}
		  
		// ]]>
	</script>

</body>
</html>
