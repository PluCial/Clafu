<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.clafu.model.*" %>
<%
UserModel loginUserModel = null;
boolean isLogin = Boolean.valueOf((String) request.getAttribute("isLogin"));
%>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="/common/html_head.jsp" />
		<meta name="description" content="これでもブログのアクセスが増えなかったらもう諦めよう！">
		<meta property="og:image" content="http://www.clafu.com/images/ogp_image.jpg">
		<style type="text/css"><!--
			header#header.alt a {
				color: #555;
			}
			#header.alt nav > ul > li a:not(.button) {
				color: #555;
			}
			#header.alt .button {
				color: #555;
				box-shadow: inset 0 0 0 2px #999;
			}

		//--></style>
	</head>
	<body class="landing">

		<!-- Header -->
		<header id="header" class="alt">
			<jsp:include page="/common/main_header.jsp" />
		</header>

		<!-- Banner -->
		<section id="banner">
			<h2 style="font-weight: 400;"><span style="color:#dd4b39">C</span>lafu</h2>
			<p>これでもアクセスが増えなかったらもう諦めよう！</p>
			<ul class="actions">
				<%if(!isLogin) { %>
				<li><a href="/login" class="button special">はじめる(ログイン)</a></li>
				<%} %>
				<li><a href="/info/clafu" class="button">Clafuとは</a></li>
			</ul>
		</section>

		<!-- Main -->
		<section id="main" class="container">
		
			<!-- Contents Menu -->
			<section id="cta" style="padding-bottom: 5px;">
				<div class="bxslider" style="background: #83d3c9;">
					<%if(!isLogin) { %>
					<div class="slide">
						<h2>新規登録でハートをゲット</h2>
						<p>新規登録キャンペーンに参加して最初のハート50個をゲットしよう。</p>
						<p style="text-align: center;"><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;font-size: 4em;"></span></p>
					</div>
					<%} %>
					<div class="slide" style="text-align: center;">
						<h2>告知を作成しよう</h2>
						<p>もらったハートを使って自分の告知を作成しよう。<br />50個のハートを使えば50人の共感してくれる方がこの告知を応援できます。</p>
						<ul class="actions">
							<%if(!isLogin) { %>
							<li><a href="/login" class="button special">ログインして作成</a></li>
							<%}else { %>
							<li><a href="/account/createStep1" class="button special">告知を作成</a></li>
							<%} %>
						</ul>
					</div>
					<div class="slide">
						<h2>人の告知を応援しよう</h2>
						<p>次の告知のためのハートが足りなくなったら、<br />他の方の告知を応援すればいつでもまたハートをゲットできます。<br /><strong style="color:#e9575f">参加していない告知の内容は投稿されることはありません。</strong></p>
					</div>
					<div class="slide">
						<h2>一斉配信の効果を体感しよう</h2>
						<p>あなたが告知に設定した配信日時になると、<br />告知に参加した全応援者のSNSアカウントからあなたの告知を再配信(リツイート)します。</p>
					</div>
				</div>
			</section>
					
			<div style="text-align: center;">
				<h3>配信直前の告知</h3>
			</div>
			<div id="contents-list" class="row">
				<jsp:include page="/common/contents_list.jsp" >
					<jsp:param name="listType" value="exeDateLimit" />
				</jsp:include>
			</div>
			<div style="text-align: right;margin-top:1em">
				<p><a href="/exeDateLimitContents">配信直前の告知をもっと見る</a><span class="icon fa-external-link" style="padding-left:5px;"></span></p>
			</div>
			
 			<div style="text-align: center;margin-top:3em">
				<h2>新着告知</h2>
			</div>
			<div id="contents-list" class="row">
				<jsp:include page="/common/contents_list.jsp" />
			</div>
			<div style="text-align: right;margin-top:1em">
				<p><a href="/newContents">新着告知をもっと見る</a><span class="icon fa-external-link" style="padding-left:5px;"></span></p>
			</div>
			
			<!-- Contents Menu -->
			<jsp:include page="/common/contents_menu.jsp" />

		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />
		
		<!-- Common script -->
		<jsp:include page="/common/common_script.jsp" />

	</body>
</html>