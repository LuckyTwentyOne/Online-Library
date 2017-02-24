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
			<mytags:message key="adminHome.adminHome" />
		</div>
	</div>
	<div class="row">
		<div class="table">
			<div class="row">
				<c:if test="${updatedBookMessage!=null }">
					<div class="alert alert-success text-center" role="alert">
						<mytags:message key="adminHomeBooks.book" />
						&nbsp;${updatedBookMessage }&nbsp;
						<mytags:message key="adminHomeBooks.bookUpdatedMesage" />
					</div>
				</c:if>
				<c:if test="${deletedBook!=null }">
					<div class="alert alert-success text-center" role="alert">
						<mytags:message key="adminHomeBooks.book" />
						&nbsp;${deletedBook }&nbsp;
						<mytags:message key="adminHomeBooks.bookDeletedMesage" />
					</div>
				</c:if>
				<c:if test="${createdBookMessage!=null }">
					<div class="alert alert-success text-center" role="alert">
						<mytags:message key="adminHomeBooks.book" />
						&nbsp;${createdBookMessage }&nbsp;
						<mytags:message key="adminHomeBooks.bookCreatedMessage" />
					</div>
				</c:if>
				<div class="col-lg-1">
					<a href="/SummaryTask4/admin/addBook"
						class="btn btn-default btn-md"><mytags:message
							key="adminHomeBooks.addBookButton" /></a>
				</div>
				<div class="col-lg-6 text-center table-header">
					<mytags:message key="adminHomeBooks.list" />
				</div>

				<div class="col-lg-5">
					<form action="/SummaryTask4/admin/home/books">

						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="<mytags:message key="adminHomeBooks.searchPlaceholder"/>">
							<div class="input-group-btn ">						
									<select class="btn btn-default mybtn">
										<option><mytags:message key="adminHomeBooks.searchByName"/></option>
										<option><mytags:message key="adminHomeBooks.searchByAuthor"/></option>
									</select>							
								<button class="btn btn-default" type="submit">
									<mytags:message key="adminHomeAccounts.searchButton" />
								</button>
							</div>
							<!-- /btn-group -->
						</div>
					</form>
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th><mytags:message key="adminHomeBooks.bookName" /></th>
						<th><mytags:message key="adminHomeBooks.bookAuthor" /></th>
						<th><mytags:message key="adminHomeBooks.bookYear" /></th>
						<th><mytags:message key="adminHomeBooks.bookPublisher" /></th>
						<th>Процент популярности</th>
						<th><mytags:message key="adminHomeBooks.edition" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${booksList }">
						<tr>
							<td>${book.name }</td>
							<td>${book.authorList }</td>
							<td>${book.imprintYear }</td>
							<td>${book.publisher }</td>
							<td>${book.formatedPercent }</td>
							<td><div class="btn-group">
									<a href="/SummaryTask4/admin/editBook?id=${book.id }"
										type="button" class="btn btn-warning btn-sm"
										title='<mytags:message
											key="adminHomeBooks.editButtonLabel" />'><span
										class="glyphicon glyphicon-wrench" aria-hidden="true"></span></a>
									<a href="/SummaryTask4/admin/deleteBook?id=${book.id }"
										onclick="return confirm('<mytags:message key="adminHomeBooks.confirmOnDeleteMessage"/>')"
										type="button" class="btn btn-danger btn-sm"
										title='<mytags:message key="adminHomeBooks.deleteButtonLabel"/>'><span
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
							href="/SummaryTask4/admin/home/books?page=${pageNumber - 1 }"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<c:forEach begin="1" end="${neededPages}" var="i">
						<c:choose>
							<c:when test="${pageNumber eq i}">
								<li><a href="#">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/SummaryTask4/admin/home/books?page=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageNumber lt neededPages}">
						<li><a
							href="/SummaryTask4/admin/home/books?page=${pageNumber + 1 }"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
	</main>
</div>