package glacialExpedition.core;

import java.util.*;
import java.util.stream.Collectors;

import glacialExpedition.models.explorers.*;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    public static final int ENERGY_UNITS = 50;
    private int exploreStateCount;

    ExplorerRepository explorerRepository = new ExplorerRepository();
    StateRepository stateRepository = new StateRepository();

    @Override
    public String addExplorer(String type, String explorerName) {
        BaseExplorer explorer;
        if (type.equals("AnimalExplorer")) {
            explorer = new AnimalExplorer(explorerName);
        } else if (type.equals("GlacierExplorer")) {
            explorer = new GlacierExplorer(explorerName);
        } else if (type.equals("NaturalExplorer")) {
            explorer = new NaturalExplorer(explorerName);
        } else throw new IllegalArgumentException(String.format(EXPLORER_INVALID_TYPE));

        explorerRepository.add(explorer);

        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);

        for (String s : exhibits) {
            state.getExhibits().add(s);
        }

        stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        explorerRepository.remove(explorer);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        State state = stateRepository.byName(stateName);
        List<Explorer> explorers = explorerRepository.getCollection().stream().collect(Collectors.toList());
        List<Explorer> filteredExplorers = explorers.stream().filter(e -> e.getEnergy() > ENERGY_UNITS).collect(Collectors.toList());

        if (filteredExplorers.isEmpty()) {
            throw new IllegalArgumentException(String.format(STATE_EXPLORERS_DOES_NOT_EXISTS));
        }

        Mission mission = new MissionImpl();
        mission.explore(state, filteredExplorers);
        exploreStateCount++;
        long retired = explorers.size() - explorerRepository.getCollection().stream().filter(explorer -> explorer.canSearch()).count();

        return String.format(STATE_EXPLORER, stateName, retired);
    }

    @Override
    public String finalResult() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_STATE_EXPLORED, exploreStateCount));
        sb.append(System.lineSeparator());
        sb.append(String.format(FINAL_EXPLORER_INFO));
        sb.append(System.lineSeparator());

        for (Explorer explorer : explorerRepository.getCollection()) {
            sb.append(String.format(explorer.toString()));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
