package com.clafu.controller.account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.TreeMap;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.StringUtil;

import com.clafu.Constants;
import com.clafu.model.UserModel;
import com.clafu.utils.DateUtils;

public class CreateStep2Controller extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        
        // 入力チェック
        if (!isPost() || !validate()) {
            return forward("/account/createStep1");
        }
        
        // contentTitle を 告知タイトルの初期値として設定
        String contentTitle = asString("contentTitle");
        if(!StringUtil.isEmpty(contentTitle)) {
            requestScope("title", contentTitle);
        }
        
        requestScope("heartNumList", getHeartNumList(userModel));
        requestScope("exeDateMapList", getDateList());
        requestScope("exeHourList", getHourList());
        requestScope("exeMinuteList", getMinuteList());
        requestScope("categoryList", Constants.CATEGORY_LIST);
        
        String error = asString("error");
        if(StringUtil.isEmpty(error)) {
            requestScope("twit", "true");
        }
        
        return forward("createStep2.jsp");
    }
    
    /**
     * バリデーション
     * @param userModel
     * @return
     */
    private boolean validate() {
        Validators v = new Validators(request);
        
        // url
        v.add("url", 
            v.required(), 
            v.regexp("(http://|https://){1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]+", "正しいURLではありません。"));
        
        return v.validate();
    }
    
    /**
     * 日付リストの取得
     * @return
     */
    private TreeMap<String, String> getDateList() {
        TreeMap<String,String> map = new TreeMap<String,String>();

        for(int i=0;i<30;i++) {
            Calendar calendar = DateUtils.addDate(i + 1, TimeZone.getTimeZone("Asia/Tokyo"));
            Date date = calendar.getTime();

            String key = DateUtils.dateToString(date, "yyyyMMdd", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN);
            String value = DateUtils.dateToString(date, "yyyy年MM月dd日(E)", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN);
            
            // Map追加
            map.put(key, value);
        }
        
        return map;
    }
    
    /**
     * 実行日時(時)リストを取得
     * @return
     */
    private List<String> getHourList() {
        List<String> list = new ArrayList<String>();
        for(int i=0; i<24; i++) {
            list.add(String.valueOf(i));
        }
        
        return list;
    }
    
    private List<String> getMinuteList() {
        List<String> list = new ArrayList<String>();
        list.add("00");
        list.add("30");
        
        return list;
    }
    
    private List<String> getHeartNumList(UserModel userModel) {
        List<String> list = new ArrayList<String>();
        
        if(userModel.getHeartCount() > 0) {
            for(int i=1;i<=userModel.getHeartCount();i++) {
                list.add(String.valueOf(i));
            }
        }
        
        return list;
    }

    @Override
    protected String getPageTitle() {
        return "告知の作成(Step2)";
    }
}
