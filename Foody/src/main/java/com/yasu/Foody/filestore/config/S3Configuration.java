package com.yasu.Foody.filestore.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Configuration {

    @Bean
    public MinioClient minioClient(S3ConfigProperties prop){
        return MinioClient.builder().credentials(prop.getAccessKey(), prop.getSecretKey())
                .endpoint(prop.getUrl())
                .build();

    }
}
