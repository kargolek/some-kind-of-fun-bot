package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.data.BarracksSlots;
import pl.kargolek.process.map.MapData;
import pl.kargolek.process.webactions.BarbariansCampActions;
import pl.kargolek.process.webactions.BarracksActions;
import pl.kargolek.process.webactions.SpartaDexMainActions;

@Slf4j
@AllArgsConstructor
public class WarService {
    private final SpartaDexMainActions spartaDexMainActions;
    private final BarracksActions barracksActions;
    private final BarbariansCampActions barbariansCampActions;

    public void prepareUnitsAttack() {
        var barracksDetailPage = spartaDexMainActions.openBarracks();
        var barracksSlots = MapData.mapBarracksSlots(barracksDetailPage.getSlotsOccupiedText());
        var diff = barracksSlots.getMaxSlots() - barracksSlots.getAvailableSlots();
        log.info("Max and available slots diff = {} barracks slots: {}", diff, barracksSlots);
        if (diff > 0) {
            log.info("Starting recruiting soldiers");
            barracksActions.recruitSoldiers();
        } else {
            log.info("Verifying attack possibility");
            verifyUnitProcess(barracksSlots);
        }
    }

    private void verifyUnitProcess(BarracksSlots barracksSlots) {
        for (int i = 0; i <= 1; i++) {
            var isMaxUnit = barracksActions.sumUnits().equals(barracksSlots.getMaxSlots());
            if (isMaxUnit) {
                barbariansCampActions.attackBarbarianCamp();
                barracksActions.recruitSoldiers();
                break;
            } else {
                if (i == 0)
                    barracksActions.claimSoldiers();
                if (i == 1)
                    barracksActions.recruitSoldiers();
            }
        }
    }
}
