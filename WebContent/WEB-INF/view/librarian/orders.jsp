<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="row">
	<div class="homePageHeader text-center">
		<mytags:message key="librarianOrders.header" />
	</div>
</div>
<div class="row" style="min-height: 600px">
	<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
		<div class="panel panel-default readerNav librarianNav">
			<div class="list-group">
				<a href="/SummaryTask4/librarian/home" class="list-group-item"><mytags:message
						key="librarianHome.navBookList" /></a> <a
					href="/SummaryTask4/librarian/myInfo" class="list-group-item"><mytags:message
						key="librarianHome.navMyInfo" /></a> <a
					href="/SummaryTask4/librarian/orders"
					class="list-group-item active"><c:if
						test="${unprocessedOrders != 0}">
						<span class="badge">${unprocessedOrders }</span>
					</c:if> <mytags:message key="librarianHome.navOrders" /></a>
			</div>
		</div>
	</aside>

	<main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
		<div class="table">
			<div class="row">
				<div class="col-lg-12 text-center table-header">
					<mytags:message key="librarianOrders.list" />
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th><mytags:message key="librarianOrders.bookName" /></th>
						<th><mytags:message key="librarianOrders.accountName" /></th>
						<th><mytags:message key="librarianOrders.borrowType" /></th>
						<th><mytags:message key="librarianOrders.borrowTime" /></th>
						<th><mytags:message key="librarianOrders.returnTime" /></th>
						<th><mytags:message key="librarianOrders.fine" /></th>
						<th><mytags:message key="librarianOrders.processOrder" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${ordersList }">

						<tr
						  <c:if test="${order.returnTime==null }">
							class="newOrder"
						  </c:if>>
							<td>${order.bookName }</td>
							<td>${order.accountLastName } ${order.accountFirstName }</td>
							<td>${order.borrowType }</td>
							<td>${order.formatedBorrowTime }</td>
							<td>${order.formatedReturnTime }</td>
							<td>${order.fine }</td>
							<td><div class="btn-group">
									<a href="/SummaryTask4/librarian/processOrder?id=${order.id }"
										type="button" class="btn btn-info btn-sm"><span
										class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span></a>
								</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</div>