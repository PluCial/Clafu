package com.clafu.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.clafu.model.ContentModel;
import com.clafu.service.ContentService;

public class SitemapsContentsController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String groupId = asString("group");
        
        List<ContentModel> contentList = ContentService.getContentListByGroup(Integer.valueOf(groupId));
        
        requestScope("contentList", contentList == null ? new ArrayList<ContentModel>() : contentList);
        
        return forward("sitemapsContents.jsp");
    }
}
