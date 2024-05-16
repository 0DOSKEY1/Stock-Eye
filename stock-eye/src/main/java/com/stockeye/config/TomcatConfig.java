package com.stockeye.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(8080);
        factory.setContextPath("");
        factory.addConnectorCustomizers(connector -> {
            connector.setProperty("relaxedQueryChars", "|{}[]");
        });
        return factory;
    }
}
