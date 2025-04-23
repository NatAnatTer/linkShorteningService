package org.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping
public class RestApiController {
  //  private final UrlRepository urlRepository;
    private final CutService cutService;



    public RestApiController(UrlService urlService, CutService cutService) {
        this.cutService = cutService;

        this.urlService = urlService;

    }

    @GetMapping("/all")
    Iterable<Links> getLinks() {
        return urlService.getAllLinks();
    }

    @PostMapping("/shorten")
    String postLinks(@RequestBody String originLink) {
        return urlService.cutUrlShorten(originLink);
    }

    @GetMapping("/shorten/{id}")
    Optional<String> getLinksById(@PathVariable String id) {
        // String ids = id.replaceAll("http://localhost:8080/","");
        Optional<Links> l = urlRepository.findById(id);
        return Optional.ofNullable(l.get().getNewUrl());
    }


    @PostMapping("/expand")
    String postLinksExpand(@RequestBody String newLink) {
        Pair<String, String> s = cutService.cutUrlShorten(originLink);
        Links links = new Links(s.getSecond(), originLink, s.getFirst());
        urlRepository.save(links);
        return s.getSecond();
    }

    @GetMapping("/expand/{id}")
    Optional<String> getLinksByIdExpand(@PathVariable String id) {
        Optional<Links> l = urlRepository.findById(id);
        return Optional.ofNullable(l.get().getOriginalUrl());
    }


//    @GetMapping("/abc")
//    String linkOrigin(String newLink) {
//        Iterable<Links> listOfUrl = urlRepository.findAll();
//        String originUrl = newLink;
//        for (Links s: listOfUrl) {
//            if(Objects.equals(s.getNewUrl(), newLink)){
//                originUrl = s.getOriginalUrl();
//            }
//        }
//        return originUrl;
//    }
/*
    @PostMapping("/changelink")
    Links postLinks(@RequestBody OriginLink originLink) {
        String newUrl = cutService.cutUrl(originLink);
        Links links = new Links(originLink.originUrl,newUrl);
        return urlRepository.save(links);
    }*/


//    @PutMapping("/changelink/{id}")
//    ResponseEntity<Links> putLinks(@PathVariable String id, @RequestBody Links links) {
//        return (urlRepository.existsById(id))
//                ? new ResponseEntity<>(urlRepository.save(links), HttpStatus.OK)
//                : new ResponseEntity<>(urlRepository.save(links), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/changelink/{id}")
//    void deleteLinks(@PathVariable String id) {
//        urlRepository.deleteById(id);
//    }

}
