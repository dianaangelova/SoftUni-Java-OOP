package viceCity.models.guns;

public class Rifle extends BaseGun {
    public static final int RIFLE_BULLETS_PER_BARREL = 50;
    public static final int RIFLE_TOTAL_BULLETS = 500;
    public static final int RIFLE_BULLETS_AT_ONE_SHOT = 5;

    public Rifle(String name) {
        super(name, RIFLE_BULLETS_PER_BARREL, RIFLE_TOTAL_BULLETS);
    }

    public int fire() {

        if (getBulletsPerBarrel() < RIFLE_BULLETS_AT_ONE_SHOT) {
            setTotalBullets(getTotalBullets() - RIFLE_BULLETS_PER_BARREL);
            setBulletsPerBarrel(RIFLE_BULLETS_PER_BARREL);
            if (getTotalBullets() < RIFLE_BULLETS_PER_BARREL) {
                canFire();
            }
        }

        if (getBulletsPerBarrel() > RIFLE_BULLETS_AT_ONE_SHOT) {
            setBulletsPerBarrel(getBulletsPerBarrel() - RIFLE_BULLETS_AT_ONE_SHOT);
        }

        return RIFLE_BULLETS_AT_ONE_SHOT;
    }
}
