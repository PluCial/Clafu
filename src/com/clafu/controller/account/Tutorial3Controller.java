package com.clafu.controller.account;

import org.slim3.controller.Navigation;

import com.clafu.model.UserModel;
import com.clafu.service.UserService;

public class Tutorial3Controller extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        
        UserModel newUserModel = UserService.getOrNull(userModel.getKey().getId());
        requestScope("newUserModel", newUserModel);
        requestScope("twit", "true");

        return forward("tutorial3.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "チュートリアル";
    }
}
