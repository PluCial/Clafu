package com.clafu.controller;

import org.slim3.controller.Navigation;

import com.clafu.model.UserModel;

public class LoginController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        String callback = asString("callback");
        // ユーザー情報をセッションに入れる
        sessionScope("callback", callback);
        
        return forward("login.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "ログイン";
    }

    @Override
    protected String getPageUri() {
        return "/login";
    }
}
