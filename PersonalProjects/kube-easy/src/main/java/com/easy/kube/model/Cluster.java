package com.easy.kube.model;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
public class Cluster {
    final Logger LOG = LogManager.getLogger(Cluster.class);
    private String clusterName;
    private String[] namespaces;
    private String kubeConfigPath;
    ApiClient apiClient;
    CoreV1Api api;
    public Cluster(){}

    public String getKubeConfigPath() {
        return kubeConfigPath;
    }

    public void setKubeConfigPath(String kubeConfigPath) {
        this.kubeConfigPath = kubeConfigPath;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(String kubeConfigPath) throws IOException {
        this.apiClient = ClientBuilder
                .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(this.kubeConfigPath))).build();
    }

    public CoreV1Api getApi() {
        return api;
    }
    public void setApi(ApiClient apiClient) {
        this.api = new CoreV1Api(apiClient);
    }

    public Cluster(String clusterName, String kubeConfigPath) throws IOException {
        this.clusterName = clusterName;
        this.kubeConfigPath = kubeConfigPath;
        try {
            this.apiClient = ClientBuilder
                    .kubeconfig(KubeConfig.loadKubeConfig(new FileReader(this.kubeConfigPath))).build();
            this.api = new CoreV1Api(this.apiClient);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Cannot initialize " + this.clusterName + " cluster");
        }
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String[] getNamespaces() {
        return namespaces;
    }

    public void setNamespaces(String[] namespaces) {
        this.namespaces = namespaces;
    }
}
