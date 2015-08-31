package com.clafu.controller.account;

import org.slim3.controller.Navigation;

import com.clafu.model.UserModel;

public class Tutorial2Controller extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {

        return forward("tutorial2.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "チュートリアル";
    }
}
