package br.com.paramazon.demo.config.properties;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "s3")
@Configuration
public class S3Properties {

    private String accessKeyId;
    private String secretKeyId;
    private String region;
    private String bucket;

}
