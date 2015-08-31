package com.clafu.controller.account;

import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import com.clafu.model.UserModel;

public class LogoutController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel)
            throws Exception {
        // セッション削除
        removeSessionScope("userModel");
        
        String callback = asString("callback");
        
        if(!StringUtil.isEmpty(callback)) {
            return redirect(callback);
        }

        return redirect("/");
    }

    @Override
    protected String getPageTitle() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }
}
