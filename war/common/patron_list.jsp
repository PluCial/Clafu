<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@ page import="com.clafu.Constants" %>
<%@ page import="com.clafu.model.*" %>
<%@ page import="com.clafu.service.*" %>
<%@ page import="com.clafu.utils.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.TimeZone" %>
<%
List<TransmitHistoryModel> historyList = (List<TransmitHistoryModel>) request.getAttribute("historyList");
String cursor = null;
boolean hasNext = false;
if (request.getAttribute("cursor") != null && request.getAttribute("hasNext") != null) {
	cursor = (String) request.getAttribute("cursor");
	hasNext = Boolean.valueOf((String) request.getAttribute("hasNext"));
}
ContentModel contentModel = (ContentModel)request.getAttribute("contentModel");
%>
				<%for(TransmitHistoryModel historyModel: historyList) { %>
					<tr>
						<td style="text-align: center;width: 40px;">
							<a href="/user/<%=historyModel.getUserModelRef().getKey().getId() %>">
								<img style="width:40px;vertical-align: middle;padding-top: 20px;" src="<%=Constants.GCS_USER_PROFILE_PUBLIC_URL_BASE %><%=historyModel.getUserModelRef().getKey().getId() %>" />
							</a>
						</td>
						<td style="text-align: left;">
							<span style="display: block;"><a href="/user/<%=historyModel.getUserModelRef().getKey().getId() %>"><%=historyModel.getUserName() %></a><br /><span style="font-size: 0.8em;"><%=DateUtils.dateToString(historyModel.getCreateDate(), "yyyy年MM月dd日 HH時mm分", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN) %></span></span>
								<%=Utils.changeStringToHtml(historyModel.getPostMessageString()) %>
						</td>
					</tr>
				<%} %>
				
				<%if(hasNext) { %>
				<tr class="12u readNext" style="text-align: center;border: 0;">
					<td colspan="2">
						<ul class="actions" style="text-align: center;">
							<li>
								<a href="#" onClick="read_more_content('/ajax/readMorePatron?contentId=<%=contentModel.getKey().getId() %>&cursor=<%=cursor %>');return false;" class="button alt">もっと読み込む</a>
							</li>
						</ul>
						<img width="25px" src="/images/loading.gif" style="display: none;">
					</td>
				</tr>
				<%} %>
