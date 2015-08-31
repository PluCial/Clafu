package com.clafu.controller;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.Constants;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;
import com.clafu.service.UserService;

public class UserPageController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin) throws Exception {
        
        String cursor = asString("cursor");
        
        String userId = asString("userId");
        UserModel targetUserModel = UserService.getOrNull(Long.valueOf(userId));
        
        if(targetUserModel == null) return redirect("/"); 
        
        S3QueryResultList<ContentModel> contentList = ContentService.getCreateContentListByUser(targetUserModel, cursor);
        
        // リクエストスコープの設定
        requestScope("contentList", contentList == null ? new ArrayList<ContentModel>() : contentList);
        requestScope("cursor", contentList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(contentList.hasNext()));
        
        requestScope("userModel", targetUserModel);
        requestScope("targetUserId", userId);
        requestScope("moreContentsType", Constants.READ_MORE_CONTENT_TYPE_MY);

        return forward("userPage.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "マイページ";
    }

    @Override
    protected String getPageUri() {
        String userId = asString("userId");
        return "/user/" + userId;
    }
}
