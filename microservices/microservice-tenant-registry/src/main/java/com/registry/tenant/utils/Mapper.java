package com.registry.tenant.utils;

import com.registry.tenant.models.Tenant;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {
    public static Tenant doMapping(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("ID").trim();
        String accountName = resultSet.getString("ACCOUNTNAME").trim();
        String kind = resultSet.getString("KIND").trim();
        String namespace = resultSet.getString("NAMESPACE").trim();
        String hostname = resultSet.getString("HOSTNAME").trim();
        return new Tenant(id, accountName, kind, namespace, hostname);
    }
}
