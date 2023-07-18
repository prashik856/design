package com.registry.tenant.rest;

import com.registry.tenant.dao.Persistence;
import com.registry.tenant.models.Config;
import com.registry.tenant.models.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;
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
     * Autowired list of all tenants.
     * */
    @Autowired
    private List<Tenant> allTenants;

    /**
     * Autowired hash of all tenants. the cache.
     * */
    @Autowired
    private HashMap<String, Tenant> cachedTenants;

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
    public ResponseEntity getTenant(@RequestParam(value="namespace", defaultValue = "") String namespace) {
        if (Objects.equals(namespace, "")) {
            logger.info("Parameter namespace value is empty. ");
        } else {
            Tenant responseTenant = null;
            logger.info("Namespace value is non empty.");
            // namespace is unique
            for (Tenant tenant : allTenants) {
                if (Objects.equals(tenant.getNameSpace(), namespace)) {
                    responseTenant = tenant;
                    break;
                }
            }
            if(!Objects.equals(responseTenant, null)) {
                logger.info("Response non empty.");
                return ResponseEntity.ok(responseTenant);
            } else {
                // Object is empty
                return ResponseEntity.ok(new ArrayList<>());
            }
        }
        // Build Proper Response
        return ResponseEntity.ok(allTenants);
    }

    /**
     * update tenant method. Used to create/update a tenant.
     * If tenant is already present in cache, update the tenant else create a new one and update cache.
     *
     * @param tenant the incoming tenant
     * @return ResponseEntity the http response entity
     * */
    @PutMapping("/api/v1/registry/tenants")
    public ResponseEntity updateTenant(@Validated @RequestBody Tenant tenant) {
        logger.info("PUT request to update/create tenant: " + tenant.toString());
        // Check if tenant is already present in cache
        if (cachedTenants.containsKey(tenant.getNameSpace())) {
            logger.info("Tenant is already present. Updating tenant.");
            tenant.setId(cachedTenants
                    .get(tenant.getNameSpace()).getId());
            try {
                // update persistence.
                Persistence.updateTenant(config, tenant);
                // update cache.
                cachedTenants.replace(tenant.getNameSpace(), tenant);
                // update list of tenants
                allTenants = cachedTenants.values().stream().toList();
                // return ok
                return ResponseEntity.ok(tenant);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                logger.info("Not able to update tenant.");
                return ResponseEntity.internalServerError().build();
            }
        } else {
            logger.info("Creating new tenant entry");
            UUID id = UUID.randomUUID();
            tenant.setId(id.toString());
            try {
                // add new instance of tenant in persistence
                Persistence.createTenant(config, tenant);
                // update cache
                cachedTenants.putIfAbsent(tenant.getNameSpace(), tenant);
                // update list of tenants
                allTenants = cachedTenants.values().stream().toList();
                // return ok
                return ResponseEntity.ok(tenant);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                logger.info("Not able to create tenant.");
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    /**
     *
     * */
    @DeleteMapping("/api/v1/registry/tenants")
    public ResponseEntity deleteTenant(@NonNull @RequestParam(value = "namespace") String namespace) {
        logger.info("Delete tenant with namespace: " + namespace);
        if(cachedTenants.containsKey(namespace)) {
            logger.info("Deleting tenant " + namespace);
            try {
                // update persistence
                Persistence.deleteTenant(config, cachedTenants.get(namespace).getId());
                // update cache
                cachedTenants.remove(namespace);
                // update list of tenants
                allTenants = cachedTenants.values().stream().toList();
                // return ok
                return ResponseEntity.ok().build();
            } catch (SQLException | ClassNotFoundException e) {
                logger.info("Error deleting tenant");
                return ResponseEntity.internalServerError().build();
            }
        } else {
            logger.info("Tenant is not present is cache. It might not even be there.");
            return ResponseEntity.notFound().build();
        }
    }
}
