package com.clafu.controller.task;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.service.ContentService;
import com.clafu.service.TransmitHistoryService;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Builder;

public class AnnounceRetweetPostCronController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String groupId = asString("group");

        if(groupId == null || groupId.isEmpty()) return null;
        
        List<TransmitHistoryModel> targetList = TransmitHistoryService.getPostTargetList(Integer.valueOf(groupId));
        
        if(targetList == null) return null;
        
        for(TransmitHistoryModel model: targetList) {
            
            ContentModel contentModel = ContentService.getOrNull(model.getContentModelRef().getKey().getId());
            
            // 既に告知作成者からの告知ツイートが配信されている場合
            if(contentModel.isTweetedFlg() && !StringUtil.isEmpty(contentModel.getTweetStatusId())) {
                Queue queue = QueueFactory.getQueue("post-message-queue-group" + model.getGroup());
                queue.add(Builder.withUrl("/task/announceRetweetPostQueue").param("historyId", String.valueOf(model.getKey().getId())));
            }
        }
        
        
        return null;
    }
}