package com.clafu.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.clafu.model.CommentModel;
import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.model.UserModel;
import com.clafu.service.CommentService;
import com.clafu.service.ContentService;
import com.clafu.service.TransmitHistoryService;

public class ContentDetailsController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        String id = asString("id");
        
        ContentModel contentModel = ContentService.getOrNull(Long.valueOf(id));
        
        if(contentModel == null) {
            return redirect("/");
        }
        
        if(userModel != null) {
            // 既に参加している
            List<TransmitHistoryModel> historyList = TransmitHistoryService.getListByUserAndCountent(userModel, contentModel);
            if(historyList != null && historyList.size() > 0) {
                contentModel.setEntered(true);
            }else {
                contentModel.setEntered(false);
            }
        }
        
        // 応援者一覧
        List<TransmitHistoryModel> historyList =  TransmitHistoryService.getPatronListByContent(contentModel, null);
        
        // コメント一覧
        S3QueryResultList<CommentModel> commentList = CommentService.getCommentListByContent(contentModel, null);
        
        requestScope("contentModel", contentModel);
        requestScope("historyList", historyList == null ? new ArrayList<TransmitHistoryModel>() : historyList);
        
        requestScope("commentList", commentList == null ? new ArrayList<CommentModel>() : commentList);
        requestScope("cursor", commentList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(commentList.hasNext()));
        
        String error = asString("error");
        if(StringUtil.isEmpty(error)) {
            requestScope("twit", "true");
        }
        
        return forward("contentDetails.jsp");
    }

    @Override
    protected String getPageTitle() {
        
        String id = asString("id");
        
        ContentModel contentModel = ContentService.getOrNull(Long.valueOf(id));
        
        if(contentModel == null) return "告知詳細";
        
        return contentModel.getTitle();
    }

    @Override
    protected String getPageUri() {
        String id = asString("id");
        return "/contents/" + id;
    }
}
