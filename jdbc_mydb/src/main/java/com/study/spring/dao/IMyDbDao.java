package com.study.spring.dao;

import java.util.List;

import com.study.spring.dto.MyDbDto;

public interface IMyDbDao {
	public List<MyDbDto> listDao(); // making the method definitions = interface apis
	public MyDbDto viewDao(String id);
	public int writeDao(String writer, String title, String content);
	public int deleteDao(String id);
}
