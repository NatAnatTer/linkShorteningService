package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {
    private final UrlRepository urlRepository;

    public DataLoader(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostConstruct
    private void LoadData() {
        urlRepository.saveAll(List.of(
                new Links("fr675", "https://peterhofmuseum.ru", "http://localhost:8080/peterh"),
                new Links("frtdv", "https://peterhofmuseum.ru/objects/peterhof", "http://localhost:8080/History")
        ));

    }
}
