package vn.edu.hust.pthtwat.pthtwat.rest.crawl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.pthtwat.pthtwat.service.CrawlService;

@RestController
public class CrawlResource {

    @Autowired
    private CrawlService crawlService;

    @GetMapping("api/v1/crawl")
    public String createPost(@RequestHeader(name = "Access-Token") String accessToken) {
        String result = crawlService.getPageLinks("https://suckhoedoisong.vn/");

        return result;
    }
}
