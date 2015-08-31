package com.clafu.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.Sort;

import com.clafu.meta.ContentModelMeta;
import com.clafu.meta.TransmitHistoryModelMeta;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.SortDirection;

/**
 * ユーザーモデル
 * @author takahara
 *
 */
@Model(schemaVersion = 1)
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** グループ */
    private int group;
    
    /** ユーザー名 */
    @Attribute(unindexed = true)
    private String userName;
    
    /** ユーザープロフィールイメージ画像 */
    @Attribute(unindexed = true)
    private String profileImageURL;
    
    /** 画像 コンテントタイプ */
    @Attribute(unindexed = true)
    private String profileImageContentType;
    
    /** twitter User Id(検索用) */
    private String twitterUserId;
    
    /** Twitter Access Token */
    @Attribute(unindexed = true)
    private String twitterAccessToken;
    
    /** Twitter Token Secret */
    @Attribute(unindexed = true)
    private String twitterTokenSecret;
    
    /** ハート数 */
    @Attribute(unindexed = true)
    private int heartCount=0;
    
    /** フォロワー数 */
    @Attribute(unindexed = true)
    private int twitterFollowersCount = 0;
    
    /** 新規登録キャンペーンフラグ */
    private boolean newUserCampaignFlg = false;
    
    /** 新規登録キャンペーン実行済みフラグ */
    private boolean newUserCampaignExeFlg = false;
    
    /** 作成日 */
    private Date createDate;
    
    // ----------------------------------------------------------------------
    // 関連
    // ----------------------------------------------------------------------
    /** 作成した ContentModelとの関連 */
    @Attribute(persistent = false)
    private InverseModelListRef<ContentModel, UserModel> createContentModelListRef =
            new InverseModelListRef<ContentModel, UserModel>(
                    ContentModel.class,
                    ContentModelMeta.get().createUserModelRef.getName(),
                    this,
                    new Sort(ContentModelMeta.get().createDate, SortDirection.DESCENDING));
    
    /** TransmitHistoryModelとの関連 */
    @Attribute(persistent = false)
    private InverseModelListRef<TransmitHistoryModel, UserModel> transmitHistoryModelListRef =
            new InverseModelListRef<TransmitHistoryModel, UserModel>(
                    TransmitHistoryModel.class,
                    TransmitHistoryModelMeta.get().userModelRef.getName(),
                    this,
                    new Sort(TransmitHistoryModelMeta.get().createDate, SortDirection.DESCENDING));

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserModel other = (UserModel) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public String getTwitterUserId() {
        return twitterUserId;
    }

    public void setTwitterUserId(String twitterUserId) {
        this.twitterUserId = twitterUserId;
    }

    public String getTwitterAccessToken() {
        return twitterAccessToken;
    }

    public void setTwitterAccessToken(String twitterAccessToken) {
        this.twitterAccessToken = twitterAccessToken;
    }

    public String getTwitterTokenSecret() {
        return twitterTokenSecret;
    }

    public void setTwitterTokenSecret(String twitterTokenSecret) {
        this.twitterTokenSecret = twitterTokenSecret;
    }

    public int getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
    
    public InverseModelListRef<ContentModel, UserModel> getCreateContentModelListRef() {
        return createContentModelListRef;
    }

    public InverseModelListRef<TransmitHistoryModel, UserModel> getTransmitHistoryModelListRef() {
        return transmitHistoryModelListRef;
    }

    public String getProfileImageContentType() {
        return profileImageContentType;
    }

    public void setProfileImageContentType(String profileImageContentType) {
        this.profileImageContentType = profileImageContentType;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public int getTwitterFollowersCount() {
        return twitterFollowersCount;
    }

    public void setTwitterFollowersCount(int twitterFollowersCount) {
        this.twitterFollowersCount = twitterFollowersCount;
    }

    public boolean isNewUserCampaignFlg() {
        return newUserCampaignFlg;
    }

    public void setNewUserCampaignFlg(boolean newUserCampaignFlg) {
        this.newUserCampaignFlg = newUserCampaignFlg;
    }

    public boolean isNewUserCampaignExeFlg() {
        return newUserCampaignExeFlg;
    }

    public void setNewUserCampaignExeFlg(boolean newUserCampaignExeFlg) {
        this.newUserCampaignExeFlg = newUserCampaignExeFlg;
    }
}
