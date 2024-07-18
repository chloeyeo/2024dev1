package com.study.spring.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductImage {
	private String fileName;
	@Setter
    private int ord;

}
