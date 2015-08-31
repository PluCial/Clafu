<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<?xml version="1.0" encoding="UTF-8"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
	<%for(CategoryModel categoryModel: Constants.CATEGORY_LIST) { %>
	<url>
		<loc>http://www.clafu.com/category/<%=categoryModel.getKey() %></loc>
		<changefreq>daily</changefreq>
		<priority>0.5</priority>
	</url>
	<%} %>
</urlset>
