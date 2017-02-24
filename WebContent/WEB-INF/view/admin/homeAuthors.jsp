<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>

<div class="row" style="min-height: 600px">
	<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
		<div class="navigation">
			<ul class="nav nav-pills nav-stacked">
				<li role="presentation"><a href="/SummaryTask4/admin/home"><mytags:message
							key="adminHome.navAccounts" /></a></li>
				<li role="presentation"><a
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
			<mytags:message key="adminHome.adminHome" />
		</div>
	</div>
	<div class="row">
		<div class="table">
			<div class="row">
				<c:if test="${createdAuthorMessage!=null }">
					<div class="alert alert-success text-center" role="alert">
						<mytags:message key="adminHomeAuthors.createdMessage" />
					</div>
				</c:if>
				<div class="col-lg-1">
					<a href="/SummaryTask4/admin/addAuthor"
						class="btn btn-default btn-md"><mytags:message
							key="adminHomeAuthors.addAuthorButton" /></a>
				</div>
				<div class="col-lg-7 text-center table-header">
					<mytags:message key="adminHomeAuthors.list" />
				</div>

				<div class="col-lg-4">
					<form action="#">
						<div class="input-group">
							<input type="text" name="name" class="form-control"
								placeholder="<mytags:message key="adminHomeAuthors.searchPlaceholder"/>">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<mytags:message key="adminHomeAccounts.searchButton" />
								</button>
							</span>
						</div>
					</form>
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th><mytags:message key="adminHomeAuthors.lastName" /></th>
						<th><mytags:message key="adminHomeAuthors.firstName" /></th>
						<th><mytags:message key="adminHomeAuthors.altLastName" /></th>
						<th>Количество книг</th>
						<!-- <mytags:message key="adminHomeAuthors.edition" /></th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="author" items="${authorsList }">
						<tr>
							<td>${author.lastName }</td>
							<td>${author.firstName }</td>
							<td>${author.alternativeLastName }</td>
							<td>${fn:length(author.books) }</td>
							<!--<td><div class="btn-group">
									<a href="/SummaryTask4/admin/editAuthor?id=${author.id }"
										type="button" class="btn btn-warning btn-sm"
										title='<mytags:message
											key="adminHomeAuthors.editButtonLabel" />'><span
										class="glyphicon glyphicon-wrench" aria-hidden="true"></span></a>
									<a href="/SummaryTask4/admin/deleteAuthor?id=${author.id }"
										onclick="return confirm('<mytags:message key="adminHomeAuthors.confirmOnDeleteMessage"/>')"
										type="button" class="btn btn-danger btn-sm"
										title='<mytags:message key="adminHomeAuthors.deleteButtonLabel"/>'><span
										class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
								</div></td>-->
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="text-center">
			<nav aria-label="Search results pages">
				<ul class="pagination">
					<c:if test="${pageNumber != 1}">
						<li><a
							href="/SummaryTask4/admin/home/authors?page=${pageNumber - 1 }"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<c:forEach begin="1" end="${neededPages}" var="i">
						<c:choose>
							<c:when test="${pageNumber eq i}">
								<li><a href="#">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/SummaryTask4/admin/home/authors?page=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageNumber lt neededPages}">
						<li><a
							href="/SummaryTask4/admin/home/authors?page=${pageNumber + 1 }"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
	</main>
</div>