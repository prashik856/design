package com.easy.kube.rest;

import com.easy.kube.KubeEasyApplication;
import com.easy.kube.model.AllKubernetesClusters;
import com.easy.kube.model.Properties;
import com.easy.kube.response.AllClustersResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clusters")
public class Clusters {
    final Logger LOG = LogManager.getLogger(KubeEasyApplication.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private Properties properties;

    @Autowired
    private AllClustersResponse response;

    @Autowired
    private AllKubernetesClusters allKubernetesClusters;

    @RequestMapping("/all")
    public AllClustersResponse getAllClusters() {
        LOG.info("Calling /clusters/all API");
        LOG.info("Size of allKubernetesClusters Bean: " + allKubernetesClusters.getAllClusters().size());
        List<String> allClusterNames = new ArrayList<>();
        for(int i=0; i<allKubernetesClusters.getAllClusters().size(); i++) {
            allClusterNames.add(allKubernetesClusters.getAllClusters().get(i).getClusterName());
        }

        if(allClusterNames.size() > 0) {
            response.setData(allClusterNames);
            response.setReason("OK");
            response.setStatus(200);
        } else {
            response.setData(allClusterNames);
            response.setReason("No Content");
            response.setStatus(204);
        }
        return response;
    }
}
