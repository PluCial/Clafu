<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%
UserModel loginUserModel = (UserModel) request.getAttribute("loginUserModel");
TreeMap<String, String> exeDateMapList = (TreeMap<String, String>) request.getAttribute("exeDateMapList");
List<String> exeHourList = (List<String>) request.getAttribute("exeHourList");
List<String> exeMinuteList = (List<String>) request.getAttribute("exeMinuteList");
List<String> heartNumList = (List<String>) request.getAttribute("heartNumList");
List<CategoryModel> categoryList = (List<CategoryModel>)request.getAttribute("categoryList");
%>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="/common/html_head.jsp" />
		<style type="text/css"><!--
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
				<h2>新しい告知を作成</h2>
				<p>皆からもらったハートを使って自分の告知を作成しよう。</p>
			</header>
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<form method="post" action="/account/contentEntry">
							<h3>タイトル</h3>
							<p>告知のタイトルを入力してください。</p>
							<div class="row uniform half">
								<div class="6u">
									<input type="text" ${f:text("title")} name="title" id="title" value="" placeholder="タイトル" maxlength="30">
									<span class="err">${errors.title}</span>
								</div>
							</div>
							
							<h3>コンテンツのURL</h3>
							<p>告知するコンテンツのURLを入力してください。</p>
							<div class="row uniform half">
								<div class="6u">
									<input type="text" ${f:text("url")} name="url" id="url" value="" placeholder="URL">
									<span class="err">${errors.url}</span>
								</div>
<!-- 								<div class="3u">
									<a href="/info/clafu" class="button special">URLをチェック</a>
								</div> -->
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
							
							<h3>この告知に使うハートの数</h3>
							<p>ハートを1つ消費すると参加できる応援者の枠が一つ増えます。<br />1から所持しているハート数までの数字を入力してください。</p>
							<div class="row uniform half">
								<div class="3u">
									<div class="select-wrapper">
										<select name="patronMaxLimit" id="patronMaxLimit">
											<option value="">- ハート数 -</option>
											<c:forEach var="heart" items="${heartNumList}">
												<option ${f:select("patronMaxLimit", f:h(heart))}>${f:h(heart)} 個</option>
											</c:forEach>
										</select>
										<span class="err">${errors.patronMaxLimit}</span>
									</div>
								</div>
							</div>
							
							<h3>告知日時</h3>
							<p>設定した告知日時になるとこの告知の応援者のSNSアカウントから告知のリツイートを行います。<br />
							また、設定した応援者の枠(=設定したハート数)の人数が集まらなくても告知は配信されます。<br />
							明日〜30日以内の日時を選択してください。<br />
							<span style="color:red">設定した時刻と実際の配信時刻は10分ほど前後する可能性がありますのでご了承ください。</span>
							</p>
							<br />
							
							<div class="row uniform half collapse-at-3">
								<div class="4u">
									<div class="select-wrapper">
										<select name="exeDate" id="exeDate">
											<option ${f:select("exeDate", "")}>- 配信日 -</option>
											<c:forEach var="date" items="${exeDateMapList}">
												<option ${f:select("exeDate", f:h(date.key))}>${f:h(date.value)}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="3u">
									<div class="select-wrapper">
										<select name="exeHour" id="exeHour">
											<option value="">- 時 -</option>
											<c:forEach var="hour" items="${exeHourList}">
												<option ${f:select("exeHour", f:h(hour))}>${f:h(hour)}時</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="3u">
									<div class="select-wrapper">
										<select name="exeMinute" id="exeMinute">
											<option value="">- 分 -</option>
											<c:forEach var="minute" items="${exeMinuteList}">
												<option ${f:select("exeMinute", f:h(minute))}>${f:h(minute)}分</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<span class="err">${errors.exeDate}</span>
								<span class="err">${errors.exeHour}</span>
								<span class="err">${errors.exeMinute}</span>
							</div>
							
							<div style="margin-top:3em;text-align: center;">
								<input type="checkbox" id="twit" ${f:checkbox("twit")} /><label for="twit">この告知をフォロワーに知らせる</label>
								<ul class="actions">
									<li><input class="special" type="submit" value="この内容で登録する"></li>
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