package com.registry.tenant;

import com.registry.tenant.models.Config;
import com.registry.tenant.utils.ConfigReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * The Tenant Registry Application. The entry point class of application.
 * */
@SpringBootApplication
@RestController
class TenantRegistryApplication {
    public static Logger logger = Logger.getLogger(TenantRegistryApplication.class.getName());
    /**
     * Entrypoint of application.
     *
     * @param args the external args
     * */
    public static void main(final String[] args) throws IOException, SQLException, ClassNotFoundException {
        logger.info("Creating application config bean");
        getConfig();

        SpringApplication.run(TenantRegistryApplication.class, args);
    }

    @Bean
    public static Config getConfig() throws IOException {
        String configFilePath = "src/main/resources/application.yaml";
        return new ConfigReader(configFilePath).readConfig();
    }
}
