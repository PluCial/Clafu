<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="/common/html_head.jsp" />
	</head>
	<body class="landing">

		<!-- Header -->
		<header id="header" class="alt">
			<jsp:include page="/common/main_header.jsp" />
		</header>
		
		<!-- Contents Menu -->
		<jsp:include page="/common/my_page_menu.jsp" />

		<!-- Main -->
		<section id="main" class="container" style="margin-top: -2em;">
		
			<div style="text-align: right;">
				<ul class="actions">
					<li>
						<a href="/account/createStep1" class="button icon fa-pencil-square-o" style="background-color: #E26564;">告知を作成</a></li>
				</ul>
			</div>
			
			<jsp:include page="/common/contents_list.jsp" />

		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />

	</body>
</html>