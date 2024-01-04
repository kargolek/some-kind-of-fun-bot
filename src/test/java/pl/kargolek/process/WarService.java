package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.data.BattleOutcome;
import pl.kargolek.process.data.CampResources;
import pl.kargolek.process.data.CampSlots;
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

    public boolean canWeAttackOpponents() throws InterruptedException {
        yourCampActions.claimSoldiers();
        var yourCampSlots = getCampSlots();
        var isOccupiedSlotsReach = isOccupiedSlotsReachMinValue(yourCampSlots);
        var isAvailableSlotsReach = isAvailableSlotsReachMinValue(yourCampSlots);
        if (!isOccupiedSlotsReach) {
            return false;
        } else {
            return isAvailableSlotsReach;
        }
    }

    public void recruitSoldiers(boolean canWeAttackOpponents) throws InterruptedException {
        log.info("Can we attack our opponents: {}", canWeAttackOpponents);
        if (!canWeAttackOpponents) {
            log.info("We are recruiting our soldiers");
            yourCampActions.recruitSoldiers();
        }
    }

    public BattleOutcome attackOpponentsCampAndRecruit(boolean canWeAttackOpponents, boolean exceedMinResourceReq)
            throws InterruptedException {

        BattleOutcome battleOutcome = new BattleOutcome(false,
                new CampResources(0, 0, 0, 0, 0), 0);

        if (canWeAttackOpponents && exceedMinResourceReq) {
            var beforeCampResources = gameActions.openGameLogExperienceInfo();
            var beforeAvailableUnits = yourCampActions.sumUnits();

            log.info("We are attacking opponents camp and recruiting our soldiers");
            opponentsCampActions.attackOpponentsCamp();

            var afterCampResources = gameActions.openGameLogExperienceInfo();
            var afterAvailableUnits = yourCampActions.sumUnits();

            var resourcesResults = this.logResourcesAfterWar(beforeCampResources, afterCampResources);
            var unitsResults = this.logUnitsAfterWar(beforeAvailableUnits, afterAvailableUnits);
            battleOutcome = new BattleOutcome(true, resourcesResults, unitsResults);

            yourCampActions.recruitSoldiers();

        } else {
            log.info("Attack and recruit process has been skipped.");
        }

        return battleOutcome;
    }

    private boolean isOccupiedSlotsReachMinValue(CampSlots campSlots) {
        var diff = campSlots.getMaxSlots() - campSlots.getOccupiedSlots();
        var isExceedMinimalValue = diff <= 2;
        log.info("Slots occupied: {}, Slots max: {}, Exceed minimal req: {}",
                campSlots.getOccupiedSlots(),
                campSlots.getMaxSlots(),
                isExceedMinimalValue);
        return isExceedMinimalValue;
    }

    private boolean isAvailableSlotsReachMinValue(CampSlots campSlots) throws InterruptedException {
        var currentAvailableSoldiers = yourCampActions.sumUnits();
        var diffMaxSlotsCurrentAvailableSoldiers = campSlots.getMaxSlots() - currentAvailableSoldiers;
        var isExceedMinimalValue = diffMaxSlotsCurrentAvailableSoldiers < 4;
        log.info("Current available: {}, Slots max: {}, Exceed minimal req: {}",
                currentAvailableSoldiers,
                campSlots.getMaxSlots(),
                isExceedMinimalValue);
        return isExceedMinimalValue;
    }

    private CampSlots getCampSlots() {
        var yourCampDetailPage = gameActions.openYourCamp();
        return MapData.mapCampSlots(yourCampDetailPage.getSlotsOccupiedText());
    }

    private CampResources logResourcesAfterWar(CampResources beforeCampResource, CampResources afterCampResource) {
        var wood = afterCampResource.wood() - beforeCampResource.wood();
        var stone = afterCampResource.stone() - beforeCampResource.stone();
        var gold = afterCampResource.gold() - beforeCampResource.gold();
        var gem = afterCampResource.gem() - beforeCampResource.gem();
        var experience = afterCampResource.experience() - beforeCampResource.experience();

        log.info("Battle outcome resources: Wood:{}, Stone:{}, Gold:{}, Gem:{}, Experience:{}",
                wood,
                stone,
                gold,
                gem,
                experience);
        return new CampResources(wood, stone, gold, gem, experience);
    }

    private Integer logUnitsAfterWar(Integer beforeUnits, Integer afterUnits) {
        var units = afterUnits - beforeUnits;
        log.info("Battle outcome unitsLost after war: {}", units);
        return units;
    }

}
