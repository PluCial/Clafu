<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="com.clafu.utils.*" %>
<%
UserModel loginUserModel = (UserModel) request.getAttribute("loginUserModel");
ContentModel contentModel = (ContentModel) request.getAttribute("contentModel");
Date exeDate = (Date) request.getAttribute("exeDate");
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
			
		form h3 {
			margin-top: 3em;
		}
		form h3:first-child {
			margin-top: 0px;
		}
		form p {
			margin:0;
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
		<jsp:include page="/common/user_header.jsp" />

		<!-- Main -->
		<section id="main" class="container" style="margin-top: 0;">
			<header>
				<h2>告知の修正</h2>
				<p>告知の内容を充実させましょう！</p>
			</header>
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<form method="post" action="/account/editContentEntry">
							
							<%if(contentModel.getContentTitle() != null) { %>
							<h3>告知するコンテンツ</h3>
							<div class="link-box-area">
								<div class="content-image-box">
									<span class="icon fa-link" style="font-size:3em;padding-right:5px;"></span>
								</div>
								<div class="content-title-box">
									<h4 style="margin-top: 0;"><a href="<%=contentModel.getUrlString() %>" target="_blank"><%=contentModel.getContentTitle() == null ? "" : contentModel.getContentTitle() %></a></h4>
									<blockquote style="margin-bottom: 0;">
										<p><span class="icon fa-globe" style="padding-right:5px;"></span><%=Utils.getURLPrivateDomain(contentModel.getUrlString()) %></p>
										<p><%=contentModel.getContentDescriptionString() == null ? "" : contentModel.getContentDescriptionString() %></p>
									</blockquote>
								</div>
							</div>
							<%} %>
							
							<h3>告知のタイトル</h3>
							<p>告知のタイトルを入力してください。</p>
							<div class="row uniform half">
								<div class="12u">
									<input type="text" ${f:text("title")} name="title" id="title" value="" placeholder="タイトル" maxlength="150">
									<span class="err">${errors.title}</span>
								</div>
							</div>
							
							<h3>告知の説明(省略可)</h3>
							<p>告知の目的や追加説明がもしあれば記入してください。</p>
							<div class="row uniform half">
								<div class="12u">
									<textarea name="explanation" id="explanation" placeholder="説明" rows="6">${f:h(explanation)}</textarea>
									<span class="err">${errors.explanation}</span>
								</div>
							</div>
							
							<h3>告知文章(90文字以内)</h3>
							<p>応援者のSNSアカウントから投稿してほしい告知文を記入してください。<br /></p>
							<div class="row uniform half">
								<div class="12u">
									<textarea name="postMessage" id="postMessage" placeholder="告知文章" rows="6" maxlength="90">${f:h(postMessage)}</textarea>
									<span class="err">${errors.postMessage}</span>
								</div>
							</div>
							
							<h3>カテゴリの選択</h3>
							<p>告知に合うカテゴリを選択してください。</p>
							<div class="row uniform half">
								<div class="6u">
									<div class="select-wrapper">
										<select name="category" id="category">
											<option value="">- カテゴリ -</option>
											<c:forEach var="categoryModel" items="${categoryList}">
												<option ${f:select("category", f:h(categoryModel.key))}>${f:h(categoryModel.name)}</option>
											</c:forEach>
										</select>
										<span class="err">${errors.category}</span>
									</div>
								</div>
							</div>
							
							<h3>この告知に参加できる応援者人数</h3>
							<p>${f:h(patronMaxLimit)} 人</p>
							
							<h3>告知日時</h3>
							<p><%=DateUtils.dateToString(exeDate, "yyyy年MM月dd日 HH時mm分", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN) %></p>
							
							<input type="hidden" name="contentId" value="<%=contentModel.getKey().getId()%>">
							<div style="margin-top:3em;text-align: center;">
								<ul class="actions">
									<li><input class="special" type="submit" value="この内容で修正する"></li>
								</ul>
							</div>
							
						</form>
					</section>

				</div>
			</div>
		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />

	</body>
</html>