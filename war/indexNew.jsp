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
	</head>
	<body class="landing">

		<!-- Header -->
		<header id="header" class="alt">
			<jsp:include page="/common/main_header.jsp" />
		</header>

		<!-- Banner -->
		<section id="banner">
			<h2 style="font-weight: 400;"><span style="color:#dd4b39">C</span>lafu</h2>
			<!-- <p>SNS告知のためのフォロワー集めはもう必要ない！<br />クラウドファンディングで資金の代わりに告知権を集めよう！</p> -->
<!-- 			<p>あなたの思いをたくさんの人に届けるソーシャルコミュニティ<br />あなたの思いを私たちが代弁します</p> -->
<!-- 			<p>人の告知に協力し、そして人はあなたの告知に協力する。</p> -->
			<p>これでもアクセスが増えなかったらもう諦めよう！</p>
			<ul class="actions">
				<%if(!isLogin) { %>
				<li><a href="/login" class="button special">はじめる(ログイン)</a></li>
				<%} %>
				<li><a href="/info/clafu" class="button">Clafuとは</a></li>
			</ul>
			
			<%-- <ul class="list-inline">
				<li style="height: 77px;display: inline-block;vertical-align: bottom;">
					<div class="g-plusone" data-size="tall" data-href="${thisPageUrl }"></div>
                </li>
                <li style="height: 77px;display: inline-block;vertical-align: bottom;">
                	<a href="https://twitter.com/share" class="twitter-share-button" data-count="vertical">Tweet</a><script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
                </li>
                <li style="height: 77px;display: inline-block;vertical-align: bottom;">
                	<div class="fb-like" data-href="${thisPageUrl }" data-send="false" data-layout="box_count" data-width="55" data-show-faces="true" data-font="arial"></div>
                </li>
                <li style="height: 77px;display: inline-block;vertical-align: bottom;">
                	<a href="http://b.hatena.ne.jp/entry/${thisPageUrl }" class="hatena-bookmark-button" data-hatena-bookmark-title="PluCial - Google+の投稿をブログにするサービス" data-hatena-bookmark-layout="vertical-balloon" data-hatena-bookmark-lang="ja" title="このエントリーをはてなブックマークに追加"><img src="http://b.st-hatena.com/images/entry-button/button-only@2x.png" alt="このエントリーをはてなブックマークに追加" width="20" height="20" style="border: none;" /></a><script type="text/javascript" src="http://b.st-hatena.com/js/bookmark_button.js" charset="utf-8" async="async"></script>
                </li>
             </ul> --%>
		</section>

		<!-- Main -->
		<section id="main" class="container">
		
			<!-- Contents Menu -->
			<jsp:include page="/common/contents_menu.jsp" />
					
			<div style="text-align: center;">
				<h3>配信直前の告知</h3>
			</div>
			<div id="contents-list" class="row">
				<jsp:include page="/common/contents_list.jsp" >
					<jsp:param name="listType" value="exeDateLimit" />
				</jsp:include>
			</div>
			<div style="text-align: right;">
				<p><a href="#">配信直前の告知をもっと見る</a><span class="icon fa-external-link" style="padding-left:5px;"></span></p>
			</div>
			
 			<div style="text-align: center;margin-top:3em">
				<h3>新着告知</h3>
			</div>
			<div id="contents-list" class="row">
				<jsp:include page="/common/contents_list.jsp" />
			</div>
			<div style="text-align: right;">
				<p><a href="#">新着告知をもっと見る</a><span class="icon fa-external-link" style="padding-left:5px;"></span></p>
			</div>

		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />
		
		<!-- Common script -->
		<jsp:include page="/common/common_script.jsp" />

	</body>
</html>