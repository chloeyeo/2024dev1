package com.study.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.spring.dao.ITransaction1Dao;
import com.study.spring.dao.ITransaction2Dao;

@Service
public class BuyTicketService implements IBuyTicketService {
	@Autowired
	ITransaction1Dao transaction1;
	@Autowired
	ITransaction2Dao transaction2;

	@Override
	public int buy(String consumerId, int money, String error) {
		try {
			transaction1.pay(consumerId, money); // this goes to values (#{param1}, #{param2}) in Transaction1Dao.xml in mybatis.mapper folder
			transaction2.pay(consumerId, money); // same to Transaction2Dao.xml (the @Mapper in Transaction2Dao connects to this xml)
			return 1; // 1 = no error
		} catch () {
			return 0; // 0 = error
		}
	}
	
}
