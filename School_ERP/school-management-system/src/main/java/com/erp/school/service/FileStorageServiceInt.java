package com.erp.school.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServiceInt {

	String uploadFile(MultipartFile file, String folderName);

	void deleteFile(String filePath);

}
