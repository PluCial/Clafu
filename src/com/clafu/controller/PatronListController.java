package com.clafu.controller;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;
import com.clafu.service.TransmitHistoryService;

public class PatronListController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        String id = asString("id");
        
        ContentModel contentModel = ContentService.getOrNull(Long.valueOf(id));
        
        if(contentModel == null) {
            if(userModel == null) return redirect("/");
        }
        
        // 応援者一覧
        S3QueryResultList<TransmitHistoryModel> historyList =  TransmitHistoryService.getPatronListByContent(contentModel, null);
        
        requestScope("contentModel", contentModel);
        requestScope("historyList", historyList == null ? new ArrayList<TransmitHistoryModel>() : historyList);
        requestScope("cursor", historyList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(historyList.hasNext()));
        
        
        return forward("patronList.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "応援者一覧";
    }

    @Override
    protected String getPageUri() {
        String id = asString("id");
        return "/contents/patrons/" + id;
    }
}
