package com.study.spring.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.study.spring.model.TestEntity;
import com.study.spring.repository.TestRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestService {
	private final TestRepository testRepository; // if 'final' + AllArgsConstructor, then no need @Autowired
	
	public TestEntity create(String name, int age) {
		TestEntity testEntity = new TestEntity(name, age);
		testRepository.save(testEntity);
		return testEntity;
	}
	
	public void delete(Long id) {
		TestEntity testEntity = testRepository.findById(id).get();
		testRepository.delete(testEntity);
	}

	public void update(Long id, String name, int age) {
		TestEntity testEntity = testRepository.findById(id).orElseThrow(()->new EntityNotFoundException("id does not exist."));
		testEntity.changeNameAndAge(name,age);
		testRepository.save(testEntity);
		
	}
}
