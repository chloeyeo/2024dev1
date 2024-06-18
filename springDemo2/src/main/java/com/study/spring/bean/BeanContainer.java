package com.study.spring.bean;

public class BeanContainer {
	public Object getBean(String id) {
		if(id.equals("lg")) return new LgTV();
		if(id.equals("samsung")) return new SamsungTV();
		return null;
	}
}
