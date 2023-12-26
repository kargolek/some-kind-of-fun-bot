package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.game.details.ItemDetailsPageable;

import java.util.List;

/**
 * @author Karol Kuta-Orlowicz
 */
@Slf4j
public class ItemLevelActions extends WebActions{

    public ItemLevelActions(InitPages initPages) {
        super(initPages);
    }

    public void logItemLevel(ItemDetailsPageable itemDetailsPageable) {
        tabSwitchToGame();
        itemDetailsPageable.open();
        log.info("Building:{}", itemDetailsPageable.getHeaderText());
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
