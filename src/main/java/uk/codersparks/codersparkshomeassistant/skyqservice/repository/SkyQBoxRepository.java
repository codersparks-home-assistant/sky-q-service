package uk.codersparks.codersparkshomeassistant.skyqservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import uk.codersparks.codersparkshomeassistant.skyqservice.model.SkyQBox;

public interface SkyQBoxRepository extends ReactiveMongoRepository<SkyQBox, String> {
}
