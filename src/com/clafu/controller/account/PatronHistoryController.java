package com.clafu.controller.account;

import java.util.ArrayList;

import org.slim3.controller.Navigation;
import org.slim3.datastore.S3QueryResultList;

import com.clafu.Constants;
import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.model.UserModel;
import com.clafu.service.TransmitHistoryService;

public class PatronHistoryController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        // ナビの設定
        requestScope("navType", Constants.MY_PAGE_NAV_TYPE_PATRON_HISTORY);
        
        String cursor = asString("cursor");
        
        S3QueryResultList<TransmitHistoryModel> contentList = TransmitHistoryService.getHistoryListByUser(userModel, cursor);
        
        // リクエストスコープの設定
        requestScope("contentList", contentList == null ? new ArrayList<ContentModel>() : contentList);
        requestScope("cursor", contentList.getEncodedCursor());
        requestScope("hasNext", String.valueOf(contentList.hasNext()));
        
        return forward("patronHistory.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "参加した告知履歴";
    }
}
