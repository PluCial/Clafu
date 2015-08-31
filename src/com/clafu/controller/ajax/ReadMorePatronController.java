package com.clafu.controller.ajax;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.controller.BaseController;
import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;
import com.clafu.service.TransmitHistoryService;

public class ReadMorePatronController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        String contentId = asString("contentId");
        String cursor = asString("cursor");
        
        ContentModel contentModel = ContentService.getOrNull(Long.valueOf(contentId));
        
        S3QueryResultList<TransmitHistoryModel> historyList =  TransmitHistoryService.getPatronListByContent(contentModel, cursor);
        
        // リクエストスコープの設定
        requestScope("historyList", historyList == null ? new ArrayList<TransmitHistoryModel>() : historyList);
        requestScope("cursor", historyList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(historyList.hasNext()));
        
        requestScope("contentModel", contentModel);
        
        return forward("/common/patron_list.jsp");
    }

    @Override
    protected String getPageTitle() {
        return null;
    }

    @Override
    protected String getPageUri() {
        return null;
    }
}
