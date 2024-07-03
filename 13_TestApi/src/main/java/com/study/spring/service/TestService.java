package com.study.spring.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.study.spring.model.TestEntity;
import com.study.spring.repository.TestRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestService {
	private final TestRepository testRepository; // if 'final' + AllArgsConstructor, then no need @Autowired
	// OR without both final and AllArgsConstructor, instead put @Autowired on testRepository field
	
	public TestEntity create(String name, int age) {
		TestEntity testEntity = new TestEntity(name, age);
//		TestEntity testEntity = TestEntity.builder()
//				.name(name)
//				.age(age)
//				.build(); // builder is from Java 11
		testRepository.save(testEntity);
		return testEntity;
	}
}
