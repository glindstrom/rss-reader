
package com.gabriel.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gabriel
 */
public class Feed {
    
    private final String url;
    private Date lastUpdated;

    public Feed(String url) {
        this.url = url;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.url);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Feed other = (Feed) obj;
        return Objects.equals(this.url, other.url);
    }
    
    

}
