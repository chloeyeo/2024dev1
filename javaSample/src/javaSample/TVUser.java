package javaSample;

public class TVUser {
	public static void main(String[] args) {
		TV tv = new LgTV();
		tv.turnOn();
		tv.soundOn();
		tv.soundOff();
		tv.turnOff();
		// Using the adapter for SamsungTV
        SamsungTV samsungTv = new SamsungTV();
        TV tv2 = new SamsungTVAdapter(samsungTv);
		tv2.turnOn();
		tv2.soundOn();
		tv2.soundOff();
		tv2.turnOff();
	}
}
