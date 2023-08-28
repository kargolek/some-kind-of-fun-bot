package pl.kargolek.process.map;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.data.CampSlots;

import java.util.Arrays;

@Slf4j
public class MapData {

    private static final String slotsOccupiedWeb = "Slots occupied:";

    public static CampSlots mapCampSlots(String slotsOccupied) {
        var slots = Arrays.stream(slotsOccupied.replace(slotsOccupiedWeb, "")
                        .trim()
                        .toLowerCase()
                        .split("/"))
                .map(Integer::parseInt)
                .toList();
        var availableSlots = slots.get(0);
        var maxSlots = slots.get(1);
        log.info("Available slots: {} Max slots: {}", availableSlots, maxSlots);
        return new CampSlots(availableSlots, maxSlots);
    }

}
