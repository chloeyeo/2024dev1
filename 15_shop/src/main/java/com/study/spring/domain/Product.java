package com.study.spring.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_product")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="imageList") // to prevent going to db so many times. Bc accessing data from a table slows down time complexity a lot
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private Long pno;
	private String pname;
	private int price; // default is 0
	private String productDescription;
	private boolean deleteFlag;

	@ElementCollection // allows us to store collection of values as separate entities without having to create an additional entity class
	@Builder.Default
	private List<ProductImage> imageList = new ArrayList<>();

	public void addImage(ProductImage image) {
		image.setOrd(imageList.size());
		imageList.add(image);
	}

	public void addImageString(String fileName) {
		ProductImage productImage = ProductImage.builder().fileName(fileName).build();
		addImage(productImage);
	}

	public void changePName(String name) {
		pname = name;
	}

	public void changePrice(int price) {
		this.price = price;
	}

	public void changeDeleteFlag(boolean bool) {
		deleteFlag = bool;
	}
	
	public void changeProductDescription(String pdec) {
		productDescription = pdec;
	}

	public void clearList() {
		this.imageList.clear();
	}
}
