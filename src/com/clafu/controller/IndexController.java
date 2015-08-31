package com.clafu.controller;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;

public class IndexController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        // 配信直前
        S3QueryResultList<ContentModel> exeDateLimitContentList = ContentService.getExeDateLimitContentList(null, 6);
        
        // 新着
        S3QueryResultList<ContentModel> contentList = ContentService.getContentList(null, 6);
        
        // リクエストスコープの設定
        requestScope("contentList", contentList == null ? new ArrayList<ContentModel>() : contentList);
        requestScope("exeDateLimitContentList", exeDateLimitContentList == null ? new ArrayList<ContentModel>() : exeDateLimitContentList);
        
        return forward("index.jsp");
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
