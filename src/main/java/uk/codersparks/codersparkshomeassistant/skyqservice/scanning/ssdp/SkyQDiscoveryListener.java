package uk.codersparks.codersparkshomeassistant.skyqservice.scanning.ssdp;

import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.codersparks.codersparkshomeassistant.skyqservice.model.SkyQBox;
import uk.codersparks.codersparkshomeassistant.skyqservice.service.SkyQBoxService;

import java.net.MalformedURLException;
import java.net.URL;

public class SkyQDiscoveryListener implements DiscoveryListener {

    private static final Logger logger = LoggerFactory.getLogger(SkyQDiscoveryListener.class);

    private final SkyQBoxService service;

    public SkyQDiscoveryListener(SkyQBoxService service) {
        this.service = service;
    }


    @Override
    public void onServiceDiscovered(SsdpService service) {
        logger.debug("Service detected: " + service);
    }

    @Override
    public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {


        try {
            logger.debug("Service Announcement detected: " + announcement);

            String id = announcement.getSerialNumber().split(":")[1];

            SkyQBox box = this.service.getSkyQBox(id).orElse(new SkyQBox(id, null, null));
            logger.debug("Retried box: " + box);
            String ipAddress = new URL(announcement.getLocation()).getHost();
            logger.debug("IP Address from announcement: " + ipAddress);
            if (!ipAddress.equals(box.getIpAddress())) {

                logger.info("Found new ip address: " + ipAddress + " for box: " + box.getId());
                box.setIpAddress(ipAddress);
                this.service.saveSkyQBox(box);
            } else {
                logger.debug("IP Address is the same for box: " + box.getId());
            }

        } catch (MalformedURLException e) {
            logger.error("Malformed announcement location", e);
        }


    }

    @Override
    public void onFailed(Exception ex) {
        logger.error("Discovery listener failed", ex);
    }
}
