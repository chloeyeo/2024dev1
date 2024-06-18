package com.study.spring.bean;

public class LgTV implements ITV{
	
	public LgTV() {
		System.out.println("Lg TV created");
	}

	@Override
	public void powerOn() {
		System.out.println("Lg TV power on");
	}

	@Override
	public void powerOff() {
		System.out.println("Lg TV power off");
	}

	@Override
	public void volumeUp() {
		System.out.println("Lg TV volume up");
	}

	@Override
	public void volumeDown() {
		System.out.println("Lg TV volume down");
	}

}
