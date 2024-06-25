package com.example.demo.jdbc;

import java.util.List; // in a different package so must import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // this is to register this as bean which is to put it in a container
public class MyDAO {
	// jdbctemplate -> MyBatis3 -> jpa (best and newest implementation of jdbc which connects Spring application to any database)
	// DAO(DTO) <-> JPA(this does queries instead via its apis so we don't use query here like jdbcTemplate.query) <-> DB
	// schema is DTO and model is DAO
	// so model(schema) when we did nodejs & mongodb is same as DAO(DTO)
	// the model(schema) was the same where we used its api to access the db
	// jdbcTemplate and MyBatis3: write our queries on our own to directly access db but MyBatis shortens query length to jdbcTemplate
	// JPA - can use ANY DB when we change DB it will automatically change all queries to be suited for that DB
	// for jdbcTemplate and MyBatis3 since we're writing query ourselves that query is hardcoded to be for a specific DB language like mysql only
	// but for JPA we're using api (Calling method of jpa to access db) so we can just change DB freely and use the same api and it'll do the same db action
	// whereas if we change db for jdbcTemplate and MyBatis the previous query for e.g. mysql won't work for another db language.
	// api using a predefined method to get data
	
	@Autowired // we got the JdbcTemplate from elsewhere so we need to inject dependencies via autowired
	private JdbcTemplate jdbcTemplate; // later on we use MyBatis3 which is an improved version of jdbcTemplate that simplifies usage of jdbc.
	
	public List<MyDTO> list() {
		String query = "select * from mybbs"; // mybbs is the table name from the database 'mydb' (put in spring.datasource.url in application.properties)
		List<MyDTO> list = jdbcTemplate.query(
				query, new BeanPropertyRowMapper<>(MyDTO.class)
				);
		return list;
	}
}
