package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.game.details.BuildingDetailsPageable;
import pl.kargolek.process.data.BuildingLevel;
import pl.kargolek.process.enums.Building;
import pl.kargolek.process.map.MapData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<BuildingLevel> getBuildingsLevelsList(){
        log.info("Items levels:{}", itemsLevels);
        return itemsLevels.stream()
                .map(item -> {
                    String[] parts = item.split("-");
                    String name = parts[0];
                    int level = Integer.parseInt(parts[1]);
                    return new BuildingLevel(MapData.mapBuilding(name), level);
                }).collect(Collectors.toList());
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
