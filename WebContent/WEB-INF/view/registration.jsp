<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="row">
	<div
		class="col-lg-6 col-lg-offset-1 col-md-6 col-md-offset-1 registration">
		<div class="regInfo">
			<p class="regHeader">
				<mytags:message key="registration.regHeader" />
			</p>
			<p>
				<mytags:message key="registration.regText" />
			</p>
		</div>
	</div>
	<div class="col-lg-4 col-lg-offset-0 col-md-4 col-md-offset-0">
		<div class="row">
			<div class="textRegistration">
				<mytags:message key="registration.regInfo" />
			</div>
		</div>
		<div class="loginForm">
			<form action="registration" method="post">
				<div class="form-group">
					<label><mytags:message key="registration.login" /></label> <input
						maxlength="25" class="form-control" name="login"
						value="${form.login }"
						placeholder="<mytags:message key="login.login"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="registration.email" /></label> <input
						type="email" name="email" class="form-control " maxlength="25"
						value="${form.email }"
						placeholder="<mytags:message key="registration.email"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="registration.firstName" /></label> <input
						maxlength="25" name="firstName" class="form-control "
						value="${form.firstName }"
						placeholder="<mytags:message key="registration.firstName"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="registration.lastName" /></label> <input
						maxlength="25" name="lastName" class="form-control "
						value="${form.lastName }"
						placeholder="<mytags:message key="registration.lastName"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="registration.password" /></label> <input
						maxlength="25" name="password" type="password"
						class="form-control"
						placeholder="<mytags:message key="registration.password"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="registration.confirmPassword" /></label>
					<input maxlength="25" name="confirmPassword" type="password"
						class="form-control"
						placeholder="<mytags:message key="registration.confirmPassword"/>">
				</div>

				<div class="g-recaptcha"
					data-sitekey="6LfFbxMUAAAAAPMkqZ4bhf15chhykKMRqu81RBG0"></div>

				<c:if test="${registrationErrorMessage!=null }">
					<div class="alert alert-danger" role="alert">${registrationErrorMessage }</div>
				</c:if>
				<div class="text-center">
					<button type="submit" class="btn btn-default">
						<mytags:message key="registration.submit" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>

