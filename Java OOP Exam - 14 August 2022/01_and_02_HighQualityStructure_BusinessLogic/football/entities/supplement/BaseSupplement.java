package football.entities.supplement;

public abstract class BaseSupplement implements Supplement {
    private int energy;
    private double price;

    public BaseSupplement(int energy, double price) {
        setEnergy(energy);
        setPrice(price);
    }

    private void setEnergy(int energy) {
        this.energy = energy;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
