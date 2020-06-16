package vn.edu.hust.pthtwat.pthtwat.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;

@Service
public class CrawlService {

    private HashSet<String> links;

    public CrawlService() {
        links = new HashSet<String>();
    }

    public String getPageLinks(String URL) {
        if (!links.contains(URL)) {
            try {
                Document document = Jsoup.connect(URL).get();
//                Elements linksOnPage = document.select("p");

                return document.toString();

            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
                return "";
            }
        } else {
            return URL;
        }
    }

//    public static void main(String[] args) {
//        new CrawlService().getPageLinks("http://vtc.vn/tin-tuc/khong-khi-ha-noi-dat-nguong-nguy-hai-vao-buoi-toi-ar550597.html");
//    }

}