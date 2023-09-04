package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.map.MapData;
import pl.kargolek.process.webactions.GameActions;
import pl.kargolek.process.webactions.OpponentsCampActions;
import pl.kargolek.process.webactions.YourCampActions;

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
        var diff = yourCampSlots.getMaxSlots() - yourCampSlots.getOccupiedSlots();
        log.info("Max and available slots diff = {} barracks slots: {}", diff, yourCampSlots);

        if (diff > 2) {
            log.info("Need to recruit soldiers");
            yourCampActions.recruitSoldiers();
        } else {
            var currentAvailableSoldiers = yourCampActions.sumUnits();
            var diffMaxSlotsCurrentAvailableSoldiers = yourCampSlots.getMaxSlots() - currentAvailableSoldiers;

            log.info("Checking possibility to attack opponents. " +
                    "Diff max slots and current available soldiers: {}," +
                            " currentAvailableSlots: {}",
                    diffMaxSlotsCurrentAvailableSoldiers, currentAvailableSoldiers);

            if (diffMaxSlotsCurrentAvailableSoldiers < 4){
                log.info("We can attack opponents");
                opponentsCampActions.attackOpponentsCamp();
            }
            yourCampActions.recruitSoldiers();
        }
        gameActions.openGameLogExperienceInfo();
    }
}
