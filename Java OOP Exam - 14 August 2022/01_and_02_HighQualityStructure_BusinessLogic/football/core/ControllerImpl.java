package football.core;


import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.*;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplement;
    private Collection<Field> fields;

    public ControllerImpl() {
        supplement = new SupplementRepositoryImpl();
        fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field;
        if (fieldType.equals("ArtificialTurf")) {
            field = new ArtificialTurf(fieldName);
        } else if (fieldType.equals("NaturalGrass")) {
            field = new NaturalGrass(fieldName);
        } else throw new NullPointerException(String.format(INVALID_FIELD_TYPE));

        fields.add(field);

        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplementItem;
        if (type.equals("Powdered")) {
            supplementItem = new Powdered();
        } else if (type.equals("Liquid")) {
            supplementItem = new Liquid();
        } else throw new IllegalArgumentException(String.format(INVALID_SUPPLEMENT_TYPE));

        supplement.add(supplementItem);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement supplementItem = supplement.findByType(supplementType);

        if (supplementItem == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }
        Field field = fields.stream().filter(f -> fieldName.equals(fieldName)).findFirst().orElse(null);

        field.addSupplement(supplementItem);
        supplement.remove(supplementItem);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player;

        if (playerType.equals("Men")) {
            player = new Men(playerName, nationality, strength);
        } else if (playerType.equals("Women")) {
            player = new Women(playerName, nationality, strength);
        } else throw new IllegalArgumentException(String.format(INVALID_PLAYER_TYPE));

        Field field = fields.stream().filter(f -> fieldName.equals(fieldName)).findFirst().orElse(null);
        String fieldType = field.getClass().getSimpleName();

        if ((playerType.equals("Men") && fieldType.equals("NaturalGrass"))
                || (playerType.equals("Women") && fieldType.equals("ArtificialTurf")))
         {
            field.addPlayer(player);
            return String.format("Successfully added %s to %s.", playerType, fieldName);
        } else {
            return String.format("The pavement of the terrain is not suitable.");
        }
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = fields.stream().filter(f -> fieldName.equals(fieldName)).findFirst().orElse(null);

        field.drag();

        int numberOfPlayer = field.getPlayers().size();

        return String.format(PLAYER_DRAG, numberOfPlayer);
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fields.stream().filter(f -> fieldName.equals(fieldName)).findFirst().orElse(null);
        int sumStrength = field.getPlayers().stream().mapToInt(Player::getStrength).sum();
        return String.format(STRENGTH_FIELD, fieldName, sumStrength);

    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Field f : fields) {
            sb.append(f.getInfo());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
