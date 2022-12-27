package com.easy.kube.rest;

import com.easy.kube.model.AllKubernetesClusters;
import com.easy.kube.model.Cluster;
import com.easy.kube.response.AllNamespacesResponse;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Namespace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cluster")
public class Namespaces {
    final Logger LOG = LogManager.getLogger(Namespaces.class);

    @Autowired
    private AllNamespacesResponse response;

    @Autowired
    private AllKubernetesClusters allKubernetesClusters;

    // Return all namespaces
    @RequestMapping("/{clusterId}/namespaces/all")
    public AllNamespacesResponse getAllNamespaces(@PathVariable("clusterId") String clusterId) throws ApiException {
        LOG.info("Calling /cluster/" + clusterId + "/namespaces/all API");
        List<String> allNamespaces = new ArrayList<>();

        LOG.info("Getting target cluster");
        Cluster cluster = allKubernetesClusters.getCluster(clusterId);
        if(cluster == null) {
            LOG.info("Cluster with id " + clusterId + " not found. Return 404.");
            response.setData(allNamespaces);
            response.setReason("Cluster Not Found");
            response.setStatus(404);
            return response;
        }

        LOG.info("Getting all namespaces from the cluster");
        List<V1Namespace> namespaceList =  cluster.getApi().listNamespace(null, false, null,
                null, null, null, null,
                null, null, false).getItems();
        for (V1Namespace v1Namespace : namespaceList) {
            allNamespaces.add(Objects.requireNonNull(v1Namespace.getMetadata()).getName());
        }
        if(allNamespaces.size() > 0) {
            LOG.info("Response with OK. 200.");
            response.setData(allNamespaces);
            response.setReason("OK");
            response.setStatus(200);
        } else {
            LOG.info("Response with No Content. 204.");
            response.setData(allNamespaces);
            response.setReason("No Content");
            response.setStatus(204);
        }

        return response;
    }
}
