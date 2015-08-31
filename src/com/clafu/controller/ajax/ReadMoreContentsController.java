package com.clafu.controller.ajax;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.StringUtil;

import com.clafu.Constants;
import com.clafu.controller.BaseController;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;
import com.clafu.service.UserService;

public class ReadMoreContentsController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        String cursor = asString("cursor");
        String category = asString("category");
        String moreContentsType = asString("type");
        String userId = asString("userId");
        
        S3QueryResultList<ContentModel> contentList = null;
        if(moreContentsType.equals(Constants.READ_MORE_CONTENT_TYPE_NEW)) {
            contentList = ContentService.getContentList(cursor);
            
        }else if(moreContentsType.equals(Constants.READ_MORE_CONTENT_TYPE_EXE_DATE_LIMIT)) {
            contentList = ContentService.getExeDateLimitContentList(cursor);
            
        }else if(moreContentsType.equals(Constants.READ_MORE_CONTENT_TYPE_CATEGORY) && !StringUtil.isEmpty(category)) {
            contentList = ContentService.getContentByCategory(category, cursor);
        
        }else if(moreContentsType.equals(Constants.READ_MORE_CONTENT_TYPE_MY) && !StringUtil.isEmpty(userId)) {
            UserModel targetUserModel = UserService.getOrNull(Long.valueOf(userId));
            contentList = ContentService.getCreateContentListByUser(targetUserModel, cursor);
        }
        
        // リクエストスコープの設定
        requestScope("contentList", contentList == null ? new ArrayList<ContentModel>() : contentList);
        requestScope("cursor", contentList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(contentList.hasNext()));
        
        requestScope("moreContentsType", moreContentsType);
        
        if(!StringUtil.isEmpty(category)) {
            requestScope("category", category);
        }
        if(!StringUtil.isEmpty(userId)) {
            requestScope("targetUserId", userId);
        }
        
        return forward("/common/contents_list.jsp");
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
