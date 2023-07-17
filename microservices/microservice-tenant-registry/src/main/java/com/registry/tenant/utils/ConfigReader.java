package com.registry.tenant.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.registry.tenant.models.Config;

import java.io.File;
import java.io.IOException;

/**
 * Class ConfigReader. Used to read application config file.
 *
 * */
public class ConfigReader {
    /**
     * The variable path.
     * */
    private String path;

    /**
     * Constructor for ConfigReader without parameters
     * */
    public ConfigReader() { }

    /**
     * Constructor.
     *
     * @param pathValue
     * */
    public ConfigReader(String pathValue) {
        this.path = pathValue;
    }

    /**
     * Returns the path.
     *
     * @return path
     * */
    public String getPath() {
        return path;
    }

    /**
     * Read application.yaml config file.
     *
     * @return Config
     * */
    public Config readConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        Config config = mapper.readValue(new File(this.path), Config.class);
        return config;
    }
}
