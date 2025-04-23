package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqids.Sqids;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CutService {
    @Autowired
    // private final UrlRepository urlRepository;
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
        if (urlService.checkNewLink(newUrl)) {
            return urlService.getOriginByNewLink(newUrl);
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
            Sqids sqids = Sqids.builder().build();
            id = sqids.encode(Arrays.asList(1L, 2L, 3L)); // "86Rf07"
            //  List<Long> numbers = sqids.decode(id);
        } while (urlService.isPresentId(id));
        return id;
    }

}


