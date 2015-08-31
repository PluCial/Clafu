package com.clafu.controller;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.Constants;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;

public class NewContentsController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        S3QueryResultList<ContentModel> contentList = ContentService.getContentList(null);
        
        // リクエストスコープの設定
        requestScope("contentList", contentList == null ? new ArrayList<ContentModel>() : contentList);
        requestScope("cursor", contentList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(contentList.hasNext()));
        
        requestScope("moreContentsType", Constants.READ_MORE_CONTENT_TYPE_NEW);
        
        return forward("newContents.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "これでもアクセスが増えなかったらもう諦めよう！";
    }

    @Override
    protected String getPageUri() {
        return "/";
    }
}
