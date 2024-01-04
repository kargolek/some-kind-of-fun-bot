package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.game.details.BuildingDetailsPageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karol Kuta-Orlowicz
 */
@Slf4j
public class BuildingLevelActions extends WebActions{

    private final List<String> itemsLevels = new ArrayList<>();

    public BuildingLevelActions(InitPages initPages) {
        super(initPages);
    }

    public void getItemLevel(BuildingDetailsPageable buildingDetailsPageable) {
        tabSwitchToGame();
        buildingDetailsPageable.open();
        itemsLevels.add(buildingDetailsPageable.getHeaderText().replace(" ", "")
                .replace("lvl", ""));
    }

    public String getBuildingsLevelsList(){
        log.info("Items levels:{}", itemsLevels);
        return String.join(", ", itemsLevels);
    }

    public List<BuildingDetailsPageable> getBuildingsPages() {
        return List.of(initPages.getGoldDetailViewPage(),
                initPages.getTreasuryDetailViewPage(),
                initPages.getAcademyDetailViewPage(),
                initPages.getTimberCampDetailViewPage(),
                initPages.getQuarryDetailViewPage(),
                initPages.getMarketDetailViewPage(),
                initPages.getTempleDetailViewPage(),
                initPages.getWallDetailViewPage(),
                initPages.getYourCampDetailPage(),
                initPages.getSenateDetailViewPage());
    }
}
