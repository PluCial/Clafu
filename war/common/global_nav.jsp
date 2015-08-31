<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%@ page import="java.util.List" %>
<%
boolean isSmartPhone = Boolean.valueOf((String) request.getAttribute("isSmartPhone"));
boolean isLogin = Boolean.valueOf((String) request.getAttribute("isLogin"));
%>
			<section id="cta" style="padding-bottom: 5px;">
				<div class="bxslider" style="background: #83d3c9;">
					<div class="slide">
						<h2 style="font-weight: 400;"><span style="color:#dd4b39">C</span>lafu</h2>
						<p>SNSでの告知力を集めるためのサービスです。<br />これでもアクセスが増えなかったらもう諦めよう！</p>
						
						<ul class="actions">
							<%if(!isLogin) { %>
							<li><a href="/login" class="button special">はじめる(ログイン)</a></li>
							<%} %>
							<li><a href="/info/clafu" class="button">Clafuとは</a></li>
						</ul>
					</div>
					<%if(!isLogin) { %>
					<div class="slide">
						<h2>新規登録でハートをゲット</h2>
						<p>新規登録キャンペーンに参加して最初のハート50個をゲットしよう。</p>
					</div>
					<%} %>
					<div class="slide" style="text-align: center;">
						<h2>告知を作成しよう</h2>
						<p>もらったハートを使って自分の告知を作成しよう。<br />50個のハートを使えば50人が参加できる告知を作成できます。</p>
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
						<p>次の告知のためのハートが足りなくなったら、<br />他の方の告知を応援すればいつでもまたハートをゲットできます。</p>
					</div>
					<div class="slide">
						<h2>一斉配信の効果を体感しよう</h2>
						<p>あなたが告知に設定した配信日時になると、<br />告知に参加した全応援者のSNSアカウントからあなたの告知をリツイートします。</p>
					</div>
				</div>
			</section>
