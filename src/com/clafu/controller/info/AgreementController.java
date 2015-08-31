package com.clafu.controller.info;

import org.slim3.controller.Navigation;

import com.clafu.controller.BaseController;
import com.clafu.model.UserModel;

public class AgreementController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        return forward("agreement.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "利用規約";
    }

    @Override
    protected String getPageUri() {
        return "/info/agreement";
    }
}
