package com.study.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.study.spring.dto.PageRequestDTO;
import com.study.spring.dto.PageResponseDTO;
import com.study.spring.dto.ProductDTO;
import com.study.spring.service.ProductService;
import com.study.spring.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor // autowiring final fields
@Log4j2
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;
	private final CustomFileUtil customFileUtil;

	@PostMapping("/") // so in talend should send post request to "/api/products/" NOT "/api/products"
						// - must have '/' at the end!
	public Map<String, Long> register(ProductDTO productDTO) {
		log.info("register: " + productDTO);
		List<MultipartFile> files = productDTO.getFiles();
		List<String> uploadFileNames = customFileUtil.saveFiles(files);

		productDTO.setUploadFileNames(uploadFileNames);

		log.info("uploadFileNames: " + uploadFileNames);

		Long pno = productService.register(productDTO);

		return Map.of("Result",pno);
	}

	@GetMapping("/view/{filename}")
	public ResponseEntity<Resource> viewFileGet(@PathVariable("filename") String filename) {
		return customFileUtil.getFile(filename);
	}

	@GetMapping("/{pno}")
	public ProductDTO read(@PathVariable("pno") Long pno) {
		return productService.get(pno);
	}

	@GetMapping("/list")
	public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
		return productService.getList(pageRequestDTO);
	}

	@PutMapping("/{pno}")
	public Map<String, String> modify(@PathVariable("pno") Long pno, ProductDTO productDTO) {

		productDTO.setPno(pno);
		
		// old filenames
		ProductDTO oldProductDTO = productService.get(pno);
		List<String> oldFileNames = oldProductDTO.getUploadFileNames();

		// new filenames
		List<MultipartFile> files = productDTO.getFiles();
		List<String> currentUploadFileNames = customFileUtil.saveFiles(files);

		// filenames that haven't been deleted
		List<String> uploadedFileNames = productDTO.getUploadFileNames();

		// compare with old file 'names'
		if (currentUploadFileNames != null && currentUploadFileNames.size() > 0)
			uploadedFileNames.addAll(currentUploadFileNames);

		// edit
		productService.modify(productDTO);

		if (oldFileNames != null && oldFileNames.size() > 0) {
			List<String> removeFiles = oldFileNames.stream()
					.filter(fileName -> uploadedFileNames.indexOf(fileName) == -1).collect(Collectors.toList());

			customFileUtil.deleteFiles(removeFiles);
		}

		return Map.of("Result", "success");
	}

	@DeleteMapping("/{pno}")
	public Map<String, String> remove(@PathVariable("pno") Long pno) {
		List<String> oldFileNames = productService.get(pno).getUploadFileNames();
		productService.remove(pno);
		customFileUtil.deleteFiles(oldFileNames);
		return Map.of("Result", "success");
	}

}
