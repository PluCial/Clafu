<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.clafu.utils.*" %>
<%@ page import="com.clafu.Constants" %>
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
		<jsp:include page="/common/global_nav.jsp" />

		<!-- Main -->
		<section id="main" class="container" style="margin-top: -2em;">
			
 			<header>
				<h2><span class="icon fa-search" style="padding-right:5px;"></span>新着告知</h2>
			</header>
			
			<div id="contents-list" class="row">
				<jsp:include page="/common/contents_list.jsp" />
			</div>
			
			<!-- Contents Menu -->
			<jsp:include page="/common/contents_menu.jsp" />

		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />
		
		<!-- Common script -->
		<jsp:include page="/common/common_script.jsp" />

	</body>
</html>