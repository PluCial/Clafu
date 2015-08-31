package com.clafu.dao;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.CoreAttributeMeta;
import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.ModelRefAttributeMeta;
import org.slim3.datastore.S3QueryResultList;
import org.slim3.datastore.Sort;
import org.slim3.util.StringUtil;

import com.clafu.Constants;
import com.clafu.meta.ContentModelMeta;
import com.clafu.model.ContentModel;
import com.clafu.model.UserModel;
import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.Query.SortDirection;

/**
 * コンテンツ DAO クラス
 * @author takahara
 *
 */
public class ContentModelDao extends DaoBase<ContentModel>{
    
    /** コンテンツMeta */
    ContentModelMeta meta = ContentModelMeta.get();
    
    /**
     * コンテンツ一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<ContentModel> getContentList(int num) {

        S3QueryResultList<ContentModel> list = Datastore.query(meta)
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * コンテンツ一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<ContentModel> getContentList(int num, String cursor) {
        
        if (StringUtil.isEmpty(cursor)) return getContentList(num);

        S3QueryResultList<ContentModel> list = Datastore.query(meta)
                .encodedStartCursor(cursor)
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    
    
    /**
     * コンテンツ一覧を取得(配信直前告知)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<ContentModel> getExeDateLimitContentList(int num) {

        S3QueryResultList<ContentModel> list = Datastore.query(meta)
                .filter(
                    meta.wishesExeDate.greaterThan(new Date()), 
                    meta.patronCountLimitFlg.equal(false),
                    meta.exeType.equal(new Category(Constants.EXE_TYPE_BUZZ)))
                .sort(new Sort(meta.wishesExeDate, SortDirection.ASCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * コンテンツ一覧を取得(配信直前告知)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<ContentModel> getExeDateLimitContentList(int num, String cursor) {
        
        if (StringUtil.isEmpty(cursor)) return getExeDateLimitContentList(num);

        S3QueryResultList<ContentModel> list = Datastore.query(meta)
                .filter(
                    meta.wishesExeDate.greaterThan(new Date()),
                    meta.patronCountLimitFlg.equal(false),
                    meta.exeType.equal(new Category(Constants.EXE_TYPE_BUZZ)))
                .encodedStartCursor(cursor)
                .sort(new Sort(meta.wishesExeDate, SortDirection.ASCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * ユーザーが作成したコンテンツ一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<ContentModel> getCreateContentListByUser(UserModel userModel, int num) {
        
        ModelRefAttributeMeta<ContentModel, ModelRef<UserModel>, UserModel> refMeta = meta.createUserModelRef;

        S3QueryResultList<ContentModel> list = Datastore.query(meta)
                .filter(refMeta.equal(userModel.getKey()))
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * ユーザーが作成したコンテンツ一覧を取得(新しい順)
     * @param userModel
     * @param num
     * @return
     */
    public S3QueryResultList<ContentModel> getCreateContentListByUser(UserModel userModel, int num, String cursor) {
        
        if (StringUtil.isEmpty(cursor)) return getCreateContentListByUser(userModel, num);

        ModelRefAttributeMeta<ContentModel, ModelRef<UserModel>, UserModel> refMeta = meta.createUserModelRef;

        S3QueryResultList<ContentModel> list = Datastore.query(meta)
                .filter(refMeta.equal(userModel.getKey()))
                .encodedStartCursor(cursor)
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * コンテンツカテゴリからコンテンツ一覧を取得(新しい順)
     * @param contentCategory
     * @param num
     * @return
     */
    public S3QueryResultList<ContentModel> getContentByCategory(String contentCategory, int num) {

        CoreAttributeMeta<ContentModel,Category> categoryMeta = meta.contentCategory;

        S3QueryResultList<ContentModel> list = Datastore.query(meta)
                .filter(
                    categoryMeta.equal(new Category(contentCategory)))
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * コンテンツカテゴリからコンテンツ一覧を取得(新しい順)
     * @param contentCategory
     * @param num
     * @return
     */
    public S3QueryResultList<ContentModel> getContentByCategory(String contentCategory, int num, String cursor) {
        
        if (StringUtil.isEmpty(cursor)) return getContentByCategory(contentCategory, num);

        CoreAttributeMeta<ContentModel,Category> categoryMeta = meta.contentCategory;

        S3QueryResultList<ContentModel> list = Datastore.query(meta)
                .filter(
                    categoryMeta.equal(new Category(contentCategory)))
                .encodedStartCursor(cursor)
                .sort(new Sort(meta.createDate, SortDirection.DESCENDING))
                .limit(num)
                .asQueryResultList();

        return list;
    }
    
    /**
     * コンテンツ一覧を取得(サイトマップ用)
     * @param List<ContentModel>
     * @param num
     * @return
     */
    public List<ContentModel> getContentListByGroup(int groupNum) {

        List<ContentModel> list = Datastore.query(meta)
                .filter(meta.group.equal(groupNum))
                .asList();

        return list;
    }
    
    /**
     * 送信処理対象リストを取得(自分自身の告知をツイート)
     * @param groupId
     * @return
     */
    public List<ContentModel> getPostTargetList() {

        List<ContentModel> list = Datastore.query(meta)
                .filter(
                    meta.wishesExeDate.lessThan(new Date())
                    ,meta.tweetedFlg.equal(false)
                    )
                .sort(new Sort(meta.wishesExeDate, SortDirection.ASCENDING))
                .asList();

        return list;
    }

}
