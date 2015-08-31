package com.clafu.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.clafu.model.UserModel;

public abstract class BaseController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        
        UserModel loginUserModel = getLoginUser();

        requestScope("isLogin", String.valueOf(loginUserModel != null));
        requestScope("isSmartPhone", String.valueOf(isSmartPhone()));

        requestScope("pageTitle", getPageTitle());
        
        requestScope("thisPageUri", getPageUri());
        requestScope("thisPageUrl", "http://www.clafu.com" + getPageUri());

        if(loginUserModel != null) {
            requestScope("loginUserModel", loginUserModel);

            return execute(loginUserModel, true);
        }


        // ログインしていない場合
        return execute(loginUserModel, false);
    }

    /**
     * 登録ユーザーの場合、登録情報を取得する。
     * 登録ユーザーではない、もしくGoogleアカウントにログインしていない場合は、
     * エラーを生成
     * @return
     * @throws Exception
     */
    public UserModel getLoginUser() {

        UserModel userModel = sessionScope("userModel");

        return userModel;
    }

    /**
     * デバイスがスマートフォンであるか判定
     * @param request
     * @return
     */
    protected boolean isSmartPhone() {

        String userAgent = request.getHeader("User-Agent").toLowerCase();

        if(userAgent != null && (userAgent.indexOf("iphone") > 0 || userAgent.indexOf("android") > 0)) {
            return true;
        }

        return false;
    }

    /**
     * リクエスト処理
     * @return
     * @throws Exception
     */
    protected abstract Navigation execute(UserModel userModel, boolean isLogin) throws Exception;

    /**
     * ページタイトルの設定
     * @return
     * @throws Exception
     */
    protected abstract String getPageTitle();
    
    /**
     * ページURIを取得
     * @return
     * @throws Exception
     */
    protected abstract String getPageUri();

}
