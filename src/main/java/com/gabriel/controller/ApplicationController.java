package com.gabriel.controller;

import com.gabriel.model.Feed;
import com.gabriel.model.FeedItem;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author gabriel
 */
@Controller
public class ApplicationController {
    
    private static final long FEED_MAX_AGE_MILLISECONDS = 6000;
    private final Map<Feed, List<FeedItem>> feedCache = new ConcurrentHashMap();

    public ApplicationController() {
        this.feedCache.put(new Feed("http://feeds.kauppalehti.fi/rss/topic/finanssiala"), new ArrayList());
        this.feedCache.put(new Feed("http://feeds.kauppalehti.fi/rss/topic/digitalous"), new ArrayList());
        this.feedCache.put(new Feed("http://feeds.kauppalehti.fi/rss/topic/johdannaiset"), new ArrayList());
        this.feedCache.put(new Feed("http://feeds.kauppalehti.fi/rss/topic/kansantalous"), new ArrayList());
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Model model) {
        updateCache();
        List<FeedItem> items = new ArrayList();
        this.feedCache.keySet().forEach(f -> items.addAll(this.feedCache.get(f)));
        Collections.sort(items, (i1, i2) -> i2.getPublicationDate().compareTo(i1.getPublicationDate()));
        model.addAttribute("items", items);
        return "index";
    }
    
    private void updateCache() {
        Date currentDate = new Date();
        for (Feed feed : this.feedCache.keySet()) {
            Date feedLastUpdated = feed.getLastUpdated();
            if (feedLastUpdated == null 
                    || (currentDate.getTime() - feedLastUpdated.getTime() > FEED_MAX_AGE_MILLISECONDS)) {
                this.feedCache.put(feed, parseFeedItems(fetchFeed(feed.getUrl())));
                feed.setLastUpdated(currentDate);
                System.out.println("Data fetched");
            } else {
                System.out.println("Cache used");
            }
        }
    }
    
    private List<FeedItem> parseFeedItems(SyndFeed feed) {
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
        return items;
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
