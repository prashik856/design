package com.easy.kube.model;

public class Properties {
    private String defaultKubeDirectory;
    private String kubeDirectory;

    public Properties(){}

    public Properties(String defaultKubeDirectory, String kubeDirectory) {
        this.defaultKubeDirectory = defaultKubeDirectory;
        this.kubeDirectory = kubeDirectory;
    }

    public String getDefaultKubeDirectory() {
        return defaultKubeDirectory;
    }

    public void setDefaultKubeDirectory(String defaultKubeDirectory) {
        this.defaultKubeDirectory = defaultKubeDirectory;
    }

    public String getKubeDirectory() {
        return kubeDirectory;
    }

    public void setKubeDirectory(String kubeDirectory) {
        this.kubeDirectory = kubeDirectory;
    }
}
