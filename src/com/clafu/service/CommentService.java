package com.clafu.service;

import java.util.Date;

import org.datanucleus.util.StringUtils;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.memcache.Memcache;
import org.slim3.util.StringUtil;

import com.clafu.Constants;
import com.clafu.dao.CommentModelDao;
import com.clafu.exception.DataInputException;
import com.clafu.meta.CommentModelMeta;
import com.clafu.model.CommentModel;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.clafu.utils.Utils;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

/**
 * コメントサービス
 * @author takahara
 *
 */
public class CommentService {
    
    /** CommentModel Dao */
    private static CommentModelDao dao = new CommentModelDao();
    
    /**
     *  コメントリストキャッシュキー
     */
    public static final String COMMENT_LIST_MEMCACHE_KEY = "comment_list_memcache_key";
    
    /**
     * コメント一覧を取得
     * @param userModel
     * @return
     */
    public static S3QueryResultList<CommentModel> getCommentListByContent(ContentModel contentModel, String cursor) {

        S3QueryResultList<CommentModel> list = null;

        if(StringUtils.isEmpty(cursor)) {
            list = Memcache.get(getCommentListMemcacheKey(contentModel));
            if(Utils.isNotEmpty(list)) return list;

            list = dao.getCommentList(contentModel, Constants.CONTENT_COMMENT_LIST_DISPLAY_NUM);
            if(Utils.isNotEmpty(list)) Memcache.put(getCommentListMemcacheKey(contentModel), list);

        }else {
            list = dao.getCommentList(contentModel, Constants.CONTENT_COMMENT_LIST_DISPLAY_NUM, cursor);
            
        }

        return list;
    }
    
    /**
     * PUT
     * @param model
     * @return
     */
    public static CommentModel put(CommentModel model) {
        // 永久化
        dao.put(model);

        return model;
    }
    
    /**
     * コメント追加
     * @param userModel
     * @param comment
     * @return
     * @throws DataInputException
     */
    public static CommentModel add(
            UserModel userModel, 
            ContentModel contentModel, 
            String comment) throws DataInputException {
        
        // 入力チェック
        if(StringUtil.isEmpty(comment)) {
            throw new DataInputException();
        }
        
        CommentModel model = new CommentModel();
        
        // ユーザー情報をコピー
        model.setCreateUserId(userModel.getKey().getId());
        model.setUserName(userModel.getUserName());
        model.setTwitterUserId(userModel.getTwitterUserId());
        model.setComment(new Text(comment));
        
        // ContentModelのRef追加
        model.getContentModelRef().setModel(contentModel);
        
        // 作成日時を設定
        model.setCreateDate(new Date());
        
        // ContentModel との関連
        model.getContentModelRef().setModel(contentModel);
        
        // 追加
        dao.put(model);
        
        // contentModelのコメント数を更新
        contentModel.setCommentCount(contentModel.getCommentCount() + 1);
        ContentService.put(contentModel);
        
        // キャッシュクリア
        Memcache.delete(getCommentListMemcacheKey(contentModel));
        
        return model;
    }
    
    /**
     * コメント削除
     * @param contentModel
     * @param commentId
     */
    public static void delete(ContentModel contentModel, long commentId) {
        Key key = createKey(commentId);
        dao.delete(key);
        
        // contentModelのコメント数を更新
        if(contentModel.getCommentCount() > 0) {
            contentModel.setCommentCount(contentModel.getCommentCount() - 1);
            ContentService.put(contentModel);
        }
        
        // キャッシュクリア
        Memcache.delete(getCommentListMemcacheKey(contentModel));
    }
    
    /**
     * キーの取得
     * @param userID
     * @return
     */
    private static Key createKey(long commentId) {
        return Datastore.createKey(CommentModelMeta.get(), commentId);
    }
    
    /**
     * コメントリストキャッシュキーの取得
     */
    public static String getCommentListMemcacheKey(ContentModel contentModel) {

        return COMMENT_LIST_MEMCACHE_KEY + "_" + contentModel.getKey().getId();
    }

}
