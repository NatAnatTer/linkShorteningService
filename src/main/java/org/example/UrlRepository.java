package org.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Links, String> {

     Optional<Links> findByOriginUrl(String originUrl);
     Optional<Links> findByShortUrl(String shortUrl);
}
