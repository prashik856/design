package com.registry.tenant.dao;

import com.registry.tenant.models.Config;
import com.registry.tenant.models.Tenant;
import com.registry.tenant.utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Persistence {
    /** SQL statement to get all tenants. */
    private static final String GET_TENANTS = "SELECT ID, ACCOUNTNAME, KIND, NAMESPACE, HOSTNAME FROM TENANT;";

    /** SQL statement to get tenant with given ID. */
    private static final String GET_TENANT_WITH_ID
            = "SELECT ID, ACCOUNTNAME, KIND, NAMESPACE, HOSTNAME FROM TENANT WHERE ID=\"@ID@\";";

    /** SQL Statement to create tenant. */
    private static final String CREATE_TENANT
            = "INSERT INTO TENANT (ID, ACCOUNTNAME, KIND, NAMESPACE, HOSTNAME) "
            + "VALUES (\"@ID@\", \"@ACCOUNTNAME@\", \"@KIND@\", \"@NAMESPACE@\", \"@HOSTNAME@\");";

    /** SQL statement to update tenant by given ID. */
    private static final String UPDATE_TENANT = "UPDATE TENANT "
            + "SET ACCOUNTNAME=\"@ACCOUNTNAME@\", KIND=\"@KIND@\", NAMESPACE=\"@NAMESPACE@\", HOSTNAME=\"@HOSTNAME@\" "
            + "WHERE ID=\"@ID@\";";

    /** SQL statement to delete tenant by given ID. */
    private static final String DELETE_TENANT = "DELETE FROM TENANT WHERE ID=\"@ID@\";";

    /**
     * get tenants method. Will returns all tenants from the database.
     * @param config the application Config
     * @return the list of tenants present in database
     * */
    public static List<Tenant> getTenants(Config config) throws SQLException, ClassNotFoundException {
        return DBUtils.runStatement(config, GET_TENANTS, new ArrayList<>());
    }

    /**
     * Get tenant. Returns tenant by given id.
     * @param config the application config
     * @param id the tenant id
     * @return the tenant associated with given id
     * */
    public static Tenant getTenant(Config config, String id) throws SQLException, ClassNotFoundException {
        String query = GET_TENANT_WITH_ID.replace("@ID@", id);
        return DBUtils.runStatement(config, query);
    }

    /**
     * Create tenant method. Will create tenant in database.
     * @param config the config
     * @param tenant the tenant object
     * */
    public static void createTenant(Config config, Tenant tenant) throws SQLException, ClassNotFoundException {
        String query = CREATE_TENANT.replace("@ID@", tenant.getId())
                .replace("@ACCOUNTNAME@", tenant.getAccountName())
                .replace("@KIND@", tenant.getKind())
                .replace("@NAMESPACE@", tenant.getNameSpace())
                .replace("@HOSTNAME@", tenant.getHostName());
        DBUtils.runUpdateStatement(config, query);
    }

    /**
     * Update tenant method. Will update the given tenant.
     * @param config the application config
     * @param tenant the tenant to update
     * */
    public static void updateTenant(Config config, Tenant tenant) throws SQLException, ClassNotFoundException {
        String query = UPDATE_TENANT.replace("@ID@", tenant.getId())
                .replace("@ACCOUNTNAME@", tenant.getAccountName())
                .replace("@KIND@", tenant.getKind())
                .replace("@NAMESPACE@", tenant.getNameSpace())
                .replace("@HOSTNAME@", tenant.getHostName());
        DBUtils.runUpdateStatement(config, query);
    }

    /**
     * Delete tenant. Will delete the tenant by given id.
     * @param config the application config
     * @param id the tenant id
     * */
    public static void deleteTenant(Config config, String id) throws SQLException, ClassNotFoundException {
        String query = DELETE_TENANT.replace("@ID@", id);
        DBUtils.runUpdateStatement(config, query);
    }
}
