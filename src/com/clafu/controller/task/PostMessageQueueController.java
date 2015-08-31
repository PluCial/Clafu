package com.clafu.controller.task;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.clafu.model.TransmitHistoryModel;
import com.clafu.service.TransmitHistoryService;
import com.clafu.utils.Utils;

public class PostMessageQueueController extends Controller {
    
    private static final Logger logger = Logger.getLogger(PostMessageQueueController.class.getName());

    @Override
    public Navigation run() throws Exception {
        TransmitHistoryModel model = null;

        try{
            model = getHistoryModel();
        }catch(Exception e) {
            return null;
        };
        
        // タスクは成功するまで実行されるため、失敗時は例外をキャッチして再実行をさせない
        try{
            String msg = model.getPostMessageString();
            msg = msg + "\n" + "[ #Clafu " + model.getTargetShortUrl() + " ]"; // + 39文字
            
            Utils.twitterPost(
                model.getTwitterAccessToken(), 
                model.getTwitterTokenSecret(),
                msg);
            
//            Long twitId = status.getId();
            
            // フラグ更新
            model.setEndFlg(true);
            TransmitHistoryService.put(model);
            
            logger.severe("Be OK");
            
            
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
