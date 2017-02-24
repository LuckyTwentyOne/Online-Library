<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="row">
	<div id="welcomeInfo"
		class="col-lg-7 col-lg-offset-1 col-md-6 col-md-offset-1 welcomeInfo">
		<div class="jumbotron ">
			<div class="container">
				<h1>
					<mytags:message key="login.welcome"></mytags:message>
				</h1>
				<p>
					<mytags:message key="login.welcome-text1"></mytags:message>
					<strong> 100 000</strong>
					<mytags:message key="login.welcome-text2"></mytags:message>
					<a href="#loginForm" class="toLoginForm"><strong> <mytags:message
								key="login.welcome-sighin"></mytags:message></strong></a>
				</p>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-lg-offset-0 col-md-4 col-md-offset-0">
		<div class="loginForm">
			<form id="loginForm" method="post" action="login">
				<div class="form-group">
					<label><mytags:message key="login.login" /></label> <input
						name="login" class="form-control" value="${loginForm.login }"
						placeholder="<mytags:message key="login.login"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="login.password" /></label> <input
						name="password" type="password" class="form-control"
						value="${loginForm.password }"
						placeholder="<mytags:message key="login.password"/>">
				</div>
				<c:if test="${loginErrorMessage!=null }">
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span>&nbsp;${loginErrorMessage }
					</div>
				</c:if>
				<button type="submit" class="btn btn-default">
					<mytags:message key="login.sighin" />
				</button>
				<a href="/SummaryTask4/fbLogin" class="btn btn-primary "><mytags:message
					key="login.facebook" />&nbsp;<i
					class="fa fa-facebook-official" aria-hidden="true"></i></a>
			</form>
		</div>
		<div class="text-center">
			<a href="/SummaryTask4/main" class="btn btn-default btn-sm"><mytags:message
					key="login.main" /></a> <a href="registration" type="button"
				class="btn btn-default btn-sm"><mytags:message
					key="login.registration" /></a> <a href="#" type="button"
				class="btn btn-default btn-sm"><mytags:message
					key="login.forgot" /></a>
		</div>
		<c:if test="${successRegistrationMessage!=null }">
			<div class="successRegistrationMessage">${successRegistrationMessage }</div>
		</c:if>
	</div>
</div>

