package uk.codersparks.codersparkshomeassistant.skyqservice.scanning.scheduled;

import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryOptions;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uk.codersparks.codersparkshomeassistant.skyqservice.config.SsdpConfigurationProperties;
import uk.codersparks.codersparkshomeassistant.skyqservice.scanning.ssdp.SkyQDiscoveryListener;

@Component
public class ScanningStartingEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ScanningStartingEventListener.class);

    private final SsdpClient ssdpClient;
    private final SsdpConfigurationProperties properties;
    private final SkyQDiscoveryListener skyQDiscoveryListener;

    public ScanningStartingEventListener(SsdpClient ssdpClient, SsdpConfigurationProperties properties, SkyQDiscoveryListener skyQDiscoveryListener) {
        this.ssdpClient = ssdpClient;
        this.properties = properties;
        this.skyQDiscoveryListener = skyQDiscoveryListener;
    }

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logger.info("Starting SSDP Client");

        DiscoveryOptions options = DiscoveryOptions.builder()
                .intervalBetweenRequests(properties.getRequestInterval())
                .maxWaitTimeSeconds(properties.getMaxWaitTime())
                .build();

        DiscoveryRequest discoveryRequest = SsdpRequest.builder()
                .serviceType(properties.getIdentifyingSchema())
                .discoveryOptions(options)
                .build();

        ssdpClient.discoverServices(discoveryRequest, skyQDiscoveryListener);
    }
}
