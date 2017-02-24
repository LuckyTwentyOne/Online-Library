<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>

<div class="row" style="min-height: 600px">
	<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
		<div class="navigation">
			<ul class="nav nav-pills nav-stacked">
				<li role="presentation"><a href="/SummaryTask4/admin/home"><mytags:message
							key="adminHome.navAccounts" /></a></li>
				<li role="presentation" ><a
					href="/SummaryTask4/admin/home/books"><mytags:message
							key="adminHome.navBooks" /></a></li>
				<li role="presentation" class="active"><a
					href="/SummaryTask4/admin/home/authors"><mytags:message
							key="adminHome.navAuthors" /></a></li>
			</ul>
		</div>
	</aside>
	<main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
	<div class="row">
		<div class="homePageHeader text-center">
			<mytags:message key="adminHomeAddAuthor.header" />
		</div>
	</div>
	<div class="row editionAuthorMenu">
		<div
			class="editionInput col-lg-4 col-lg-offset-1 col-md-5 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1">
			<form method="post" action="/SummaryTask4/admin/addAuthor">
				<div class="form-group">
					<label><mytags:message key="adminHomeAddAuthor.lastName" /></label>
					<input name="lastName" class="form-control" value="${form.lastName }"
						placeholder="<mytags:message key="adminHomeAddAuthor.lastName"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="adminHomeAddAuthor.firstName" /></label>
					<input name="firstName" class="form-control"
						value="${form.firstName }"
						placeholder="<mytags:message key="adminHomeAddAuthor.firstName"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="adminHomeAddAuthor.altLastName" /></label>
					<input name="alternativeLastName" class="form-control" value="${form.alternativeLastName }"
						placeholder="<mytags:message key="adminHomeAddAuthor.altLastName"/>">
				</div>
				<c:if test="${addAuthorErrorMessage!=null }">
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span>&nbsp;${addAuthorErrorMessage }
					</div>
				</c:if>
				<div class="text-center">
					<button type="submit" class="btn btn-success">
						<mytags:message key="adminHomeAddAuthor.addButton" />
					</button>
					<a href="/SummaryTask4/admin/home/authors" class="btn btn-danger "><mytags:message
							key="adminHomeEditAcc.cancelButton" /></a>
				</div>
			</form>
		</div>

	</div>

	</main>
</div>