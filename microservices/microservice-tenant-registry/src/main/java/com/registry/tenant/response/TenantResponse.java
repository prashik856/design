package com.registry.tenant.response;

import com.registry.tenant.models.Tenant;

import java.util.List;

/**
 * Response to tenants API Class.
 * */
public class TenantResponse {
    /** The http status code. */
    private int status;

    /** The http status reason. */
    private String reason;

    /** The response data */
    List<Tenant> data;

    /**
     * Constructor.
     * @param status the http status code
     * @param reason the http response reason
     * @param data the data of response
     * */
    public TenantResponse(int status, String reason, List<Tenant> data) {
        this.status = status;
        this.reason = reason;
        this.data = data;
    }

    /**
     * Empty Constructor.
     * */
    public TenantResponse() {

    }

    /** Get http status.
     * @return the http status
     * */
    public int getStatus() {
        return status;
    }

    /** set http status.
     * @param status the http status
     * */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns http response reason
     * @return reason
     * */
    public String getReason() {
        return reason;
    }

    /**
     * Sets http response reason.
     * @param reason the http response reason.
     * */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Gets the list of tenants.
     * @return data as list of tenants
     * */
    public List<Tenant> getData() {
        return data;
    }

    /**
     * Sets the data value as list of tenants.
     * @param data a list of tenants
     * */
    public void setData(List<Tenant> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TenantResponse{"
                + "status=" + status
                + ", reason='" + reason + '\''
                + ", data=" + data + '}';
    }
}
