package com.clafu.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.memcache.Memcache;
import org.slim3.util.StringUtil;

import com.clafu.Constants;
import com.clafu.dao.ContentModelDao;
import com.clafu.exception.DataInputException;
import com.clafu.meta.ContentModelMeta;
import com.clafu.model.CommentModel;
import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.model.UserModel;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.urlshortener.Urlshortener;
import com.google.api.services.urlshortener.UrlshortenerScopes;
import com.google.api.services.urlshortener.model.Url;
import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

/**
 * コンテンツサービス
 * @author takahara
 *
 */
public class ContentService {
    
    protected static final HttpTransport TRANSPORT = new NetHttpTransport();
    protected static final JacksonFactory JSON_FACTORY = new JacksonFactory();
    
    private static ContentModelDao dao = new ContentModelDao();
    
    /**
     *  コンテンツリストキャッシュキー
     */
    public static final String CONTENT_LIST_MEMCACHE_KEY = "content_list_memcache_key";
    
    /**
     *  カテゴリ検索コンテンツリストキャッシュキー
     */
    public static final String CATEGORY_SEARCH_CONTENT_LIST_MEMCACHE_KEY = "category_search_content_list_memcache_key";
    
    /**
     *  ユーザー作成したコンテンツリストキャッシュキー
     */
    public static final String USER_CREATE_CONTENT_LIST_MEMCACHE_KEY = "user_create_content_list_memcache_key";
    
    /**
     * コンテンツ一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public static S3QueryResultList<ContentModel> getContentList(String cursor) {
        S3QueryResultList<ContentModel> list = dao.getContentList(Constants.CONTENT_LIST_DISPLAY_NUM, cursor);
        
        return list;
    }
    
    /**
     * コンテンツ一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public static S3QueryResultList<ContentModel> getContentList(String cursor, int num) {
        S3QueryResultList<ContentModel> list = dao.getContentList(num, cursor);
        
        return list;
    }
    
    /**
     * コンテンツ一覧を取得(配信直前告知)
     * @param userModel
     * @param num
     * @return
     */
    public static S3QueryResultList<ContentModel> getExeDateLimitContentList(String cursor) {
        S3QueryResultList<ContentModel> list = dao.getExeDateLimitContentList(Constants.CONTENT_LIST_DISPLAY_NUM, cursor);
        
        return list;
    }
    
    /**
     * コンテンツ一覧を取得(配信直前告知)
     * @param userModel
     * @param num
     * @return
     */
    public static S3QueryResultList<ContentModel> getExeDateLimitContentList(String cursor, int num) {
        S3QueryResultList<ContentModel> list = dao.getExeDateLimitContentList(num, cursor);
        
        return list;
    }
    
    /**
     * ユーザーが作成したコンテンツ一覧を取得
     * @param userModel
     * @return
     */
    public static S3QueryResultList<ContentModel> getCreateContentListByUser(UserModel userModel, String cursor) {

//        S3QueryResultList<ContentModel> list = null;
//
//        if(StringUtils.isEmpty(cursor)) {
//            list = Memcache.get(getUserContentListMemcacheKey(userModel));
//            if(Utils.isNotEmpty(list)) return list;
//
//            list = dao.getCreateContentListByUser(userModel, Constants.USER_CREATE_CONTENT_LIST_DISPLAY_NUM);
//            if(Utils.isNotEmpty(list)) Memcache.put(getUserContentListMemcacheKey(userModel), list);
//
//        }else {
//            list = dao.getCreateContentListByUser(userModel, Constants.USER_CREATE_CONTENT_LIST_DISPLAY_NUM, cursor);
//            
//        }
        
        S3QueryResultList<ContentModel> list = dao.getCreateContentListByUser(userModel, Constants.USER_CREATE_CONTENT_LIST_DISPLAY_NUM, cursor);

        return list;
    }
    
    /**
     * カテゴリからコンテンツ一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public static S3QueryResultList<ContentModel> getContentByCategory(String contentCategory, String cursor) {
        S3QueryResultList<ContentModel> list = dao.getContentByCategory(contentCategory, Constants.CONTENT_LIST_DISPLAY_NUM, cursor);
        
        return list;
    }
    
    /**
     * PUT
     * @param model
     * @return
     */
    public static ContentModel put(ContentModel model) {
        // 永久化
        dao.put(model);

        // 自信のキャッシュをクリア
        Memcache.delete(model.getKey().toString());
        // Listキャッシュのクリア

        return model;
    }
    
