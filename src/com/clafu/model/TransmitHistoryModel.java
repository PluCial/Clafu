package com.clafu.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@Model(schemaVersion = 1)
public class TransmitHistoryModel implements Serializable {

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
    
    /** twitter User Id(検索用) */
    @Attribute(unindexed = true)
    private String twitterUserId;
    
    /** Twitter Access Token */
    @Attribute(unindexed = true)
    private String twitterAccessToken;
    
    /** Twitter Token Secret */
    @Attribute(unindexed = true)
    private String twitterTokenSecret;
    
    
    
    /** コンテンツタイプカテゴリ(カテゴリ検索用　入力) */
    private Category socialType;
    
    /** コンテンツのURL */
    @Attribute(unindexed = true)
    private Text contentUrl;
    
    /** 登録したターゲットの短縮URL */
    @Attribute(unindexed = true)
    private String targetShortUrl;
    
    /** サービス自身の短縮URL */
    @Attribute(unindexed = true)
    private String pageShortUrl;
    
    /** SNS投稿メッセージ(入力) */
    @Attribute(unindexed = true)
    private Text postMessage;
    
    /** 作成したユーザーのID */
    @Attribute(unindexed = true)
    private long createUserId;
    
    /** 実行日時(入力) */
    private Date exeDate;
    
    /** 終了フラグ */
    private boolean endFlg = false;
    
    /** 作成日 */
    private Date createDate;
    
    // ----------------------------------------------------------------------
    // 関連
    // ----------------------------------------------------------------------
    /** UserModel に対しての関連 */
    private ModelRef<UserModel> userModelRef = new ModelRef<UserModel>(UserModel.class);
    
    /** ContentModel に対しての関連 */
    private ModelRef<ContentModel> contentModelRef = new ModelRef<ContentModel>(ContentModel.class);
    
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
        TransmitHistoryModel other = (TransmitHistoryModel) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public ModelRef<UserModel> getUserModelRef() {
        return userModelRef;
    }

    public ModelRef<ContentModel> getContentModelRef() {
        return contentModelRef;
    }

    public Category getSocialType() {
        return socialType;
    }

    public void setSocialType(Category socialType) {
        this.socialType = socialType;
    }

    public Text getPostMessage() {
        return postMessage;
    }
    
    public String getPostMessageString() {
        if (postMessage == null) {
            return null;
        }
        return postMessage.getValue();
    }

    public void setPostMessage(Text postMessage) {
        this.postMessage = postMessage;
    }

    public Date getExeDate() {
        return exeDate;
    }

    public void setExeDate(Date exeDate) {
        this.exeDate = exeDate;
    }

    public boolean isEndFlg() {
        return endFlg;
    }

    public void setEndFlg(boolean endFlg) {
        this.endFlg = endFlg;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public Text getContentUrl() {
        return contentUrl;
    }
    
    public String getContentUrlString() {
        if (contentUrl == null) {
            return null;
        }
        return contentUrl.getValue();
    }

    public void setContentUrl(Text contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getTargetShortUrl() {
        return targetShortUrl;
    }

    public void setTargetShortUrl(String targetShortUrl) {
        this.targetShortUrl = targetShortUrl;
    }

    public String getPageShortUrl() {
        return pageShortUrl;
    }

    public void setPageShortUrl(String pageShortUrl) {
        this.pageShortUrl = pageShortUrl;
    }
}
