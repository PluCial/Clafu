package com.clafu.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
/**
 * コメントモデル
 * @author takahara
 *
 */
@Model(schemaVersion = 1)
public class CommentModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** 作成したユーザーのID */
    @Attribute(unindexed = true)
    private long createUserId;
    
    /** ユーザー名 */
    @Attribute(unindexed = true)
    private String userName;
    
    /** twitter User Id(検索用) */
    @Attribute(unindexed = true)
    private String twitterUserId;

    /** コメント */
    @Attribute(unindexed = true)
    private Text comment;
    
    /** 作成日 */
    private Date createDate;
    
    // ----------------------------------------------------------------------
    // 関連
    // ----------------------------------------------------------------------
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
        CommentModel other = (CommentModel) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
    
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


    public Text getComment() {
        return comment;
    }
    
    public String getCommentString() {
        if (comment == null) {
            return null;
        }
        return comment.getValue();
    }


    public void setComment(Text comment) {
        this.comment = comment;
    }


    public ModelRef<ContentModel> getContentModelRef() {
        return contentModelRef;
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

    public String getTwitterUserId() {
        return twitterUserId;
    }

    public void setTwitterUserId(String twitterUserId) {
        this.twitterUserId = twitterUserId;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }
}
