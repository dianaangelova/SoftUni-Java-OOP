package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;
import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {


    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Collection<String> exhibits = state.getExhibits();
        Suitcase suitcase = new Carton();

        for (Explorer explorer:explorers){
            while(exhibits.iterator().hasNext() && explorer.canSearch()){
                explorer.search();
                String currentExhibit = exhibits.iterator().next();
                explorer.getSuitcase().getExhibits().add(currentExhibit);
                state.getExhibits().remove(currentExhibit);
            }
        }

//        for (Explorer exp : explorersWithEnergy) {
//            for (String exhibit : exhibits) {
//                if (exhibit != null && exp.getEnergy() > 0) {
//                    exp.search(); // decreases energy
//                    exp.getSuitcase().getExhibits().add(exhibit);
//                    state.getExhibits().remove(exhibit);
//                }
//                if (exhibits.size() == 0) {
//                    return;
//                }
//            }
//        }
    }
}

