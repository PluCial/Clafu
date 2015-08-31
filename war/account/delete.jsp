<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.clafu.model.*" %>
<%@ page import="com.clafu.service.*" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="com.clafu.utils.*" %>
<%@ page import="com.clafu.Constants" %>
<%
ContentModel contentModel = (ContentModel) request.getAttribute("contentModel");
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
		<jsp:include page="/common/user_header.jsp" />

		<!-- Main -->
		<section id="main" class="container" style="margin-top: 0;">
			
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<h2>【<%=contentModel.getTitle() %>】</h2>
						<h3>本当にこちらの告知を削除してよろしいでしょうか？</h3>
						<p>この告知を削除しても使用したハートは返ってきません。</p>
						
						<ul class="actions" style="text-align: center;">
							<li>
								<a href="/contentDetails?id=<%=contentModel.getKey().getId() %>" class="button alt">キャンセル</a>
							</li>
							<li>
								<a href="/account/deleteEntry?id=<%=contentModel.getKey().getId() %>" class="button alt">削除する</a>
							</li>
						</ul>
					</section>

				</div>
			</div>
		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />

	</body>
</html>