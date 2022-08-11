package viceCity.models.guns;

import static viceCity.common.ExceptionMessages.*;

public class BaseGun implements Gun {
    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;
    private boolean canFire;

    public BaseGun(String name, int bulletsPerBarrel, int totalBullets) {
        setName(name);
        setBulletsPerBarrel(bulletsPerBarrel);
        setTotalBullets(totalBullets);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(String.format(NAME_NULL));
        }
        this.name = name;
    }

    protected void setBulletsPerBarrel(int bulletsPerBarrel) {
        if (bulletsPerBarrel < 0) {
            throw new IllegalArgumentException(String.format(BULLETS_LESS_THAN_ZERO));
        }
        this.bulletsPerBarrel = bulletsPerBarrel;
    }

    protected void setTotalBullets(int totalBullets) {
        if (totalBullets < 0) {
            throw new IllegalArgumentException(String.format(TOTAL_BULLETS_LESS_THAN_ZERO));
        }
        this.totalBullets = totalBullets;
    }

    private void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return bulletsPerBarrel;
    }

    @Override
    public boolean canFire() {
        return totalBullets + bulletsPerBarrel > 0;
    }

    @Override
    public int getTotalBullets() {
        return totalBullets;
    }

    @Override
    public int fire() {
        return 0;
    }
}
