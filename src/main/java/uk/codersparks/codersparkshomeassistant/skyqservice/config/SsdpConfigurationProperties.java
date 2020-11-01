package uk.codersparks.codersparkshomeassistant.skyqservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "home-assistant.client")
@Data
public class SsdpConfigurationProperties {

    /**
     * Time between requests for update (milliseconds)
     */
    private long requestInterval = 300000L;

    /**
     * Max wait time to await response
     */
    private int maxWaitTime = 30;

    /**
     * Schema event that should be used to identfy box.
     *
     * It's unlikely this is going to need to be changed (so be careful!!!)
     */
    private String identifyingSchema = "urn:schemas-nds-com:service:SkyRC:2";
}
