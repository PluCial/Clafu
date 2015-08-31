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
			<header>
				<h2>Clafuのプライバシーポリシー</h2>
				<p>Clafuをご利用いただきありがとうございます。</p>
			</header>
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<p>Clafuをご利用いただきありがとうございます。Clafuを利用者の皆様にご利用いただくにあたり、Clafuが利用者情報をどのように利用し、プライバシーをどのように保護するかをご説明します。</p>
						<h3 style="color: #333">情報の収集方法</h3>
						<p>ClafuはTwitterが提供しているAPIを使って、ユーザーのTwitterアカウントのプロフィール情報と更新権限を取得しています。</p>
						<h3 style="color: #333">情報の保持</h3>
						<p>ClafuはTwitterが提供している各APIを使って収集した情報をGoogleが提供しているプラットフォームGoogle App Engineに保持しています。ご利用者の同意なく、それ以外の場所でのデータ保持やデータベース作成を行うことがありません。</p>
						<h3 style="color: #333">収集情報の利用</h3>
						<p>これらの情報についてはClafuがご利用者の同意なく、以下の目的以外で利用することがありません。</p>
						<ul>
							<li>①ご利用者から収集した情報を、サービスの提供、維持、保護および改善、新しいサービスの開発、ならびに、Clafuと利用者の保護のために利用します。</li>
							<li>②利用者のメールアドレスを使用して、Clafuサービスに関する情報を通知することがあります。</li>
						</ul>

						<h3 style="color: #333">他者への情報提供</h3>
						<p>Clafuではいかなる理由であっても、ご利用者の同意なくClafu以外の企業、組織、個人にご利用者から収集した情報を提供することはありません。</p>

						<h3 style="color: #333">情報の保護</h3>
						<p>Clafuは、Clafuが保持する情報への不正アクセスや、不正な改変、開示または破壊から、利用者を保護するよう努めます。</p>

 						<h3 style="color: #333">当サイトに掲載されている広告について</h3>
						<p>当サイトでは、第三者配信の広告サービス（Googleアドセンス、A8.net、Amazonアソシエイト）を利用しています。 このような広告配信事業者は、ユーザーの興味に応じた商品やサービスの広告を表示するため、当サイトや他サイトへのアクセスに関する情報 『Cookie』(氏名、住所、メール アドレス、電話番号は含まれません) を使用することがあります。 またGoogleアドセンスに関して、このプロセスの詳細やこのような情報が広告配信事業者に使用されないようにする方法については、ここをご参照ください。</p>

						<h3 style="color: #333">当サイトが使用しているアクセス解析ツールについて</h3>
						<p>当サイトでは、Googleによるアクセス解析ツール「Googleアナリティクス」を利用しています。 このGoogleアナリティクスはトラフィックデータの収集のためにCookieを使用しています。 このトラフィックデータは匿名で収集されており、個人を特定するものではありません。 この機能はCookieを無効にすることで収集を拒否することが出来ますので、お使いのブラウザの設定をご確認ください。 この規約に関して、詳しくはここをご参照ください。</p>
					</section>

				</div>
			</div>
		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />

	</body>
</html>