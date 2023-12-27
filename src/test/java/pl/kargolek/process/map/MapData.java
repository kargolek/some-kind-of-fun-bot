package pl.kargolek.process.map;

import pl.kargolek.process.data.CampSlots;

import java.util.Arrays;

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
        return new CampSlots(availableSlots, maxSlots);
    }

}
