package fairyShop.models;

import java.util.*;
import java.util.stream.Collectors;

public class ShopImpl implements Shop {

    @Override
    public void craft(Present present, Helper helper) {
        Collection<Instrument> instruments = helper.getInstruments();

        List<Instrument> instrumentNotBrokenList = instruments.stream().filter(i -> i.isBroken() == false).collect(Collectors.toList());

        int instrumentCount = instrumentNotBrokenList.size();

        if (!instrumentNotBrokenList.isEmpty()) {
            while (!present.isDone() && helper.canWork()) {
                for (Instrument i : instrumentNotBrokenList) {
                    while (!i.isBroken()) {
                        helper.work();
                        i.use();
                        present.getCrafted();
                        if (present.isDone()) {
                            return;
                        }
                        if (!helper.canWork()) {
                            return;
                        }
                    }
                    instrumentCount--;
                    if (instrumentCount == 0) {
                        return;
                    }
                }
            }
        }
    }
}
