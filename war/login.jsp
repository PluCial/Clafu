<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
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
		
		<section id="main" class="container">
			<div class="row">
				<div class="12u">

					<!-- Text -->
<!-- 					<section class="box" style="text-align: center;">
						<h2>Clafu ログイン</h2>
						<p>Clafuを使うためにはお持ちのTwitterアカウントでログインしてください。</p>
						<p>ログインした場合は、Clafuの利用規約とプライバシーポリシーに同意したことを意味します。</p>
						
						<div>
							<ul class="actions">
								<li><a href="/twitter/oAuth" class="button special" style="background-color: #00A7FF;">Twitterでログイン</a></li>
							</ul>
						</div>
					</section> -->
					
					<section class="box">
						<h2 style="text-align: center;">サービスの一時停止</h2>
						<p>大変申し訳ございません。現在サービスを一時的に停止とさせて頂いております。</p>
						<h4>原因：</h4>
						<p>Clafuで利用しているTwitterアプリは現在凍結されておます。<br />今年5月に更新されたTwitter APIの利用ポリシーが原因だと思われますが、詳細については現在調査しております。
						</p>
						<p>
						皆様には大変ご不便をおかけしますが、ご理解ご協力のほどよろしくお願い申し上げます。
						</p>
						
						<!-- <div>
							<ul class="actions">
								<li><a href="/twitter/oAuth" class="button special" style="background-color: #00A7FF;">Twitterでログイン</a></li>
							</ul>
						</div> -->
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