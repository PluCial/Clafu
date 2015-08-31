package com.clafu.controller.account;

import org.slim3.controller.Navigation;

import com.clafu.model.UserModel;

public class TutorialEndController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {

        return forward("tutorialEnd.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "チュートリアル";
    }
}
