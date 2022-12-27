package com.easy.kube.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class AllKubernetesClusters {
    final Logger LOG = LogManager.getLogger(AllKubernetesClusters.class);
    private List<Cluster> allClusters;

    public AllKubernetesClusters() {}
    public AllKubernetesClusters(List<Cluster> allClusters) {
        this.allClusters = allClusters;
    }

    public List<Cluster> getAllClusters() {
        return allClusters;
    }

    public void setAllClusters(List<Cluster> allClusters) {
        this.allClusters = allClusters;
    }

    public Cluster getCluster(String clusterId) {
        Cluster cluster = null;
        for(int i=0; i<allClusters.size(); i++) {
            if(Objects.equals(allClusters.get(i).getClusterName(), clusterId)) {
                LOG.info("Found cluster with id: " + clusterId);
                cluster = allClusters.get(i);
                break;
            }
        }
        return cluster;
    }
}
