package com.study.spring.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value; // Value from beans factory, NOT from lombok!
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource; //must use this one!
import org.springframework.http.HttpHeaders; // must be this HttpHeaders
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Component
@RequiredArgsConstructor
@Log4j2
public class CustomFileUtil {
	@Value("${com.study.spring.upload.path}") // com.study.spring.upload.path from application.properties
	private String uploadPath;
	
	@PostConstruct // for initialization of bean, prevents bean from getting initialized more than once in its lifecycle.
	public void init() {
		File tempFolder = new File(uploadPath);
		
		if (!tempFolder.exists()) {
			tempFolder.mkdir(); // if folder doesn't exist then make a folder
		}
		
		uploadPath = tempFolder.getAbsolutePath();
		
		log.info("------------------------------------------------");
		log.info("upload path: "+uploadPath);
	}

	public List<String> saveFiles(List<MultipartFile> files) {
		if(files==null || files.size()==0) {
			return null;
		}
		
		List<String> uploadNames = new ArrayList<>();
		for (MultipartFile multipartFile: files) {
			String saveName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
			Path savePath = Paths.get(uploadPath, saveName);
			try {
				Files.copy(multipartFile.getInputStream(), savePath); // this SAVES the multipart file to the file in our path!
				String contentType = multipartFile.getContentType();
				if (contentType != null && contentType.startsWith("image")) {
					Path thumbnailPath = Paths.get(uploadPath, "s_" + saveName);
					Thumbnails.of(savePath.toFile()).size(400, 400).toFile(thumbnailPath.toFile());
				}
				uploadNames.add(saveName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return uploadNames;
	}
	
	// ResponseEntity is the response with the json data
	// Resource is file (java file.io => practice java.file.io)
	public ResponseEntity<Resource> getFile(String fileName) {
		// must use File.separator to separate file names cannot use anything else like " "
		Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
		if (!resource.exists()) {
			resource = new FileSystemResource(uploadPath+File.separator+"default.png");
		}
		HttpHeaders headers = new HttpHeaders();
		try {
			// mime type is always in the header when we send files.
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath())); // mime-type = whether the file is jpg or txt etc
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return ResponseEntity.ok().headers(headers).body(resource); // set response body to be 'resource' = our file
	}
	
	
	
}
