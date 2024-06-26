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
	
	// delete and write -> only process the query and redirect to list page
	// when we need a VIEW, we need a DTO

	@Override
	public int writeDao(String writer, String title, String content) {
		String query = "insert into mybbs (writer,title,content)" // if string is too long break string and press enter then reconnect string
				+ " values (?,?,?)"; // then writer,title,content will be inserted into each '?' in ?,?,?
		return jdbcTemplate.update(query,writer,title,content); // the writer,title,content here will also be inserted to ?,?,?
	}

	@Override
	public int deleteDao(String id) {
		String query = "delete from mybbs where id=?"; // if we put '?", the int id from Integer.parseInt(id) will get inserted into '?'
		// if there are many to insert, we can use ?,?,? etc then params will be inserted by order
		return jdbcTemplate.update(query, Integer.parseInt(id)); // delete is also an 'update'. second argument is saying we want return value to be an 'int'.
		// here 'Integer' is a wrapper class. We are converting String id to int then returning it.
	}

}
