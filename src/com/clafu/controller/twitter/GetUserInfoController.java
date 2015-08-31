package com.clafu.controller.twitter;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import twitter4j.Twitter;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.clafu.model.SpamUserModel;
import com.clafu.model.UserModel;
import com.clafu.service.SpamUserService;
import com.clafu.service.UserService;

/**
 * Twitter ログインコントローラー
 * @author takahara
 *
 */
public class GetUserInfoController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        // Titterオブジェクトの生成
        Twitter twitter = sessionScope("twitter");
        RequestToken twitterRequestToken = sessionScope("twitterRequestToken");
        String verifier = asString("oauth_verifier");

        AccessToken accessToken = null;
        boolean fistFlg = false;
        
        UserModel userModel = null;

        try {
            // RequestTokenからAccessTokenを取得
            accessToken = twitter.getOAuthAccessToken(twitterRequestToken, verifier);

            User user = twitter.verifyCredentials();
            
            // スパムユーザーチェック
            SpamUserModel spamUserModel = SpamUserService.getOrNull(String.valueOf(user.getId()));
            if(spamUserModel != null) {
                return forward("/spamUser.jsp");
            }
            
            
            userModel = UserService.getUserModelByTwitterId(String.valueOf(user.getId()));
            int followersCount = user.getFollowersCount();
            
            // 新規登録
            if(userModel == null) {
                fistFlg = true;
                userModel = UserService.addUserByTwitter(
                    String.valueOf(user.getId()),
                    user.getScreenName(),
                    user.getFollowersCount(),
                    user.getOriginalProfileImageURL(),
                    accessToken.getToken(),
                    accessToken.getTokenSecret());
            }else {
                if(userModel.getTwitterFollowersCount() != followersCount) {
                    userModel.setTwitterFollowersCount(followersCount);
                }
                
                UserService.put(userModel);
            }

            if(userModel == null) return redirect("/");

            // ユーザー情報をセッションに入れる
            sessionScope("userModel", userModel);

        } catch (Exception e) {
            e.printStackTrace();
            return redirect("/");
        }
        
        String callback = sessionScope("callback");
        if(fistFlg) {
            return redirect("/account/tutorial1");
            
        }else if(!StringUtil.isEmpty(callback)) {
            return redirect(callback);
        }

        return redirect("/");
    }
}
