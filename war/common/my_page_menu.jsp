<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%
String navType = (String) request.getAttribute("navType");
UserModel loginUserModel = (UserModel) request.getAttribute("loginUserModel");
%>

			<section id="cta">
				<header>
					<div style="display: inline-block;">
						<img style="width:70px;border-radius: 50%;" src="/userImage?id=<%=loginUserModel.getKey().getId() %>" />
					</div>
					<div style="display: inline-block;">
						<h2><span style="margin-left: 0.5em;"><%=loginUserModel.getUserName() %></span></h2>
						<p style="color:#fff;font-size:1.5em"><span class="icon fa-heart" style="color: #dd4b39;"></span> <%=loginUserModel.getHeartCount() %></p>
					</div>
				</header>
				
				<ul class="actions">
					<li><a href="/account/myPage" class="button icon fa-search <%=navType != null && navType.equals(Constants.MY_PAGE_NAV_TYPE_CREATE_HISTORY) ? "special" : "" %>">投稿した告知</a></li>
					<li><a href="/account/patronHistory" class="button icon fa-search <%=navType != null && navType.equals(Constants.MY_PAGE_NAV_TYPE_PATRON_HISTORY) ? "special" : "" %>">参加した告知</a></li>
				</ul>	
			</section>
