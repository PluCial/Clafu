<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%@ page import="java.util.List" %>

			<section id="cta">
				<h2>共感できる告知を探す</h2>
				<p>他の方の告知を応援してハートをゲットしよう！<br />そしてそのハートを使って自分の告知を作成しよう。</p>
				<ul class="actions">
					<%for(CategoryModel categoryModel: Constants.CATEGORY_LIST) { %>
					<li><a href="/category/<%=categoryModel.getKey() %>" class="button icon fa-search"><%=categoryModel.getName() %></a></li>
					<%} %>
				</ul>	
			</section>
