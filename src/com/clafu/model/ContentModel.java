package com.clafu.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.Sort;

import com.clafu.Constants;
import com.clafu.meta.CommentModelMeta;
import com.clafu.meta.TransmitHistoryModelMeta;
import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Text;

/**
 * コンテンツ
 * @author takahara
 *
 */
@Model(schemaVersion = 1)
public class ContentModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** グループ */
    private int group;
    
    /** コンテンツタイプカテゴリ(カテゴリ検索用　入力) */
    private Category contentCategory;
    
    /** 実行タイプ(入力 暫定Buzz) */
    private Category exeType = new Category(Constants.EXE_TYPE_BUZZ);
    
    /** コンテンツURL(入力) */
    @Attribute(unindexed = true)
    private Text url;
    
    /** 登録したターゲットの短縮URL */
    @Attribute(unindexed = true)
    private String targetShortUrl;
    
    /** サービス自身の短縮URL */
    @Attribute(unindexed = true)
    private String pageShortUrl;
    
    /** 告知タイトル(APIによる自動取得) */
    @Attribute(unindexed = true)
    private String title;
    
    /** コンテンツタイトル(APIによる自動取得) */
    @Attribute(unindexed = true)
    private String contentTitle;
    
    /** コンテンツDescription(APIによる自動取得) */
    @Attribute(unindexed = true)
    private Text contentDescription;
    
    /** 告知説明(入力) */
    @Attribute(unindexed = true)
    private Text explanation;
    
    /** SNS投稿メッセージ(入力) */
    @Attribute(unindexed = true)
    private Text postMessage;
    
    /** プレゼントハート数(入力 暫定１) */
    private int giftHeartCount = 1;
    
    /** バトロンの上限数(入力) */
    @Attribute(unindexed = true)
    private int patronMaxLimit = 0;
    
    /** 参加者のフォロワー数の制限(入力) */
    @Attribute(unindexed = true)
    private int followerMinLimit = 0;
    
    /** 現在のバトロン数(システム入力) */
    @Attribute(unindexed = true)
    private int patronCount = 0;
    
    /** コメント数(システム入力) */
    @Attribute(unindexed = true)
    private int commentCount = 0;
    
    /** リーチ数(システム入力) */
    @Attribute(unindexed = true)
    private int reachCount = 0;
    
    /** 参加者上限達したかどうかのフラグ */
    private boolean patronCountLimitFlg = false;
    
    /** リツイートのためのツイートが既に終わったかフラグ */
    private boolean tweetedFlg = false;
    
    /** ツイートのステータスID */
    @Attribute(unindexed = true)
    private String tweetStatusId;
    
    /** 希望実行日時(入力) */
    private Date wishesExeDate;
    
    /** 作成日時(システム入力) */
    private Date createDate;
    
    /** 終了フラグ(永久かしない) */
    @Attribute(persistent = false)
    private boolean endFlg = false;
    
    /** 参加不可フラグ(既に参加している場合) */
    @Attribute(persistent = false)
    private boolean entered = false;
    
    // ----------------------------------------------------------------------
    // 関連
    // ----------------------------------------------------------------------
    /** 作成者のUserModel に対しての関連 */
    private ModelRef<UserModel> createUserModelRef = new ModelRef<UserModel>(UserModel.class);
    
    /** CommentModelとの関連 */
    @Attribute(persistent = false)
    private InverseModelListRef<CommentModel, ContentModel> commentModelListRef =
            new InverseModelListRef<CommentModel, ContentModel>(
                    CommentModel.class,
                    CommentModelMeta.get().contentModelRef.getName(),
                    this,
                    new Sort(CommentModelMeta.get().createDate, SortDirection.DESCENDING));
    
    /** TransmitHistoryModelとの関連 */
    @Attribute(persistent = false)
    private InverseModelListRef<TransmitHistoryModel, ContentModel> transmitHistoryModelListRef =
            new InverseModelListRef<TransmitHistoryModel, ContentModel>(
                    TransmitHistoryModel.class,
                    TransmitHistoryModelMeta.get().contentModelRef.getName(),
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
        ContentModel other = (ContentModel) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public Category getExeType() {
        return exeType;
    }

    public void setExeType(Category exeType) {
        this.exeType = exeType;
    }

    public Text getUrl() {
        return url;
    }

    public void setUrl(Text url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Text getExplanation() {
        return explanation;
    }
    
    public String getExplanationString() {
        if (explanation == null) {
            return null;
        }
        return explanation.getValue();
    }

    public void setExplanation(Text explanation) {
        this.explanation = explanation;
    }

    public Text getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(Text postMessage) {
        this.postMessage = postMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Category getContentCategory() {
        return contentCategory;
    }

    public void setContentCategory(Category contentCategory) {
        this.contentCategory = contentCategory;
    }

    public int getGiftHeartCount() {
        return giftHeartCount;
    }

    public void setGiftHeartCount(int giftHeartCount) {
        this.giftHeartCount = giftHeartCount;
    }
    
    public ModelRef<UserModel> getCreateUserModelRef() {
        return createUserModelRef;
    }

    public int getPatronCount() {
        return patronCount;
    }

    public void setPatronCount(int patronCount) {
        this.patronCount = patronCount;
    }

    public InverseModelListRef<CommentModel, ContentModel> getCommentModelListRef() {
        return commentModelListRef;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFollowerMinLimit() {
        return followerMinLimit;
    }

    public void setFollowerMinLimit(int followerMinLimit) {
        this.followerMinLimit = followerMinLimit;
    }

    public int getPatronMaxLimit() {
        return patronMaxLimit;
    }

    public void setPatronMaxLimit(int patronMaxLimit) {
        this.patronMaxLimit = patronMaxLimit;
    }

    public Date getWishesExeDate() {
        return wishesExeDate;
    }

    public void setWishesExeDate(Date wishesExeDate) {
        this.wishesExeDate = wishesExeDate;
    }

    public boolean isEndFlg() {
        
        if(exeType.getCategory().equals(Constants.EXE_TYPE_SIMPLE)) {
            if(patronCount >= patronMaxLimit) return true;
            
        }else if(exeType.getCategory().equals(Constants.EXE_TYPE_BUZZ)) {
            long datetime1 = wishesExeDate.getTime();
            long datetime2 = new Date().getTime();
            
            if(datetime1 <= datetime2) return true;
        }
        
        return false;
    }

    public InverseModelListRef<TransmitHistoryModel, ContentModel> getTransmitHistoryModelListRef() {
        return transmitHistoryModelListRef;
    }

    public int getReachCount() {
        return reachCount;
    }

    public void setReachCount(int reachCount) {
        this.reachCount = reachCount;
    }
    
    public String getPostMessageString() {
        if (postMessage == null) {
            return null;
        }
        return postMessage.getValue();
    }
    
    public String getUrlString() {
        if (url == null) {
            return null;
        }
        return url.getValue();
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
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

    public boolean isPatronCountLimitFlg() {
        return patronCountLimitFlg;
    }

    public void setPatronCountLimitFlg(boolean patronCountLimitFlg) {
        this.patronCountLimitFlg = patronCountLimitFlg;
    }

    public String getTweetStatusId() {
        return tweetStatusId;
    }

    public void setTweetStatusId(String tweetStatusId) {
        this.tweetStatusId = tweetStatusId;
    }

    public boolean isTweetedFlg() {
        return tweetedFlg;
    }

    public void setTweetedFlg(boolean tweetedFlg) {
        this.tweetedFlg = tweetedFlg;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public Text getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(Text contentDescription) {
        this.contentDescription = contentDescription;
    }
    
    public String getContentDescriptionString() {
        if (contentDescription == null) {
            return null;
        }
        return contentDescription.getValue();
    }
}
