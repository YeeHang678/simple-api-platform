package com.ehong.ehongapiclientsdk;

import com.ehong.ehongapiclientsdk.client.EApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("ehong.client")
@Data
@ComponentScan
public class EApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public EApiClient EApiClient() {
        return new EApiClient(accessKey, secretKey);
    }

}
