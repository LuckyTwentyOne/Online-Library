<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="row">

</div>
<div class="row" style="min-height: 600px">
	<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
		<div id="readerNav" class="panel panel-default readerNav">
				<div class="list-group">
					<a href="/SummaryTask4/reader/home" class="list-group-item "><mytags:message key="readerHome.navMain"/></a>
					<a href="/SummaryTask4/reader/myInfo" class="list-group-item"><mytags:message key="readerHome.navMyInfo"/></a>
					 <a href="/SummaryTask4/reader/myBooks"	class="list-group-item  active"><mytags:message key="readerHome.navMyBooks"/></a>
				</div>
			</div>
	</aside>

	<main class="col-xs-12 col-sm-8 col-md-9 col-lg-9">
	<div class="table">
			<div class="row">
				<div class="col-lg-12 text-center table-header">
					<mytags:message key="readerMyBooks.listHeader" />
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th><mytags:message key="librarianOrders.bookName" /></th>
						<th><mytags:message key="librarianOrders.borrowType" /></th>
						<th><mytags:message key="librarianOrders.borrowTime" /></th>
						<th><mytags:message key="librarianOrders.returnTime" /></th>
						<th><mytags:message key="librarianOrders.fine" /></th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${fn:length(ordersList) == 0}">
					<tr>
						<td colspan="5"><mytags:message key="reader.myBooks.empty"/></td>
					</tr>
				</c:if>
				
					<c:forEach var="order" items="${ordersList }">
						<tr>
							<td>${order.bookName }</td>
							<td>${order.borrowType }</td>
							<td>${order.formatedBorrowTime }</td>
							<td>${order.formatedReturnTime }</td>
							<td>${order.fine }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</div>