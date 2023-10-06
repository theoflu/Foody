package com.yasu.Foody.filestore.service.s3;

import java.io.File;
import java.io.InputStream;

public interface FileService {
    void save(String id, String ContentType,InputStream file);
    void delete(String id);
    void deleteBucket(String name);
    byte[] get(String id);
}
