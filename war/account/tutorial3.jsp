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
UserModel userModel = (UserModel) request.getAttribute("newUserModel");
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
				<h2>Clafuの使い方 (ハートの入手)</h2>
			</header>
			
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<h3>人の告知に参加して<span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>をゲット</h3>
						<p>自分の告知を作成するためのハートが足りなくなったら、他の方の告知を応援するといつでもハートをゲットすることができます。<br />
						また、他の方の告知に参加するとその告知に設定している配信日時になるとあなたのSNSアカウントから告知が配信されます。</p>
						<p>自分のSNSアカウントから参加していない告知が配信されることがありません。自分の共感できる告知を探してどんどんハートを貯めましょう。
						</p>
						
						<%if(!userModel.isNewUserCampaignFlg()) { %>
						<h3 style="margin-top: 2.5em;">新規登録キャンペーンで<span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>をゲット</h3>
						<p>新規登録時に限り、こちらのキャンペーンに参加すると<span style="color:red">ハート50個</span>をゲットできます。これを使って50人の応援者を集められる告知を作成することができます。</p>
						
						<h4>キャンペーン詳細：</h4>
						<table style="margin-bottom: 1em;">
							<tbody>
								<tr>
									<td colspan="2">こちらのキャンペーンに参加すると、Clafuの告知を一度だけあなたのSNSアカウントから配信されます。<br />また、この告知は一定の応援者数になると配信されます。(配信日時未定)</td>
								</tr>
								<tr>
									<td><span class="icon fa-calendar" style="padding-right:5px"></span>告知予定日時</td>
									<td style="text-align: right;">未定</td>
								</tr>
								<tr>
									<td><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>もらえるハート</td>
									<td style="text-align: right;">50 個</td>
								</tr>
							</tbody>
						</table>
						
						<form method="post" action="/account/newUserCampaign">
							<div style="margin-top: 1.5em;text-align: center;">
							<!-- <p style="color:red">参加・不参加の選択はあとから変更することはできません。</p> -->
							<input type="checkbox" id="twit" ${f:checkbox("twit")} /><label for="twit">このキャンペーンに参加する</label>
							</div>						
						
						
							<ul class="actions" style="text-align: center;">
								<li><a href="/account/tutorial2" class="button alt">戻る</a></li>
								<li>
									<input class="special" type="submit" value="Clafuをはじめる">
								</li>
							</ul>
						</form>
						<%}else { %>
						<ul class="actions" style="text-align: center;">
							<li><a href="/account/tutorial2" class="button alt">戻る</a></li>
							<li>
								<a href="/account/tutorialEnd" class="button alt">Clafuをはじめる</a>
							</li>
						</ul>
						
						<%} %>
					</section>

				</div>
			</div>
		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />

	</body>
</html>