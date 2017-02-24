<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="row">
	<div class="homePageHeader text-center">
		<mytags:message key="librarianHome.librarianHome" />
	</div>
</div>
<div class="row" style="min-height: 600px">
	<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
		<div class="panel panel-default readerNav librarianNav">
			<div class="list-group">
				<a href="/SummaryTask4/librarian/home"
					class="list-group-item active"><mytags:message
						key="librarianHome.navBookList" /></a> <a href="/SummaryTask4/librarian/myInfo"
					class="list-group-item"><mytags:message
						key="librarianHome.navMyInfo" /></a> <a href="/SummaryTask4/librarian/orders"
					class="list-group-item"><c:if
						test="${unprocessedOrders != 0}">
						<span class="badge">${unprocessedOrders }</span>
					</c:if> <mytags:message key="librarianHome.navOrders" /></a>
			</div>
		</div>
	</aside>

	<main class="col-xs-12 col-sm-8 col-md-9 col-lg-9">
	<div class="row">
		<div class="table">
			<div class="row">
				<div class="col-lg-7 text-center table-header">
					<mytags:message key="adminHomeBooks.list" />
				</div>

				<div class="col-lg-5">
					<form action="/SummaryTask4/librarian/searchBook">

						<div class="input-group">
							<input type="text" class="form-control" name="query"
								placeholder="<mytags:message key="adminHomeBooks.searchPlaceholder"/>">
							<div class="input-group-btn ">
								<select class="btn btn-default mybtn" name="searchBy">
									<option value="name"><mytags:message
											key="adminHomeBooks.searchByName" /></option>
									<option value="author"><mytags:message
											key="adminHomeBooks.searchByAuthor" /></option>
								</select>
								<button class="btn btn-default" type="submit">
									<mytags:message key="adminHomeAccounts.searchButton" />
								</button>
							</div>
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
						<th><mytags:message key="librarianHome.numberCopies" /></th>
						<th><mytags:message key="librarianHome.addCopies" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${booksList }">
						<tr>
							<td>${book.name }</td>
							<td>${book.authorList }</td>
							<td>${book.imprintYear }</td>
							<td>${book.publisher }</td>
							<td>${book.numberOfCopies }</td>
							<td><div class="btn-group">
									<a href="/SummaryTask4/librarian/addCopy?idBook=${book.id }&page=${pageNumber}"
										type="button" class="btn btn-success btn-sm"
										title='<mytags:message
											key="librarianHome.addCopies" />'><span
										class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
								</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="text-center">
			<nav aria-label="Search results pages">
				<ul class="pagination">
					
					<c:forEach begin="1" end="${neededPages}" var="i">
						<c:choose>
							<c:when test="${pageNumber eq i}">
								<li><a href="#">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/SummaryTask4/librarian/home?page=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
				</ul>
			</nav>
		</div>
	</div>
	</main>
</div>