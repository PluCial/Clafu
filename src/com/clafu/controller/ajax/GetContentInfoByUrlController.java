package com.clafu.controller.ajax;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

public class GetContentInfoByUrlController extends Controller {

    @Override
    public Navigation run() throws Exception {

        String url = asString("url");

        try {
            Document doc = Jsoup.connect(url).get();

            String title = doc.title();
            String description = getMetaTag(doc, "description");

            String ogpTitle = getMetaTag(doc, "og:title");
            String ogpDescription = getMetaTag(doc, "og:description");
            String ogpImage = getMetaTag(doc, "og:image");

            if(!StringUtil.isEmpty(ogpTitle)) {
                title = ogpTitle;
            }

            if(!StringUtil.isEmpty(ogpDescription)) {
                description = ogpDescription;
            }

            List<String> imagesList = new ArrayList<String>();
            if(!StringUtil.isEmpty(ogpImage)) {
                imagesList.add(ogpImage);
            }

            Elements images = doc.getElementsByTag("img");
            for (Element link : images) {
                imagesList.add(link.attr("src"));
            }

            requestScope("contentTitle", title);
            requestScope("description", description);
            requestScope("imagesList", imagesList);

            return forward("getContentInfoByUrl.jsp");


        }catch(Exception e) {
            return null;
        }
    }
    
    String getMetaTag(Document document, String attr) {
        Elements elements = document.select("meta[name=" + attr + "]");
        for (Element element : elements) {
            final String s = element.attr("content");
            if (s != null) return s;
        }
        elements = document.select("meta[property=" + attr + "]");
        for (Element element : elements) {
            final String s = element.attr("content");
            if (s != null) return s;
        }
        return null;
    }
}
