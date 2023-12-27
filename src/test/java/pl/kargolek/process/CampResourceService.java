package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.data.CampResources;
import pl.kargolek.process.data.TreasuryMaxResource;
import pl.kargolek.process.webactions.GameActions;

/**
 * @author Karol Kuta-Orlowicz
 */
@Slf4j
@AllArgsConstructor
public class CampResourceService {

    private GameActions gameActions;

    public boolean isExceedMinRequirements(double minResourceRatio) throws InterruptedException {
        var campResources = gameActions.openGameLogExperienceInfo();
        var treasuryMaxResources = gameActions.openTreasuryAndGetMaxResourcesValue();
        return isRationExceedRequirementsValue(campResources, treasuryMaxResources, minResourceRatio);
    }

    private boolean isRationExceedRequirementsValue(CampResources campResources,
                                                    TreasuryMaxResource treasuryMaxResource,
                                                    double minResourceRatio) {

        var woodResult = campResources.wood() / treasuryMaxResource.wood();
        var stoneResult = campResources.stone() / treasuryMaxResource.stone();
        var goldResult = campResources.gold() / treasuryMaxResource.gold();

        log.info("CampResources: Wood:{}, Stone:{}, Gold:{} Gem:{} Experience:{}",
                campResources.wood(),
                campResources.stone(),
                campResources.gold(),
                campResources.gem(),
                campResources.experience());
        log.info("TreasuryMaxResource: Wood:{}, Stone:{}, Gold:{}",
                treasuryMaxResource.wood(),
                treasuryMaxResource.stone(),
                treasuryMaxResource.gold());
        log.info("Camp Treasury Resource Ratio: Wood:{}, Stone:{}, Gold:{}, Ratio:{}",
                woodResult,
                stoneResult,
                goldResult,
                minResourceRatio);

        var isExceedMinReq = woodResult > minResourceRatio &&
                stoneResult > minResourceRatio &&
                goldResult > minResourceRatio;

        log.info("Min resource requirements exceed:{}", isExceedMinReq);
        return isExceedMinReq;
    }
}
