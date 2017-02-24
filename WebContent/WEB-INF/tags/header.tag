<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<nav class="navbar ">
		<div class="container-fluid">
			<div id="jqueryScriptClock">
				<span class="hours"></span><span class="min"></span>
			</div>
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#libNav" aria-expanded="false">
					<span class="sr-only">Lib-On navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="/SummaryTask4/login" class="navbar-brand "> <span
					class="fa fa-book">&nbsp;Lib-On&nbsp;</span><span
					class="fa fa-book"></span>
				</a>

			</div>
			<div class="collapse navbar-collapse" id="libNav">
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${currentPage=='login.jsp' }">
						<li><a href="/SummaryTask4/switch"><mytags:message
									key="page-template.switchLang"></mytags:message></a></li>
					</c:if>
					<c:if test="${CURRENT_ACCOUNT!=null }">
						<li><p class="navbar-text">
								<mytags:message key="page-template.signed" />
								&nbsp;${CURRENT_ACCOUNT.lastName }&nbsp;${CURRENT_ACCOUNT.firstName }
							</p></li>
						<li><a href="/SummaryTask4/logout"><mytags:message
									key="page-template.logout"></mytags:message></a></li>
					</c:if>
					<c:if test="${CURRENT_ACCOUNT==null }">
						<li><a href="/SummaryTask4/login"><mytags:message
									key="page-template.login"></mytags:message></a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
</header>