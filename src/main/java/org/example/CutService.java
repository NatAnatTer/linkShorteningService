package org.example;


import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class CutService {


    public static String DEFAULT_URL = "http://localhost:8080"; //TODO link to our service


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

    private String getUid() {
        int length = 4;
        Random r = new Random();
        String s = r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return s;
    }

    private Boolean isExsistLink(String newUrl) {
        //TODO проверить в БД что такой ссылки не существует
        return false;
    }

}
