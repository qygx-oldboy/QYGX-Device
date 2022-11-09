package com.qygx.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("emq")
public class EmqConfig {

    private String mqttServerUrl;

    public String getMqttServerUrl() {
        return mqttServerUrl;
    }

    public void setMqttServerUrl(String mqttServerUrl) {
        this.mqttServerUrl = mqttServerUrl;
    }
}
