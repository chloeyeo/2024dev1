package com.study.spring.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // to autowire 'final' fields
public class S3FileUploadTestService {
	private final AmazonS3Client amazonS3;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	
	private String dir = "/raw"; // name of folder we created in our s3 bucket
	
	// the 'object url' of the file in the raw folder in our s3 bucket
	// full url path is https://chloe-s3-file.s3.ap-northeast-2.amazonaws.com/raw/spongebob.jpg
	private String defaultUrl = "https://chloe-s3-file.s3.ap-northeast-2.amazonaws.com"; // full url path
	
	public String uploadFile(MultipartFile file) throws IOException {
		String bucketDir = bucketName + dir;
		String dirUrl = defaultUrl + dir + "/";
		String fileName = generateFileName(file);
		
		amazonS3.putObject(bucketDir,fileName,file.getInputStream(), getObjectMetadata(file));
		return dirUrl+fileName;
	}

	private String generateFileName(MultipartFile file) {
		return UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	}

	private ObjectMetadata getObjectMetadata(MultipartFile file) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		objectMetadata.setContentLength(file.getSize());
		return objectMetadata;
	}
}
