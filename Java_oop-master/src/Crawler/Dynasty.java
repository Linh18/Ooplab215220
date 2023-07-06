package Crawler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import datamodel.DynastyEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Dynasty extends AbstractCrawler {

    @Override
    public void start() throws Exception {
        int MAX_PAGE = 1000;
        int STEP_PAGE = 5;
        LinkedList<Object> crawler = new LinkedList<>();
        String ROOT_URL = "https://nguoikesu.com";

        for (int i = 0; i <= MAX_PAGE; i += STEP_PAGE) {
            try {
                System.out.println(i);
                String url = ROOT_URL + "/dong-lich-su?start=" + Integer.toString(i);
                Document doc = Jsoup.connect(url).timeout(5000).get();
                for (Element sup : doc.select("sup"))
                    sup.remove();
                Elements eles = doc.select("div.blog-items").select("h2").select("a[href^=/dong-lich-su/]");
                for (org.jsoup.nodes.Element ele : eles) {
                    //Entity Element define here
                    String eName = ele.text();
                    HashMap<String, String> addInfo = new HashMap<String, String>();

                    String infoUrl = ROOT_URL + ele.attr("href");
                    Document infoDoc = Jsoup.connect(infoUrl).timeout(5000).get();

                    Elements infoEles = infoDoc.select("div.item-page").select("h3");

                    Element content = infoDoc.selectFirst("div.com-content-article__body");

                    StringBuilder description = new StringBuilder();
                    String whitespace = "    ";

                    Elements childs = content.children();
                    HashSet<Element> notValidTags = new HashSet<Element>();
                    for (Element child : childs) {
                        String type = child.tagName();
                        switch (type) {
                                case "figure":
                            case "table":
                               
                                if (child.hasClass("cquote")) {
                                    break;
                                }
                            case "div":
                                notValidTags.add(child);
                                break;
                        }
                    }
                    
                    for (Element child : notValidTags) {
                        child.remove();
                    }
                    int index_h2 = 0;
                    int index_h3 = 1;

                    // Thêm các nội dung có thể thực sự trình bày
                    childs = content.children();
                    int nChilds = childs.size();
                    for (int i2 = 0; i2 < nChilds; i2++) {
                        Element child = childs.get(i2);
                        if (child.text().length() == 0) {
                            continue;
                        }

                        String type = child.tagName();

                        switch (type) {
                            case "d1":
                            case "p":
                                description.append(whitespace).append(child.text()).append("\n\n");
                            case "table":
                                if (child.hasClass("cquote")) {
                                    description.append(whitespace).append(whitespace).append(child.text()).append("\n\n");
                                }
                                break;
                            // Đầu đề lớn
                            case "h2":
                                if (i2 == nChilds - 1 || childs.get(i2 + 1).tagName() == "h2")
                                    break;
                                index_h2++;
                                
                                description.append(index_h2).append(".").append(" ").append(child.text()).append("\n\n");
                                index_h3 = 1;
                                break;
                            // Đầu đề nhỏ
                            case "h3":
                                description.append(index_h2).append(".").append(index_h3).append(".").append(" ").append(child.text()).append("\n\n");
                                index_h3++;
                                break;
                            // Danh sách
                            case "ul":
                                if (child.classNames().size() == 0) {
                                    for (Element e : child.select("li")) {
                                        description.append(whitespace).append(whitespace).append("+ ").append(e.text()).append("\n\n");
                                    }
                                }
                                break;
                        }
                    }
                    crawler.add(new DynastyEntity(eName, addInfo, description.toString(), ROOT_URL));
                    System.out.println("Crawl completed successfully!"+ eName+description );
                    //System.out.println(crawled);
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }

        }
    }

    public static void main(String[] args) {
        Dynasty dynasty = new Dynasty();
        try {
            dynasty.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}