package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OriginLink {
    @Id
    String originUrl;
    String keyWord;


    public OriginLink(String originUrl, String keyWord) {
        this.originUrl = originUrl;
        this.keyWord = keyWord;
    }

    public OriginLink(String originUrl) {
        this.originUrl = originUrl;
        this.keyWord = "";
    }

    public OriginLink() {
    }


    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
