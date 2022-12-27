package com.easy.kube.response;

import java.util.List;

public class AllClustersResponse extends Response {
    List<String> data;
    public AllClustersResponse(){}
    public AllClustersResponse(int status, String reason, List<String> data) {
        this.status = status;
        this.data = data;
        this.reason = reason;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
