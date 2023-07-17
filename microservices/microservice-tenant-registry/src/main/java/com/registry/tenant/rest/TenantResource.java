package com.registry.tenant.rest;

import com.registry.tenant.models.Tenant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Tenant Resource Class.
 * Is used to service tenants.
 * */
@RestController
public class TenantResource {
    /**
     * getTenant method. Returns the tenant for the given id.
     *
     * @param id Unique tenant id
     * @return tenant
     * */
    @GetMapping("/api/v1/registry/tenants")
    public Tenant getTenant(@RequestParam(value="id") String id) {
        Tenant tenant = new Tenant();
        tenant.setId(id);
        return tenant;
    }
}
