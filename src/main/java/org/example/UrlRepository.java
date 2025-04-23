package org.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Links, String> {

     Optional<Links> findByOriginalUrl(String originalUrl);
     Optional<Links> findByNewUrl(String newUrl);
     Boolean checkByOriginalUrl(String originalUrl);
     Boolean checkIfExistId(String id);

     Boolean checkByNewUrl(String newUrl);

}
