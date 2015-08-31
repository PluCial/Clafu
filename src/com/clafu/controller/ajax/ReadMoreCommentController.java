package com.clafu.controller.ajax;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.controller.BaseController;
import com.clafu.model.CommentModel;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.CommentService;
import com.clafu.service.ContentService;

public class ReadMoreCommentController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        String contentId = asString("contentId");
        String cursor = asString("cursor");
        
        ContentModel contentModel = ContentService.getOrNull(Long.valueOf(contentId));
        
        S3QueryResultList<CommentModel> commentList = CommentService.getCommentListByContent(contentModel, cursor);
        
        // リクエストスコープの設定
        requestScope("commentList", commentList == null ? new ArrayList<CommentModel>() : commentList);
        requestScope("cursor", commentList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(commentList.hasNext()));
        
        requestScope("contentModel", contentModel);
        
        return forward("/common/comment_list.jsp");
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
