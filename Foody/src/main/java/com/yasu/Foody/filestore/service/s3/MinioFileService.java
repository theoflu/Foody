package com.yasu.Foody.filestore.service.s3;

import com.yasu.Foody.filestore.config.S3ConfigProperties;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

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
    public void deleteBucket(String name) {
        deleteAllObjectsInBucket(name);
    }

    public void deleteAllObjectsInBucket(String bucketName) {
        try {
            // Kovanın içindeki tüm nesneleri listeleyin
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .build()
            );

            List<String> objectNames = new LinkedList();


            // Tüm nesne adlarını alın
            for (Result<Item> result : results) {
                objectNames.add(result.get().objectName());


            }

            // Tüm nesneleri silin
            for (String objectName : objectNames) {
               delete(objectName);
                System.out.println("Nesne silindi: " + objectName);
            }

            System.out.println("Kova içindeki tüm nesneler başarıyla silindi.");

        }
        catch (MinioException e) {
            // MinioException ile ilgili işlemler
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // IllegalArgumentException ile ilgili işlemler
            e.printStackTrace();
        } catch (Exception e) {
            // Diğer istisnalar ile ilgili genel işlemler
            e.printStackTrace();
        }
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
