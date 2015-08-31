<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%@ page import="com.clafu.service.*" %>
<%@ page import="com.clafu.utils.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.TimeZone" %>
<%
List<TransmitHistoryModel> contentList = (List<TransmitHistoryModel>) request.getAttribute("contentList");
String cursor = null;
boolean hasNext = false;
if (request.getAttribute("cursor") != null && request.getAttribute("hasNext") != null) {
	cursor = (String) request.getAttribute("cursor");
	hasNext = Boolean.valueOf((String) request.getAttribute("hasNext"));
}
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
		<section id="main" class="container" style="margin-top: -2em;">
			
			<div class="row">
				<%
				for(TransmitHistoryModel historyModel: contentList) {
					long userId = historyModel.getCreateUserId();
					UserModel createUserModel = UserService.getOrNull(userId);
					ContentModel contentModel = ContentService.getOrNull(historyModel.getContentModelRef().getKey().getId());
				%>
				<div class="6u">
					<section class="box special" style="text-align: left;">
						<header style="margin-bottom:1em;">
							<span style="padding-right:10px;">
								<img style="width:50px;border-radius: 50%;vertical-align: middle;" src="<%=Constants.GCS_USER_PROFILE_PUBLIC_URL_BASE %><%=userId %>">
							</span>
							<span><%=createUserModel.getUserName() %></span>
 							<span>
								<a href="https://twitter.com/<%=createUserModel.getUserName() %>" target="_blank" class="icon fa-twitter"><span class="label">Twitter</span></a>
							</span>
						</header>
						<h3 style="min-height: 120px;"><%=contentModel.getTitle() %></h3>
						<p style="min-height: 100px;"><%=contentModel.getPostMessageString() %></p>
						<table>
							<tbody>
								<tr>
									<td><span class="icon fa-calendar" style="padding-right:5px"></span>配信日時</td>
									<td style="text-align: right;"><%=DateUtils.dateToString(contentModel.getWishesExeDate(), "yyyy年MM月dd日 HH時mm分", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN) %></td>
								</tr>
								<tr>
									<td><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>応援者数</td>
									<td style="text-align: right;"><%=contentModel.getPatronCount() %> / <%=contentModel.getPatronMaxLimit() %> 人</td>
								</tr>
								<tr>
									<td><span class="icon fa-users" style="padding-right:5px;color:#00A7FF"></span>リーチ人数</td>
									<td style="text-align: right;"><%=contentModel.getReachCount() %> 人</td>
								</tr>
							</tbody>
						</table>
						
 						<ul class="actions" style="text-align: center;">
							<li><a href="/contentDetails?id=<%=contentModel.getKey().getId() %>" class="button alt">詳細を見る</a></li>
						</ul>
						
					</section>
						
				</div>
				<%}%>
				
				<%if(hasNext) { %>
				<div class="12u">
					<ul class="actions" style="text-align: center;">
						<li><a href="?cursor=<%=cursor %>" class="button alt">もっと読み込む</a></li>
					</ul>
				</div>
				<%} %>
			</div>

		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />

	</body>
</html>