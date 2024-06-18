package com.study.spring.bean;

public class SamsungTV implements ITV {
	
	public SamsungTV() {
		System.out.println("Samsung TV created");
	}

	@Override
	public void powerOn() {
		System.out.println("Samsung TV power on");
	}

	@Override
	public void powerOff() {
		System.out.println("Samsung TV power off");
	}

	@Override
	public void volumeUp() {
		System.out.println("Samsung TV volume up");
	}

	@Override
	public void volumeDown() {
		System.out.println("Samsung TV volume down");
	}

}
