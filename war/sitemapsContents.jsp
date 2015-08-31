<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%@ page import="java.util.List" %>
<%
List<ContentModel> contentList = (List<ContentModel>) request.getAttribute("contentList");
%>
<?xml version="1.0" encoding="UTF-8"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
	<%for(ContentModel model: contentList) { %>
	<url>
		<loc>http://www.clafu.com/contents/<%=model.getKey().getId() %></loc>
		<changefreq>daily</changefreq>
		<priority>0.5</priority>
	</url>
	<%} %>
</urlset>

