
package com.gabriel.controller;

import com.gabriel.model.FeedItem;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author gabriel
 */
@Controller
public class ApplicationController {
    
    
    @RequestMapping("/")
    public String index(Model model) {
        String url = "http://feeds.kauppalehti.fi/rss/topic/finanssiala";
        SyndFeed feed = fetchFeed(url);
        List<FeedItem> items = new ArrayList();
        for (SyndEntry entry : feed.getEntries()) {
            FeedItem item = new FeedItem();
            item.setDescription(entry.getDescription().getValue());
            item.setCategory(entry.getCategories().get(0).getName());
            item.setLink(entry.getLink());
            item.setTitle(entry.getTitle());
            item.setPublicationDate(entry.getPublishedDate());
            items.add(item);
        }
        Collections.sort(items, (i1, i2) -> i2.getDisplayDate().compareTo(i1.getDisplayDate()));
        model.addAttribute("items", items);
        return "index";
    }
    
    private SyndFeed fetchFeed(String url) {
         try {
            URL feedUrl = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            return input.build(new XmlReader(feedUrl));
        } catch (Exception e) {
            throw new RuntimeException("Failed fetching feed");
        } 
    }
    
}
