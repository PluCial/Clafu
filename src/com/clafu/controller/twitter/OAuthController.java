package com.clafu.controller.twitter;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import com.clafu.Constants;

/**
 * Twitter OAuthコントローラー
 * @author takahara
 *
 */
public class OAuthController extends Controller {

    private static TwitterFactory twitterFactory = new TwitterFactory();

    @Override
    protected Navigation run() throws Exception {
        // Titterオブジェクトの生成
        Twitter twitter = twitterFactory.getInstance();
        twitter.setOAuthConsumer(Constants.TWITTER_APP_API_KEY, Constants.TWITTER_APP_API_SECRET);

        // リクエストトークンの生成
        RequestToken twitterRequestToken = twitter.getOAuthRequestToken();

        // RequestTokenをセッションに保存しておきます。
        sessionScope("twitter", twitter);
        sessionScope("twitterRequestToken", twitterRequestToken);

        // 認証画面にリダイレクトするためのURLを生成
        String oauthUrl = twitterRequestToken.getAuthorizationURL();

        return redirect(oauthUrl);
    }
}
