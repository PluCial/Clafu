<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%
UserModel acsessUserModel = (UserModel) request.getAttribute("acsessUserModel");

String pageTitle = (String) request.getAttribute("pageTitle");
String pageDescription = (String) request.getAttribute("pageDescription");
%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Clafu - <%=pageTitle %></title>
	<link rel="icon" type="image/png" href="/favicon.png" >

	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="description" content="<%=pageDescription == null ? "" : pageDescription %>" />
	<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.dropotron.min.js"></script>
	<script src="/js/jquery.scrollgress.min.js"></script>
	<script src="/js/skel.min.js"></script>
	<script src="/js/skel-layers.min.js"></script>
	<script src="/js/init.js"></script>
	<script src="/js/common_script.js"></script>
	<noscript>
		<link rel="stylesheet" href="/css/skel.css" />
		<link rel="stylesheet" href="/css/style.css" />
		<link rel="stylesheet" href="/css/style-wide.css" />
	</noscript>
	<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->

	<!-- SP -->
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum=1, minimal-ui">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<!-- /SP -->

	<!-- OGP -->
	<meta property="og:title" content="Clafu - <%=pageTitle %>" />
	<meta property="og:type" content="article" />
	<meta property="og:site_name" content="Clafu">
	<meta property="og:email" content="info@clafu.com">
	<%if(pageDescription != null) { %>
	<meta property="og:description" content="<%=pageDescription == null ? "" : pageDescription %>">
	<%} %>
	<!-- /OGP -->
	
	<!-- bxSlider -->
	<script src="/bxSlider/jquery.bxslider.min.js"></script>
	<link href="/bxSlider/jquery.bxslider.css" rel="stylesheet" />
	<!-- /bxSlider -->
	
	<script type="text/javascript">
	$(document).ready(function(){
		$('.bxslider').bxSlider({
			auto:true,
	    	pause:4000
		});
	});
	</script>