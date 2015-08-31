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
<!-- 			<p>人の告知を応援し、人はあなたの告知を応援する。</p> -->
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
		
			<section class="box special" style="margin-bottom:3em">
				<header class="major">
					<h2>告知のためのフォロワー集めはもう必要ない！</h2>
					<p>ブログ更新、ニュース記事、プレスリリースなど<br />自分のフォロワーを集める代わりに告知の協力者を集めよう。<br />
					集まった協力者が持つすべてのフォロワーにあなたの告知が届きます。</p>
				</header>
 				<span class="image featured"><img src="/images/banner2.jpg" alt="" /></span>
			</section>
			
 			<div style="text-align: center;">
				<h3><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>SNSアカウントを使って協力し合おう</h3>
			</div>
						
			<section class="box special features">
				<div class="features-row">
					<section>
						<span class="icon major button fa-heart accent2"></span>
						<h3>①新規登録でハートをゲット</h3>
						<p style="text-align: left;">新規登録キャンペーンに参加して最初のハート50個をゲットしよう。<br />
					</section>
					<section>
						<span class="icon major fa-pencil-square-o accent3"></span>
						<h3>②告知を作成しよう</h3>
						<p style="text-align: left;">もらったハートを使って自分の告知を作成しよう。50個のハートを使えば50人の協力者を集められる告知を作成できます。</p>
					</section>
				</div>
				<div class="features-row">
					<section>
						<span class="icon major fa-heart accent4"></span>
						<h3>③人の告知に協力する</h3>
						<p style="text-align: left;">次の告知のためのハートが足りなくなったら、他の方の告知に協力すればいつでもまたハートをゲットできます。</p>
					</section>
					<section>
						<span class="icon major fa-bullhorn accent5"></span>
<!-- 						<h3><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>の効果を体感しよう</h3> -->
						<h3>一斉配信の効果を体感する</h3>
						<p style="text-align: left;">あなたが告知に設定した配信日時になると、告知に参加した全協力者のSNSアカウントからあなたの告知が一斉に配信されます。</p>
					</section>
				</div>
			</section>
					
 			<div style="text-align: center;">
				<h3>新着告知</h3>
			</div>
			<div id="contents-list" class="row">
				<jsp:include page="/common/contents_list.jsp" />
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