<!DOCTYPE HTML>
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
<%@ page import="java.util.List" %>
<%
ContentModel contentModel = (ContentModel) request.getAttribute("contentModel");
List<TransmitHistoryModel> historyList = (List<TransmitHistoryModel>) request.getAttribute("historyList");

long createUserId = contentModel.getCreateUserModelRef().getKey().getId();
UserModel createUserModel = UserService.getOrNull(createUserId);

UserModel loginUserModel = null;
boolean isLogin = Boolean.valueOf((String) request.getAttribute("isLogin"));
if(isLogin) {
	loginUserModel = (UserModel) request.getAttribute("loginUserModel");
}
%>
<html>
	<head>
		<jsp:include page="/common/html_head.jsp" />
		<style type="text/css"><!--
		form span.err {
			color: red;
		}

		//--></style>
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
		
			<header>
				<h2>この告知を応援しよう</h2>
			</header>
			
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<header style="margin-bottom:1em;">
							<span style="padding-right:10px;">
								<img style="width:50px;border-radius: 50%;vertical-align: middle;" src="<%=Constants.GCS_USER_PROFILE_PUBLIC_URL_BASE %><%=createUserId %>">
							</span>
							<a href="https://twitter.com/<%=createUserModel.getUserName() %>" target="_blank" rel="nofollow">
								<span><%=createUserModel.getUserName() %></span>
							</a>
							<span class="icon fa-twitter" style="padding-left:5px;color: #00A7FF;"></span>
						</header>
						<h2 style="font-size: 1.5em;">
							<a href="/contentDetails?id=<%=contentModel.getKey().getId() %>">
								<%=contentModel.getTitle() %>
							</a>
						</h2>
						
						<table style="margin-bottom: 1em;">
							<tbody>
								<tr>
									<td><span class="icon fa-calendar" style="padding-right:5px"></span>配信日時</td>
									<td style="text-align: right;"><%=DateUtils.dateToString(contentModel.getWishesExeDate(), "yyyy/MM/dd/ HH:mm", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN) %></td>
								</tr>
								<tr>
									<td><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>応援者数</td>
									<td style="text-align: right;"><%=contentModel.getPatronCount() %> / <%=contentModel.getPatronMaxLimit() %> 人</td>
								</tr>
								<tr>
									<td><span class="icon fa-users" style="padding-right:5px;color:#00A7FF"></span>リーチ人数</td>
									<td style="text-align: right;"><%=contentModel.getReachCount() %> 人</td>
								</tr>
								<tr>
									<td><span class="icon fa-comments-o" style="padding-right:5px;"></span>コメント</td>
									<td style="text-align: right;"><%=contentModel.getCommentCount() %> 件</td>
								</tr>
							</tbody>
						</table>
						
					</section>
					
					<section class="box" style="background: #f8f8f8;">
						<h3><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>応援者一覧(<%=contentModel.getPatronCount() %>人)</h3>
						
						<table>
							<tbody id="contents-list">
								<jsp:include page="/common/patron_list.jsp" />
							</tbody>
						</table>
					</section>

				</div>
			</div>
		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />
		
		<!-- Common script -->
		<jsp:include page="/common/common_script.jsp" />

	</body>
</html>