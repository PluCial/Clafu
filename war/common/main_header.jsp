<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%
UserModel loginUserModel = null;
boolean isLogin = Boolean.valueOf((String) request.getAttribute("isLogin"));
if(isLogin) {
	loginUserModel = (UserModel) request.getAttribute("loginUserModel");
}
String callbackUri = (String)request.getAttribute("thisPageUri");
%>
			<h1><a href="/">Clafu</a></h1>
			<nav id="nav">
				<ul>
					<li><a href="/">ホーム</a></li>
					<%if(!isLogin) { %>
					<li><a href="/info/clafu">Clafuとは</a></li>
					<%} %>
					<li>
						<a href="" class="icon fa-angle-down"><span class="icon fa-search" style="padding-right:5px;"></span>告知を探す</a>
						<ul>
							<li><a href="/exeDateLimitContents">配信直前告知</a></li>
							<li><a href="/newContents">新着告知</a></li>
							<li>
								<a href="">カテゴリから探す</a>
								<ul>
									<%for(CategoryModel categoryModel: Constants.CATEGORY_LIST) { %>
									<li><a href="/category/<%=categoryModel.getKey() %>"><%=categoryModel.getName() %></a></li>
									<%} %>
								</ul>
							</li>
						</ul>
					</li>
					<%if(!isLogin) { %>
					<li><a href="/login?callback=<%=callbackUri %>" class="button">ログイン</a></li>
					<%}else { %>
					<li><a href="/account/createStep1" class="icon fa-pencil-square-o button">告知の作成</a></li>
					<li>
						<a href="" class="icon fa-angle-down"><span class="icon fa-user" style="padding-right:5px;color:#00A7FF"></span><%=loginUserModel.getUserName() %></a>
						<ul>
							<li><a href="/account/createStep1">告知の作成</a></li>
							<li><a href="/user/<%=loginUserModel.getKey().getId() %>">作成した告知一覧</a></li>
							<li><a href="/account/tutorial1">チュートリアル</a></li>
							<li><a href="/account/logout?callback=<%=callbackUri %>">ログアウト</a></li>
						</ul>
					</li>
					<%} %>
				</ul>
			</nav>
