package com.clafu.controller.twitter;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.clafu.model.UserModel;
import com.clafu.service.UserService;

/**
 * Twitter ログインコントローラー
 * @author takahara
 *
 */
public class LoginController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        // Titterオブジェクトの生成
        Twitter twitter = sessionScope("twitter");
        RequestToken twitterRequestToken = sessionScope("twitterRequestToken");
        String verifier = asString("oauth_verifier");

        AccessToken accessToken = null;

        try {
            // RequestTokenからAccessTokenを取得
            accessToken = twitter.getOAuthAccessToken(twitterRequestToken, verifier);

            User user = twitter.verifyCredentials();
            UserModel userModel = UserService.getUserModelByTwitterId(String.valueOf(user.getId()));
            
            int followersCount = user.getFollowersCount();

            // 新規登録
            if(userModel == null) {
                userModel = UserService.addUserByTwitter(
                    String.valueOf(user.getId()),
                    user.getScreenName(),
                    followersCount,
                    user.getOriginalProfileImageURL(),
                    accessToken.getToken(),
                    accessToken.getTokenSecret());
            
            // 変更
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

        return redirect("/account/myPage");
    }
}
