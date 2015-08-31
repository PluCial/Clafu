package com.clafu.controller;

import org.slim3.controller.router.RouterImpl;

/**
 * 公開部分ルーティング
 * addRoutingメソッドの順番変更は禁止！
 * @author takahara
 *
 */
public class AppRouter extends RouterImpl {

	public AppRouter() {

	    // カテゴリページ
        addRouting(
                "/category/{categoryId}",
                "/contents?category={categoryId}");
        
        // バトロン一覧
        addRouting(
                "/contents/patrons/{contentId}",
                "/patronList?id={contentId}");

	    // コンテンツ詳細ページ
        addRouting(
                "/contents/{contentId}",
                "/contentDetails?id={contentId}");
        
        // ユーザーページ
        addRouting(
                "/user/{userId}",
                "/userPage?userId={userId}");
	}


//	/**
//	 * <pre>
//	 * 拡張子つきURIが静的リクエストと見なされるのを回避するには、
//	 * RouterImpl#IsStatic()をオーバーライドして、処理を記述する。
//	 * </pre>
//	 */
//	@Override
//	public boolean isStatic(String path) throws NullPointerException {
//		boolean isStatic = super.isStatic(path);
//
//		// 複数の拡張子を解除する場合は "||" を使用する。
//		if("html".equals(RequestUtil.getExtension(path)) || "xml".equals(RequestUtil.getExtension(path))) {
//			return false;
//		} else {
//			return isStatic;
//		}
//	}

}
