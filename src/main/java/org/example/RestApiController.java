package org.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping
public class RestApiController {
    private final CutService cutService;

    public RestApiController(CutService cutService) {
        this.cutService = cutService;
    }

    @GetMapping("/all")
    Iterable<Links> getLinks() {
        return cutService.getAllLinks();
    }

    @PostMapping("/shorten")
    String postLinks(@RequestBody String originLink) {
        return cutService.cutUrlShorten(originLink);
    }

    @GetMapping("/shorten/{id}")
    Optional<String> getLinksById(@PathVariable String id) {
        return Optional.ofNullable(cutService.getLinksByIdShorten(id));
    }


    @PostMapping("/expand")
    String postLinksExpand(@RequestBody String newLink) {
        return cutService.cutUrlExpanded(newLink);
    }

    @GetMapping("/{id}")
    Optional<String> getLinksByIdExpand(@PathVariable String id) {
        return Optional.ofNullable(cutService.getLinksByIdExpanded(id));
    }

}
