<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="java.util.List" %>
<%@ page import="com.clafu.utils.*" %>
<%
String url = (String) request.getAttribute("url");
String title = (String) request.getAttribute("contentTitle");
String description = (String) request.getAttribute("description");
List<String> imagesList =(List<String>)request.getAttribute("imagesList");
%>
								<%-- <%if(imagesList.size() > 0) { %>
								<div style="margin-top: 2em;">
									<p style="text-align: left;">告知するコンテンツの内容を確認ししてください。<br />ページ内の画像が複数ある場合は告知に表示する画像を選択してください。<br /><span style="color:red">横500×縦250</span> より大きい画像のみ登録されます。</p>
									<div class="fotorama" data-auto="false" data-width="100%" data-height="250" style="background: #dee0e3;height: 300px;overflow: hidden;-webkit-box-shadow: 0px -2px 5px rgba(0, 0, 0, 0.3) inset;-moz-box-shadow: 0px -2px 5px rgba(0, 0, 0, 0.3) inset;box-shadow: 0px -2px 5px rgba(0, 0, 0, 0.3) inset;padding: 10px 0;">
										<%for(String imageUrl: imagesList) { %>
										<a href="<%=imageUrl %>"></a>
										<%} %>
									</div>
								</div>
								<%}else { %>
								<hr />
								<%} %> --%>
								
								<h3>告知するコンテンツはこちらでよろしいですか？</h3>
								<div class="link-box-area" style="margin-top:3em;">
									<div class="content-image-box">
										<span class="icon fa-link" style="font-size:3em;padding-right:5px;"></span>
									</div>
									<div class="content-title-box">
										<h4 style="margin-top: 0;"><a href="<%=url %>" target="_blank"><%=title == null ? "" : title %></a></h4>
										<blockquote style="margin-bottom: 0;">
											<p><span class="icon fa-globe" style="padding-right:5px;"></span><%=Utils.getURLPrivateDomain(url) %></p>
											<p><%=description == null ? "" : description %></p>
										</blockquote>
									</div>
								</div>
								
								<input id="image-url-hidden" type="hidden" name="imageUrl" value="<%=imagesList.size() > 0 ? imagesList.get(0) : "" %>">
								<input type="hidden" name="url" value="<%=url == null ? "" : url %>">
								<input type="hidden" name="contentTitle" value="<%=title == null ? "" : title %>">
								<input type="hidden" name=description value="<%=description == null ? "" : description %>">
