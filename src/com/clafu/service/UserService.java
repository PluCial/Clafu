package com.clafu.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.datanucleus.util.StringUtils;
import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.clafu.dao.UserModelDao;
import com.clafu.meta.UserModelMeta;
import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.model.UserModel;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

/**
 * ユーザーサービス
 * @author takahara
 */
public class UserService {
    
    private static UserModelDao dao = new UserModelDao();

    /**
     * PUT
     * @param model
     * @return
     */
    public static UserModel put(UserModel model) {
        // 永久化
        dao.put(model);

        // 自信のキャッシュをクリア
        Memcache.delete(model.getKey().toString());

        return model;
    }
    
    /**
     * ユーザー追加
     * @param userId
     * @param userName
     * @param screenName
     * @param location
     * @param profileImageURL
     * @param profileURL
     * @param accessToken
     * @return UserModel
     * @throws MalformedURLException
     * @throws IOException
     */
    public static UserModel addUserByTwitter(
            String twitterUserId,
            String twitterUserName,
            int followersCount,
            String profileImageURL,
            String accessToken,
            String tokenSecret) throws MalformedURLException, IOException {

        UserModel userModel = new UserModel();
        
        // Twitter User ID
        if(StringUtils.notEmpty(twitterUserId)) {
            userModel.setTwitterUserId(twitterUserId);
        }

        // Twitter User Name
        if(StringUtils.notEmpty(twitterUserName)) {
            userModel.setUserName(twitterUserName);
        }
        
        // フォロワー数
        userModel.setTwitterFollowersCount(followersCount);

        // Twitter AccessToken
        if(StringUtils.notEmpty(accessToken)) {
            userModel.setTwitterAccessToken(accessToken);
        }

        // Twitter TokenSecret
        if(StringUtils.notEmpty(tokenSecret)) {
            userModel.setTwitterTokenSecret(tokenSecret);
        }

        // 新しいユーザーをまず(0〜49)グループに割り当てる
        Random rnd = new Random();
        userModel.setGroup(rnd.nextInt(50));
        
        // 作成日付
        userModel.setCreateDate(new Date());
        
        //TODO: ハートプレゼント
        userModel.setHeartCount(50);

        // 永久化
        dao.put(userModel);
        
        // アイコン画像の取得
        if(StringUtils.notEmpty(profileImageURL)) {
            userModel.setProfileImageURL(profileImageURL);
            
            URLFetchService fetchService =
                    URLFetchServiceFactory.getURLFetchService();
            HTTPResponse fetchResponse = fetchService.fetch(new URL(profileImageURL));
            
            // プロフィールアイコンのコンテンツタイプを取得
            ListIterator<HTTPHeader> iterator = fetchResponse.getHeaders().listIterator();

            while(iterator.hasNext()) {
                HTTPHeader header = (HTTPHeader)iterator.next();
                if(header.getName().toLowerCase().equals("content-type")) {
                    userModel.setProfileImageContentType(header.getValue());
                }
            }

            
            // 追加画像の縮小
            ImagesService imagesService = ImagesServiceFactory.getImagesService();
            Image profileImage = ImagesServiceFactory.makeImage(fetchResponse.getContent());
            Transform imageTransform = ImagesServiceFactory.makeResize(250, 250);
            imagesService.applyTransform(imageTransform, profileImage);
//            
//            // プロフィールアイコンのバイトを保存
//            Blob profileImageBlob = new Blob(profileImage.getImageData());
//            userModel.setProfileImageBlob(profileImageBlob);
            
            
            // GCSに保存
            GcsService gcsService =
                    GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());
            
            GcsOutputChannel outputChannel =
                    gcsService.createOrReplace(
                        new GcsFilename(
                            AppIdentityServiceFactory.getAppIdentityService().getDefaultGcsBucketName(), 
                            String.valueOf("profile_image/" + userModel.getKey().getId())), 
                        new GcsFileOptions.Builder()
                                .mimeType(userModel.getProfileImageContentType())
                                .acl("public-read")
                                .build()
                        );
            
            outputChannel.write(ByteBuffer.wrap(profileImage.getImageData()));
            
            outputChannel.close();
        }
        
        // 永久化
        dao.put(userModel);

        return userModel;
    }
    
    /**
     * ユーザーの取得
     * @param email
     * @return
     */
    public static UserModel getOrNull(long userID) {

        Key key = createKey(userID);
        UserModel model = Memcache.get(key.toString());
        if(model != null) return model;

        model = dao.getOrNull(key);
        if(model != null) Memcache.put(model.getKey().toString(), model);

        return model;
    }
    
    /**
     * User Delete
     * @param userId
     */
    public static void delete(Long userId) {
        
        UserModel userModel = getOrNull(userId);
        
        // 参加告知情報を削除
        List<TransmitHistoryModel> historyList = userModel.getTransmitHistoryModelListRef().getModelList();
        for(TransmitHistoryModel historyModel: historyList) {
            TransmitHistoryService.delete(historyModel.getKey().getId());
        }
        
        // ユーザー作成したコンテンツ
        List<ContentModel> contentList = userModel.getCreateContentModelListRef().getModelList();
        for(ContentModel contentModel: contentList) {
        
            ContentService.delete(contentModel.getKey().getId());
        }
        
        // コメントは悪さしないので残す
        dao.delete(userModel.getKey());
        
        // 自信のキャッシュをクリア
        Memcache.delete(userModel.getKey().toString());
        
    }
    
    /**
     * Twitter User Id からユーザーモデルを取得
     * @param twitterUserId
     * @return
     */
    public static UserModel getUserModelByTwitterId(String twitterUserId) {

        return dao.getUserModelByTwitterId(twitterUserId);
    }
    
    /**
     * キーの取得
     * @param userID
     * @return
     */
    private static Key createKey(long userID) {
        return Datastore.createKey(UserModelMeta.get(), userID);
    }

}
