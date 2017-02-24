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
			<form class="form-horizontal" method="post" action="processOrder">
				<input type="hidden" name="id" class="form-control"
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
					<div class="col-sm-8 col-sm-offset-1">
						<select name="borrowType" class="form-control">
							<option value="library card"><mytags:message
									key="librarianNewOrder.libraryCard" /></option>
							<option value="reading hall"><mytags:message
									key="librarianNewOrder.readingHall" /></option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"><mytags:message
							key="librarianOrders.returnTime" /></label>
					<div class="col-sm-8 col-sm-offset-1">
						<div class="input-group date " id="datetimepicker1">
							<input name="returnTime" type="text" class="form-control" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<c:if test="${validationErrorMessage!=null }">
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span>&nbsp;${validationErrorMessage }
					</div>
				</c:if>
				<div class="text-center">
					<button type="submit" class="btn btn-default">
						<mytags:message key="librarianNewOrder.confirm" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
</main>
</div>