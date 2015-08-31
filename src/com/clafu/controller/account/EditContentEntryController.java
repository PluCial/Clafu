package com.clafu.controller.account;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;
import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.Text;

public class EditContentEntryController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {

        String id = asString("contentId");

        ContentModel contentModel = ContentService.getOrNull(Long.valueOf(id));

        // 入力チェック
        if (!isPost() || !editValidate() || contentModel == null) {
            return forward("/account/editContent?id=" + id);
        }

        String title = asString("title");
        String category = asString("category");
        String postMessage = asString("postMessage");
        String explanation = asString("explanation");

        contentModel.setTitle(title);
        contentModel.setContentCategory(new Category(category));
        contentModel.setPostMessage(new Text(postMessage));
        contentModel.setExplanation(new Text(explanation));

        ContentService.put(contentModel);

        return redirect("/contentDetails?id=" + id);
    }
    
    /**
     * バリデーション
     * @param userModel
     * @return
     */
    private boolean editValidate() {
        Validators v = new Validators(request);
        
        // タイトル
        v.add("title", v.required(),v.maxlength(150));
        
        // カテゴリ
        v.add("category", v.required());
        
        // postメッセージ
        v.add("postMessage", v.required(), v.maxlength(90));
        
        // explanation
        v.add("explanation");
        
        return v.validate();
    }
    
    @Override
    protected String getPageTitle() {
        return "null";
    }
}
