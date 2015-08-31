package com.clafu.controller.task;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.service.ContentService;
import com.clafu.service.TransmitHistoryService;
import com.clafu.utils.Utils;

public class AnnounceRetweetPostQueueController extends Controller {
    
    private static final Logger logger = Logger.getLogger(AnnounceRetweetPostQueueController.class.getName());

    @Override
    public Navigation run() throws Exception {
        TransmitHistoryModel model = null;
        ContentModel contentModel = null;
        try{
            model = getHistoryModel();
            contentModel = ContentService.getOrNull(model.getContentModelRef().getKey().getId());
        }catch(Exception e) {
            return null;
        };
        
        // タスクは成功するまで実行されるため、失敗時は例外をキャッチして再実行をさせない
        try{
            
            Utils.reTweetPost(
                model.getTwitterAccessToken(), 
                model.getTwitterTokenSecret(),
                Long.valueOf(contentModel.getTweetStatusId()));
            
            // フラグ更新
            model.setEndFlg(true);
            TransmitHistoryService.put(model);
            
            
        }catch(Exception e) {
            logger.severe("User Name:" + model.getUserName());
            logger.severe(e.toString());
        }
        
        
        return null;
    }
    
    public TransmitHistoryModel getHistoryModel() throws Exception {

        String historyId = asString("historyId");
        
        if(historyId == null || historyId.isEmpty()) new Exception();

        TransmitHistoryModel model = TransmitHistoryService.getOrNull(Long.valueOf(historyId));

        if(model == null) throw new Exception();

        return model;
    }
}
