<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<%@ taglib prefix="appTags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>Library</title>
<link rel="stylesheet" href="/SummaryTask4/static/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/SummaryTask4/static/css/bootstrap-theme.css">
<link rel="stylesheet"
	href="/SummaryTask4/static/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="/SummaryTask4/static/css/font-awesome.css">
<link rel="stylesheet" href="/SummaryTask4/static/css/app.css">
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
	<appTags:header/>
	<div class="container-fluid">
		<jsp:include page="${currentPage }"></jsp:include>
	</div>
	<appTags:footer/>
	<script src="/SummaryTask4/static/js/jquery-1.11.1.min.js"></script>
	<script src="/SummaryTask4/static/js/moment-with-locales.js"></script>
	<script src="/SummaryTask4/static/js/bootstrap.min.js"></script>
	<script src="/SummaryTask4/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="/SummaryTask4/static/js/app.js"></script>
</body>
</html>