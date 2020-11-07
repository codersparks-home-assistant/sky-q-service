package uk.codersparks.codersparkshomeassistant.skyqservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableCaching
public class SkyQServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyQServiceApplication.class, args);
    }

}
