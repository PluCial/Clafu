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
UserModel loginUserModel = (UserModel) request.getAttribute("loginUserModel");
%>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="/common/html_head.jsp" />
	</head>
	<body>

		<!-- Header -->
		<header id="header" class="skel-layers-fixed">
			<jsp:include page="/common/main_header.jsp" />
		</header>

		<!-- Main -->
		<section id="main" class="container">
			<header>
				<h2>Clafuの使い方 (告知の作成)</h2>
			</header>
			
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<h3><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>の数 = 告知に参加できる応援者の人数枠</h3>
						<p>告知を作成するためにはハートが必要です。告知に使用するハート数を告知作成時に設定することができます。<br />
						そして、その告知に使用したハートの数はその告知に参加できる応援者の人数枠になります。<br />
						例えば、100ハートを使えば100人が参加できる告知を作成することができます。</p>
						<p>ハートの入手方法について次で説明します。</p>
						
						<h3><span class="icon fa-calendar" style="padding-right:5px;"></span>告知の配信日時</h3>
						<p>告知を作成する時にその告知の配信する日時を設定することができます。<br />
						設定した日時になると、集まった応援者全員のSNSアカウントからあなたの告知をリツイートします。<br />また、設定したハートの数の応援者が集まらなくても告知は配信されます。ただし、この場合は余った分のハートは返ってくることがありませんのでご了承ください。</p>
						
						<ul class="actions" style="text-align: center;">
							<li><a href="/account/tutorial1" class="button alt">戻る</a></li>
							<li>
								<a href="/account/tutorial3" class="button alt">次へ</a>
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