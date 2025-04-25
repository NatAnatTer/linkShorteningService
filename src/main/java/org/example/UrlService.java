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

    public Optional<Links> getLinkById(String id) {
        return urlRepository.findById(id);
    }


    public boolean checkLink(String checkedLink) {
        Optional<Links> link = urlRepository.findByOriginalUrl(checkedLink);
        return link.isPresent();
    }
    public boolean checkNewLink(String checkedLink) {
        Optional<Links> link = urlRepository.findByNewUrl(checkedLink);
        return link.isPresent();

    }

    public String getShortLinkByOrigin(String originLink) {
        Optional<Links> l = urlRepository.findByOriginalUrl(originLink);
        return l.get().getNewUrl();
    }

    public String getOriginByNewLink(String newLink){
        Optional<Links> l = urlRepository.findByNewUrl(newLink);
        return l.get().getOriginalUrl();
    }

    public Boolean isPresentId(String id) {
       Optional<Links> link = urlRepository.findById(id);
        return link.isPresent();

    }

}
