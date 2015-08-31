package com.clafu.controller.account;

import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import com.clafu.model.UserModel;
import com.clafu.service.UserService;

public class NewUserCampaignController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        
        String twit = asString("twit");
        boolean twitFlg = !StringUtil.isEmpty(twit) && (twit.equals("on") || twit.equals("true")) ? true : false;
        
        userModel.setNewUserCampaignFlg(twitFlg);
        UserService.put(userModel);

        return redirect("tutorialEnd");
    }

    @Override
    protected String getPageTitle() {
        return "チュートリアル";
    }
}
