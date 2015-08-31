package com.clafu.service;

import org.slim3.datastore.Datastore;

import com.clafu.dao.SpamUserModelDao;
import com.clafu.meta.SpamUserModelMeta;
import com.clafu.model.SpamUserModel;
import com.google.appengine.api.datastore.Key;


public class SpamUserService {
    
    private static SpamUserModelDao dao = new SpamUserModelDao();
    
    /**
     * 
     * @param id
     * @return
     */
    public static SpamUserModel getOrNull(String id) {
        
        Key key = createKey(id);

        return dao.getOrNull(key);
    }
    
    /**
     * PUT
     * @param model
     * @return
     */
    public static SpamUserModel add(String userId, String userName) {
        
        SpamUserModel model = new SpamUserModel();
        model.setKey(createKey(userId));
        model.setUserName(userName);
        
        // 永久化
        dao.put(model);

        return model;
    }
    
    /**
     * キーの取得
     * @param userID
     * @return
     */
    private static Key createKey(String userID) {
        return Datastore.createKey(SpamUserModelMeta.get(), userID);
    }

}
