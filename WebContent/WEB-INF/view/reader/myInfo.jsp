<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="row">
</div>
<div class="row" style="min-height: 600px">
	<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
		<div id="readerNav" class="panel panel-default readerNav">
				<div class="list-group">
					<a href="/SummaryTask4/reader/home" class="list-group-item "><mytags:message key="readerHome.navMain"/></a>
					<a href="/SummaryTask4/reader/myInfo" class="list-group-item active"><mytags:message key="readerHome.navMyInfo"/></a>
					 <a href="/SummaryTask4/reader/myBooks"	class="list-group-item"><mytags:message key="readerHome.navMyBooks"/></a>
				</div>
			</div>
	</aside>

	<main class="col-xs-12 col-sm-8 col-md-9 col-lg-9">
	<div class="row">
		<div class="myInfo librarian">
			<div class="myInfoData ">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 control-label"><mytags:message key="librarianHome.myInfoLogin"/></label>
						<div class="col-sm-10">
							<p class="form-control-static">${CURRENT_ACCOUNT.login }</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><mytags:message key="librarianHome.myInfoEmail"/></label>
						<div class="col-sm-10">
							<p class="form-control-static">${CURRENT_ACCOUNT.email }</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><mytags:message key="librarianHome.myInfoFirstName"/></label>
						<div class="col-sm-10">
							<p class="form-control-static">${CURRENT_ACCOUNT.firstName }</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><mytags:message key="librarianHome.myInfoLastName"/></label>
						<div class="col-sm-10">
							<p class="form-control-static">${CURRENT_ACCOUNT.lastName }</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><mytags:message key="librarianHome.myInfoCreated"/></label>
						<div class="col-sm-10">
							<p class="form-control-static">${CURRENT_ACCOUNT.created }</p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</main>
</div>