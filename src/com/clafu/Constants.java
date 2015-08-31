package com.clafu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clafu.model.CategoryModel;

public class Constants {
    
    // ---------------------------------------------------------------------------
    // GOOGLE PROJECT 定数
    // ---------------------------------------------------------------------------
    public static final String GOOGLE_APPLICATION_NAME = "Clafu";
    
    // GCS
    public static final String GCS_USER_PROFILE_PUBLIC_URL_BASE = "http://storage.googleapis.com/clafu-production.appspot.com/profile_image/";
    
    
    // p12 password:notasecret
    public static final String GOOGLE_SERVICE_ACCOUNT_EMAIL = "693754283624-o682nia66spbp95fkr018isj8c8oopql@developer.gserviceaccount.com";
    public static final String GOOGLE_SERVICE_ACCOUNT_PKCS12_FILE_PATH = "WEB-INF/clafu-production-326bed257bc9.p12";
    
    // ---------------------------------------------------------------------------
    // Twitter 定数
    // ---------------------------------------------------------------------------
    /** Twitter App API Key */
    public static final String TWITTER_APP_API_KEY = "JNdhz1oOMFSqfMoyfHvxOLoGv";

    /** Twitter App API secret */
    public static final String TWITTER_APP_API_SECRET = "VRrbfEvT4NTiVi6pSeBYxG89BsA0OzD6UFz5rAd8sRBfOcLmnc";
    
    // ---------------------------------------------------------------------------
    // ソーシャルタイプ
    // ---------------------------------------------------------------------------
    /** シンプルタイプ(リアルタイム配信) */
    public static final String SOCIAL_TYPE_TWITTER = "twitter";
    /** Buzzタイプ(設定した日時になるとまとめて配信) */
    public static final String SOCIAL_TYPE_FACEBOOK = "facebook";
    
    // ---------------------------------------------------------------------------
    // 実行タイプ
    // ---------------------------------------------------------------------------
    /** シンプルタイプ(リアルタイム配信) */
    public static final String EXE_TYPE_SIMPLE = "simple";
    /** Buzzタイプ(設定した日時になるとまとめて配信) */
    public static final String EXE_TYPE_BUZZ = "buzz";
    
    
    
    // ---------------------------------------------------------------------------
    // List 表示数
    // ---------------------------------------------------------------------------
    /** コンテンツリストの表示件数 */
    public static final int CONTENT_LIST_DISPLAY_NUM = 10;
    
    /** ユーザー作成したコンテンツリストの表示件数 */
    public static final int USER_CREATE_CONTENT_LIST_DISPLAY_NUM = 10;
    
    /** ユーザーが参加したコンテンツリストの表示数 */
    public static final int USER_HISTORY_LIST_DISPLAY_NUM = 10;
    
    /** ユーザーが参加したコンテンツリストの表示数 */
    public static final int CONTENT_PATRON_LIST_DISPLAY_NUM = 5;
    
    /** コンテンツのコメントリストの表示数 */
    public static final int CONTENT_COMMENT_LIST_DISPLAY_NUM = 10;
    
    // ---------------------------------------------------------------------------
    // マイページナビー
    // ---------------------------------------------------------------------------
    /** 作成履歴 */
    public static final String MY_PAGE_NAV_TYPE_CREATE_HISTORY = "create_history";
    /** 参加履歴 */
    public static final String MY_PAGE_NAV_TYPE_PATRON_HISTORY = "patron_history";
    
    // ---------------------------------------------------------------------------
    // もっと読むのコンテンツタイプ
    // ---------------------------------------------------------------------------
    /** 新着 */
    public static final String READ_MORE_CONTENT_TYPE_NEW = "new";
    /** 配信直前 */
    public static final String READ_MORE_CONTENT_TYPE_EXE_DATE_LIMIT = "exeDateLimit";
    /** カテゴリ */
    public static final String READ_MORE_CONTENT_TYPE_CATEGORY = "category";
    /** マイ */
    public static final String READ_MORE_CONTENT_TYPE_MY = "my";
    /** パトロン */
    public static final String READ_MORE_CONTENT_TYPE_PATRON = "patron";
    
    // ---------------------------------------------------------------------------
    // マイページナビー
    // ---------------------------------------------------------------------------
    /** カテゴリリスト */
    public static final List<CategoryModel> CATEGORY_LIST = new ArrayList<CategoryModel>(
            Arrays.asList(
                new CategoryModel("socialcontribution", "社会貢献"),
                new CategoryModel("technology", "IT・テクノロジー"),
                new CategoryModel("lifestyle", "生活・ライフスタイル"),
                new CategoryModel("beauty", "美容・ビューティ"),
                new CategoryModel("sports", "スポーツ"),
                new CategoryModel("business", "ビジネス"),
                new CategoryModel("creative", "クリエイティブ"),
                new CategoryModel("recipe", "料理・レシピ"),
                new CategoryModel("hobby", "趣味・娯楽"),
                new CategoryModel("health", "健康・医療"),
                new CategoryModel("education", "教育・学習"),
                new CategoryModel("surprise", "サプライズ"),
                new CategoryModel("etc", "その他")
           ));
    
    public static final Map<String, String> CATEGORY_MAP = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;

        {
            put("socialcontribution", "社会貢献");
            put("technology", "IT・テクノロジー");
            put("lifestyle", "生活・ライフスタイル");
            put("beauty", "美容・ビューティ");
            put("sports", "スポーツ");
            put("business", "ビジネス");
            put("creative", "クリエイティブ");
            put("recipe", "料理・レシピ");
            put("hobby", "趣味・娯楽");
            put("health", "健康・医療");
            put("education", "教育・学習");
            put("surprise", "サプライズ");
            put("etc", "その他");
        }
    };
    
}
