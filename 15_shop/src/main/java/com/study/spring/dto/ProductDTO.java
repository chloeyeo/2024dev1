package com.study.spring.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // check from which java version this is available!
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long pno; // null by default (bc Long not long) bc we didnt put this in form data in request in talend
	private String pname;
	private int price;
	private String productDescription;
	// we don't actually 'delete' the productDTO we just mark deleteFlag as true to say it's 'deleted' (not actually deleted)
	private boolean deleteFlag; // false by default even tho we didnt put this in form data in request in talend
	
//	 should i upload from frontend to S3 or from backend to S3 when uploading image files? This is our choice to make
//	 for multi part data. There is no filename set if you go straight from front to S3 so you can
//	 first send file from front to back then give filename in the back then send file to S3
//	 OR you can go from front to S3 directly for multi part form data image upload
	
	
	// 빌더 패턴을 통해 인스턴스를 만들 때 특정 필드를 null이 아닌 특정 값으로 초기화하고 싶다면 @Builder.Default를 쓰면 된다
	@Builder.Default // without this Builder may not get this initialized value and instead set files as null
	private List<MultipartFile> files = new ArrayList<>();
	
	@Builder.Default
	private List<String> uploadFileNames = new ArrayList<>();
}
