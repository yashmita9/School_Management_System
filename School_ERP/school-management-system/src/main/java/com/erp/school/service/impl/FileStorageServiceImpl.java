package com.erp.school.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.erp.school.service.FileStorageServiceInt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for handling file upload and delete operations.
 */
@Service
public class FileStorageServiceImpl implements FileStorageServiceInt {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageServiceImpl.class);

    @Value("${file.upload-dir}")
    private String baseDir;

    /**
     * Upload file to server.
     *
     * @param file       file to upload
     * @param folderName target folder
     * @return file URL
     */
    @Override
    public String uploadFile(MultipartFile file, String folderName) {

        if (file == null || file.isEmpty()) {
            logger.error("File upload failed: file is empty");
            throw new RuntimeException("File is empty");
        }

        String uploadDir = baseDir + folderName + File.separator;
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
            logger.info("Created directory: {}", uploadDir);
        }

        try {
            File destination = new File(uploadDir + fileName);
            file.transferTo(destination);

            logger.info("File uploaded successfully: {}", fileName);

        } catch (IOException e) {
            logger.error("File upload failed: {}", e.getMessage());
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }

        return "/images/" + folderName + "/" + fileName;
    }

    /**
     * Delete file from server.
     *
     * @param filePath file URL path
     */
    @Override
    public void deleteFile(String filePath) {

        try {
            String fullPath = baseDir + filePath.replace("/images/", "");
            File file = new File(fullPath);

            if (file.exists()) {
                file.delete();
                logger.info("File deleted: {}", filePath);
            } else {
                logger.warn("File not found for deletion: {}", filePath);
            }

        } catch (Exception e) {
            logger.error("File delete failed: {}", e.getMessage());
            throw new RuntimeException("File delete failed");
        }
    }
}