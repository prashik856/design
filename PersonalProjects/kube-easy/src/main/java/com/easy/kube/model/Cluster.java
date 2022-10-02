package com.easy.kube.model;

public class Cluster {
    private String clusterName;

    public Cluster(){}

    public Cluster(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
}
