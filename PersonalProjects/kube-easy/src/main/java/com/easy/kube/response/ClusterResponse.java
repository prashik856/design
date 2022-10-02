package com.easy.kube.response;

import com.easy.kube.model.Cluster;

import java.util.List;

public class ClusterResponse extends Response {
    List<Cluster> data;

    public ClusterResponse(){}
    public ClusterResponse(int status, String reason, List<Cluster> data) {
        this.status = status;
        this.data = data;
        this.reason = reason;
    }

    public List<Cluster> getData() {
        return data;
    }

    public void setData(List<Cluster> data) {
        this.data = data;
    }
}
