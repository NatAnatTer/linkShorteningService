package org.example;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Links saveLInk(String id, String originUrl, String newUrl) {
        Links link = new Links(id, originUrl, newUrl);
        return urlRepository.save(link);
    }

    public Iterable<Links> getAllLinks() {
        return urlRepository.findAll();
    }

    public Optional<Links> findLinkById(String id) {
        return urlRepository.findById(id);
    }


    public boolean checkLink(String checkedLink) {

        return urlRepository.checkByOriginalUrl(checkedLink);

    }

    public String getShortLinkByOrigin(String originLink) {
        Optional<Links> l = urlRepository.findByOriginalUrl(originLink);
        return l.get().getNewUrl();
    }

    public Boolean isPresentId(String id) {
        return urlRepository.checkIfExistId(id);
    }

}
