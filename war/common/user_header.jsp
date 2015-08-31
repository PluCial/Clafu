<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%
UserModel userModel = (UserModel) request.getAttribute("loginUserModel");
%>

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
