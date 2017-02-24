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
				<li role="presentation" class="active"><a
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
			<mytags:message key="adminHomeEditBook.header" />
			<strong>&nbsp;'${book.name }'</strong>
		</div>
	</div>
	<div class="row editionBookMenu">
		<div
			class="editionInput col-lg-4 col-lg-offset-7 col-md-5 col-md-offset-6 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1">
			<form method="post" action="/SummaryTask4/admin/editBook">
				<input type="hidden" name="id" class="form-control"
					value=${book.id }>
				<div class="form-group">
					<label><mytags:message key="adminHomeEditBook.bookName" /></label>
					<input name="name" class="form-control" value="${book.name }"
						placeholder="<mytags:message key="adminHomeEditBook.bookName"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="adminHomeEditBook.publisher" /></label>
					<input name="publisher" class="form-control"
						value="${book.publisher }"
						placeholder="<mytags:message key="adminHomeEditBook.publisher"/>">
				</div>
				<div class="form-group">
					<label><mytags:message key="adminHomeEditBook.imprintYear" /></label>
					<input name="imprintYear" class="form-control"
						value="${book.imprintYear }" type="number" min="1564"
						placeholder="<mytags:message key="adminHomeEditBook.imprintYear"/>">
				</div>
				<div class="form-group">
					<label><mytags:message
							key="adminHomeEditBook.alternativeName" /></label> <input
						name="alternativeName" class="form-control"
						value="${book.alternativeName }"
						placeholder="<mytags:message key="adminHomeEditBook.alternativeName"/>">
				</div>
				<div id="authors">
					<c:forEach items="${book.authors }" var="bookAuthor">
						<div class="form-group authorSelect">
							<label><mytags:message key="adminHomeEditBook.author" /></label>
							<select name="author" class="form-control selectField">
								<c:forEach items="${authors}" var="author">
									<option value="${author.id }"
										<c:if test="${bookAuthor.id == author.id}">selected</c:if>>${author.lastName }&nbsp;
										${author.firstName }</option>
								</c:forEach>
							</select>
							<div class="removeAuthor">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="text-left addAuthor">
					<span id="addAuthor" class="glyphicon glyphicon-plus"
						aria-hidden="true"></span>
					<mytags:message key="adminHomeEditBook.addAuthorBtn" />
				</div>
				<c:if test="${editBookErrorMessage!=null }">
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span>&nbsp;${editBookErrorMessage }
					</div>
				</c:if>
				<div class="text-center">
					<button type="submit" class="btn btn-warning">
						<mytags:message key="adminHomeEditAcc.editButton" />
					</button>
					<a href="/SummaryTask4/admin/home/books" class="btn btn-danger "><mytags:message
							key="adminHomeEditAcc.cancelButton" /></a>
				</div>
			</form>
		</div>

	</div>

	</main>
</div>