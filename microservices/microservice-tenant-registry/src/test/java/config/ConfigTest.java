package config;

import com.registry.tenant.models.Config;
import com.registry.tenant.utils.ConfigReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class ConfigTest {
    /**
     * test application config file path.
     * */
    private final String configFilePath = "src/test/resources/application.yaml";

    /**
     * Test configreader method.
     * */
    @Test
    public void testConfig() throws IOException {
        Config config = new ConfigReader(configFilePath).readConfig();

        assertEquals("https://dummy-url.com", config.getDatabase().getUrl());
        assertEquals("microservice-tenant-registry",
                config.getDatabase().getUsername());
        assertEquals("myDummyPass321", config.getDatabase().getPassword());
    }
}
