package com.clafu.controller.task;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Status;

import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;
import com.clafu.service.UserService;
import com.clafu.utils.Utils;

public class AnnouncePostQueueController extends Controller {
    
    private static final Logger logger = Logger.getLogger(AnnouncePostQueueController.class.getName());

    @Override
    public Navigation run() throws Exception {
        ContentModel contentModel = null;
        UserModel userModel = null;

        try{
            contentModel = getContentModel();
            userModel = UserService.getOrNull(contentModel.getCreateUserModelRef().getKey().getId());
        }catch(Exception e) {
            return null;
        };
        
        // タスクは成功するまで実行されるため、失敗時は例外をキャッチして再実行をさせない
        try{
            String msg = contentModel.getPostMessageString();
            msg = msg + "\n" + "[ #Clafu " + contentModel.getTargetShortUrl() + " ]"; // + 39文字
            
            // ツイートする
            Status status = Utils.twitterPost(
                userModel.getTwitterAccessToken(), 
                userModel.getTwitterTokenSecret(),
                msg);
            
            // ツイートしたIDを取得
            Long twitStatusId = status.getId();
            
            // ツイートIDを保存と、フラグの更新
            contentModel.setTweetedFlg(true);
            contentModel.setTweetStatusId(String.valueOf(twitStatusId));
            ContentService.put(contentModel);
            
            
        }catch(Exception e) {
            logger.severe("User Name:" + userModel.getUserName());
            logger.severe(e.toString());
        }
        
        return null;
    }
    
    public ContentModel getContentModel() throws Exception {

        String contentId = asString("contentId");
        
        if(contentId == null || contentId.isEmpty()) new Exception();

        ContentModel model = ContentService.getOrNull(Long.valueOf(contentId));

        if(model == null) throw new Exception();

        return model;
    }
}
