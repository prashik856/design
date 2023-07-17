package com.registry.tenant.models;

public class Config {
    /**
     * database attribute.
     * */
    private Database database;

    /**
     * Config Constructor without any parameters.
     * */
    public Config() { }

    /**
     * Config Constructor.
     *
     * @param databaseValue
     * */
    public Config(Database databaseValue) {
        this.database = databaseValue;
    }

    /**
     * Returns database attribute.
     *
     * @return database
     * */
    public Database getDatabase() {
        return database;
    }

    /**
     * Sets a new value of Database Object.
     *
     * @param database
     * */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * Returns Config object in json.
     *
     * @return config in json
     * */
    @Override
    public String toString() {
        return "Config{"
                + "database=" + database
                + '}';
    }
}
