package com.registry.tenant;

import com.registry.tenant.models.Config;
import com.registry.tenant.utils.ConfigReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
/**
 * The Tenant Registry Application. The entry point class of application.
 * */
@SpringBootApplication
@RestController
class TenantRegistryApplication {
    /**
     * Entrypoint of application.
     *
     * @param args
     * */
    public static void main(final String[] args) throws IOException {
        String configFilePath = "src/main/resources/application.yaml";
        Config config = new ConfigReader(configFilePath).readConfig();

        SpringApplication.run(TenantRegistryApplication.class, args);
    }
}
