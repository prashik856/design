package com.registry.tenant.models;

/**
 * Class Tenant.
 * Has all the attributes the tenant is supposed to have.
 * */
public class Tenant {
    /**
     * Unique ID of the tenant.
     * */
    private String id;
    /**
     * Account name of the tenant.
     * In our case, this should be unique too.
     * */
    private String accountName;
    /**
     * Tenant kind.
     * Can be test or prod.
     * */
    private String kind;
    /**
     * Namespace created using accountName.
     * Namespace is equal to <accountName>-<kind>
     * */
    private String nameSpace;
    /**
     * Tenant hostname. ${namespace}.internal.myorg.com
     * */
    private String hostName;

    /**
     * The Constructor.
     *
     * @param id id of tenant
     * @param accountName account name of tenant
     * @param kind kind of tenant
     * @param nameSpace namespace of tenant
     * @param hostName hostname of tenant
     * */
    public Tenant(String id, String accountName, String kind, String nameSpace, String hostName) {
        this.id = id;
        this.accountName = accountName;
        this.kind = kind;
        this.nameSpace = nameSpace;
        this.hostName = hostName;
    }

    /**
     * Empty Constructor.
     * */
    public Tenant() {

    }

    /**
     * Returns id of tenant.
     *
     * @return id
     * */
    public String getId() {
        return id;
    }

    /**
     * Sets id of the tenant.
     *
     * @param id unique id of tenant
     * */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns account name of tenant
     *
     * @return accountName
     * */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets accountName of tenant
     *
     * @param accountName accountName of tenant
     * */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Get kind of tenant
     *
     * @return kind
     * */
    public String getKind() {
        return kind;
    }

    /**
     * Sets kind of tenant
     *
     * @param kind kind of tenant
     * */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Returns namespace of tenant
     *
     * @return namespace
     * */
    public String getNameSpace() {
        return nameSpace;
    }

    /**
     * Sets namespace of tenant
     *
     * @param nameSpace namespace of tenant
     * */
    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    /**
     * Returns hostname of tenant
     *
     * @return hostname
     * */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets hostname of tenant
     *
     * @param hostName tenant hostname
     * */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Overridden toString method.
     * */
    @Override
    public String toString() {
        return "Tenant{"
                + "id='" + id + '\''
                + ", accountName='" + accountName + '\''
                + ", kind='" + kind + '\''
                + ", namespaceSpace='" + nameSpace + '\''
                + ", hostName='" + hostName + '\''
                + '}';
    }
}
