package com.easy.kube.response;

import java.util.List;

public class AllJobsResponse extends Response{
    List<String> data;

    public AllJobsResponse(){}

    public AllJobsResponse(int status, String reason, List<String> data) {
        this.status = status;
        this.reason = reason;
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
