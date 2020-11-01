package uk.codersparks.codersparkshomeassistant.skyqservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uk.codersparks.codersparkshomeassistant.skyqservice.model.SkyQBox;
import uk.codersparks.codersparkshomeassistant.skyqservice.repository.SkyQBoxRepository;

import java.util.Optional;

@Service
public class SkyQBoxService {

    private static final Logger logger = LoggerFactory.getLogger(SkyQBoxService.class);
    private final SkyQBoxRepository repository;


    public SkyQBoxService(SkyQBoxRepository repository) {
        this.repository = repository;
    }


    @Cacheable("boxes")
    public Optional<SkyQBox> getSkyQBox(String id) {

        logger.debug("Getting box with id: " + id);
        return this.repository.findById(id).blockOptional();
    }

    @CacheEvict("boxes")
    public SkyQBox saveSkyQBox(SkyQBox box) {
        logger.debug("Attempting to save box: " + box);
        return this.repository.save(box).block();
    }

}
