package org.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping("/changelink")
public class RestApiController {
    private final UrlRepository urlRepository;
    CutService cutService;

    public RestApiController(UrlRepository urlRepository, CutService cutService) {
        this.urlRepository = urlRepository;
        this.cutService = cutService;
    }


    @GetMapping
    Iterable<Links> getLinks() {
        return urlRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Links> getLinksById(@PathVariable String id) {
        return urlRepository.findById(id);
    }

    @PostMapping
    Links postLinks(@RequestBody OriginLink originLink) {
        String newUrl = cutService.cutUrl(originLink);
        Links links = new Links(originLink.originUrl,newUrl);
        return urlRepository.save(links);
    }

    @PutMapping("/{id}")
    ResponseEntity<Links> putLinks(@PathVariable String id, @RequestBody Links links) {
        return (urlRepository.existsById(id))
                ? new ResponseEntity<>(urlRepository.save(links), HttpStatus.OK)
                : new ResponseEntity<>(urlRepository.save(links), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteLinks(@PathVariable String id) {
        urlRepository.deleteById(id);
    }

}
