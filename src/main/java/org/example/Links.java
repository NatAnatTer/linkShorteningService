package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity

public class Links {
    @Id
    private String id;
    private String originalUrl;
    private String newUrl;

    public Links( String originalUrl, String newUrl) {
        this.id = UUID.randomUUID().toString();
        this.originalUrl = originalUrl;
        this.newUrl = newUrl;
    }

    public Links(){}

    public String getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }
}
