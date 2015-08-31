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
List<ContentModel> contentList = null;

String listType = request.getParameter("listType");

if(listType == null) {
	contentList = (List<ContentModel>) request.getAttribute("contentList");
	
}else if(listType.equals("exeDateLimit")) {
	contentList = (List<ContentModel>) request.getAttribute("exeDateLimitContentList");
}

String cursor = null;
boolean hasNext = false;
if (request.getAttribute("cursor") != null && request.getAttribute("hasNext") != null) {
	cursor = (String) request.getAttribute("cursor");
	hasNext = Boolean.valueOf((String) request.getAttribute("hasNext"));
}
String moreContentsType = (String)request.getAttribute("moreContentsType");
String category = (String)request.getAttribute("category");
String targetUserId = (String)request.getAttribute("targetUserId");
%>
				<%
				for(ContentModel contentModel: contentList) {
					long userId = contentModel.getCreateUserModelRef().getKey().getId();
					UserModel createUserModel = UserService.getOrNull(userId);
					
					boolean isEnd = contentModel.isEndFlg() || contentModel.getPatronMaxLimit() <= contentModel.getPatronCount();
				%>
				<div class="6u">
					<section class="box special" style="text-align: left;position: relative;<%=isEnd ? "background-color: #f0f0f0;" : "" %>">
						<div style="position: absolute;top: 5px;left: 0px;padding: 2px 10px;background-color: #e9575f;font-size: 12px;">
							<a href="/category/<%=contentModel.getContentCategory().getCategory() %>" style="color:#fff"><%=Constants.CATEGORY_MAP.get(contentModel.getContentCategory().getCategory()) %></a>
						</div>
						
						<div style="position: absolute;top: 5px;right: 5px;padding: 2px 10px;font-size: 12px;<%=isEnd ? "" : "display: none;" %>">
							<%if(contentModel.isEndFlg()) { %>
							配信済み
							<%}else if(contentModel.getPatronMaxLimit() <= contentModel.getPatronCount()) { %>
							応援者の募集終了
							<%} %>
						</div>
						
						<header style="margin-bottom:1em;max-height: 50px;min-height: 50px;overflow: hidden;">
							<span style="padding-right:10px;">
								<a href="/user/<%=createUserModel.getKey().getId() %>">
								<img style="width:50px;border-radius: 50%;vertical-align: middle;" src="<%=Constants.GCS_USER_PROFILE_PUBLIC_URL_BASE %><%=userId %>">
								</a>
							</span>
							<a href="/user/<%=createUserModel.getKey().getId() %>">
								<span><%=createUserModel.getUserName() %></span>
 							</a>
						</header>
						
						<!-- <span class="image featured"><img src="http://html5up.net/uploads/demos/alpha/images/pic03.jpg" alt=""></span> -->
						
						<h3 style="font-size:1.2em;min-height: 60px;overflow: hidden;height: 60px;">
							<a href="/contents/<%=contentModel.getKey().getId() %>">
								<%=Utils.subContentsString(contentModel.getTitle(), 45) %>
							</a>
						</h3>
<%-- 						<p style="min-height: 100px;"><%=contentModel.getPostMessageString() %></p> --%>
						<table style="margin-bottom: 1em;">
							<tbody>
								<tr>
									<td><span class="icon fa-calendar" style="padding-right:5px"></span>配信日時</td>
									<td style="text-align: right;"><%=DateUtils.dateToString(contentModel.getWishesExeDate(), "yyyy/MM/dd HH:mm", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN) %></td>
								</tr>
								<tr>
									<td><span class="icon fa-heart" style="padding-right:5px;color: #dd4b39;"></span>応援者数</td>
									<td style="text-align: right;"><%=contentModel.getPatronCount() %> / <%=contentModel.getPatronMaxLimit() %> 人</td>
								</tr>
								<tr>
									<td><span class="icon fa-users" style="padding-right:5px;color:#00A7FF"></span>リーチ人数</td>
									<td style="text-align: right;"><%=contentModel.getReachCount() %> 人</td>
								</tr>
								<tr>
									<td><span class="icon fa-comments-o" style="padding-right:5px;"></span>コメント</td>
									<td style="text-align: right;"><%=contentModel.getCommentCount() %> 件</td>
								</tr>
							</tbody>
						</table>
						
					</section>
						
				</div>
				<%}%>
				
				<%if(hasNext) { %>
				<div class="12u readNext" style="text-align: center;">
					<ul class="actions" style="text-align: center;">
						<li>
							<%if(moreContentsType.equals(Constants.READ_MORE_CONTENT_TYPE_NEW) || moreContentsType.equals(Constants.READ_MORE_CONTENT_TYPE_EXE_DATE_LIMIT)) { %>
							<a href="#" onClick="read_more_content('/ajax/readMoreContents?type=<%=moreContentsType%>&cursor=<%=cursor %>');return false;" class="button alt">もっと読み込む</a>
							
							<%}else if(moreContentsType.equals(Constants.READ_MORE_CONTENT_TYPE_CATEGORY)){ %>
							<a href="#" onClick="read_more_content('/ajax/readMoreContents?type=<%=moreContentsType%>&category=<%=category %>&cursor=<%=cursor %>');return false;" class="button alt">もっと読み込む</a>
							
							<%}else if(moreContentsType.equals(Constants.READ_MORE_CONTENT_TYPE_MY)){ %>
							<a href="#" onClick="read_more_content('/ajax/readMoreContents?type=<%=moreContentsType%>&userId=<%=targetUserId %>&cursor=<%=cursor %>');return false;" class="button alt">もっと読み込む</a>
							
							<%} %>
						</li>
					</ul>
					<img width="30px" src="/images/loading.gif" style="display: none;margin-top: 1em;margin-bottom: 2em;">
				</div>
				<%} %>
