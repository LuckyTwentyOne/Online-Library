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
			<mytags:message key="adminHome.adminHome" />
		</div>

	</div>
	<div class="row">
		<div class="table">
			<div class="row">
				<div class="col-lg-1">
					<a href="/SummaryTask4/admin/addAccount"
						class="btn btn-default btn-md"><mytags:message
							key="adminHomeAccounts.addAccountButton" /></a>
				</div>
				<div class="col-lg-7 text-center table-header">
					<mytags:message key="adminHomeAccountsSearch.result" />
				</div>

				<div class="col-lg-4">
					<form action="/SummaryTask4/admin/home/searchAccounts">
						<div class="input-group">
							<input type="text" name="login" class="form-control"
								value="${searchedLogin }"
								placeholder='<mytags:message key="adminHomeAccounts.searchPlaceholder"/>'>
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
						<th><mytags:message key="adminHomeAccounts.tableLogin" /></th>
						<th><mytags:message key="adminHomeAccounts.tableEmail" /></th>
						<th><mytags:message key="adminHomeAccounts.tableFirstName" /></th>
						<th><mytags:message key="adminHomeAccounts.tableLastName" /></th>
						<th><mytags:message key="adminHomeAccounts.tableRole" /></th>
						<th><mytags:message key="adminHomeAccounts.tableActive" /></th>
						<th><mytags:message key="adminHomeAccounts.tableEdit" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="account" items="${accountsList }">
						<tr>
							<td>${account.login }</td>
							<td>${account.email }</td>
							<td>${account.firstName }</td>
							<td>${account.lastName }</td>
							<td>${account.roleName }</td>
							<td>${account.active }</td>
							<td><div class="btn-group">
									<a href="/SummaryTask4/admin/editAccount?id=${account.id }"
										type="button" class="btn btn-warning btn-sm"
										title='<mytags:message key="adminHomeAccounts.editButtonLabel"/>'><span
										class="glyphicon glyphicon-wrench" aria-hidden="true"></span></a>
									<a href="/SummaryTask4/admin/deleteAccount?id=${account.id }"
										onclick="return confirm('<mytags:message key="adminHomeAccounts.confirmOnDeleteMessage"/>')"
										type="button" class="btn btn-danger btn-sm"
										title='<mytags:message key="adminHomeAccounts.deleteButtonLabel"/>'><span
										class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
								</div></td>
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
							href="/SummaryTask4/admin/home/searchAccounts?page=${pageNumber - 1 }&login=${searchedLogin}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<c:forEach begin="1" end="${neededPages}" var="i">
						<c:choose>
							<c:when test="${pageNumber eq i}">
								<li><a href="#">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="/SummaryTask4/admin/home/searchAccounts?page=${i}&login=${searchedLogin}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageNumber lt neededPages}">
						<li><a
							href="/SummaryTask4/admin/home/searchAccounts?page=${pageNumber + 1  }&login=${searchedLogin}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>
			</nav>
		</div>

	</div>
	</main>
</div>