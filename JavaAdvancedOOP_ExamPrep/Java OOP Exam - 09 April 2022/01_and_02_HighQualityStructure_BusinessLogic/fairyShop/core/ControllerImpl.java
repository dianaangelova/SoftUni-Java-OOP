package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.*;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private HelperRepository helperRepository;
    private PresentRepository presentRepository;

    public ControllerImpl() {
        helperRepository = new HelperRepository();
        presentRepository = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;

        if (type.equals("Happy")) {
            helper = new Happy(helperName);
        } else if (type.equals("Sleepy")) {
            helper = new Sleepy(helperName);
        } else throw new IllegalArgumentException(String.format(HELPER_TYPE_DOESNT_EXIST));

        helperRepository.add(helper);

        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(String.format(HELPER_DOESNT_EXIST));
        }

        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> helpersReadyToWork = helperRepository.getModels().stream().filter(h -> h.getEnergy() > 50).collect(Collectors.toList());

        if (helpersReadyToWork.size()==0) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        Present present = presentRepository.findByName(presentName);

        Shop shop = new ShopImpl();

        for (Helper h : helpersReadyToWork) {
            shop.craft(present, h);
        }

        boolean isDone = false;
        String text = "not done";

        if (present.isDone()) {
            isDone = true;
            text = "done";
        }

        List<Helper> helpersList = helperRepository.getModels().stream().collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PRESENT_DONE, presentName, text));
        sb.append(String.format(COUNT_BROKEN_INSTRUMENTS, getBrokenInstrumentsCounter(helpersList)));

        return sb.toString();
    }

    private int getBrokenInstrumentsCounter(List<Helper> helpersList) {
        int brokenCounter = 0;
        for (Helper h : helpersList) {
            Collection<Instrument> instrumentCollection = h.getInstruments();
            for (Instrument i : instrumentCollection) {
                if (i.isBroken()) {
                    brokenCounter++;
                }
            }
        }
        return brokenCounter;
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        long donePresents = presentRepository.getModels().stream().filter(p -> p.isDone()).count();
        sb.append(String.format("%d presents are done!", donePresents));
        sb.append(System.lineSeparator());
        sb.append(String.format("Helpers info:"));
        sb.append(System.lineSeparator());

        for (Helper h : helperRepository.getModels()) {
            sb.append(h.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
