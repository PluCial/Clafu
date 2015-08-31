package com.clafu.controller.account;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.Constants;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;

public class MyPageController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        // ナビの設定
        requestScope("navType", Constants.MY_PAGE_NAV_TYPE_CREATE_HISTORY);
        
        String cursor = asString("cursor");
        
        S3QueryResultList<ContentModel> contentList = ContentService.getCreateContentListByUser(userModel, cursor);
        
        // リクエストスコープの設定
        requestScope("contentList", contentList == null ? new ArrayList<ContentModel>() : contentList);
        requestScope("cursor", contentList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(contentList.hasNext()));

        return forward("mypage.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "マイページ";
    }
}
