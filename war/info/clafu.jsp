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
				<h2>Clafu(クラフ)とは</h2>
				<p>不特定多数の人のSNSアカウントの配信権を集めるためのサービスです。</p>
			</header>
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<p>20人に一人がブログを書いていると言われている日本で、多くの方がブログのアクセスアップで悩んでると思います。地道なSEO対策やSNSフォロワー集めは大変ですよね。</p>
						
						<h3>SNSを使って助け合えばいい</h3>
						<p>簡単に言えば、自分のSNSフォロワーが今10人しかいないとしても、フォロワー1,000人の応援者を100人集めて、設定した日時にその100人のSNSアカウントからあなたの告知(ブログの更新など・・)が自動的に配信されれば、10万人に対してあなたが告知を出したことになります。</p>
						
						<h3>10人のフォロワーで10万人に告知を出す？</h3>
						<p>これをを実現するためのサービスこそがClafuです。これからは10万ものフォロワーを集めるかわりに共感してくれる100人の応援者をClafuで集めよう。</p>
						
						<!-- <h3>人は共感だけでは動かない</h3>
						<p>すてきなブログ記事を書いたとしても、その記事を見て共感できたとしても自分のソーシャルを使って拡散してくれる人は非常に少ない。</p>
						<p>また、クラウドファンディングで共感できるプロジェクトを見つけたとしても、実際募金する人はどれくらいいるでしょうか。</p> -->
						
						<h3>応援者が自然に集まるハートの仕組み</h3>
						<p>フォロワー集めの代わりに応援者集めをしないといけないようなサービスでは何も解決しない！<p>
						<p>Clafuでは、告知を作成するためにはハートが必要です。10ハート使えば10人まで参加できる告知を作成することができます。100ハートを使えば100人が参加できる告知を作成することができます。
						そして、このハートは他人の告知に参加することで貯まります。
						</p>
						<p>Clafuはこのように告知を出したい人同士が応援し合う仕組みになっています。</p>
						
						<h3>一斉に自動配信します</h3>
						<p>Clafuはあなたが設定した告知の日時になると、その告知に参加している全応援者のSNSアカウントを使ってあなたの告知を再配信(リツイート)します。この一斉配信による今までにない効果を是非体感ください。</p>
						
						<!-- <h3>Clafu(クラフ)の名前の由来</h3>
						<p>告知権を集めるためのクラウドファンディング(Crowd License Of Announcement funding)の略称です。<p> -->
					</section>

				</div>
			</div>
		</section>
			
		<!-- Footer -->
		<jsp:include page="/common/main_footer.jsp" />

	</body>
</html>