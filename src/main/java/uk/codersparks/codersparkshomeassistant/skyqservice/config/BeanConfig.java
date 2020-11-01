package uk.codersparks.codersparkshomeassistant.skyqservice.config;

import io.resourcepool.ssdp.client.SsdpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.codersparks.codersparkshomeassistant.skyqservice.scanning.ssdp.SkyQDiscoveryListener;
import uk.codersparks.codersparkshomeassistant.skyqservice.service.SkyQBoxService;

@Configuration
public class BeanConfig {

    private final SsdpConfigurationProperties properties;

    public BeanConfig(SsdpConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public SsdpClient ssdpClient() {

        return SsdpClient.create();
    }

    @Bean
    public SkyQDiscoveryListener skyQDiscoveryListener(SkyQBoxService service) {
        return new SkyQDiscoveryListener(service);
    }


}
