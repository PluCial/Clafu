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
UserModel loginUserModel = (UserModel) request.getAttribute("loginUserModel");
UserModel createUserModel = (UserModel) request.getAttribute("createUserModel");
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
		<jsp:include page="/common/global_nav.jsp" />

		<!-- Main -->
		<section id="main" class="container" style="margin-top: 0;">
			
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<header style="margin-bottom:1em;">
							<span style="padding-right:10px;">
								<img style="width:50px;border-radius: 50%;vertical-align: middle;" src="<%=Constants.GCS_USER_PROFILE_PUBLIC_URL_BASE %><%=createUserModel.getKey().getId() %>">
							</span>
							<span><%=createUserModel.getUserName() %></span>
							<span>
								<a href="https://twitter.com/<%=createUserModel.getUserName() %>" target="_blank" class="icon fa-twitter"><span class="label">Twitter</span></a>
							</span>
						</header>
						<h3><%=loginUserModel.getUserName() %> さんへ</h3>
						<p>告知の応援ありがとうございます。<br />ささやかですが、私からのハートをよかったらご自身の告知に使ってください。</p>
						<p style="margin-top: 2em;color:#e89980;font-weight: 400;">		
							<%=createUserModel.getUserName() %> さんから<span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span><%=Utils.getGiftHeartCount(loginUserModel.getTwitterFollowersCount()) %> 個のプレゼントを受け取りました。
						</p>
						<ul class="actions">
							<li>
								<a href="/contentDetails?id=<%=contentModel.getKey().getId() %>" class="button alt">戻る</a>
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