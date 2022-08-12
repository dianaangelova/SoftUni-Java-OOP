package fairyShop.models;

import static fairyShop.common.ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO;

public class InstrumentImpl implements Instrument {
    public static final int INSTRUMENT_POWER_DROP = 10;

    private int power;

    public InstrumentImpl(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(String.format(INSTRUMENT_POWER_LESS_THAN_ZERO));
        }
        setPower(power);
    }

    private void setPower(int power) {
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void use() {
        power = (Math.max(0, power - INSTRUMENT_POWER_DROP));
    }

    @Override
    public boolean isBroken() {
        return power == 0;
    }
}
