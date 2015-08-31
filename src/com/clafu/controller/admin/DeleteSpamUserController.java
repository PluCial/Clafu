package com.clafu.controller.admin;

import org.slim3.controller.Navigation;

import com.clafu.controller.BaseController;
import com.clafu.model.UserModel;
import com.clafu.service.SpamUserService;
import com.clafu.service.UserService;

public class DeleteSpamUserController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        
        String userId = asString("userId");
        
        UserModel targetUserModel = UserService.getOrNull(Long.valueOf(userId));
        
        SpamUserService.add(targetUserModel.getTwitterUserId(), targetUserModel.getUserName());
        
        UserService.delete(Long.valueOf(userId));
        
        return null;
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
