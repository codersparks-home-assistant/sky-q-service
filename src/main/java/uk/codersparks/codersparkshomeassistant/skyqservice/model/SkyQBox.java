package uk.codersparks.codersparkshomeassistant.skyqservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkyQBox {

    @Id
    private String id;

    private String friendlyName;

    private String ipAddress;

}
