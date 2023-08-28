package com.yasu.Foody.filestore.service.s3;

import com.yasu.Foody.filestore.config.S3ConfigProperties;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class MinioFileService implements FileService{

    private final MinioClient minioClient;
    private final S3ConfigProperties s3prop;
    @Override
    public void save(String id, String ContentType, InputStream file) {

        try{
       var obj= PutObjectArgs.builder().contentType(ContentType)
               .object(id)
                .stream(file,file.available(),-1)
                .bucket(s3prop.getBucket())
                .build();
minioClient.putObject(obj);
    } catch (Exception e){
        log.error("File put error",e);}
    }


    @Override
    public void delete(String id) {
        try{

            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(s3prop.getBucket())
                    .object(id)
                    .build());
        }
        catch (Exception e){
            log.error("File remove error",e);}

    }

    @Override
    public byte[] get(String id) {
        try{

           return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(s3prop.getBucket())
                    .object(id)
                    .build()).readAllBytes();

        }
        catch (Exception e){
            log.error("File get error",e);}
        return new byte[0];
    }
}
