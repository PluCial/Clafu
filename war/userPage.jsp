<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.clafu.model.*" %>
<%@ page import="com.clafu.Constants" %>
<%
UserModel userModel = (UserModel) request.getAttribute("userModel");

%>
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
		<section id="cta">
				<header>
					<div style="display: inline-block;">
						<img style="width:70px;border-radius: 50%;" src="<%=Constants.GCS_USER_PROFILE_PUBLIC_URL_BASE %><%=userModel.getKey().getId() %>" />
					</div>
					<div style="display: inline-block;">
						<h2>
							<span style="margin-left: 0.5em;"><%=userModel.getUserName() %></span>
						</h2>
						<p style="color:#fff;font-size:1.5em"><span class="icon fa-heart" style="color: #dd4b39;"></span> <%=userModel.getHeartCount() %></p>
					</div>
				</header>
			</section>

		<!-- Main -->
		<section id="main" class="container" style="margin-top: -2em;">
			
 			<header>
				<h2>作成したコンテンツ一覧</h2>
			</header>
			
			<div id="contents-list" class="row">
				<jsp:include page="/common/contents_list.jsp" />
			</div>

		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />
		
		<!-- Common script -->
		<jsp:include page="/common/common_script.jsp" />

	</body>
</html>