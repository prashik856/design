package com.registry.tenant.rest;

import com.registry.tenant.dao.Persistence;
import com.registry.tenant.models.Config;
import com.registry.tenant.models.Tenant;
import com.registry.tenant.response.TenantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Tenant Resource Class.
 * Is used to service tenants.
 * */
@RestController
public class TenantResource {

    /**
     * Autowired Config.
     * */
    @Autowired
    private Config config;

    /**
     * The logger.
     * */
    public static Logger logger = Logger.getLogger(TenantResource.class.getName());

    /**
     * getTenant method. Returns the tenant for the given id.
     *
     * @param namespace Unique tenant id
     * @return tenant
     * */
    @GetMapping("/api/v1/registry/tenants")
    public TenantResponse getTenant(@RequestParam(value="namespace", defaultValue = "") String namespace) {
        List<Tenant> tenants;
        TenantResponse tenantResponse = new TenantResponse();
        try {
            tenants = Persistence.getTenants(config);
            if (Objects.equals(namespace, "")) {
                logger.info("Parameter namespace value is empty. ");
            } else {
                Tenant responseTenant = null;
                logger.info("Namespace value is non empty.");
                // namespace is unique
                for (Tenant tenant : tenants) {
                    if (Objects.equals(tenant.getNameSpace(), namespace)) {
                        responseTenant = tenant;
                        break;
                    }
                }
                tenants.clear();
                if(!Objects.equals(responseTenant, null)) {
                    tenants.add(responseTenant);
                }
            }

            // Build Proper Response
            createResponse(tenantResponse,
                    200,
                    "OK",
                    tenants);
        } catch (SQLException e) {
            e.printStackTrace();
            createResponse(tenantResponse,
                    500,
                    "SQL Exception. Service not available.",
                    new ArrayList<>());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            createResponse(tenantResponse,
                    500,
                    "ClassNotFoundException. Service not available.",
                    new ArrayList<>());
        }
        return tenantResponse;
    }

    private void createResponse(TenantResponse tenantResponse,
                                int status,
                                String reason,
                                List<Tenant> data) {
        tenantResponse.setStatus(status);
        tenantResponse.setReason(reason);
        tenantResponse.setData(data);
    }
}
