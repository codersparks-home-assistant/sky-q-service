package uk.codersparks.codersparkshomeassistant.skyqservice.config;

import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.codersparks.codersparkshomeassistant.skyqservice.scanning.ssdp.SkyQDiscoveryListener;
import uk.codersparks.codersparkshomeassistant.skyqservice.service.SkyQBoxService;

@Configuration
public class BeanConfig {

    @Bean
    public SsdpClient ssdpClient() {
        return SsdpClient.create();
    }

    @Bean
    public DiscoveryListener skyQDiscoveryListener(SkyQBoxService service) {
        return new SkyQDiscoveryListener(service);
    }


}
