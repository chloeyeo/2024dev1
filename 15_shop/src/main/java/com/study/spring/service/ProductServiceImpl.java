package com.study.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.study.spring.domain.Product;
import com.study.spring.domain.ProductImage;
import com.study.spring.dto.PageRequestDTO;
import com.study.spring.dto.PageResponseDTO;
import com.study.spring.dto.ProductDTO;
import com.study.spring.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
		// data comes from pageRequestDTO.
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
				Sort.by("pno").descending());
		Page<Object[]> result = productRepository.selectList(pageable);

//		List<ProductDTO> dtoList = result.get().map().toList();
		List<ProductDTO> dtoList = result.get().map(arr -> {
			ProductDTO productDTO = null;
			Product product = (Product) arr[0]; // using parent's class type
			ProductImage productImage = (ProductImage) arr[1];
			productDTO = ProductDTO.builder().pno(product.getPno()).pname(product.getPname())
					.productDescription(product.getProductDescription()).deleteFlag(product.isDeleteFlag())
					.price(product.getPrice()).build();

			String imageStr = productImage.getFileName();
			productDTO.setUploadFileNames(List.of(imageStr));

			return productDTO;
		}).toList();
		long totalCount = result.getTotalElements();
		return PageResponseDTO.<ProductDTO>withAll().dtoList(dtoList).totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO).build();
	}

	@Override
	public Long register(ProductDTO productDTO) { // we must convert DTO into an Entity
		Product product = dtoToEntity(productDTO);
		log.info("#################################################");
		log.info("Product: " + product);
		log.info("Product's pno: " + product.getPno());
		Long pno = productRepository.save(product).getPno();
		return pno;
	}

	private Product dtoToEntity(ProductDTO productDTO) {
		Product product = Product.builder().pno(productDTO.getPno()).pname(productDTO.getPname())
				.price(productDTO.getPrice()).productDescription(productDTO.getProductDescription())
				.deleteFlag(productDTO.isDeleteFlag()).build();
		List<String> uploadFileNames = productDTO.getUploadFileNames();
		if (uploadFileNames == null || uploadFileNames.size() == 0)
			return product;
		uploadFileNames.forEach(fileName -> {
			product.addImageString(fileName);
		});
		return product;
	}

	@Override
	public ProductDTO get(Long pno) {
		// if you're getting a SINGLE data, use Optional!!
		Optional<Product> result = productRepository.findById(pno);
		Product product = result.orElseThrow();
		ProductDTO productDTO = entityToDto(product);
		return productDTO;
	}

	private ProductDTO entityToDto(Product product) {
		ProductDTO productDTO = ProductDTO.builder().pname(product.getPname()).pno(product.getPno())
				.price(product.getPrice()).productDescription(product.getProductDescription())
				.deleteFlag(product.isDeleteFlag()).build();
		List<ProductImage> imageList = product.getImageList();
		if (imageList == null || imageList.size() == 0)
			return productDTO;
		List<String> fileNameList = imageList.stream().map(productImage -> productImage.getFileName()).toList();
		productDTO.setUploadFileNames(fileNameList);
		return productDTO;
	}

	@Override
	public void modify(ProductDTO productDTO) {
		// 1. read pno (optional for single)
		Optional<Product> result = productRepository.findById(productDTO.getPno());
		Product product = result.orElseThrow();

		// 2. change
		product.changePName(productDTO.getPname());
		product.changePrice(productDTO.getPrice());
		product.changeProductDescription(productDTO.getProductDescription());

		// 3. delete/clear uploaded image files
		product.clearList();

		List<String> uploadFileNames = productDTO.getUploadFileNames();
		if (uploadFileNames != null && uploadFileNames.size() > 0) {
			uploadFileNames.stream().forEach(uploadName -> {
				product.addImageString(uploadName);
			});
		}
	}

	@Override
	public void remove(Long pno) {
		productRepository.updateToDelete(pno, true);
	}

}
