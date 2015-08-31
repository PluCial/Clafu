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
<%@ page import="java.util.List" %>
<%
ContentModel contentModel = (ContentModel) request.getAttribute("contentModel");
List<TransmitHistoryModel> historyList = (List<TransmitHistoryModel>) request.getAttribute("historyList");
List<CommentModel> commentList = (List<CommentModel>) request.getAttribute("commentList");

long createUserId = contentModel.getCreateUserModelRef().getKey().getId();
UserModel createUserModel = UserService.getOrNull(createUserId);

UserModel loginUserModel = null;
boolean isLogin = Boolean.valueOf((String) request.getAttribute("isLogin"));
if(isLogin) {
	loginUserModel = (UserModel) request.getAttribute("loginUserModel");
}

String callbackUri = (String)request.getAttribute("thisPageUri");
%>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="/common/html_head.jsp" />
		<style type="text/css"><!--
			.link-box-area {
				/* boxレイアウトの指定 */
				display: box;
				display: -webkit-box;
				display: -moz-box;

				/* 配置したボックスを左右中央寄せにする */
				box-pack: center;
				-webkit-box-pack: center;
				-moz-box-pack: center;

				border: 1px solid #ccc;
				border-radius: 5px;
			}
			
			.link-box-area .content-image-box {
				padding: 30px 30px 0px 20px;
				text-align: center;
				width:4em;
			}
			
			.link-box-area .content-title-box {
				box-flex: 1;
				-webkit-box-flex: 1;
				-moz-box-flex: 1;
				padding: 10px 10px 10px 20px;
				text-align: left;
			}
			
			.link-box-area .content-title-box p {
				color: #aaa;
				font-size: 12px;
				margin-bottom: 0;
			}
		form span.err {
			color: red;
		}

		//--></style>
	</head>
	<body class="landing">

		<!-- Header -->
		<header id="header" class="alt">
			<jsp:include page="/common/main_header.jsp" />
		</header>
		
		<!-- Contents Menu -->
		<jsp:include page="/common/global_nav.jsp" />

		<!-- Main -->
		<section id="main" class="container" style="margin-top: 0;">
		
			<header>
				<h2>この告知を応援しよう</h2>
			</header>
			
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<header style="margin-bottom:1em;">
							<span style="padding-right:10px;">
								<a href="/user/<%=createUserModel.getKey().getId() %>">
								<img style="width:50px;border-radius: 50%;vertical-align: middle;" src="<%=Constants.GCS_USER_PROFILE_PUBLIC_URL_BASE %><%=createUserId %>">
								</a>
							</span>
							<a href="/user/<%=createUserModel.getKey().getId() %>">
								<span><%=createUserModel.getUserName() %></span>
							</a>
						</header>
						<h2 style="font-size: 1.5em;border-bottom: 1px solid #ccc;padding-bottom: 1em;margin-bottom: 1em;">
							<a href="/contentDetails?id=<%=contentModel.getKey().getId() %>">
								<%=contentModel.getTitle() %>
							</a>
						</h2>
						<p><%=Utils.changeStringToHtml(contentModel.getExplanationString()) %></p>
						
						<%if(contentModel.getContentTitle() == null) { %>
						<ul class="actions" style="text-align: center;font-size: 0.8em;">
 							<li>
 								<a href="<%=contentModel.getUrlString() %>" target="_blank" rel="nofollow" class="button alt">
 									<span class="icon fa-globe" style="padding-right:5px;"></span>告知ページはこちら
 								</a>
 							</li>
 						</ul>
 						<%}else { %>
 						<div class="link-box-area" style="margin:3em 0;">
 							<div class="content-image-box">
								<span class="icon fa-link" style="font-size:3em;padding-right:5px;"></span>
							</div>
							<div class="content-title-box">
								<h4 style="margin-top: 0;"><a href="<%=contentModel.getUrlString() %>" target="_blank"><%=contentModel.getContentTitle() %></a></h4>
								<blockquote style="margin-bottom: 0;">
									<p><span class="icon fa-globe" style="padding-right:5px;"></span><%=Utils.getURLPrivateDomain(contentModel.getUrlString()) %></p>
									<p><%=contentModel.getContentDescriptionString() == null ? "" : contentModel.getContentDescriptionString() %></p>
								</blockquote>
							</div>
						</div>
						<%} %>
						
						<table style="margin-bottom: 1em;">
							<tbody>
								<tr>
									<td><span class="icon fa-calendar" style="padding-right:5px"></span>配信日時</td>
									<td style="text-align: right;"><%=DateUtils.dateToString(contentModel.getWishesExeDate(), "yyyy/MM/dd/ HH:mm", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN) %></td>
								</tr>
								<tr>
									<td><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>応援者数</td>
									<td style="text-align: right;"><%=contentModel.getPatronCount() %> / <%=contentModel.getPatronMaxLimit() %> 人</td>
								</tr>
								<tr>
									<td><span class="icon fa-users" style="padding-right:5px;color:#00A7FF"></span>リーチ人数</td>
									<td style="text-align: right;"><%=contentModel.getReachCount() %> 人</td>
								</tr>
								<tr>
									<td><span class="icon fa-comments-o" style="padding-right:5px;"></span>コメント</td>
									<td style="text-align: right;"><%=contentModel.getCommentCount() %> 件</td>
								</tr>
							</tbody>
						</table>
						
						<%if(isLogin && loginUserModel != null 
 								&&	!contentModel.isEndFlg()
 								&& loginUserModel.getKey().getId() == contentModel.getCreateUserModelRef().getKey().getId()) { %>
						<ul class="actions" style="text-align: center;font-size: 0.8em;">
 							<li>
 								<a href="/account/editContent?id=<%=contentModel.getKey().getId() %>" class="button alt">
 									<span class="icon fa-pencil" style="padding-right:5px;"></span>コンテンツを修正
 								</a>
 							</li>
 							<li>
 								<a href="/account/delete?id=<%=contentModel.getKey().getId() %>" class="button alt">
 									<span class="icon fa-times" style="padding-right:5px;"></span>コンテンツを削除
 								</a>
 							</li>
						</ul>
						<%} %>
					</section>
					

					<!-- Text -->
					<section class="box">
						
						<h3><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>この告知を応援する</h3>
						
						<div class="row collapse-at-2">
							<div class="8u">
								<%if(contentModel.isEndFlg()) { %>
								<p>この告知既に終了しています。</p>
								
								<%}else if(contentModel.getPatronMaxLimit() <= contentModel.getPatronCount()) { %>
								<p>この告知はもう既に応援者枠の上限に達しているため参加できません。</p>
								
								<%}else if(contentModel.isEntered()) { %>
								<p>既にこの告知を応援しています。告知予定日時になるとこの告知はあなたのタイムラインに自動的に投稿されます。</p>
								
								<%}else { %>
								<p>この告知を応援すると、自分が告知を作成する時に利用できる<span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>をゲットできます。</p>
								
								<h4><span class="icon fa-bullhorn" style="padding-right:5px;"></span>告知がどういう風に配信されるのか。</h4>
								<p>告知に設定されている配信日時になると告知は作成者と応援者のアカウントから以下のように自動的に配信されます。また、配信は<span style="color:#e9575f">5分〜20分のタイムラグ</span>が発生する可能性がございますのでご了承ください。</p>
								<ol>
									<li>まず告知作成者のアカウントから告知が配信されます。</li>
									<li>告知配信後にすべての応援者のアカウントから再配信(リツイート)します。</li>
								</ol>
<!-- 								<p><span style="color:#e9575f">5分〜20分のタイムラグ</span>がございますのでご了承ください。</p> -->
								<!-- <p>告知予定日時になると告知作成者のアカウントから自動的にこの告知が配信され、告知に参加しているすべての応援者のアカウントから自動的に配信された告知に対してリツイートが開始されます。<br /><span style="color:#e9575f">5分〜20分のタイムラグ</span>がございますのでご了承ください。</p> -->
								<!-- <p>告知予定日時になると以下の内容があなたのタイムラインに自動で投稿されます。<br />投稿内容を確認し、<span style="color:#e9575f">必要があれば修正した上</span>で応援ボタンをクリックしてください。</p> -->
								<%}%>
								
								<form method="post" action="/account/patronEntry">
							
