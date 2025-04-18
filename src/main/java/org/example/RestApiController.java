package org.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping
public class RestApiController {
    private final UrlRepository urlRepository;
    CutService cutService;

    public RestApiController(UrlRepository urlRepository, CutService cutService) {
        this.urlRepository = urlRepository;
        this.cutService = cutService;
    }


    @GetMapping("/changelink")
    Iterable<Links> getLinks() {
        return urlRepository.findAll();
    }


    @GetMapping("/abc")
    String linkOrigin(String newLink) {
        Iterable<Links> listOfUrl = urlRepository.findAll();
        String originUrl = newLink;
        for (Links s: listOfUrl) {
            if(Objects.equals(s.getNewUrl(), newLink)){
                originUrl = s.getOriginalUrl();
            }
        }
        return originUrl;
    }


    @GetMapping("/changelink/{id}")
    Optional<Links> getLinksById(@PathVariable String id) {
        return urlRepository.findById(id);
    }
/*
    @PostMapping("/changelink")
    Links postLinks(@RequestBody OriginLink originLink) {
        String newUrl = cutService.cutUrl(originLink);
        Links links = new Links(originLink.originUrl,newUrl);
        return urlRepository.save(links);
    }*/
    @PostMapping("/changelink")
    Links postLinks(@RequestBody String originLink) {
        Pair <String, String> s = cutService.cutUrl(originLink);
        Links links = new Links(s.getSecond(), originLink,s.getFirst());
        return urlRepository.save(links);
    }

    @PutMapping("/changelink/{id}")
    ResponseEntity<Links> putLinks(@PathVariable String id, @RequestBody Links links) {
        return (urlRepository.existsById(id))
                ? new ResponseEntity<>(urlRepository.save(links), HttpStatus.OK)
                : new ResponseEntity<>(urlRepository.save(links), HttpStatus.CREATED);
    }

    @DeleteMapping("/changelink/{id}")
    void deleteLinks(@PathVariable String id) {
        urlRepository.deleteById(id);
    }

}
