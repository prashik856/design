package com.registry.tenant.models;

public class Database {
    /**
     * database username.
     * */
    private String username;
    /**
     * database password.
     * */
    private String password;
    /**
     * database url.
     * */
    private String url;

    /**
     * Database Constructor without parameters.
     * */
    public Database() { }

    /**
     * Database Constructor.
     *
     * @param usernameValue
     * @param passwordValue
     * @param urlValue
     * */
    public Database(String usernameValue,
                    String passwordValue,
                    String urlValue) {
        this.username = usernameValue;
        this.password = passwordValue;
        this.url = urlValue;
    }

    /**
     * Returns database username.
     *
     * @return username
     * */
    public String getUsername() {
        return username;
    }

    /**
     * Returns database password.
     *
     * @return password
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Returns database url.
     *
     * @return url
     * */
    public String getUrl() {
        return url;
    }

    /**
     * Custom to string method.
     *
     * @return json string of database object
     * */
    @Override
    public String toString() {
        return "Database{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", url='" + url + '\''
                + '}';
    }
}
