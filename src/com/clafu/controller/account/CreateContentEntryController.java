package com.clafu.controller.account;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;
import com.clafu.utils.DateUtils;
import com.clafu.utils.Utils;

public class CreateContentEntryController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {

            // 入力チェック
            if (!isPost() || !createValidate(userModel)) {
                return forward("/account/createStep2?error=error");
            }

            String title = asString("title");
            String url = asString("url");
            String category = asString("category");
            String postMessage = asString("postMessage");
            String explanation = asString("explanation");
            String contentTitle = asString("contentTitle");
            String contentDescription = asString("description");
            Integer patronMaxLimit = asInteger("patronMaxLimit");
            String exeDate = asString("exeDate");
            String exeHour = asString("exeHour");
            String exeMinute = asString("exeMinute");
            
            String twit = asString("twit");
            boolean twitFlg = !StringUtil.isEmpty(twit) && (twit.equals("on") || twit.equals("true")) ? true : false;

            Date wishesExeDate = DateUtils.toDate(exeDate + " " + exeHour + ":" + exeMinute, "yyyyMMdd HH:mm", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN);

            ContentModel contentModel = ContentService.add(
                userModel, 
                category, 
                url, 
                title, 
                postMessage, 
                explanation, 
                contentTitle,
                contentDescription,
                patronMaxLimit, 
                wishesExeDate);
            
            // Clafuページの短縮URL
            contentModel.setPageShortUrl(ContentService.getShortUrl("http://www.clafu.com/contents/" + contentModel.getKey().getId()));
            ContentService.put(contentModel);
            
            // twit
            if(twitFlg) {
                String msg = "告知のご協力をお願いします。";
                msg = msg + "\n" + "【" + Utils.subContentsString(title, 60) + "】"; // + 34文字
                msg = msg + "\n" + "[ #Clafu " + contentModel.getPageShortUrl() + " ]"; // + 39文字
                
                Utils.twitterPost(
                    userModel.getTwitterAccessToken(), 
                    userModel.getTwitterTokenSecret(),
                    msg);
            }
            
            return redirect("/user/" + userModel.getKey().getId());
    }

    /**
     * バリデーション
     * @param userModel
     * @return
     */
    private boolean createValidate(UserModel userModel) {
        Validators v = new Validators(request);
        
        // タイトル
        v.add("title", v.required(),v.maxlength(150));
        
        // url
        v.add("url", 
            v.required(), 
            v.regexp("(http://|https://){1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]+", "正しいURLではありません。"));
        
        // カテゴリ
        v.add("category", v.required());
        
        // postメッセージ
        v.add("postMessage", v.required(), v.maxlength(90));
        
        // explanation
        v.add("explanation");
        
        // contentTitle
        v.add("contentTitle");
        
        // description
        v.add("description");
        
        // バトロン上限
        int limit = userModel.getHeartCount() < 100 ? userModel.getHeartCount() : 100;
        v.add("patronMaxLimit", v.required(), v.integerType(), v.longRange(1,limit));
        
        // 配信日
        v.add("exeDate", 
            v.required(), 
            v.dateType("yyyyMMdd"));
        
        // 配信時間(時)
        v.add("exeHour", 
            v.required(), 
            v.dateType("HH"));
        
        // 配信時間(分)
        v.add("exeMinute", 
            v.required(), 
            v.dateType("mm"));
        
        // twit
        v.add("twit");
        
        return v.validate();
    }
    
    @Override
    protected String getPageTitle() {
        return "null";
    }
}
