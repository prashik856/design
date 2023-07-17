package com.registry.tenant;

import com.registry.tenant.dao.Persistence;
import com.registry.tenant.models.Config;
import com.registry.tenant.models.Tenant;
import com.registry.tenant.utils.ConfigReader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public class test {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        String configFilePath = "src/main/resources/application.yaml";
        Config config = new ConfigReader(configFilePath).readConfig();

//        UUID id = UUID.randomUUID();
//        Tenant tenant = new Tenant(id.toString(),
//                "prashik",
//                "dev",
//                "prashik-dev",
//                "prashik-dev.internal.registry.com");
//        System.out.println("tenant: " + tenant.toString());
//
//        Persistance.createTenant(config, tenant);

//        List<Tenant> tenants = Persistance.getTenants(config);
//        System.out.println(tenants);

//        Tenant tenant = Persistance.getTenant(config, "1ee7a917-7959-4980-bf65-ec7afcda1227");
//        System.out.println(tenant);

//        Persistance.deleteTenant(config, "1ee7a917-7959-4980-bf65-ec7afcda1227");
    }
}
