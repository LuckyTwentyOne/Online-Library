<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>

<div class="row" style="min-height: 600px">
	<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
		<div class="navigation">
			<ul class="nav nav-pills nav-stacked">
				<li role="presentation" class="active"><a
					href="/SummaryTask4/admin/home"><mytags:message
							key="adminHome.navAccounts" /></a></li>
				<li role="presentation"><a
					href="/SummaryTask4/admin/home/books"><mytags:message
							key="adminHome.navBooks" /></a></li>
				<li role="presentation"><a
					href="/SummaryTask4/admin/home/authors"><mytags:message
							key="adminHome.navAuthors" /></a></li>
			</ul>
		</div>
	</aside>
	<main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
	<div class="row">
		<div class="homePageHeader text-center">
			<mytags:message key="adminHomeEditAcc.header" />
			<strong>&nbsp;${account.login }</strong>
		</div>
	</div>
	<div class="row editionMenu">
		<div
			class="editionInput col-lg-4 col-lg-offset-7 col-md-5 col-md-offset-6 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1">
			<form method="post" action="/SummaryTask4/admin/editAccount">
				<input type="hidden" name="id" class="form-control"
					value=${account.id }> <input type="hidden" name="login"
					class="form-control" value=${account.login }>
				<div class="form-group">
					<label><mytags:message key="adminHomeEditAcc.firstName" /></label>
					<input name="firstName" class="form-control"
						value="${account.firstName }"
						placeholder="<mytags:message key="adminHomeEditAcc.firstName"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="adminHomeEditAcc.lastName" /></label>
					<input name="lastName" class="form-control"
						value="${account.lastName }"
						placeholder="<mytags:message key="adminHomeEditAcc.lastName"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="adminHomeEditAcc.role" /></label> <select
						name="role" class="form-control">
						<option value="1"><mytags:message
								key="adminHomeEditAcc.admin" /></option>
						<option value="2"
							<c:if test="${account.roleName eq 'librarian' }">selected</c:if>><mytags:message
								key="adminHomeEditAcc.librarian" /></option>
						<option value="3"
							<c:if test="${account.roleName eq 'reader' }">selected</c:if>><mytags:message
								key="adminHomeEditAcc.reader" /></option>
					</select>
				</div>
				<div class="form-group">
					<label><mytags:message key="adminHomeEditAcc.active" /></label> <select
						name="active" class="form-control">
						<option value="false">Yes</option>
						<option value="true"
							<c:if test="${account.active}">selected</c:if>>No</option>
					</select>
				</div>
				<c:if test="${editAccountErrorMessage!=null }">
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span>&nbsp;${editAccountErrorMessage }
					</div>
				</c:if>
				<div class="text-center">
					<button type="submit" class="btn btn-warning">
						<mytags:message key="adminHomeEditAcc.editButton" />
					</button>
					<a href="/SummaryTask4/admin/home" class="btn btn-danger "><mytags:message
							key="adminHomeEditAcc.cancelButton" /></a>
				</div>
			</form>
		</div>

	</div>

	</main>
</div>