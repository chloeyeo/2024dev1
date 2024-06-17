package javaSample;

public class TVUser {
	public static void main(String[] args) {
		TV tv = new LgTV();
		tv.turnOn();
		tv.soundOn();
		tv.soundOff();
		tv.turnOff();
		SamsungTV tv2 = new SamsungTV();
		tv2.powerOn();
		tv2.volumeOn();
		tv2.volumeOff();
		tv2.powerOff();
	}
}
