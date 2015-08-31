package com.clafu.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.ModelRefAttributeMeta;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.datastore.Sort;
import org.slim3.util.StringUtil;

import com.clafu.meta.CommentModelMeta;
import com.clafu.model.CommentModel;
import com.clafu.model.ContentModel;
import com.google.appengine.api.datastore.Query.SortDirection;

/**
 * コメントモデル Dao クラス
 * @author takahara
 *
 */
public class CommentModelDao extends DaoBase<CommentModel>{
    
    /** コメントMeta */
    CommentModelMeta meta = CommentModelMeta.get();
    
    /**
     * コメント一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<CommentModel> getCommentList(ContentModel contentModel, int num) {
        
        ModelRefAttributeMeta<CommentModel, ModelRef<ContentModel>, ContentModel> refMeta = meta.contentModelRef;

        S3QueryResultList<CommentModel> list = Datastore.query(meta)
                .filter(refMeta.equal(contentModel.getKey()))
                .sort(new Sort(meta.createDate, SortDirection.ASCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * コメント一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<CommentModel> getCommentList(ContentModel contentModel, int num, String cursor) {
        
        if (StringUtil.isEmpty(cursor)) return getCommentList(contentModel, num);
        
        ModelRefAttributeMeta<CommentModel, ModelRef<ContentModel>, ContentModel> refMeta = meta.contentModelRef;

        S3QueryResultList<CommentModel> list = Datastore.query(meta)
                .filter(refMeta.equal(contentModel.getKey()))
                .encodedStartCursor(cursor)
                .sort(new Sort(meta.createDate, SortDirection.ASCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }

}
