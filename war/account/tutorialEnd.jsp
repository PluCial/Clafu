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
				<h2>Clafuを始める</h2>
			</header>
			
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<h3><span class="icon fa-pencil-square-o" style="padding-right:5px;"></span>さっそく告知を作成する</h3>
						<p>何かをSNSで告知したい場合は、メニューの「<span class="icon fa-pencil-square-o" style="padding-right:5px;"></span>告知の作成」からいつでも告知を作成することができます。<br />
						また、自分が作成した告知を見たい場合は「マイメニュー」の「作成した告知一覧」をクリックしてください。
						</p>
						
						<ul class="actions" style="text-align: center;">
							<li><a href="/account/createStep1" class="button special">告知の作成</a></li>
						</ul>
						
						<h3 style="margin-top: 2.5em;"><span class="icon fa-heart" style="padding-right:5px;"></span>告知を応援してハートをゲットする</h3>
						<p>ハートをゲットするにはメニューの「<span class="icon fa-search" style="padding-right:5px;"></span>告知を探す」からいつでも共感できる告知を探して参加することができます。応援すればするほどハートがどんどん溜まり、あなたが出す告知の宣伝力がどんどん上がる！</p>
						
						<h3 style="margin-top: 2.5em;">このチュートリアル</h3>
						<p>このチュートリアルをもう一回見たい場合は、「マイメニュー」の「チュートリアル」をクリックすればいつでも見れます。
						</p>
						
						<ul class="actions" style="text-align: center;">
							<li><a href="/account/tutorial3" class="button alt">戻る</a></li>
						</ul>
					</section>

				</div>
			</div>
		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />

	</body>
</html>