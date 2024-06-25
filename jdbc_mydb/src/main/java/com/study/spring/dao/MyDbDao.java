package com.study.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.spring.dto.MyDbDto;

@Repository
public class MyDbDao implements IMyDbDao {
	@Autowired // bc we need to inject this dependency into this class
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MyDbDto> listDao() {
		String query = "select * from mybbs order by id desc";
		List<MyDbDto> dtos = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(MyDbDto.class));
		return dtos;
	}

	@Override
	public MyDbDto viewDao(String id) {
		String query = "select * from mybbs where id="+id;
		// jdbcTemplate.query() to get MANY, jdbcTemplate.queryForObject to get SINGLE
		// BeanPropertyRowMapper<T> converts a row into a new instance of the specified mapped target class. 
		MyDbDto dto = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(MyDbDto.class));
		return dto;
	}

	@Override
	public int writeDao(String writer, String title, String content) {
		return 0;
	}

	@Override
	public int deleteDao(String id) {
		return 0;
	}

}
