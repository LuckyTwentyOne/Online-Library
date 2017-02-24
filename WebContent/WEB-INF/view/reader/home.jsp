<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="row readerHeader">


	<div class="panel panel-default searchPanel">
		<form action="/SummaryTask4/main/searchBook">
			<div class="panel-heading">
				<h3 class="panel-title"><mytags:message key="readerHome.searchBook"/></h3>
			</div>
			<div class="panel-body">
				<div class="input-group">
					<input type="text" name="query" class="form-control"
						placeholder='<mytags:message key="readerHome.searchQuery"/>'> <span class="input-group-btn">
						<button type="submit" id="goSearch" class="btn btn-default"><mytags:message key="readerHome.search"/></button>
					</span>
				</div>
				<div class="search-options">
					<a data-toggle="collapse" href="#searchOptions"><mytags:message key="readerHome.searchFilter"/>
						<span class="caret"></span>
					</a>
				</div>
			</div>
			<div id="searchOptions" class="collapse">
				<div class="panel-body">
					<div class="radio">
						<label> <input type="radio" name="searchBy" value="name"
							checked> <mytags:message key="readerHome.searchByName"/>
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="searchBy" value="author">
							<mytags:message key="readerHome.searchByAuthor"/>
						</label>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="panel panel-default searchPanel searchPanelNav">
		<div class="panel-heading">
			<h3 class="panel-title"><mytags:message key="readerHome.sortBooks"/></h3>
		</div>
		<div class="panel-body">
			<ol class="breadcrumb">
				<li><a href="/SummaryTask4/main"><mytags:message key="readerHome.recentlyAdded"/></a></li>
				<li><a href="/SummaryTask4/main/sortByName"><mytags:message key="readerHome.byName"/></a></li>
				<li><a href="/SummaryTask4/main/sortByAuthor"><mytags:message key="readerHome.byAuthor"/></a></li>
				<li><a href="/SummaryTask4/main/sortByPublisher"><mytags:message key="readerHome.byPublisher"/></a></li>
				<li><a href="/SummaryTask4/main/sortByYear"><mytags:message key="readerHome.byYear"/></a></li>
			</ol>
		</div>
	</div>


</div>
<div class="row" style="min-height: 600px">
	<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
		<c:if test="${CURRENT_ACCOUNT!=null}">
			<div id="readerNav" class="panel panel-default readerNav">
				<div class="list-group">
					<a href="/SummaryTask4/reader/main" class="list-group-item active"><mytags:message key="readerHome.navMain"/></a>
					<a href="/SummaryTask4/reader/myInfo" class="list-group-item"><mytags:message key="readerHome.navMyInfo"/></a>
					<a href="/SummaryTask4/reader/myBooks" class="list-group-item"><mytags:message key="readerHome.navMyBooks"/></a>
				</div>
			</div>
			<c:if test="${errorMessage!=null }">
				<div class="alert alert-danger" role="alert">
					<span class="glyphicon glyphicon-exclamation-sign"
						aria-hidden="true"></span>&nbsp;${errorMessage }
				</div>
			</c:if>
		</c:if>
		<c:if test="${CURRENT_ACCOUNT==null}">
			<div class="infoPanel">
				<p><mytags:message key="readerHome.infoMessage"/></p>
			</div>

			<c:if test="${errorMessage!=null }">
				<div class="alert alert-danger" role="alert">
					<span class="glyphicon glyphicon-exclamation-sign"
						aria-hidden="true"></span>&nbsp;${errorMessage }
				</div>
			</c:if>
		</c:if>
	</aside>
	<main class="col-xs-12 col-sm-8 col-md-9 col-lg-9">
	<div id="bookList">
		<div class="row">
			<c:forEach var="book" items="${booksList }">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
					<div id="book${book.id }" class="panel panel-default book">
						<div class="panel-body">
							<h4 class="name">${book.name }</h4>
							<div class="thumbnail">
								<img src="/SummaryTask4/static/img/book-cover.png"
									alt="book-cover" />
							</div>

							<div class="bookDescription">
								<div>
									<strong><mytags:message key="readerHome.bookList.author"/> </strong><span class="author">&nbsp;${book.authorList }</span>
								</div>
								<div>
									<strong><mytags:message key="readerHome.bookList.publisher"/> </strong><span class="publisher">"&nbsp;${book.publisher }"</span>
								</div>
								<div>
									<strong><mytags:message key="readerHome.bookList.year"/> </strong><span class="year">&nbsp;${book.imprintYear }</span>
								</div>
								<div>
									<strong><mytags:message key="readerHome.bookList.freeBooks"/> </strong><span class="freeCopies">&nbsp;${book.freeCopies }</span>
								</div>
							</div>
							<div class="clear"></div>
							<hr />
							<div class="text-center">
								<a href="/SummaryTask4/makeOrder?idBook=${book.id }"
									class="btn btn-default"
									onclick="return confirm('<mytags:message key="readerHome.bookList.orderConfirm"/>')">
									<mytags:message key="readerHome.bookList.order"/>	
								</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<div class="clear"></div>
			<div class="text-center">
				<nav aria-label="Search results pages">
					<ul class="pagination">
						<c:if test="${pageNumber != 1}">
							<li><a href="${curURI }${query }&page=${pageNumber - 1 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						<c:forEach begin="1" end="${neededPages}" var="i">
							<c:choose>
								<c:when test="${pageNumber eq i}">
									<li><a href="#">${i }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${curURI }${query }&page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${pageNumber lt neededPages}">
							<li><a href="${curURI }${query }&page=${pageNumber + 1 }"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>

	</div>
	</main>
</div>