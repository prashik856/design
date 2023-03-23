package com.easy.kube.rest;

import com.easy.kube.model.AllKubernetesClusters;
import com.easy.kube.response.AllJobsResponse;
import com.easy.kube.response.AllNamespacesResponse;
import com.easy.kube.model.Cluster;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Job;
import io.kubernetes.client.openapi.models.V1JobList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cluster")
public class Jobs {
    final Logger LOG = LogManager.getLogger(Jobs.class);

    @Autowired
    private AllKubernetesClusters allKubernetesClusters;

    @Autowired
    private AllJobsResponse response;

    @RequestMapping("/{clusterId}/{namespaceId}/jobs/all")
    public AllJobsResponse getAllJobs(@PathVariable("clusterId") String clusterId,
                                      @PathVariable("namespaceId") String namespaceId) throws ApiException {
        LOG.info("Calling /cluster/" + clusterId + "/" + namespaceId + "/jobs/all API");
        List<String> allJobs = new ArrayList<>();

        LOG.info("Getting target cluster");
        Cluster cluster = allKubernetesClusters.getCluster(clusterId);
        if(cluster == null) {
            LOG.info("Cluster with id " + clusterId + " not found. Return 404.");
            response.setData(allJobs);
            response.setReason("Cluster Not Found");
            response.setStatus(404);
            return response;
        }

        LOG.info("Getting all jobs from " + namespaceId + " namespace");
        List<V1Job> allJobsList = cluster.getBatchV1Api().listNamespacedJob(namespaceId,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null).getItems();
        V1Job job = new V1Job();

        return response;
    }

}
