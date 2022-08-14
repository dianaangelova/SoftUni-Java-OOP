package football.entities.player;

import static football.common.ExceptionMessages.*;

public abstract class BasePlayer implements Player {
    private String name;
    private String nationality;
    private double kg;
    private int strength;

    public BasePlayer(String name, String nationality, int strength) {
        setName(name);
        setNationality(nationality);
        setStrength(strength);
        setKg(0);
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(String.format(PLAYER_NAME_NULL_OR_EMPTY));
        }
        this.name = name;
    }

    private void setNationality(String nationality) {
        if (nationality == null || nationality.trim().isEmpty()) {
            throw new NullPointerException(String.format(PLAYER_NATIONALITY_NULL_OR_EMPTY));
        }
        this.nationality = nationality;
    }

    protected void setStrength(int strength) {
        if (strength <= 0) {
            throw new IllegalArgumentException(String.format(PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO));
        }
        this.strength = strength;
    }

    protected void setKg(double kg) {
        this.kg = kg;
    }

    @Override
    public void stimulation() {

    }

    @Override
    public double getKg() {
        return kg;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStrength() {
        return strength;
    }
}
