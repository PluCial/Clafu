package com.clafu.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.clafu.Constants;
import com.google.common.net.InternetDomainName;

public class Utils {

    private static final Pattern COMMENT_START = Pattern.compile("<[^>/!]{1}[^>]*?>");
    private static final Pattern COMMENT_END = Pattern.compile("</[^>!]*?>");

    private static final Pattern LINK_COMMENT_START = Pattern.compile("<a[^>/!]{1}[^>]*?>");
    private static final Pattern LINK_COMMENT_END = Pattern.compile("</a[^>!]*?>");

    private static final Pattern B_TAG_COMMENT_START = Pattern.compile("<b[^>!]*?>");
    private static final Pattern B_TAG_COMMENT_END = Pattern.compile("</b[^>!]*?>");

    public static String removeAllTags(String str) {
        return removePattern(removePattern(str, COMMENT_START), COMMENT_END);
    }

    public static String removeLinkTags(String str) {
        return removePattern(removePattern(str, LINK_COMMENT_START), LINK_COMMENT_END);
    }

    public static String removeBTags(String str) {
        return removePattern(removePattern(str, B_TAG_COMMENT_START), B_TAG_COMMENT_END);
    }

    public static String removePattern(String target, Pattern pattern) {
        if (target == null || target.trim().equals("")) {
            return target;
        }

        Matcher matcher = pattern.matcher(target);
        StringBuilder buff = new StringBuilder();
        int start = 0;
        while (matcher.find()) {
            buff.append(target.substring(start, matcher.start()));
            start = matcher.end();
        }
        buff.append(target.substring(start, target.length()));

        return buff.toString();
    }

    /**
     * List型の引数がnullまたは空であればtrueを返します
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * List型の引数がnullでも空でもなければtrueを返却します
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }


    /**
     * 2つの日付の差を求めます。
     * java.util.Date 型の日付 date1 - date2 が何日かを返します。
     *
     * 計算方法は以下となります。
     * 1.最初に2つの日付を long 値に変換します。
     * 　※この long 値は 1970 年 1 月 1 日 00:00:00 GMT からの
     * 　経過ミリ秒数となります。
     * 2.次にその差を求めます。
     * 3.上記の計算で出た数量を 1 日の時間で割ることで
     * 　日付の差を求めることができます。
     * 　※1 日 ( 24 時間) は、86,400,000 ミリ秒です。
     *
     * @param date1    日付 java.util.Date
     * @param date2    日付 java.util.Date
     * @return    2つの日付の差
     */
    public static int differenceDays(Date date1,Date date2) {
        long datetime1 = date1.getTime();
        long datetime2 = date2.getTime();
        long one_date_time = 1000 * 60 * 60 * 24;
        long diffDays = (datetime1 - datetime2) / one_date_time;
        return (int)diffDays;
    }
    
    /**
     * 文字列を適切なHTMLに変換
     * @param data
     * @return
     */
    public static String changeStringToHtml(String data) {
        if(data == null) return "";
        
        // エンコード
        String newdata = data.replace("\"", "&quot;");
        newdata = data.replace("&", "&amp;");
        newdata = data.replace("<", "&lt;");
        newdata = data.replace(">", "&gt;");
        
        // 改行
        newdata = data.replace("\n\r", "<br />");
        newdata = newdata.replace("\r\n", "<br />");
        newdata = newdata.replace("\n", "<br />");
        
        return newdata;
    }
    
    /**
     * 文字の切り出し
     * @param str
     * @param num
     * @return
     */
    public static String subContentsString(String str, int num) {

        if(str == null) return "";

        if(str.length() > num) {
            return str.substring(0, num) + "....";
        }

        return str;
    }
    
    /**
     * Twitter Post
     * @param acessToken
     * @param tokenSecret
     * @param msg
     * @return 
     * @throws Exception
     */
    public static Status twitterPost(String acessToken, String tokenSecret, String msg) throws Exception {
        // 承認情報の生成
        ConfigurationBuilder cb = getTwitterConfigurationBuilder(acessToken, tokenSecret);

        // Titterオブジェクトの生成
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        Twitter twitter = twitterFactory.getInstance();

        // ツイート
        return twitter.updateStatus(msg);
    }
    
    /**
     * Twitter Post
     * @param acessToken
     * @param tokenSecret
     * @param msg
     * @return 
     * @throws Exception
     */
    public static Status reTweetPost(String acessToken, String tokenSecret, long reTweetTargetId) throws Exception {
        // 承認情報の生成
        ConfigurationBuilder cb = getTwitterConfigurationBuilder(acessToken, tokenSecret);

        // Titterオブジェクトの生成
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        Twitter twitter = twitterFactory.getInstance();

        // ツイート
        return twitter.retweetStatus(reTweetTargetId);
    }
    
    /**
     * Twitter承認情報の生成
     * @param userModel
     * @param msg
     * @return
     */
    private static ConfigurationBuilder getTwitterConfigurationBuilder(String acessToken, String tokenSecret) throws Exception {

        // 承認情報の生成
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(Constants.TWITTER_APP_API_KEY)
        .setOAuthConsumerSecret(Constants.TWITTER_APP_API_SECRET)
        .setOAuthAccessToken(acessToken)
        .setOAuthAccessTokenSecret(tokenSecret);

        return cb;
    }
    
    public static int getGiftHeartCount(int followersCount) {
        int giftHeartCount = 1;
        
        if(giftHeartCount < 1000) {
            giftHeartCount = 1;

        }else if(giftHeartCount < 2000) {
            giftHeartCount = 2;

        }else if(giftHeartCount < 3000) {
            giftHeartCount = 3;

        }else if(giftHeartCount < 4000) {
            giftHeartCount = 4;

        }else if(giftHeartCount < 5000) {
            giftHeartCount = 5;

        }else {
            giftHeartCount = 6;
        }
        
        return giftHeartCount;
    }
    
    /**
     * URLからドメインを取得
     * @param urlString
     * @return
     */
    public static String getURLPrivateDomain(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
            
            String domain = InternetDomainName.from(url.getHost()).topPrivateDomain().toString();
            
            if(domain == null) return "";
            
            return domain;
            
        } catch (MalformedURLException e) {
            return "";
        }
    }
}
