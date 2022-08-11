package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GangNeighbourhood implements Neighbourhood {


    @Override
    public void action(Player tommyVercetty, Collection<Player> civilPlayers) {
        Repository<Gun> tommyGunRepository = tommyVercetty.getGunRepository();
        Deque<Gun> tommyGuns = new ArrayDeque<>(tommyGunRepository.getGuns());
        Deque<Player> players = new ArrayDeque<>(civilPlayers);

        Player player = players.poll();
        Gun gun = tommyGuns.poll();

        while (player != null && gun != null) {

            while (player.isAlive() && gun.canFire()) {
                int point = gun.fire();
                player.takeLifePoints(point);
            }

            if (gun.canFire()) {
                player = players.poll();
            } else {
                gun = tommyGuns.poll();
            }
        }

        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                Repository<Gun> civilRepository = civilPlayer.getGunRepository();
                Deque<Gun> civilPlayerGuns = new ArrayDeque<>(civilRepository.getGuns());

                Gun civilPlayerGun = civilPlayerGuns.poll();

                while (civilPlayerGun != null) {
                    while (civilPlayerGun.canFire() && tommyVercetty.isAlive()) {
                        int points = civilPlayerGun.fire();
                        tommyVercetty.takeLifePoints(points);
                    }

                    if (!tommyVercetty.isAlive()) {
                        return;
                    }
                    civilPlayerGuns.poll();
                }
            }

        }

    }
}
