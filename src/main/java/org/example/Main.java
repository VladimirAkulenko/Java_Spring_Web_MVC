package org.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static final int PORT = 8080;

    public static void main(String[] args) throws LifecycleException, IOException {
        final Tomcat tomcat = new Tomcat();
        final Path baseDir = Files.createTempDirectory("tomcat");
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final Connector connector = new Connector();
        connector.setPort(PORT);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        tomcat.start();
        tomcat.getServer().await();
    }
}