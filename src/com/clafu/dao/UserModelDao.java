package com.clafu.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.clafu.meta.UserModelMeta;
import com.clafu.model.UserModel;

public class UserModelDao extends DaoBase<UserModel>{
    
    /** UserModel Meta */
    UserModelMeta meta = UserModelMeta.get();
    
    /**
     * Twitter User Id からユーザーモデルを取得
     * @param twitterUserId
     * @return
     */
    public UserModel getUserModelByTwitterId(String twitterUserId) {
        return Datastore.query(meta)
                .filter(meta.twitterUserId.equal(twitterUserId))
                .asSingle();
    }

}
