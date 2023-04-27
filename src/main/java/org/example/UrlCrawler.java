package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UrlCrawler {
    private String url;
    private ArrayList<String> links;
    public ArrayList<String> linkCrawl(ArrayList<String> links){
        this.links= links;
        try{
            Connection con = Jsoup.connect(this.url);
            Document doc = con.get();
            if(con.response().statusCode() == 200){
                Collection<Element> elements = doc.getElementsByClass("body container");
                for (Element element : elements){
                    Collection<Element> gridHighlights = element.getElementsByClass("grid highlight");
                    for (Element gridHighlight: gridHighlights) {
                        Collection<Element> articleWraps = gridHighlight.getElementsByClass("article-wrap");
                        for (Element articleWrap : articleWraps){
                            Collection<Element> articleGrids = articleWrap.getElementsByClass("article grid");
                            for(Element articleGrid : articleGrids){
                                Collection<Element> articleItems = articleGrid.getElementsByClass("article-item");
                                for (Element articleItem : articleItems){
                                    Collection<Element> articleThumbs = articleItem.getElementsByClass("article-thumb");
                                    for (Element articleThumb : articleThumbs ){
                                        String link = articleThumb.select("a").attr("href");
                                        links.add(link);
//                                        System.out.println("Link: " +"https://dantri.com.vn" + link);
                                    }
                                }
                            }
                        }
                        for(Element articleWrap : articleWraps){
                            Collection<Element> articleColumns = articleWrap.getElementsByClass("article column");
                            for(Element articleColumn : articleColumns){
                                Collection<Element> articleItems = articleColumn.getElementsByClass("article-item");
                                for (Element articleItem : articleItems){
                                    Collection<Element> articleThumbs = articleItem.getElementsByClass("article-thumb");
                                    for (Element articleThumb : articleThumbs ){
                                        String link = articleThumb.select("a").attr("href");
                                        links.add(link);
//                                        System.out.println("Link: " + "https://dantri.com.vn" + link);
                                    }
                                }
                            }
                        }
                    }
                }
                for (Element element : elements){
                    Collection<Element> gridLists = element.getElementsByClass("grid list");
                    for(Element gridList : gridLists){
                        Collection<Element> mains = gridList.getElementsByClass("main");
                        for (Element main : mains){
                            Collection<Element> articleLists = main.getElementsByClass("article list");
                            for(Element articleList : articleLists){
                                Collection<Element> articleItems = articleList.getElementsByClass("article-item");
                                for (Element articleItem : articleItems){
                                    Collection<Element> articleThumbs = articleItem.getElementsByClass("article-thumb");
                                    for (Element articleThumb : articleThumbs ){
                                        String link = articleThumb.select("a").attr("href");
                                        links.add(link);
//                                        System.out.println("Link: " + "https://dantri.com.vn" + link);
                                    }
                                }
                            }
                        }
                    }
                }
                return links;
            }

        }catch (IOException e){
            return null;
        }
        return null;
    }
}
