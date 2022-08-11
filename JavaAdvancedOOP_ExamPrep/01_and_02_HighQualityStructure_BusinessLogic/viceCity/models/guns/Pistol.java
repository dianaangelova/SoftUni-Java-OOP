package viceCity.models.guns;

public class Pistol extends BaseGun {
    public static final int PISTOL_BULLETS_PER_BARREL = 10;
    public static final int PISTOL_TOTAL_BULLETS = 100;
    public static final int PISTOL_BULLETS_AT_ONE_SHOT = 1;



    public Pistol(String name) {
        super(name, PISTOL_BULLETS_PER_BARREL, PISTOL_TOTAL_BULLETS);
    }

    public int fire() {
        if (getBulletsPerBarrel()==0 && getTotalBullets()>0){
            reload();
        }

        if (getBulletsPerBarrel()>0){
            setBulletsPerBarrel(getBulletsPerBarrel()-PISTOL_BULLETS_AT_ONE_SHOT);
        }

        return PISTOL_BULLETS_AT_ONE_SHOT;
    }

    private void reload() {
        setTotalBullets(getTotalBullets()-getBulletsPerBarrel());
        setBulletsPerBarrel(getBulletsPerBarrel());
    }
}
