package com.clafu.dao;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.ModelRefAttributeMeta;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.datastore.Sort;
import org.slim3.util.StringUtil;

import com.clafu.meta.TransmitHistoryModelMeta;
import com.clafu.model.ContentModel;
import com.clafu.model.TransmitHistoryModel;
import com.clafu.model.UserModel;
import com.google.appengine.api.datastore.Query.SortDirection;

/**
 * 送信ヒストリ DAO
 * @author takahara
 *
 */
public class TransmitHistoryModelDao extends DaoBase<TransmitHistoryModel>{
    
    /** ヒストリーMeta */
    TransmitHistoryModelMeta meta = TransmitHistoryModelMeta.get();
    
    /**
     * 送信処理対象リストを取得(古い順)
     * @param groupId
     * @return
     */
    public List<TransmitHistoryModel> getPostTargetList(int groupId) {

        List<TransmitHistoryModel> list = Datastore.query(meta)
                .filter(
                    meta.group.equal(groupId)
                    ,meta.exeDate.lessThan(new Date())
                    ,meta.endFlg.equal(false)
                    )
                .sort(new Sort(meta.exeDate, SortDirection.ASCENDING))
                .asList();

        return list;
    }
    
    /**
     * ユーザーがバトロンとなったコンテンツ一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<TransmitHistoryModel> getHistoryListByUser(UserModel userModel, int num) {
        
        ModelRefAttributeMeta<TransmitHistoryModel, ModelRef<UserModel>, UserModel> refMeta = meta.userModelRef;

        S3QueryResultList<TransmitHistoryModel> list = Datastore.query(meta)
                .filter(refMeta.equal(userModel.getKey()))
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * ユーザーがバトロンとなったコンテンツ一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<TransmitHistoryModel> getHistoryListByUser(UserModel userModel, int num, String cursor) {
        
        if (StringUtil.isEmpty(cursor)) return getHistoryListByUser(userModel, num);
        
        ModelRefAttributeMeta<TransmitHistoryModel, ModelRef<UserModel>, UserModel> refMeta = meta.userModelRef;

        S3QueryResultList<TransmitHistoryModel> list = Datastore.query(meta)
                .filter(refMeta.equal(userModel.getKey()))
                .encodedStartCursor(cursor)
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * コンテンツのバトロン一覧を取得
     * @param contentModel
     * @param num
     * @return
     */
    public S3QueryResultList<TransmitHistoryModel> getPatronListByContent(ContentModel contentModel, int num) {
        
        ModelRefAttributeMeta<TransmitHistoryModel, ModelRef<ContentModel>, ContentModel> refMeta = meta.contentModelRef;

        S3QueryResultList<TransmitHistoryModel> list = Datastore.query(meta)
                .filter(refMeta.equal(contentModel.getKey()))
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * コンテンツのバトロン一覧を取得
     * @param contentModel
     * @param num
     * @param cursor
     * @return
     */
    public S3QueryResultList<TransmitHistoryModel> getPatronListByContent(ContentModel contentModel, int num, String cursor) {
        
        if (StringUtil.isEmpty(cursor)) return getPatronListByContent(contentModel, num);
        
        ModelRefAttributeMeta<TransmitHistoryModel, ModelRef<ContentModel>, ContentModel> refMeta = meta.contentModelRef;

        S3QueryResultList<TransmitHistoryModel> list = Datastore.query(meta)
                .filter(refMeta.equal(contentModel.getKey()))
                .encodedStartCursor(cursor)
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * 指定したuserModelとContentModel両方にマッチするリストを取得
     * @param userModel
     * @param contentModel
     * @return
     */
    public List<TransmitHistoryModel> getListByUserAndCountent(UserModel userModel, ContentModel contentModel) {
        ModelRefAttributeMeta<TransmitHistoryModel, ModelRef<UserModel>, UserModel> userRefMeta = meta.userModelRef;
        ModelRefAttributeMeta<TransmitHistoryModel, ModelRef<ContentModel>, ContentModel> contentRefMeta = meta.contentModelRef;
        
        return Datastore.query(meta)
        .filter(
            userRefMeta.equal(userModel.getKey()), 
            contentRefMeta.equal(contentModel.getKey())
            )
        .asList();
        
    }

}
