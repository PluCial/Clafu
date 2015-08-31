package com.clafu.controller.account;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;
import com.clafu.service.TransmitHistoryService;
import com.clafu.service.UserService;

public class PatronEntryController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        
        String contentId = asString("contentId");
        String socialType = asString("socialType");
        ContentModel contentModel = ContentService.getOrNull(Long.valueOf(contentId));
        
        // 入力チェック
        if (!isPost() || contentModel==null || contentModel.getPatronMaxLimit() <= contentModel.getPatronCount() || !validate()) {
            return forward("/contentDetails?id=" + contentId + "&error=error");
        }
        
        // 既に参加しているかどうかチェック
        if(TransmitHistoryService.getListByUserAndCountentAndSocialType(userModel, contentModel, socialType) != null) {
            return forward("/contentDetails?id=" + contentId + "&error=error");
        }
        
        
        String postMessage = asString("postMessage");
        String twit = asString("twit");
        boolean twitFlg = !StringUtil.isEmpty(twit) && (twit.equals("on") || twit.equals("true")) ? true : false;
        
        // ヒストリ登録
        TransmitHistoryService.add(
            userModel, 
            contentModel.getCreateUserModelRef().getKey().getId(), 
            contentModel, 
            socialType, 
            postMessage,
            twitFlg);
        
        // Thanks
        UserModel createUserModel = UserService.getOrNull(contentModel.getCreateUserModelRef().getKey().getId());
        requestScope("createUserModel", createUserModel);
        requestScope("contentModel", contentModel);
        
        return forward("thanks.jsp");
    }

    /**
     * バリデーション
     * @param userModel
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        
        // postメッセージ
        v.add("postMessage", v.required(), v.maxlength(90));
        
        return v.validate();
    }
    
    @Override
    protected String getPageTitle() {
        return "ありがとうございました。";
    }
}
