package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqids.Sqids;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class CutService {
    @Autowired
    private final UrlService urlService;

    public static String DEFAULT_URL = "http://localhost:8080"; //TODO link to our service

    public CutService(UrlService urlService) {
        this.urlService = urlService;
    }

    public Iterable<Links> getAllLinks() {
        return urlService.getAllLinks();
    }

    public String cutUrlShorten(String originLink) {
        if (urlService.checkLink(originLink)) {
            return urlService.getShortLinkByOrigin(originLink);
        } else {
            String id = getUid();
            String newUrl = DEFAULT_URL + "/" + id;
            urlService.saveLInk(id, originLink, newUrl);
            return newUrl;
        }
    }

    public String cutUrlExpanded(String newUrl) {
        String newUrlPrepare = newUrl.replace("\"", "");
        if (urlService.checkNewLink(newUrlPrepare)) {
            return urlService.getOriginByNewLink(newUrlPrepare);
        } else {
            return "";
        }

    }

    public String getLinksByIdShorten(String id){
        Optional<Links> l = urlService.getLinkById(id);
        return l.get().getNewUrl();
    }

    public String getLinksByIdExpanded(String id){
        Optional<Links> l = urlService.getLinkById(id);
        return l.get().getOriginalUrl();
    }

    private String getUid() {
        String id = "";
        do {
            String uid = UUID.randomUUID().toString();
            id = uid.split("-")[0];
        } while (urlService.isPresentId(id));
        return id;
    }

}


