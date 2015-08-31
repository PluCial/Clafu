package com.clafu.controller.account;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.clafu.exception.UserLoginException;
import com.clafu.model.UserModel;
import com.clafu.service.UserService;

public abstract class BaseController extends Controller {

    @Override
    protected Navigation run() throws Exception {

        // アクセス承認
        try {
            UserModel loginUserModel = getLoginUser();
            // 再取得する(ハートの更新などがあるので)
            loginUserModel = UserService.getOrNull(loginUserModel.getKey().getId());
            
            requestScope("isLogin", String.valueOf(true));
            requestScope("loginUserModel", loginUserModel);

            requestScope("pageTitle", getPageTitle());
            requestScope("isSmartPhone", String.valueOf(isSmartPhone()));

            return execute(loginUserModel);

        }catch(UserLoginException e) {
            return redirect("/");
        }
    }

    /**
     * 登録ユーザーの場合、登録情報を取得する。
     * 登録ユーザーではない、もしくGoogleアカウントにログインしていない場合は、
     * エラーを生成
     * @return
     * @throws Exception
     */
    public UserModel getLoginUser() throws UserLoginException {

        UserModel userModel = sessionScope("userModel");

        if(userModel == null) throw new UserLoginException();

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
    protected abstract Navigation execute(UserModel userModel) throws Exception;

    /**
     * ページタイトルの設定
     * @return
     * @throws Exception
     */
    protected abstract String getPageTitle();

}
