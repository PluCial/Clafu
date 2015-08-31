package com.clafu.controller;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.Constants;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;

public class ExeDateLimitContentsController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        String cursor = asString("cursor");
        
        S3QueryResultList<ContentModel> contentList = ContentService.getExeDateLimitContentList(cursor, 10);
        
        // リクエストスコープの設定
        requestScope("contentList", contentList == null ? new ArrayList<ContentModel>() : contentList);
        requestScope("cursor", contentList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(contentList.hasNext()));
        
        requestScope("moreContentsType", Constants.READ_MORE_CONTENT_TYPE_EXE_DATE_LIMIT);
        
        return forward("exeDateLimit.jsp");
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
