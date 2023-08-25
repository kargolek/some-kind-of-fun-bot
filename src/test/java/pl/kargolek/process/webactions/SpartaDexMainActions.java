package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.spartadex.details.BarracksDetailPage;
import pl.kargolek.util.WebDriverUtil;

@Slf4j
public class SpartaDexMainActions extends WebActions {

    public SpartaDexMainActions(InitPages initPages) {
        super(initPages);
    }

    public void openSpartaDexAndClickConnectMetamask() {
        WebDriverUtil.switchToNextTab(initPages.driver(), "data", 5000);
        log.info("Click connect wallet button");
        initPages.getSpartaDexMainPage()
                .open()
                .getSideNavigationPage()
                .clickConnectWalletButton()
                .clickMetamaskButton();
    }

    public void enterPolisAfterConnectMetamask() {
        tabSwitchToSpartaDex();
        log.info("Entering polis nft");
        initPages.getChooseCityModalPage()
                .clickEnterButton()
                .getSideNavigationPage();
    }

    public BarracksDetailPage openBarracks() {
        tabSwitchToSpartaDex();
        log.info("Open barracks page");
        var barracksDetailsPage = initPages.getSpartaDexMainPage()
                .getSideNavigationPage()
                .clickBarracksButton();
        barracksDetailsPage.waitForTitleDetailViewHeader("barracks", "Barracks");
        return barracksDetailsPage;
    }
}
