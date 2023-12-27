package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public boolean canWeAttackOpponents() {
        yourCampActions.claimSoldiers();
        var yourCampSlots = getCampSlots();
        var isOccupiedSlotsReach = isOccupiedSlotsReachMinValue(yourCampSlots);
        var isAvailableSlotsReach = isAvailableSlotsReachMinValue(yourCampSlots);
        if (!isOccupiedSlotsReach){
            return false;
        } else {
            return isAvailableSlotsReach;
        }
    }

    public void recruitSoldiers(boolean canWeAttackOpponents) throws InterruptedException {
        log.info("Can we attack our opponents: {}", canWeAttackOpponents);
        if (!canWeAttackOpponents){
            log.info("We are recruiting our soldiers");
            yourCampActions.recruitSoldiers();
        }
    }

    public void attackOpponentsCampAndRecruit(boolean canWeAttackOpponents, boolean exceedMinResourceReq) throws InterruptedException {
        if (canWeAttackOpponents && exceedMinResourceReq){
            log.info("We are attacking opponents camp and recruiting our soldiers");
            opponentsCampActions.attackOpponentsCamp();
            yourCampActions.recruitSoldiers();
        }
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

    private boolean isAvailableSlotsReachMinValue(CampSlots campSlots){
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
}
