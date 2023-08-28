package com.yasu.Foody.filestore.startup;

import com.yasu.Foody.filestore.config.S3ConfigProperties;
import com.yasu.Foody.filestore.service.FileStoreService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileStoreStartUp {

    private final S3ConfigProperties s3prop;
    private final MinioClient minioClient;

    @EventListener(ApplicationStartedEvent.class)
    public void init() throws Exception{
        boolean bucket=minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(s3prop.getBucket())
                .build());

        if (!bucket){

            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(s3prop.getBucket())
                    .build());
        }
    }
}
