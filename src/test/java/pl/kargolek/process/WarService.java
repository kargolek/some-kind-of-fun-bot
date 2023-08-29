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

        if (diff > 2) {
            log.info("Need to recruit soldiers");
            yourCampActions.recruitSoldiers();
        } else {
            log.info("We can attack opponents");
            opponentsCampActions.attackOpponentsCamp();
            yourCampActions.recruitSoldiers();
        }

        gameActions.openGameLogExperienceInfo();
    }
}
