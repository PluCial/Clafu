package com.clafu.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.util.BeanUtil;
import org.slim3.util.StringUtil;

import com.clafu.Constants;
import com.clafu.dao.TransmitHistoryModelDao;
import com.clafu.exception.DataInputException;
import com.clafu.meta.TransmitHistoryModelMeta;
import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.model.UserModel;
import com.clafu.utils.Utils;
import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

/**
 * そーしんヒストリサービス
 * @author takahara
 *
 */
public class TransmitHistoryService {
    
    private static TransmitHistoryModelDao dao = new TransmitHistoryModelDao();
    
    public static TransmitHistoryModel getOrNull(long historyId) {
        return dao.getOrNull(createKey(historyId));
    }
    
    /**
     * PUT
     * @param model
     * @return
     */
    public static TransmitHistoryModel put(TransmitHistoryModel model) {
        // 永久化
        dao.put(model);

        return model;
    }
    
    /**
     * 送信処理対象リストを取得(古い順)
     * @param groupId
     * @return
     */
    public static List<TransmitHistoryModel> getPostTargetList(int groupId) {
        
        List<TransmitHistoryModel> list = dao.getPostTargetList(groupId);
        
        return list;
    }
    
    /**
     * ユーザーがバトロンとなったコンテンツ一覧を取得(新しい順)
     * @param userModel
     * @return
     */
    public static S3QueryResultList<TransmitHistoryModel> getHistoryListByUser(UserModel userModel, String cursor) {
        
        S3QueryResultList<TransmitHistoryModel> list = dao.getHistoryListByUser(
            userModel, 
            Constants.USER_HISTORY_LIST_DISPLAY_NUM, 
            cursor);
        
        return list;
    }
    
    /**
     * コンテンツのバトロン一覧を取得
     * @param contentModel
     * @param cursor
     * @return
     */
    public static S3QueryResultList<TransmitHistoryModel> getPatronListByContent(ContentModel contentModel, String cursor) {
        
        S3QueryResultList<TransmitHistoryModel> list = dao.getPatronListByContent(
            contentModel, 
            Constants.CONTENT_PATRON_LIST_DISPLAY_NUM, 
            cursor);
        
        return list;
    }
    
    /**
     * 告知に協力
     * @param userModel
     * @param comment
     * @return
     * @throws Exception 
     */
    public static TransmitHistoryModel add(
            UserModel userModel, 
            long createUserId, 
            ContentModel contentModel,
            String socialType,
            String postMessage,
            boolean twitFlg) throws Exception {
        
        // 入力チェック
        if(StringUtil.isEmpty(socialType) || StringUtil.isEmpty(postMessage)) {
            throw new DataInputException();
        }
        
        TransmitHistoryModel model = new TransmitHistoryModel();
        
        // ユーザー情報をコピー
        BeanUtil.copy(userModel, model);
        
        // キーをコピーしない
        model.setKey(null);
        
        model.setSocialType(new Category(socialType));
        model.setPostMessage(new Text(postMessage));
        model.setContentUrl(contentModel.getUrl());
        model.setTargetShortUrl(contentModel.getTargetShortUrl());
        model.setPageShortUrl(contentModel.getPageShortUrl());
        
        // 作成したユーザーのID
        model.setCreateUserId(createUserId);
        
        // 新しいユーザーをまず(0〜49)グループに割り当てる
        Random rnd = new Random();
        model.setGroup(rnd.nextInt(50));
        
        // Ref追加
        model.getContentModelRef().setModel(contentModel);
        model.getUserModelRef().setModel(userModel);
        
        // リツートするため5分加算
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(contentModel.getWishesExeDate());
        calendar.add(Calendar.MINUTE, 5);
        model.setExeDate(calendar.getTime());
        
        // 作成日時を設定
        model.setCreateDate(new Date());
        
        // 追加
        dao.put(model);
        
        // コンテンツModelの更新
        if(socialType.equals(Constants.SOCIAL_TYPE_TWITTER)) {
            contentModel.setPatronCount(contentModel.getPatronCount() + 1);
            contentModel.setReachCount(contentModel.getReachCount() + userModel.getTwitterFollowersCount());
            
            if(contentModel.getPatronCount() >= contentModel.getPatronMaxLimit()) {
                contentModel.setPatronCountLimitFlg(true);
            }
        }
        ContentService.put(contentModel);
        
        // ハートを増やす
        userModel.setHeartCount(userModel.getHeartCount() + Utils.getGiftHeartCount(userModel.getTwitterFollowersCount()));
        UserService.put(userModel);
        
        // twit
        if(twitFlg) {
            String msg = "この告知に協力しました。";
            msg = msg + "\n" + "【" + Utils.subContentsString(contentModel.getTitle(), 30) + "】"; // + 34文字
            msg = msg + "\n" + "[ #Clafu " + contentModel.getPageShortUrl() + " ]"; // + 39文字
            
            Utils.twitterPost(
                userModel.getTwitterAccessToken(), 
                userModel.getTwitterTokenSecret(),
                msg);
        }
        
        return model;
    }
    
    /**
     * 指定したuserModelとContentModel両方にマッチするリストを取得
     * @param userModel
     * @param contentModel
     * @return
     */
    public static List<TransmitHistoryModel> getListByUserAndCountent(UserModel userModel, ContentModel contentModel) {
        return dao.getListByUserAndCountent(userModel, contentModel);
    }
    
    /**
     * 既にエントリーしているかをチェック
     * @param userModel
     * @param contentModel
     * @param socialType
     * @return
     */
    public static TransmitHistoryModel getListByUserAndCountentAndSocialType(UserModel userModel, ContentModel contentModel, String socialType) {
        List<TransmitHistoryModel> list = getListByUserAndCountent(userModel, contentModel);
        
        if(list == null || list.size() <= 0) {
            return null;
        }
        
        for(TransmitHistoryModel model: list) {
            if(model.getSocialType().getCategory().equals(socialType)) {
                return model;
            }
        }
        
        return null;
    }
    
    /**
     * 削除
     * @param historyId
     */
    public static void delete(long historyId) {
        Key key = createKey(historyId);
        dao.delete(key);
    }
    
    /**
     * キーの取得
     * @param userID
     * @return
     */
    private static Key createKey(long historyId) {
        return Datastore.createKey(TransmitHistoryModelMeta.get(), historyId);
    }

}
