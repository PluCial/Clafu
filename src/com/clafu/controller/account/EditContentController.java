package com.clafu.controller.account;

import org.slim3.controller.Navigation;

import com.clafu.Constants;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;

public class EditContentController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {

        String id = asString("id");
        ContentModel contentModel = ContentService.getOrNull(Long.valueOf(id));

        if(contentModel != null && contentModel.getCreateUserModelRef().getKey().getId() == userModel.getKey().getId()) {

            requestScope("contentModel", contentModel);
            requestScope("url", contentModel.getUrlString());
            requestScope("title", contentModel.getTitle());
            requestScope("category", contentModel.getContentCategory().getCategory());
            requestScope("postMessage", contentModel.getPostMessageString());
            requestScope("explanation", contentModel.getExplanationString());
            requestScope("patronMaxLimit", contentModel.getPatronMaxLimit());
            requestScope("exeDate", contentModel.getWishesExeDate());

            requestScope("categoryList", Constants.CATEGORY_LIST);

            return forward("edit.jsp");
        }
        
        return redirect("/");
    }

    @Override
    protected String getPageTitle() {
        return "告知の修正";
    }
}
