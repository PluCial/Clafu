package com.clafu.controller.info;

import org.slim3.controller.Navigation;

import com.clafu.controller.BaseController;
import com.clafu.model.UserModel;

public class ClafuController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel, boolean isLogin)
            throws Exception {
        return forward("clafu.jsp");
    }

    @Override
    protected String getPageTitle() {
        return "Clafuとは";
    }

    @Override
    protected String getPageUri() {
        return "/info/clafu";
    }
}
