package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.game.details.YourCampDetailPage;
import pl.kargolek.util.WebDriverUtil;

@Slf4j
public class GameActions extends WebActions {

    public GameActions(InitPages initPages) {
        super(initPages);
    }

    public void openGameAndClickConnectWallet() {
        WebDriverUtil.switchToNextTab(initPages.driver(), "data", 5000);
        log.info("Click connect wallet button");
        initPages.getGameMainPage()
                .open()
                .getSideNavigationPage()
                .clickConnectWalletButton()
                .clickWalletButton();
    }

    public void openGameLogExperienceInfo() throws InterruptedException {
        tabSwitchToGame();
        var experiencePanelPage = initPages.getGameMainPage()
                .open()
                .getExperiencePanelPage();

        Thread.sleep(3000);

        var wood = experiencePanelPage.getWoodValue();
        var stone = experiencePanelPage.getStoneValue();
        var gold = experiencePanelPage.getGoldValue();
        var gem = experiencePanelPage.getGemValue();
        var experience = experiencePanelPage.getExperienceValue();

        log.info("Wood:{}, Stone:{}, Gold:{} Gem:{} Experience:{}", wood, stone, gold, gem, experience);
    }

    public void acceptTermsAndUserLicenseAgreements() {
        tabSwitchToGame();
        initPages.getGameMainPage()
                .getAgreementsModalPage()
                .clickTermsUserLicenseCheckbox()
                .clickAgreementCheckbox()
                .clickAcceptButton();
    }

    public void enterCityAfterConnectWallet() {
        tabSwitchToGame();
        log.info("Entering city nft");
        initPages.getChooseCityModalPage()
                .clickEnterCityButton()
                .getSideNavigationPage();
    }

    public YourCampDetailPage openYourCamp() {
        tabSwitchToGame();
        log.info("Open your camp page");
        var barracksDetailsPage = initPages.getGameMainPage()
                .getSideNavigationPage()
                .clickCampButton();
        barracksDetailsPage.waitForTitleDetailViewHeader("barracks", "Barracks");
        return barracksDetailsPage;
    }
}
