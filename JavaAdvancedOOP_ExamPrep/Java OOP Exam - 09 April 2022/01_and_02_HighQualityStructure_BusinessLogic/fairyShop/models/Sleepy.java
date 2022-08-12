package fairyShop.models;

public class Sleepy extends BaseHelper {
    public static final int INITIAL_ENERGY_UNITS = 50;
    public static final int ADDITIONAL_SLEEPY_WORKER_WORK_DROP = 5;

    public Sleepy(String name) {
        super(name, INITIAL_ENERGY_UNITS);
    }

    @Override
    public void work() {
        setEnergy(Math.max(0, getEnergy() - HELPER_WORK_DROP - ADDITIONAL_SLEEPY_WORKER_WORK_DROP));
    }
}
