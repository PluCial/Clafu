package com.clafu.controller;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.Constants;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;

public class ContentsController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        String cursor = asString("cursor");
        String category = asString("category");
        
        S3QueryResultList<ContentModel> contentList = ContentService.getContentByCategory(category, cursor);
        
        // リクエストスコープの設定
        requestScope("contentList", contentList == null ? new ArrayList<ContentModel>() : contentList);
        requestScope("cursor", contentList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(contentList.hasNext()));
        
        requestScope("moreContentsType", Constants.READ_MORE_CONTENT_TYPE_CATEGORY);
        
        requestScope("category", category);
        
        return forward("contents.jsp");
    }

    @Override
    protected String getPageTitle() {
        
        String category = asString("category");
        
        return Constants.CATEGORY_MAP.get(category);
    }

    @Override
    protected String getPageUri() {
        String category = asString("category");
        return "/category/" + category;
    }
}
