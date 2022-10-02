package com.easy.kube.rest;

import com.easy.kube.KubeEasyApplication;
import com.easy.kube.model.Cluster;
import com.easy.kube.model.Properties;
import com.easy.kube.response.ClusterResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
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
    private ClusterResponse response;

    @RequestMapping("/all")
    public ClusterResponse getAllClusters() {
        LOG.info("Getting all clusters from " + properties.getKubeDirectory() + " directory.");
        List<Cluster> allClusters= new ArrayList<>();
        File folder = new File(properties.getKubeDirectory());
        File[] allFiles = folder.listFiles();

        for(int i=0; i<allFiles.length; i++) {
            if(allFiles[i].isFile()) {
                Cluster cluster = new Cluster(allFiles[i].getName());
                allClusters.add(cluster);
            }
        }

        if(allClusters.size() > 0) {
            response.setData(allClusters);
            response.setReason("OK");
            response.setStatus(200);
        } else {
            response.setData(allClusters);
            response.setReason("No Content");
            response.setStatus(204);
        }
        return response;
    }
}
