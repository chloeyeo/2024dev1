package javaSample;

// implement adapter pattern
// so that Samsung TV can adapt to 'TV' interface
public class SamsungTVAdapter implements TV {
    private SamsungTV samsungTV;

    public SamsungTVAdapter(SamsungTV samsungTV) {
        this.samsungTV = samsungTV;
    }

    @Override
    public void turnOn() {
        samsungTV.powerOn();
    }

    @Override
    public void turnOff() {
        samsungTV.powerOff();
    }

    @Override
    public void soundOn() {
        samsungTV.volumeOn();
    }

    @Override
    public void soundOff() {
        samsungTV.volumeOff();
    }
}
