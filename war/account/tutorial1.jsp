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
				<h2>Clafuをご利用頂きありがとうございます。</h2>
			</header>
			
			<div class="row">
				<div class="12u">
					<!-- Text -->
					<section class="box">
						<p>Clafu(クラフ)とは不特定多数の人のSNSアカウントの配信権を集めるためのサービスです。</p>
						<p>ブログ更新など何かをSNSで告知したい時に、Clafuを使うことであなたの告知を応援してくれる方を集めることができます。
						そして、あなたが設定した告知配信日時になると、すべての応援者のSNSアカウントからあなたの告知がリツートされます。</p>
						
						<h4>応援者者が集まりやすい仕組み</h4>
						<p>応援者が集まりやすい所はClafuのすごい所の一つです。<br />「人の告知を応援すると、人はあなたの告知を応援する」詳細は・・・</p>
						<p><a href="/info/clafu" target="_blank">Clafuとは<span class="icon fa-external-link" style="padding-left:5px;"></span></a></p>
						
						
						<ul class="actions" style="text-align: center;">
							<li>
								<a href="/account/tutorial2" class="button alt">次へ</a>
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