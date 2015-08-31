<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%@ page import="java.util.Date" %>
<%
String dateValue = new java.text.SimpleDateFormat( "yyyy", java.util.Locale.US).format(new Date());
%>
		<footer id="footer">
			<ul class="icons">
				<li><a href="https://twitter.com/ClafuJp" class="icon fa-twitter" target="_blank"><span class="label">Twitter</span></a></li>
				<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
				<li><a href="https://plus.google.com/+ClafuJp" class="icon fa-google-plus"><span class="label">Google+</span></a></li>
			</ul>
			<ul class="copyright">
				<li><a href="/info/clafu">Clafuとは</a></li>
				<li><a href="/info/agreement">利用規約</a></li>
				<li><a href="/info/privacy">プライバシーポリシー</a></li>
				<li><a href="https://plus.google.com/communities/104723249976996156239" target="_blank">コミュニティ</a></li>
				<li><a href="http://inc.plucial.com/" target="_blank">運用会社</a></li>
			</ul>
			<ul class="copyright">
				<li>Copyright &copy; clafu.com <%=dateValue %>. All Rights Reserved.</li>
			</ul>
		</footer>
