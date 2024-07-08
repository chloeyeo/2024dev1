package com.study.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="test")
@Data // (@Data=@Getter+@Setter)
//@Builder
public class TestEntity {
	// make entity from db
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment and also auto create this field
	private Long id;
	private String name;
	private int age;
	public TestEntity(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public void changeNameAndAge(String name2, int age2) {
		name=name2;
		age=age2;
	}
	
}
