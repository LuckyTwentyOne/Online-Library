<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="row">
	<div class="homePageHeader text-center">
		<mytags:message key="librarianNewOrder.header" />
		№${order.id }
	</div>
</div>
<div class="row" style="min-height: 500px">
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
	<div class="myInfo librarian">
		<div class="orderData ">
			<form class="form-horizontal" method="post" action="closeOrder">
				<input type="hidden" name="idOrder" class="form-control"
					value=${order.id }>
				<div class="form-group">
					<label class="col-sm-3 control-label"><mytags:message
							key="librarianOrders.bookName" /></label>
					<div class="col-sm-9">
						<p class="form-control-static">${order.bookName }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"><mytags:message
							key="librarianOrders.accountName" /></label>
					<div class="col-sm-9">
						<p class="form-control-static">${order.accountLastName }
							${order.accountFirstName }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"><mytags:message
							key="librarianOrders.borrowType" /></label>
					<div class="col-sm-9">
						<p class="form-control-static">${order.borrowType }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"><mytags:message
							key="librarianCloseOrder.borrowTime" /></label>
					<div class="col-sm-9">
						<p class="form-control-static">${order.formatedBorrowTime }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"><mytags:message
							key="librarianCloseOrder.returnTime" /></label>
					<div class="col-sm-9">
						<p class="form-control-static">${order.formatedReturnTime }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"><mytags:message
							key="librarianCloseOrder.fine" /></label>
					<div class="col-sm-9">
						<p class="form-control-static">
							<strong>${order.fine }</strong>
						</p>
					</div>
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-success"
						onclick="return confirm('<mytags:message key="librarianCloseOrder.confirmClosing"/>')">
						<mytags:message key="librarianCloseOrder.confirm" />
					</button>
					<a href="/SummaryTask4/librarian/orders" class="btn btn-danger "><mytags:message
							key="adminHomeEditAcc.cancelButton" /></a>
				</div>
			</form>
		</div>
	</div>
</div>
</main>
</div>