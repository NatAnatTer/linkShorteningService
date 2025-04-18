package org.example;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.sqids.Sqids;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CutService {
    @Autowired
    private final UrlRepository urlRepository;

    public static String DEFAULT_URL = "http://localhost:8080"; //TODO link to our service

    public CutService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    public Pair<String, String> cutUrl(String originLink) {
        if(urlRepository.findByOriginUrl(originLink).isPresent()){
            Links l = urlRepository.findByOriginUrl(originLink);
            return Pair.of(l.getNewUrl(), l.getId());
        }
        String id = getUid();
        return Pair.of(DEFAULT_URL + "/" + id, id);
    }

    private String getUid() {
        String id = "";
        do {
            Sqids sqids = Sqids.builder().build();
            id = sqids.encode(Arrays.asList(1L, 2L, 3L)); // "86Rf07"
            List<Long> numbers = sqids.decode(id);
        } while (urlRepository.findById(id).isPresent());
        return id;
    }

    private Boolean isExsistLink(String newUrl) {
        //TODO проверить в БД что такой ссылки не существует
        return false;
    }

}



  /*
        int length = 4;
        Random r = new Random();
        String s = r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

         */

  /*
        public String cutUrl(OriginLink originLink) {
            String newUrl = "";
            String newUidLink = "";
            do {
                newUidLink = getUid();
                if (Objects.equals(originLink.keyWord.replaceAll("[\\s|\\u00A0]+", ""), "")) {
                    newUrl = DEFAULT_URL + "/" + newUidLink;
                } else {
                    newUrl = DEFAULT_URL + "/" + newUidLink + "/" + originLink.keyWord.replaceAll("[\\s|\\u00A0]+", "");
                }
            } while (isExsistLink(newUrl));
            return newUrl;
        }
    */