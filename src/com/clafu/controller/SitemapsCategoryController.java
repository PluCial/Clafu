package com.clafu.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class SitemapsCategoryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("sitemapsCategory.jsp");
    }
}
