package com.yasu.Foody.filestore.service;

import com.yasu.Foody.filestore.service.s3.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.init.ResourceReaderRepositoryPopulator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStoreService {
    private final FileService fileService;

    public Mono<byte[]> getImage(String id) {
        return Mono.just(fileService.get(id));
    }
    public void saveImage(String id,InputStream file) {
        fileService.save(id,MediaType.IMAGE_JPEG_VALUE,file);

    }
    public void deleteImage(String id) {
        fileService.delete(id);

    }

    public ResponseEntity deleteBucket(String name){
        fileService.deleteBucket(name);
return ResponseEntity.ok(200);
    }


}