    /**
     * コンテンツの追加
     * @param userModel
     * @param contentCategory
     * @param url
     * @param explanation
     * @param postMessage
     * @param patronMaxLimit
     * @param wishesExeDate
     * @return
     * @throws Exception 
     */
    public static ContentModel add(
            UserModel userModel,
            String contentCategory,
            String url,
            String title,
            String postMessage,
            String explanation,
            String contentTitle,
            String contentDescription,
            int patronMaxLimit,
            Date wishesExeDate) throws Exception {
        
        // 入力チェック
        if(StringUtil.isEmpty(contentCategory)
                || StringUtil.isEmpty(url)
                || StringUtil.isEmpty(postMessage)
                || patronMaxLimit <= 0) {
            throw new DataInputException();
        }
        
        ContentModel model = new ContentModel();
        model.setContentCategory(new Category(contentCategory));
        model.setUrl(new Text(url));
        
        // ターゲットの短縮URL
        model.setTargetShortUrl(getShortUrl(url));
        
        // 告知タイトル
        if(!StringUtil.isEmpty(title)) {
            model.setTitle(title);
        }
        
        // コンテンツタイトル
        if(!StringUtil.isEmpty(contentTitle)) {
            model.setContentTitle(contentTitle);
        }
        
        // コンテンツDescription
        if(!StringUtil.isEmpty(contentDescription)) {
            model.setContentDescription(new Text(contentDescription));
        }
        
        // ツイート内容
        model.setPostMessage(new Text(postMessage));
        
        // 告知説明
        if(!StringUtil.isEmpty(explanation)) {
            model.setExplanation(new Text(explanation));
        }
        
        // 使用ハート数
        model.setPatronMaxLimit(patronMaxLimit);
        
        // 告知配信日時
        model.setWishesExeDate(wishesExeDate);
        
        // ユーザーRefの追加
        model.getCreateUserModelRef().setModel(userModel);
        model.setCreateDate(new Date());
        
        // 新しい(0〜49)グループに割り当てる
        Random rnd = new Random();
        model.setGroup(rnd.nextInt(50));
        
        // 追加
        dao.put(model);
        
        // ハートの数を減らす
        userModel.setHeartCount(userModel.getHeartCount() - patronMaxLimit);
        UserService.put(userModel);
        
        return model;
    }
    
    /**
     * ユーザーの取得
     * @param email
     * @return
     */
    public static ContentModel getOrNull(long contentId) {

        Key key = createKey(contentId);
        ContentModel model = Memcache.get(key.toString());
        if(model != null) return model;

        model = dao.getOrNull(key);
        if(model != null) Memcache.put(model.getKey().toString(), model);

        return model;
    }
    
    /**
     * コンテンツの削除
     * @param contentId
     */
    public static void delete(long contentId) {
        ContentModel model = getOrNull(contentId);
        
        if(model == null) return;
        
        // コメントを削除
        List<CommentModel> commentList = model.getCommentModelListRef().getModelList();
        if(commentList != null) {
            for(CommentModel commentModel: commentList) {
                CommentService.delete(model, commentModel.getKey().getId());
            }
        }
        
        // 参加者情報を削除
        List<TransmitHistoryModel> historyList = model.getTransmitHistoryModelListRef().getModelList();
        for(TransmitHistoryModel transmitHistoryModel: historyList) {
            TransmitHistoryService.delete(transmitHistoryModel.getKey().getId());
        }
        
        dao.delete(model.getKey());
        
        // 自信のキャッシュをクリア
        Memcache.delete(model.getKey().toString());
    }
    
    /**
     * キーの取得
     * @param userID
     * @return
     */
    private static Key createKey(long contentID) {
        return Datastore.createKey(ContentModelMeta.get(), contentID);
    }
    
    /**
     * コンテンツリストキャッシュキーの取得
     */
    public static String getContentListMemcacheKey() {

        return CONTENT_LIST_MEMCACHE_KEY;
    }
    
    /**
     * ユーザー作成したコンテンツリストキャッシュキーの取得
     */
    public static String getUserContentListMemcacheKey(UserModel userModel) {

        return USER_CREATE_CONTENT_LIST_MEMCACHE_KEY + "_" + userModel.getKey().getId();
    }
    
    /**
     * カテゴリ検索コンテンツリストキャッシュキーの取得
     */
    public static String getCategorySearchContentListMemcacheKey(String contentCategory) {

        return CATEGORY_SEARCH_CONTENT_LIST_MEMCACHE_KEY + "_" + contentCategory;
    }
    
    /**
     * 短縮URLの取得
     * @return
     * @throws Exception
     */
    public static String getShortUrl(String longUrl) throws Exception {
        
        // アプリ承認オブジェクトの生成
        GoogleCredential credential = new GoogleCredential.Builder()
        .setJsonFactory(JSON_FACTORY)
        .setTransport(TRANSPORT)
        .setServiceAccountId(Constants.GOOGLE_SERVICE_ACCOUNT_EMAIL)
        .setServiceAccountScopes(UrlshortenerScopes.all())
        .setServiceAccountPrivateKeyFromP12File(new File("WEB-INF/clafu-production-326bed257bc9.p12")).build();

        // Urlshortener オブジェクトの生成
        Urlshortener.Builder builder = new Urlshortener.Builder(TRANSPORT, JSON_FACTORY, credential);
        builder.setApplicationName(Constants.GOOGLE_APPLICATION_NAME);
        Urlshortener urlshortener = builder.build();

        // URLの設定
        Url url = new Url();
        url.setLongUrl(longUrl);

        // 短縮URLの発行
        url = urlshortener.url().insert(url).execute();

        return url.getId();
    }
    
    /**
     * コンテンツ一覧を取得(サイトマップ用)
     * @param List<ContentModel>
     * @param num
     * @return
     */
    public static List<ContentModel> getContentListByGroup(int groupNum) {
        return dao.getContentListByGroup(groupNum);
    }

    /**
     * 送信処理対象リストを取得(古い順)
     * @param groupId
     * @return
     */
    public static List<ContentModel> getPostTargetList() {
        return dao.getPostTargetList();
    }
}
