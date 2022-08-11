package viceCity.core;

import java.util.*;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.GunRepository;
import viceCity.repositories.interfaces.Repository;

import java.util.Collections;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private Player tommyVercetti;
    private Map<String, Player> civilPlayers;
    private Deque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        tommyVercetti = new MainPlayer();
        civilPlayers = new LinkedHashMap<>();
        guns = new ArrayDeque<>();
        neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player player;
        player = new CivilPlayer(name);
        civilPlayers.put(name, player);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        if (type.equals("Pistol")) {
            gun = new Pistol(name);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name);
        } else {
            return String.format(GUN_TYPE_INVALID);
        }
        guns.offer(gun);
        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String playerName) {
        Gun gunToAdd = guns.poll();

        if (gunToAdd == null) {
            return String.format(GUN_QUEUE_IS_EMPTY);
        }

        if (playerName.equals("Vercetti")) {
            tommyVercetti.getGunRepository().add(gunToAdd);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gunToAdd.getName(), tommyVercetti.getName());
        }
        Player civilPlayer = civilPlayers.get(playerName);
        if (civilPlayer == null) {
            return String.format(CIVIL_PLAYER_DOES_NOT_EXIST);
        } else {
            civilPlayer.getGunRepository().add(gunToAdd);
            return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gunToAdd.getName(), civilPlayer.getName());
        }
    }

    @Override
    public String fight() {
        neighbourhood.action(tommyVercetti, civilPlayers.values());
        if (tommyVercetti.getLifePoints() == 100 &&
                civilPlayers.values().stream().anyMatch(p -> p.getLifePoints() == 50)) {
            return String.format(FIGHT_HOT_HAPPENED);
        }

        List<Player> deadPlayers = civilPlayers.values().stream().filter(p -> p.isAlive() == false).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(FIGHT_HAPPENED + System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, tommyVercetti.getLifePoints()) + System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadPlayers.size()) + System.lineSeparator());
        sb.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayers.values().size() - deadPlayers.size()));

        for (Player dead : deadPlayers) {
            civilPlayers.remove(dead.getName());
        }

        return sb.toString();
    }
}
