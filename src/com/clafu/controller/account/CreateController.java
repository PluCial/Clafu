package com.clafu.controller.account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.TreeMap;

import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import com.clafu.Constants;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.service.ContentService;
import com.clafu.utils.DateUtils;

public class CreateController extends BaseController {

    @Override
    protected Navigation execute(UserModel userModel) throws Exception {
        
        String id = asString("id");
        if(!StringUtil.isEmpty(id)) {
            ContentModel contentModel = ContentService.getOrNull(Long.valueOf(id));
            
            if(contentModel != null && contentModel.getCreateUserModelRef().getKey().getId() == userModel.getKey().getId()) {
                
                requestScope("contentModel", contentModel);
                requestScope("url", contentModel.getUrlString());
                requestScope("title", contentModel.getTitle());
                requestScope("category", contentModel.getContentCategory().getCategory());
                requestScope("postMessage", contentModel.getPostMessageString());
                requestScope("explanation", contentModel.getExplanationString());
                requestScope("patronMaxLimit", contentModel.getPatronMaxLimit());
                requestScope("exeDate", contentModel.getWishesExeDate());
                
                requestScope("categoryList", Constants.CATEGORY_LIST);
                
                return forward("edit.jsp");
            }
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
        
        return forward("create.jsp");
    }
    
    /**
     * 日付リストの取得
     * @return
     */
    private TreeMap<String, String> getDateList() {
        TreeMap<String,String> map = new TreeMap<String,String>();
        
//        System.out.println("UTC:" + (DateUtils.dateToString("yyyy/MM/dd HH:mm:ss a z", TimeZone.getTimeZone("UTC"), Locale.getDefault())));
//        System.out.println("Asia/Tokyo:" + (DateUtils.dateToString("yyyy/MM/dd HH:mm:ss a z", TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN)));

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
        return "告知の作成";
    }
}
