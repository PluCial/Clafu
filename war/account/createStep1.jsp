<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<!DOCTYPE HTML>
<html>
	<head>
		<jsp:include page="/common/html_head.jsp" />
		
		<script type="text/javascript">
  			function get_content_info() {
				var href = "/ajax/getContentInfoByUrl?url=" + encodeURIComponent($("#url").val());
				console.log('href:', href);
				$("#loading-block img").css({"display":"inline"});
				
  				$.ajax({
  					type : 'GET',
  					url : href,
  					dataType : 'html',
  					timeout : 1000000000,
  					success : function(result) {
  						if(result.length > 0) {
  							$('#content-info').append(result);
  							$("#loading-block").css({"display":"none"});
  							$('#input-block').css({"display":"none"});
  							$("#url-check-action").css({"display":"none"});
  							$("#next-action input").css({"display":"inline"});
  						}
  						$("#loading-block img").css({"display":"none"});
  					},
  					error : function() {
  						read_more_reset();
  					}
  				});
  			};
		</script>
		
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
				<h2>新しい告知を作成</h2>
				<p>Step1: 告知するコンテンツのURL確認</p>
			</header>
			<div class="row">
				<div class="12u">

					<!-- Text -->
					<section class="box">
						<form method="post" action="/account/createStep2">
							<div id="input-block"> 
								<h3>コンテンツのURL</h3>
								<p>告知するコンテンツのURLを入力してください。</p>
								<div class="row uniform half">
									<div class="12u">
										<input type="text" ${f:text("url")} name="url" id="url" value="" placeholder="URL">
										<span class="err">${errors.url}</span>
									</div>
								</div>
							</div>
							
							<section id="content-info" style="text-align: center;margin-bottom: 1em;">
							</section>
							<div id="loading-block" style="text-align:center;padding-top: 1em;margin-bottom: 1em;">
								<img width="25px" src="/images/loading.gif" style="display: none;">
							</div>
							
							<div style="margin-top:3em;text-align: center;">
								<ul class="actions">
									<li>
										<a href="/account/createStep1" class="button alt">キャンセル</a>
 									</li>
 									<li id="url-check-action">
										<a href="#" class="button special" onClick="get_content_info();return false;">URLチェック</a>
 									</li>
									<li id="next-action"><input class="special" type="submit" value="この内容で次に進む" style="display: none;"></li>
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