package com.registry.tenant.dao;

public class Persistance {
    /** SQL statement to get all tenants. */
    private final String GET_TENANTS = "SELECT ID, ACCOUNTNAME, KIND, NAMESPACE, HOSTNAME FROM TENANT;";
    /** SQL statement to get tenant with given ID. */
    private final String GET_TENANT_WITH_ID
            = "SELECT ID, ACCOUNTNAME, KIND, NAMESPACE, HOSTNAME FROM TENANT WHERE ID=@ID@;";
    /** SQL Statement to create tenant. */
    private final String CREATE_TENANT
            = "INSERT INTO TENANT (ID, ACCOUNTNAME, KIND, NAMESPACE, HOSTNAME) "
            + "VALUES (@ID@, @ACCOUNTNAME@, @KIND@, @NAMESPACE@, @HOSTNAME@);";
    /** SQL statement to update tenant by given ID. */
    private final String UPDATE_TENANT = "UPDATE TENANT "
            + "SET ACCOUNTNAME=@ACCOUNTNAME@, KIND=@KIND@, NAMESPACE=@NAMESPACE@, HOSTNAME=@HOSTNAME@ "
            + "WHERE ID=@ID@;";
    /** SQL statement to delete tenant by given ID. */
    private final String DELETE_TENANT = "DELETE FROM TENANT WHERE ID=@ID@;";


}
