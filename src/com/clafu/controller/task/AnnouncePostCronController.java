package com.clafu.controller.task;

import java.util.List;
import java.util.Random;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.clafu.model.ContentModel;
import com.clafu.service.ContentService;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Builder;

public class AnnouncePostCronController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        List<ContentModel> targetList = ContentService.getPostTargetList();
        
        if(targetList == null) return null;
        
        System.out.println("size:" + targetList.size());
        
        for(ContentModel model: targetList) {
            // 0〜9のグループ
            Random rnd = new Random();
            
                Queue queue = QueueFactory.getQueue("announce-post-queue-group" + rnd.nextInt(10));
                queue.add(Builder.withUrl("/task/announcePostQueue").param("contentId", String.valueOf(model.getKey().getId())));
            
        }
        
        
        return null;
    }
}
