package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.game.details.ItemDetailsPageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karol Kuta-Orlowicz
 */
@Slf4j
public class ItemLevelActions extends WebActions{

    private List<String> itemsLevels = new ArrayList<>();

    public ItemLevelActions(InitPages initPages) {
        super(initPages);
    }

    public void getItemLevel(ItemDetailsPageable itemDetailsPageable) {
        tabSwitchToGame();
        itemDetailsPageable.open();
        itemsLevels.add(itemDetailsPageable.getHeaderText().replace(" ", "")
                .replace("lvl", ""));
    }

    public void logItemsLevelsList(){
        log.info("Items levels:{}", itemsLevels);
    }

    public List<ItemDetailsPageable> getItemsPages() {
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
