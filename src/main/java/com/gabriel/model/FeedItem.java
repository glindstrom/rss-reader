
package com.gabriel.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gabriel
 */
public class FeedItem {
    
    private String description;
    private String link;
    private String title;
    private Date publicationDate;
    private String category;

    public FeedItem() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getDisplayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH:mm");
        return sdf.format(publicationDate);
    }
   
}