<%-- 									<div class="row uniform half">
										<div class="12u">
											<textarea name="postMessage" id="postMessage" placeholder="告知文章" rows="4" maxlength="90" <%=!isLogin || contentModel.isEndFlg() || contentModel.isEntered() ? "disabled" : "" %>>${f:h(contentModel.postMessageString)}</textarea>
											<span class="err">${errors.postMessage}</span>
										</div>
									</div> --%>
									
									<div class="row uniform half">
										<div class="12u">
											<textarea name="postMessage" id="postMessage" placeholder="告知文章" rows="4" maxlength="90" disabled>${f:h(contentModel.postMessageString)}</textarea>
											<span class="err">${errors.postMessage}</span>
										</div>
									</div>
							
									<%if(isLogin) { %>
										<%if(!contentModel.isEndFlg() && !contentModel.isEntered() && contentModel.getPatronMaxLimit() > contentModel.getPatronCount()) { %>
										<div style="margin-top:3em;text-align: center;">
											<input type="checkbox" id="twit" ${f:checkbox("twit")} /><label for="twit">応援したことをフォロワーに知らせる</label>
											<ul class="actions">
												<li>
													<input type="hidden" name="contentId" value="<%=contentModel.getKey().getId()%>">
													<input type="hidden" name="socialType" value="<%=Constants.SOCIAL_TYPE_TWITTER%>">
													<input type="hidden" name="postMessage" value="<%=contentModel.getPostMessageString() %>">
													<input style="background-color: #00A7FF;" class="special" type="submit" value="Twitterで応援">
												</li>
											</ul>
										</div>
										<%} %>
										
									<%}else { %>
									<div style="margin-top:3em;text-align: center;">
										<ul class="actions">
											<li>
												<a href="/login?callback=<%=callbackUri %>" class="button alt">Clafuにログインして応援する</a>
											</li>
										</ul>
									</div>
									<%} %>
							
								</form>
							</div>
							<div class="4u">
								<h4>応援者(<%=contentModel.getPatronCount() %>人)</h4>
								<ul style="list-style: none;">
									<%for(TransmitHistoryModel historyModel: historyList) { %>
									<li>
										<a href="/user/<%=historyModel.getUserModelRef().getKey().getId() %>">
											<img style="width:40px;vertical-align: -15px;padding-top: 20px;" src="<%=Constants.GCS_USER_PROFILE_PUBLIC_URL_BASE %><%=historyModel.getUserModelRef().getKey().getId() %>" />
										</a>
										<a href="/user/<%=historyModel.getUserModelRef().getKey().getId() %>">
											<span style="margin-left: 5px;"><%=historyModel.getUserName() %></span>
										</a>
									</li>
									<%} %>
								</ul>
								<%if(historyList.size() > 0) { %>
								<div style="font-size: 0.8em;text-align: right;">
									<span class="icon fa-external-link" style="padding-right:5px;"></span><a href="/contents/patrons/<%=contentModel.getKey().getId() %>">すべての応援者を見る</a>
								</div>
								<%} %>
							</div>
						</div>
					</section>
					
					<section class="box" style="background: #f8f8f8;">
						<h3><span class="icon fa-comments-o" style="padding-right:5px;"></span>コメント(<%=contentModel.getCommentCount() %>件)</h3>
						<form method="post" action="/account/addComment">
							<%if(isLogin) { %>
							<div class="row uniform half">
								<div class="12u">
									<textarea name="comment" id="comment" placeholder="コメントを入力してください" rows="3">${f:h(comment)}</textarea>
									<span class="err">${errors.comment}</span>
								</div>
							</div>
							<%}%>
							
							<div style="text-align: right;">
								<ul class="actions">
									<li>
										<%if(isLogin) { %>
										<input type="hidden" name="contentId" value="<%=contentModel.getKey().getId()%>">
										<input class="special" type="submit" value="コメントする">
										<%}else { %>
										<a href="/login" class="button alt">Clafuにログインしてコメントする</a>
										<%}%>
									</li>
								</ul>
							</div>
						</form>
						
						<table>
							<tbody id="contents-list">
								<jsp:include page="/common/comment_list.jsp" />
							</tbody>
						</table>
					</section>

				</div>
			</div>
		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />
		
		<!-- Common script -->
		<jsp:include page="/common/common_script.jsp" />

	</body>
</html>