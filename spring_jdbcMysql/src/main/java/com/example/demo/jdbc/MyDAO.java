package com.example.demo.jdbc;

import java.util.List; // in a different package so must import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // this is to register this as bean which is to put it in a container
public class MyDAO {
	@Autowired // we got the JdbcTemplate from elsewhere so we need to inject dependencies via autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<MyDTO> list() {
		String query = "select * from mybbs";
		List<MyDTO> list = jdbcTemplate.query(
				query, new BeanPropertyRowMapper<>(MyDTO.class)
				);
		return list;
	}
}
