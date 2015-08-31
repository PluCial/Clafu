package com.clafu.controller.account;

import org.slim3.controller.Navigation;

import com.clafu.model.UserModel;

public class CreateStep1Controller extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        
        return forward("createStep1.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "告知の作成(Step1)";
    }
}
