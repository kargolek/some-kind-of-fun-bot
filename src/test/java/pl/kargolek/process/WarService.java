package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.data.CampSlots;
import pl.kargolek.process.map.MapData;
import pl.kargolek.process.webactions.OpponentsCampActions;
import pl.kargolek.process.webactions.YourCampActions;
import pl.kargolek.process.webactions.GameActions;

@Slf4j
@AllArgsConstructor
public class WarService {
    private final GameActions gameActions;
    private final YourCampActions yourCampActions;
    private final OpponentsCampActions opponentsCampActions;

    public void prepareUnitsAttack() throws InterruptedException {
        yourCampActions.claimSoldiers();
        var yourCampDetailPage = gameActions.openYourCamp();
        var yourCampSlots = MapData.mapCampSlots(yourCampDetailPage.getSlotsOccupiedText());
        var diff = yourCampSlots.getMaxSlots() - yourCampSlots.getAvailableSlots();
        log.info("Max and available slots diff = {} barracks slots: {}", diff, yourCampSlots);
        if (diff > 0) {
            log.info("Starting recruiting soldiers");
            yourCampActions.recruitSoldiers();
        } else {
            log.info("Verifying attack possibility");
            verifyUnitProcess(yourCampSlots);
        }

        gameActions.openGameLogExperienceInfo();
    }

    private void verifyUnitProcess(CampSlots campSlots) {
        for (int i = 0; i <= 1; i++) {
            var isMaxUnit = yourCampActions.sumUnits().equals(campSlots.getMaxSlots());
            if (isMaxUnit) {
                opponentsCampActions.attackOpponentsCamp();
                yourCampActions.recruitSoldiers();
                break;
            } else {
                if (i == 1)
                    yourCampActions.recruitSoldiers();
            }
        }
    }
}
