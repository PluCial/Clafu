package com.clafu.controller.account;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.CommentService;
import com.clafu.service.ContentService;

public class AddCommentController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        
        String contentId = asString("contentId");
        ContentModel contentModel = ContentService.getOrNull(Long.valueOf(contentId));
        
        // 入力チェック
        if (!isPost() || contentModel==null || !validate()) {
            return forward("/contentDetails?id=" + contentId);
        }
        
        String comment = asString("comment");
        
        CommentService.add(userModel, contentModel, comment);
        
        return redirect("/contentDetails?id=" + contentId);
    }

    /**
     * バリデーション
     * @param userModel
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        
        // postメッセージ
        v.add("comment", v.required());
        
        return v.validate();
    }
    
    @Override
    protected String getPageTitle() {
        return "ありがとうございました。";
    }
}
