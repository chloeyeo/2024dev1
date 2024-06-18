package com.study.spring.bean;

public class TVUser {

	public static void main(String[] args) {
//		ITV tv = new LgTV();
//		tv.powerOn();
//		tv.powerOff();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv = new SamsungTV();
//		tv.powerOn();
//		tv.powerOff();
//		tv.volumeUp();
//		tv.volumeDown();
		BeanContainer container = new BeanContainer();
		ITV tv = (ITV) container.getBean("samsung");
		tv.powerOn();
		tv.powerOff();
		tv.volumeUp();
		tv.volumeDown();
	}

}
