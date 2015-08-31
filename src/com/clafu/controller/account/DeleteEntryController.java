package com.clafu.controller.account;

import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;

public class DeleteEntryController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        
        String contentId = asString("id");
        if(!StringUtil.isEmpty(contentId)) {
            ContentModel contentModel = ContentService.getOrNull(Long.valueOf(contentId));

            // 入力チェック
            if (contentModel!=null && contentModel.getCreateUserModelRef().getKey().getId() == userModel.getKey().getId()) {
                
                ContentService.delete(Long.valueOf(contentId));
                
                
                return redirect("/user/" + userModel.getKey().getId());
            }
        }
        
        return redirect("/contentDetails?id=" + contentId);
    }
    
    @Override
    protected String getPageTitle() {
        return "告知の削除";
    }
}
