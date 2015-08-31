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
					<section class="box" style="text-align: center;">
						<h2>ログインできません</h2>
						<h4>理由：</h4>
						<p>このアカウントはスパムとして判断されたため既に削除されています。またこのアカウントを使った再登録とログインはできませんのでご了承ください。</p>
						<p>Clafuの<a href="/info/agreement" target="_blank">利用規約</a>を再確認し、処置に不服がある場合は<a href="https://plus.google.com/communities/104723249976996156239" target="_blank">こちら</a>から申し立てください。</p>
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