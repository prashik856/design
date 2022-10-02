package com.easy.kube;

import com.easy.kube.model.Properties;
import com.easy.kube.response.ClusterResponse;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class KubeEasyApplication {
    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public Properties getProperties() {
        System.out.println("Defining Properties Bean");
        return new Properties(System.getProperty("DEFAULT_KUBE_DIRECTORY"),
                System.getProperty("KUBE_DIRECTORY"));
    }

    @Bean
    public ClusterResponse getClusterResponse(){
        System.out.println("Defining cluster response bean");
        return new ClusterResponse();
    }

    public static void main(String[] args) {
        // Setup logging
        final Logger LOG = LogManager.getLogger(KubeEasyApplication.class);
        LOG.info("Setting app Properties");

        LOG.info("Setting up default kube directory");
        final String DEFAULT_KUBE_DIRECTORY = "DEFAULT_KUBE_DIRECTORY";
        final String KUBE_DIRECTORY = "KUBE_DIRECTORY";
        final String userHome = System.getenv("HOME");
        final String defaultKubeDirectory = userHome + "/.kube";
        String kubeDirectory;
        System.setProperty(DEFAULT_KUBE_DIRECTORY, defaultKubeDirectory);

        Namespace res;
        LOG.info("Setting up Argument Parser");
        ArgumentParser parser = ArgumentParsers
                .newFor("ls")
                .build()
                .defaultHelp(true)
                .description("Program takes in input as the directory location where all kubernetes configuration"
                        + "files are present. Give by --config-directory. Defaults to $HOME/.kube");

        parser.addArgument("-cd", "--config-directory")
                .help("The path to configuration directory where all kubeconfig files are present")
                .type(String.class)
                .setDefault(defaultKubeDirectory);

        LOG.info("Reading provided input");
        try {
            res = parser.parseArgs(args);
            kubeDirectory = res.get("config_directory");
            System.setProperty(KUBE_DIRECTORY, kubeDirectory);
            LOG.info(kubeDirectory);
        } catch (ArgumentParserException e) {
            LOG.error("Error while parsing arguments provided.");
            e.printStackTrace();
            parser.handleError(e);
            System.exit(1);
        }

        SpringApplication.run(KubeEasyApplication.class);
    }
}